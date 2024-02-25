package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Entity.Role;
import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Exception.User.InternalException;
import com.Springboot.aha.Exception.User.UsernameIsNotUniqueException;
import com.Springboot.aha.Repository.IRoleRepository;
import com.Springboot.aha.Repository.IUserRepository;
import com.Springboot.aha.Service.IUserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public User save(User user) {

        if (usernameIsExisted(user.getUsername())) {
            throw new UsernameIsNotUniqueException("Username has been occupied, please chose another :))");
        }
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User update(User newAccount)  {

        if (userRepository.existsById(newAccount.getUser_id())) {
            User old = userRepository.findById(newAccount.getUser_id()).get();
            if (newAccount.getUsername() != null) {
                if (usernameIsExisted(newAccount.getUsername())) {
                    throw new UsernameIsNotUniqueException("Username has been occupied, please chose another :))");
                }
                old.setUsername(newAccount.getUsername());
            }
            if (newAccount.getPassword() != null)
                old.setPassword(encoder.encode(newAccount.getPassword()));
            return userRepository.save(old);
        } else
            throw new InternalException("An internal error has occurred, please try again");
    }

    @Override
    public User changePassword(int id, String newPassword) {
        try {
            User old = userRepository.findById(id).get();
            old.setPassword(encoder.encode(newPassword));
            return userRepository.save(old);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new InternalException("An internal error has occurred, please try again");
        }
    }

    @Override
    public int delete(int id) {
        try {
            User entity = userRepository.findById(id).get();
            userRepository.delete(entity);
            return id;
        } catch (Exception e) {
            return -1;
        }
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
    public boolean usernameIsExisted(String username) {
        if (getUserByUsername(username) != null)
            return true;
        else
            return false;
    }


    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }


}
