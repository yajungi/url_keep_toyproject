package com.example.toy.link.repository;

import com.example.toy.link.domain.Link;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkRepositoryTest {
    @Autowired
    private LinkRepository linkRepository;

    @After
    public void cleanup() {
        linkRepository.deleteAll();
    }

    @Test
    public void saveLink() {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";

        linkRepository.save(Link.builder()
                .url(url)
                .content(content)
                .build());

        //When
        List<Link> Links = linkRepository.findAll();

        //Then
        Link link = Links.get(0);

        assertThat(link.getUrl()).isEqualTo(url);
        assertThat(link.getContent()).isEqualTo(content);
        assertThat(link.getRemoveFlag()).isEqualTo(false);
    }
}