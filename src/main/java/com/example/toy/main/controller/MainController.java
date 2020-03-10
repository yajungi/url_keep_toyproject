package com.example.toy.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    private static final String MAIN_VIEW = "mainview";

    @GetMapping("/")
    public String getMainpage() {
        return MAIN_VIEW;
    }
}
