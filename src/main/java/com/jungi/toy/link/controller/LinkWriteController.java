package com.jungi.toy.link.controller;

import com.jungi.toy.config.auth.common.LoginUser;
import com.jungi.toy.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinkWriteController {
    private static final String LINK_WRITE_VIEW = "LinkWriteView";

    @GetMapping("/user/links")
    public String getLinkWriteView(Model model, @LoginUser SessionUser user) {
        model.addAttribute("email", user.getEmail());
        return LINK_WRITE_VIEW;
    }
}
