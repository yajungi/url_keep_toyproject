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

    @Builder
    public LinkRequestDto(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public Link convertLink() {
        return Link.builder()
                .url(url)
                .content(content)
                .build();
    }
}
