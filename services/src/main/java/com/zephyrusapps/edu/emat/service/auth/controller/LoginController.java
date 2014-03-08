package com.zephyrusapps.edu.emat.service.auth.controller;

import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller

public class LoginController {

    @ApiIgnore
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Authentication authentication, Model model) {
        if(authentication != null) {
            model.addAttribute("principal", authentication.getPrincipal());
        }
        return "user/login";
    }
}
