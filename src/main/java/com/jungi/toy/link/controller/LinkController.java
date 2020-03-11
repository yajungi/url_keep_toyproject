package com.jungi.toy.link.controller;

import com.jungi.toy.link.dto.LinkRequestDto;
import com.jungi.toy.link.dto.LinkResponseDto;
import com.jungi.toy.link.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LinkController {
    private final LinkService linkService;

    @GetMapping("/api/link/{id}")
    public LinkResponseDto findLinkById(@PathVariable int id) {
        return linkService.findLinkById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/links")
    public void saveLink(@RequestBody LinkRequestDto linkRequestDto) {
        linkService.saveLink(linkRequestDto);
    }

    @PutMapping("/api/link/{id}")
    public int update(@PathVariable int id, @RequestBody LinkRequestDto linkRequestDto) {
        return linkService.updateLink(id, linkRequestDto);
    }
}