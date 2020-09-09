package org.messenger.controllers;

import org.messenger.Service.UserService;
import org.messenger.model.Authority;
import org.messenger.model.RegistrationDTO;
import org.messenger.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@ComponentScan
public class RegistrationController {
    UserService userService;

   @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/login_page")
    public String getLogin(@ModelAttribute("registrationDTO") RegistrationDTO dataTransfer){
        return "login_page";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model ) {
        model.addAttribute("registrationDTO",new RegistrationDTO() );
        return "registration";
    }

    @PostMapping(value = "/registration")

    public String checkInfo(@Valid @ModelAttribute("registrationDTO") RegistrationDTO dataTransfer, BindingResult bindingResult )throws Exception {

        if(bindingResult.hasErrors()){
            return "registration";

        }
        if(userService.userExists(dataTransfer.getName())){
            bindingResult.addError(new FieldError("dataTransfer","name","Már létezik ez a felhasználó név! Kérlek addj más felhasználónevet!"));
            return "registration";
        }else if(!dataTransfer.getPassword().equals(dataTransfer.getPassword2())){
            bindingResult.addError(new FieldError("dataTransfer","password2","A két jelszó nemeggyezik meg!"));
            return "registration";
        }else {

            Authority userAuthority = userService.getUserAuthority();
            SecurityUser securityUser=new SecurityUser();
            securityUser.setName(dataTransfer.getName());
            securityUser.setPassword(passwordEncoder.encode(dataTransfer.getPassword()));
            securityUser.setDateOfBirth(dataTransfer.getDateOfBirth());
            securityUser.setEmail(dataTransfer.getEmail());
            securityUser.getAuthorityList().add(userAuthority);
            userAuthority.getSecurityUsers().add(securityUser);
            securityUser.setRole(userAuthority.getRoleName());
            userService.createUser(securityUser);
        }

        return "redirect:/login_page";
    }
    @GetMapping(value = "/logout")
    public String logout(){

        return "login_page";
    }


}
