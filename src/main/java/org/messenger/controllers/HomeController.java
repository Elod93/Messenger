package org.messenger.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    @RequestMapping(value="/greeting", method  = RequestMethod.GET)
    public String homePage(Model model){
        model.addAttribute("message","Menü");
        return "home";

    }


}
