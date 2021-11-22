package com.Springboot.aha.API;

import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Security.JwtUtils;
import com.Springboot.aha.Service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationAPI.class)
class AuthenticationAPIIntergrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService accountService;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    AuthenticationManager authenticationManager;

//    @Test
//    void givenUserAccount_thenSignIn() {
//
//    }

    @Test
    void givenUserAccount_thenSignUp() throws Exception {
        //given
        User user = User.builder().username("new").password("pass").build();
        //when
        when(accountService.save(user)).thenReturn(user);
        //then
        mvc.perform(post("/signup"))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}