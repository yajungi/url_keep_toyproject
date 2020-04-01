package com.jungi.toy.link.dto;

import com.jungi.toy.link.domain.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LinkRequestDto {
    private String url;
    private String content;
    private String email;

    @Builder
    public LinkRequestDto(String url, String content, String email) {
        this.url = url;
        this.content = content;
        this.email = email;
    }

    public Link convertLink() {
        return Link.builder()
                .url(url)
                .content(content)
                .email(email)
                .build();
    }
}
