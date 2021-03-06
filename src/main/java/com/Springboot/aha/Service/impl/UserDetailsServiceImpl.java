package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Entity.UserDetailsImpl;
import com.Springboot.aha.Exception.User.InvalidUsernameOrPasswordException;
import com.Springboot.aha.Repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            User user = userRepository.findUserByUsername(s);
            return UserDetailsImpl.build(user);

    }
}
