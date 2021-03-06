package com.jungi.toy.main.controller;

import com.jungi.toy.config.auth.common.LoginUser;
import com.jungi.toy.config.auth.dto.SessionUser;
import com.jungi.toy.link.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class MainController {
    private static final String MAIN_VIEW = "MainView";
    private static final int FIRST_PAGE = 0;
    private static final int PAGE_SIZE = 10;

    private final LinkService linkService;

    @GetMapping("/")
    public String getMainPage(Model model, @LoginUser SessionUser user) {
        if (Objects.nonNull(user)) {
            model.addAttribute("userName", user.getName());
        }

        model.addAttribute("links", linkService.findAllLinksByEmail(
                PageRequest.of(FIRST_PAGE, PAGE_SIZE, Sort.by("id").descending()),
                user.getEmail()));

        model.addAttribute("count", linkService.countByEmail(user.getEmail()));

        return MAIN_VIEW;
    }
}
