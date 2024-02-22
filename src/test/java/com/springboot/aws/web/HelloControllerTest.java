package com.springboot.aws.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 10000;

        mvc.perform(MockMvcRequestBuilders.get("/hello/dto")
                    .param("name", name).param("amount", String.valueOf(amount)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.amount", Matchers.is(amount)));
    }

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(hello));
    }
}
