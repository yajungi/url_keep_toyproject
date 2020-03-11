package com.jungi.toy.link.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jungi.toy.link.domain.Link;
import com.jungi.toy.link.repository.LinkRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LinkControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LinkRepository linkRepository;

    @After
    public void tearDown() {
        linkRepository.deleteAll();
    }

    @Test
    public void findLink() throws Exception {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";

        Link link = linkRepository.save(Link.builder()
                                .url(url)
                                .content(content)
                                .build());

        //When & Then
        this.mockMvc.perform(get("/api/link/{id}", link.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        List<Link> links = this.linkRepository.findAll();

        assertThat(links.get(0).getUrl()).isEqualTo(url);
        assertThat(links.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void createLink() throws Exception {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";

        Link link = Link.builder()
                        .url(url)
                        .content(content)
                        .build();

        //When & Then
        this.mockMvc.perform(post("/api/links")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(link)))
                    .andExpect(status().isCreated())
                    .andDo(print());

        List<Link> links = this.linkRepository.findAll();

        assertThat(links.get(0).getUrl()).isEqualTo(url);
        assertThat(links.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void updateLink() throws Exception {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";

        Link link = this.linkRepository.save(Link.builder()
                                    .url(url)
                                    .content(content)
                                    .build());

        String expectedUrl = "https://www.naver.com/";
        String expectedContent = "네이버홈페이지";

        Link expectedLink = Link.builder()
                                .url(expectedUrl)
                                .content(expectedContent)
                                .build();

        //When & Then
        this.mockMvc.perform(put("/api/link/{id}", link.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedLink)))
                .andExpect(status().isOk())
                .andDo(print());

        List<Link> links = this.linkRepository.findAll();

        assertThat(links.get(0).getUrl()).isEqualTo(expectedUrl);
        assertThat(links.get(0).getContent()).isEqualTo(expectedContent);
    }
}