package com.jungi.toy.link.service;

import com.jungi.toy.link.dto.LinkRequestDto;
import com.jungi.toy.link.dto.LinkResponseDto;

public interface LinkService {
    LinkResponseDto findLinkById(int id);

    void saveLink(LinkRequestDto linkRequestDto);

    int updateLink(int id, LinkRequestDto linkRequestDto);
}
