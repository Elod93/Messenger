package org.messenger.controllers;

import org.messenger.Service.MessageService;
import org.messenger.SessionBean.UserInfo;
import org.messenger.model.Message;
import org.messenger.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {

    MessageService messageService;
    UserInfo userInfo;

    @Autowired
    public MessageController(MessageService messageService , UserInfo userInfo) {
        this.messageService = messageService;
        this.userInfo=userInfo;

    }
    @RequestMapping(value = "/messages", method = {RequestMethod.GET})
    public String messages2(@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                            @RequestParam(value = "order_by", required = false, defaultValue = "name") String order_by,
                            @RequestParam(value = "dir", required = false, defaultValue = "asc") String dir,
                            @RequestParam(value = "delete", required = false,defaultValue = "") Boolean deleted,
                            @RequestParam(value = "topicId",required = false,defaultValue = "0") Integer topicId,
                            @RequestParam(value = "dateFrom", required = false) String dateFrom,
                            @RequestParam(value = "dateTo", required = false) String dateTo,
                           HttpServletRequest httpServletRequest,Model model){
        model.addAttribute("topics",messageService.allTopic());
//       if(httpServletRequest.isUserInRole("USER")) {
//           model.addAttribute("messages", messageService.messageHandlerWithJpqlUser(limit, order_by, dir,topicId,deleted));
//       }
//        if(httpServletRequest.isUserInRole("ADMIN")) {
//            model.addAttribute("messages",messageService.messageHandlerWithJpqlAdmin(limit,order_by,dir,deleted,topicId,dateFrom,dateTo));
//        }
        return "messenger";

    }
    @RequestMapping(value = "/one_message/{userId}", method = RequestMethod.GET)
    public String showUserData(
            @PathVariable("userId") Integer userId, Model model){
        model.addAttribute("one_message", messageService.oneMessage(userId));
        return "one_message";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreateMessagePage(Message message,Model model) {
       message.setName(userInfo.getName());
       model.addAttribute("topics",messageService.allTopic());
       model.addAttribute("selectedTopic",null);
        return "form";
    }

    @PostMapping(value = "/create")
    public String createMessage(@Valid @ModelAttribute("message") Message message, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "form";
        } else {
            //messageService.setMessageTopic(message);
            messageService.createMessage(message);
            userInfo.setName(message.getName());
            userInfo.setTextCounter(userInfo.getTextCounter()+1);
        }
        model.addAttribute("message", message);
        return "redirect:/messages";
    }
    @GetMapping("/user_info")
    public String checkUserInfo(Model model){
        messageService.userNameController(userInfo);
        model.addAttribute("userInfo", userInfo);
        return "user_info";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/messages/delete/{userId}")
    public String setDeleted(@PathVariable("userId") Integer userId) {
        messageService.setDeleted(userId);
        return "redirect:/messages";
    }
    @GetMapping(value = "/add_topic")
    public String getTopic(Model model, Topic topic){
     model.addAttribute("topic",topic);
       return "topic";
    }
    @PostMapping(value = "/add_topic")
    public String addTopic(Topic topic, Model model){
       messageService.addNewTopic(topic);
       model.addAttribute("topic",topic);

       return "redirect:/create";
    }
 @RequestMapping(value = "/textAutoComplete")
 @ResponseBody
 public List<String> textAutoComplete(@RequestParam(value = "term",required = false,defaultValue = "") String term ) {
        List<String> texts=new ArrayList<>();
        texts.add("Hello");
        texts.add("Elod");
        texts.add("Sziasztok");
        texts.add("Nem mukodik");
        return texts;
 }


}