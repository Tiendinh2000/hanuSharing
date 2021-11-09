package com.Springboot.aha.Repository;

import com.Springboot.aha.Entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private IUserRepository underTest;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void canFindUserByUsername() {
        //given
        String name = "testcase";
        User user = new User(1, name, "test", null, null);
        underTest.save(user);
        //when
        User expected = underTest.findUserByUsername(name);
        ///then
        assertThat(expected).isEqualTo(user);
    }

    @Test
    void findUserByUsernameIsNotExisted() {
        //given
        String name = "thisNameIsNotExist;";
        //when
        User expected = underTest.findUserByUsername(name);
        ///then
        assertThat(expected).isNull();
    }
}