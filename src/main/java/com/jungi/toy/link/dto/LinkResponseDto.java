package com.jungi.toy.link.dto;

import com.jungi.toy.link.domain.Link;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LinkResponseDto {
    private int id;
    private String url;
    private String content;
    private boolean removeFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public LinkResponseDto(Link link) {
        this.id = link.getId();
        this.url = link.getUrl();
        this.content = link.getContent();
        this.removeFlag = link.getRemoveFlag();
        this.createDate = link.getCreateDate();
        this.modifyDate = link.getModifyDate();
    }
}
