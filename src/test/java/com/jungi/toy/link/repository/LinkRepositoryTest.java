package com.jungi.toy.link.repository;

import com.jungi.toy.link.domain.Link;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.LongStream;

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

    @Test
    public void countLinks() {
        //Given
        String email = "test@test.com";
        long expectedCount = 30;

        LongStream.range(0, expectedCount).forEach(i -> {
            this.generateLink(email);
        });

        //When
        long count = linkRepository.countByEmailAndRemoveFlag(email, false);

        //Then
        assertThat(count).isEqualTo(expectedCount);
    }

    @Test
    public void checkBaseTimeTest() {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";
        LocalDateTime now = LocalDateTime.of(2020,3,12,0, 0, 0);

        linkRepository.save(Link.builder()
                                .url(url)
                                .content(content)
                                .build());

        //When
        List<Link> links = linkRepository.findAll();

        //Then
        Link link = links.get(0);

        assertThat(link.getCreateDate()).isAfter(now);
        assertThat(link.getModifyDate()).isAfter(now);
    }

    private Link generateLink(String email) {
        Link link = Link.builder()
                .url("https://github.com")
                .content("테스트")
                .email(email)
                .build();

        return this.linkRepository.save(link);
    }
}