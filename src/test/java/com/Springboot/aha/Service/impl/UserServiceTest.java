package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private IUserRepository userRepository;
    @Mock
    private PasswordEncoder encoder;

    @Autowired
    private UserService underTest;

    @BeforeEach
    void setUp() {

        underTest = UserService.builder().userRepository(userRepository).encoder(encoder).build();

        User user = User.builder().username("tien").password("tien").build();
        ///    Mockito.when(userRepository.findUserByUsername("tien")).thenReturn(user);
    }


    @Test
    void canSaveUser() {
        //given
        User user = User.builder().username("tien").password("tiendinh124").build();
        //when
        underTest.save(user);
        //then
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(argumentCaptor.capture());

        User captured = argumentCaptor.getValue();

        assertThat(captured).isEqualTo(user);

    }

//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }

    @Test
    void canFindAll() {
//when
        underTest.findAll();
        //then
        verify(userRepository).findAll();
    }

    //    @Test
//    void saveRole() {
//    }
//
//    @Test
//    void addRoletoUser() {
//    }
//

//    @Test
//    void canGetUserByUsername() {
//        String name = "tien";
//        User found = underTest.getUserByUsername(name);
//
//        assertEquals(null, found);
//    }


//
//    @Test
//    void getUserById() {
//    }
}