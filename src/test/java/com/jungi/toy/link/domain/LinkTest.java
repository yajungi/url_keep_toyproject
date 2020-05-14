package com.jungi.toy.link.domain;

import com.jungi.toy.common.TestDescription;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkTest {

    @Test
    @TestDescription("link update 테스트")
    public void updateLinkTest() {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";
        String email = "test@test.com";

        Link link = Link.builder()
                .url(url)
                .content(content)
                .email(email)
                .build();

        String expectedUrl = "https://www.naver.com/";
        String expectedContent = "네이버홈페이지";

        //when & then
        link.updateLink(expectedUrl, expectedContent);

        assertThat(link.getUrl()).isEqualTo(expectedUrl);
        assertThat(link.getContent()).isEqualTo(expectedContent);
    }

    @Test
    @TestDescription("link remove 테스트")
    public void removeLinkTest() {
        //Given
        String url = "https://github.com";
        String content = "깃헙홈페이지";
        String email = "test@test.com";

        Link link = Link.builder()
                .url(url)
                .content(content)
                .email(email)
                .build();

        //when & then
        link.removeLink();

        assertThat(link.getRemoveFlag()).isEqualTo(true);
    }
}
