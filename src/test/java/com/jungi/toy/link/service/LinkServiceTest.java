package com.jungi.toy.link.service;

import com.jungi.toy.common.TestDescription;
import com.jungi.toy.link.domain.Link;
import com.jungi.toy.link.dto.LinkResponseDto;
import com.jungi.toy.link.repository.LinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkServiceTest {

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    LinkService linkService;

    @Before
    public void setUp() {
        this.linkRepository.deleteAll();
    }

    @Test
    @TestDescription("30개의 링크 조회를 위한 테스트")
    public void findAllLinksTest() {
        //Given
        IntStream.range(0, 30).forEach(i -> {
            this.generateLink(i + 1);
        });

        int page = 0;
        int pageSize = 30;

        int expectedPageSize = 30;

        //When & Then
        List<LinkResponseDto> linksList = linkService.findAllLinks(
                PageRequest.of(page, pageSize, Sort.by("id").descending()));

        assertThat(linksList.size()).isEqualTo(expectedPageSize);
    }

    @Test
    @TestDescription("등록된 email 유저가 지워지지 않은 링크 10개를 조회")
    public void findAllLinksByEmail() {
        String expectedEmail = "test2@test.com";

        //Given
        IntStream.range(0, 10).forEach(i -> {
            this.generateLinkByEmail(i + 1, expectedEmail);

            //default email = "test@test.com"
            this.generateLink(i + 11);
        });

        int page = 0;
        int pageSize = 10;

        int expectedPageSize = 10;

        //When & Then
        List<LinkResponseDto> linksList = linkService.findAllLinksByEmail(
                PageRequest.of(page, pageSize, Sort.by("id").descending()),
                expectedEmail);

        assertThat(linksList.size()).isEqualTo(expectedPageSize);
    }

    @Test
    @TestDescription("등록되지 않은 email 유저가 지워지지 않은 링크 10개를 조회")
    public void findAllLinksByEmptyEmail() {
        //Given
        IntStream.range(0, 30).forEach(i -> {
            //default email = "test@test.com"
            this.generateLink(i + 1);
        });

        int page = 0;
        int pageSize = 10;

        int expectedPageSize = 0;
        String expectedEmail = "test2@test.com";

        //When & Then
        List<LinkResponseDto> linksList = linkService.findAllLinksByEmail(
                PageRequest.of(page, pageSize, Sort.by("id").descending()),
                    expectedEmail);

        assertThat(linksList.size()).isEqualTo(expectedPageSize);
    }

    @Test
    @TestDescription("링크 id로 링크 조회하기")
    public void findLinkByIdTest() {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";

        //When & Then
        Link link = linkRepository.save(Link.builder()
                .url(url)
                .content(content)
                .build());

        LinkResponseDto expectedLink = linkService.findLinkById(link.getId());

        assertThat(url).isEqualTo(expectedLink.getUrl());
        assertThat(content).isEqualTo(expectedLink.getContent());
    }

    @Test
    @TestDescription("사용자가 등록한 링크를 삭제")
    public void removeLinkByIdTest() {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";
        String email = "test@test.com";

        //When & Then
        Link link = linkRepository.save(Link.builder()
                .url(url)
                .content(content)
                .email(email)
                .build());

        linkService.removeLinkById(link.getId());

        long expectedCount = 0;

        long linkCount = linkService.countByEmail(email);

        assertThat(linkCount).isEqualTo(expectedCount);
    }

    private Link generateLink(int index) {
        Link link = Link.builder()
                .url("https://github.com")
                .content("테스트 " + index)
                .email("test@test.com")
                .build();

        return this.linkRepository.save(link);
    }

    private Link generateLinkByEmail(int index, String email) {
        Link link = Link.builder()
                .url("https://github.com")
                .content("테스트 " + index)
                .email(email)
                .build();

        return this.linkRepository.save(link);
    }
}
