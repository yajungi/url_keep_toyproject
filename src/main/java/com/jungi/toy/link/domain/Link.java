package com.jungi.toy.link.domain;

import com.jungi.toy.common.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class Link extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 500, nullable = false)
    private String url;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "boolean default false")
    private Boolean removeFlag = false;

    private String email;

    @Builder
    public Link(String url, String content, String email) {
        this.url = url;
        this.content = content;
        this.email = email;
    }

    public void updateLink(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public void removeLink() {
        this.removeFlag = true;
    }
}
