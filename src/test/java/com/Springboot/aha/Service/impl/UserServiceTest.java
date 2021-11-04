package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Autowired
    private UserService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void save() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void canFindAll() {
    }

    @Test
    void saveRole() {
    }

    @Test
    void addRoletoUser() {
    }

    @Test
    void getUserByUsername() {
    }

    @Test
    void getUserById() {
    }
}