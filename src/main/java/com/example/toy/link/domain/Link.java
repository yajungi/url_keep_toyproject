package com.example.toy.link.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 500, nullable = false)
    private String url;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "boolean default false")
    private Boolean removeFlag = false;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime modifyTime;

    @Builder
    public Link(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public void removeLink() {
        this.removeFlag = true;
    }
}
