package com.jungi.toy.link.service;

import com.jungi.toy.link.dto.LinkRequestDto;
import com.jungi.toy.link.dto.LinkResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LinkService {
    List<LinkResponseDto> findAllLinks(Pageable pageable);

    List<LinkResponseDto> findAllLinksByEmail(Pageable pageable, String email);

    LinkResponseDto findLinkById(int id);

    long countByEmail(String email);

    void saveLink(LinkRequestDto linkRequestDto);

    int updateLink(int id, LinkRequestDto linkRequestDto);

    int removeLinkById(int id);
}
