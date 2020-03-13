package com.jungi.toy.link.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinksWriteController {
    private static final String LINK_WRITE_VIEW = "linkwriteview";

    @GetMapping("/user/links")
    public String getLinkWriteView() {
        return LINK_WRITE_VIEW;
    }
}
