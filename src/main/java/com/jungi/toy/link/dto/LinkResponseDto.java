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
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    public LinkResponseDto(Link link) {
        this.id = link.getId();
        this.url = link.getUrl();
        this.content = link.getContent();
        this.removeFlag = link.getRemoveFlag();
        this.createTime = link.getCreateTime();
        this.modifyTime = link.getModifyTime();
    }
}
