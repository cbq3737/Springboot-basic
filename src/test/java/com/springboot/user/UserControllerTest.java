package com.springboot.user;

import com.springboot.user.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//HttpMessageConverters
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc; // @WebMvcTest를 사용하면 자동으로 Bean으로 등록됨

    @Test
    public void createUser_JSON() throws Exception {
        String userJson = "{\"username\": \"beomkyu\", \"password\": \"qlxm39\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")           // 요청을 만드는 단계
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)//여길 APPLICATION_XML이면 요청은 xml로
                .content(userJson))
                // 응답을 확인하는 단계
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(equalTo("beomkyu"))))//jsonPath가 xpath가되면 응답은 json으로
                .andExpect(jsonPath("$.password", is(equalTo("qlxm39"))));
    }
}