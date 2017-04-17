package com.statestr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by e604845 on 4/17/2017.
 */
public class UserController extends AbstractController {
    @GetMapping("/user/login")
    public ModelAndView userLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
