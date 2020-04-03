package com.jungi.toy.link.service;

import com.jungi.toy.link.domain.Link;
import com.jungi.toy.link.dto.LinkRequestDto;
import com.jungi.toy.link.dto.LinkResponseDto;
import com.jungi.toy.link.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LinkServiceImpl implements LinkService {
    private static final boolean DEFAULT_REMOVE_FLAG = false;

    private final LinkRepository linkRepository;

    @Override
    public List<LinkResponseDto> findAllLinks(Pageable pageable) {
        return linkRepository.findByRemoveFlag(DEFAULT_REMOVE_FLAG, pageable)
                .getContent()
                .stream()
                .map(LinkResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<LinkResponseDto> findAllLinksByEmail(Pageable pageable, String email) {
        return linkRepository.findByEmailAndRemoveFlag(email, DEFAULT_REMOVE_FLAG, pageable)
                .getContent()
                .stream()
                .map(LinkResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public LinkResponseDto findLinkById(int id) {
        Link link = linkRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("해당 링크가 없습니다. id= " + id));

        return new LinkResponseDto(link);
    }

    @Override
    public long countByEmail(String email) {
        return linkRepository.countByEmailAndRemoveFlag(email, DEFAULT_REMOVE_FLAG);
    }

    @Override
    @Transactional
    public void saveLink(LinkRequestDto linkRequestDto) {
        linkRepository.save(linkRequestDto.convertLink());
    }

    @Override
    @Transactional
    public int updateLink(int id, LinkRequestDto linkRequestDto) {
        Link link = linkRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("해당 링크가 없습니다. id= " + id));

        link.updateLink(linkRequestDto.getUrl(), linkRequestDto.getContent());

        return id;
    }

    @Override
    @Transactional
    public int removeLinkById(int id) {
        Link link = linkRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("해당 링크가 없습니다. id= " + id));

        link.removeLink();

        return id;
    }
}
