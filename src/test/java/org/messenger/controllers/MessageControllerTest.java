package org.messenger.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.messenger.Service.MessageService;
import org.messenger.SessionBean.UserInfo;
import org.messenger.model.Message;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@WebMvcTest (controllers = MessageController.class)
public class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;
    @MockBean
    private UserInfo userInfo;
/*
    @Test
    @WithUserDetails("USER")
    public void testFindMessages() throws Exception {
        List<Message> msgs = new ArrayList<>();
        msgs.add(new Message("aladár", "hello",0));
        Mockito.when(messageService.messageHandler(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString())).thenReturn(msgs);
        mockMvc.perform(MockMvcRequestBuilders.get("/messages"))
                .andExpect(MockMvcResultMatchers.view().name("messenger"))
                .andExpect(MockMvcResultMatchers.model().attribute("messages", msgs));
        Mockito.verify(messageService, Mockito.times(1))
                .messageHandler(Mockito.anyInt(), Mockito.anyString(),  Mockito.anyString());
    }
*/

}