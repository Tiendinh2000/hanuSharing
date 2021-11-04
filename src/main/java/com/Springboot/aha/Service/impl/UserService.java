package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Entity.Role;
import com.Springboot.aha.Repository.IUserRepository;
import com.Springboot.aha.Repository.IRoleRepository;
import com.Springboot.aha.Service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public User save(User item) {
        item.setPassword(encoder.encode(item.getPassword()));
        return userRepository.save(item);
    }

    @Override
    public User update(User newAccount) {

        if(userRepository.existsById(newAccount.getUser_id())){
            User old = userRepository.findById(newAccount.getUser_id()).get();
            if(newAccount.getUsername()!=null)
                old.setUsername(newAccount.getUsername());
            if(newAccount.getPassword()!=null)
                old.setPassword(encoder.encode(newAccount.getPassword()));
          return  userRepository.save(old);
        }
        else
           return null;
    }

    @Override
    public int delete(int id) {
       try {
           User entity = userRepository.findById(id).get();
           userRepository.delete(entity);
           return id;
       }catch (Exception e){
       return -1;}
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoletoUser(String username, String rolename) {
        User user = userRepository.findUserByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    
}
