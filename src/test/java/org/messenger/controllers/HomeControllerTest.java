package org.messenger.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers =HomeController.class)
class HomeControllerTest {
@Autowired
    MockMvc mockMvc;
@Test
    public  void testHomePage()throws  Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/greeting"))
            .andExpect(MockMvcResultMatchers.view().name("home"));
}
}