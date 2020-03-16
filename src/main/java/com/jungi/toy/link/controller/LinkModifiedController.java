package com.jungi.toy.link.controller;

import com.jungi.toy.link.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class LinkModifiedController {
    private static final String LINK_MODIFIED_VIEW = "LinkModifiedView";

    private final LinkService linkService;

    @GetMapping("/user/links/{id}")
    public String getLinkModifiedView(Model model, @PathVariable int id) {
        model.addAttribute("link", linkService.findLinkById(id));

        return LINK_MODIFIED_VIEW;
    }
}
