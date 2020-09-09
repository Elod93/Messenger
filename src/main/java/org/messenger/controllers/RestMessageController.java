package org.messenger.controllers;

import org.messenger.Service.MessageService;
import org.messenger.model.Message;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestMessageController {
    MessageService messageService;

    public RestMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/rest/messages", method = {RequestMethod.GET})
    public List<Message> messages2() {
     return messageService.findAllMessages();
    }


    @PostMapping(value = "/rest/messages")
    public Message createMessage(@RequestBody Message message){
        return messageService.createMessageList(message);

    }

    @DeleteMapping("/messages/{id}")
    void deleteMessage(@PathVariable Long id) {
        messageService.deleteById(id);
    }

    @GetMapping("/rest/csrf")
        public CsrfToken csrf(CsrfToken token) {
            return token;
        }
    }

