package com.jungi.toy.link.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jungi.toy.common.TestDescription;
import com.jungi.toy.config.auth.dto.SessionUser;
import com.jungi.toy.link.domain.Link;
import com.jungi.toy.link.repository.LinkRepository;
import com.jungi.toy.user.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LinkControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LinkRepository linkRepository;

    private MockHttpSession session;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        User user = User.builder()
                        .name("test")
                        .email("test@test.com")
                        .picture("test")
                        .build();

        session = new MockHttpSession();
        session.setAttribute("user", new SessionUser(user));

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                    .apply(springSecurity())
                    .addFilters(new CharacterEncodingFilter("UTF-8", true))
                    .build();
    }

    @After
    public void tearDown() {
        linkRepository.deleteAll();
    }

    @Test
    @TestDescription("id를 통한 링크 1개 조회")
    @WithMockUser(roles="USER")
    public void findLink() throws Exception {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";

        Link link = linkRepository.save(Link.builder()
                                .url(url)
                                .content(content)
                                .build());

        //When & Then
        this.mockMvc.perform(get("/api/links/{id}", link.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("createDate").exists())
                .andExpect(jsonPath("modifyDate").exists());

        List<Link> links = this.linkRepository.findAll();

        assertThat(links.get(0).getUrl()).isEqualTo(url);
        assertThat(links.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @TestDescription("30개의 이벤트를 10개 씩 2번째 페이지 조회하기")
    @WithMockUser(username = "user", roles="USER")
    public void findLinksByEmail() throws Exception {
        //Given
        IntStream.range(0, 30).forEach(i -> {
            this.generateLink(i + 1);
        });

        //When & Then
        this.mockMvc.perform(get("/api/links")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("size", "10")
                        .param("sort", "id,desc")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @TestDescription("USER 권한을 가진 사용자가 링크 추가")
    @WithMockUser(roles="USER")
    public void createLink() throws Exception {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";
        String email = "test@test.com";

        Link link = Link.builder()
                        .url(url)
                        .content(content)
                        .email(email)
                        .build();

        //When & Then
        this.mockMvc.perform(post("/api/links")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(link)))
                        .andDo(print())
                        .andExpect(status().isCreated());

        List<Link> links = this.linkRepository.findAll();

        assertThat(links.get(0).getUrl()).isEqualTo(url);
        assertThat(links.get(0).getContent()).isEqualTo(content);
        assertThat(links.get(0).getEmail()).isEqualTo(email);
    }

    @Test
    @TestDescription("USER 권한을 가진 사용자가 링크 수정")
    @WithMockUser(roles="USER")
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
        this.mockMvc.perform(put("/api/links/{id}", link.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedLink)))
                .andExpect(status().isOk());

        List<Link> links = this.linkRepository.findAll();

        assertThat(links.get(0).getUrl()).isEqualTo(expectedUrl);
        assertThat(links.get(0).getContent()).isEqualTo(expectedContent);
    }

    private Link generateLink(int index) {
        Link link = Link.builder()
                        .url("https://github.com")
                        .content("테스트 " + index)
                        .email("test@test.com")
                        .build();

        return this.linkRepository.save(link);
    }
}