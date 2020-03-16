package com.jungi.toy.link.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinkWriteController {
    private static final String LINK_WRITE_VIEW = "LinkWriteView";

    @GetMapping("/user/links")
    public String getLinkWriteView() {
        return LINK_WRITE_VIEW;
    }
}
