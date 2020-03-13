package com.jungi.toy.link.service;

import com.jungi.toy.link.domain.Link;
import com.jungi.toy.link.dto.LinkRequestDto;
import com.jungi.toy.link.dto.LinkResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LinkService {
    Page<Link> findAllLinks(Pageable pageable);

    LinkResponseDto findLinkById(int id);

    void saveLink(LinkRequestDto linkRequestDto);

    int updateLink(int id, LinkRequestDto linkRequestDto);
}
