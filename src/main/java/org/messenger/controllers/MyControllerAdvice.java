package org.messenger.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class MyControllerAdvice
{
    Logger logger= LoggerFactory.getLogger(MyControllerAdvice.class);
    @ExceptionHandler(Exception.class)
    public String handleErrors(Exception ex, Model model){
        logger.error("Error",ex);
        String s = Arrays.toString(ex.getStackTrace());
        model.addAttribute("exceptionMessage", s);
        logger.debug("handlerError called");
        return "error";
    }

}
