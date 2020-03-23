package com.jungi.toy.link.dto;

import com.jungi.toy.link.domain.Link;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class LinkResponseDto {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int id;
    private String url;
    private String content;
    private boolean removeFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public String getConvertedModifyDate() {
        return this.modifyDate.format(TIME_FORMATTER);
    }

    public LinkResponseDto(Link link) {
        this.id = link.getId();
        this.url = link.getUrl();
        this.content = link.getContent();
        this.removeFlag = link.getRemoveFlag();
        this.createDate = link.getCreateDate();
        this.modifyDate = link.getModifyDate();
    }
}
