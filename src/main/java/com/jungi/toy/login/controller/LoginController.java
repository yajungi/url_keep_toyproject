package com.jungi.toy.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private static final String LOGIN_PAGE = "Login";

    @GetMapping("/user/login")
    public String getLoginPage() {
        return LOGIN_PAGE;
    }
}
