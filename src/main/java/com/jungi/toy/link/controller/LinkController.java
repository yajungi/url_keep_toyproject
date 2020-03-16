package com.jungi.toy.link.controller;

import com.jungi.toy.link.dto.LinkRequestDto;
import com.jungi.toy.link.dto.LinkResponseDto;
import com.jungi.toy.link.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class LinkController {
    private final LinkService linkService;

    @GetMapping("/api/links/{id}")
    public LinkResponseDto findLinkById(@PathVariable int id) {
        return linkService.findLinkById(id);
    }

    @GetMapping("/api/links")
    public Map<String, Object> queryLinks(Pageable pageable) {
        Map<String, Object> linksJsonResponse = new HashMap<>();
        linksJsonResponse.put("links", linkService.findAllLinks(pageable));

        return linksJsonResponse;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/links")
    public void saveLink(@RequestBody LinkRequestDto linkRequestDto) {
        linkService.saveLink(linkRequestDto);
    }

    @PutMapping("/api/links/{id}")
    public int updateLink(@PathVariable int id, @RequestBody LinkRequestDto linkRequestDto) {
        return linkService.updateLink(id, linkRequestDto);
    }

    @DeleteMapping("/api/links/{id}")
    public void removeLink(@PathVariable int id) {
        linkService.removeLinkById(id);
    }
}
