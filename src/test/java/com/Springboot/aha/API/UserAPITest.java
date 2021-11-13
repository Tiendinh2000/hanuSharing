package com.Springboot.aha.API;

import com.Springboot.aha.Repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


class UserAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void givenUser_thenGetUser() {
//given

//        given();
//        //then
//        mockMvc.perform(get("/api/user")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].name", is("bob")));
    }

//    @Test
//    void insert() {
//    }

//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
}