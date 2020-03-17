package com.jungi.toy.config.auth.dto;

import com.jungi.toy.user.domain.Role;
import com.jungi.toy.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OauthAttribute {
    private Map<String, Object> attribute;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OauthAttribute(Map<String, Object> attribute, String nameAttributeKey, String name, String email, String picture) {
        this.attribute = attribute;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OauthAttribute of(String registrationId, String userAttributeName, Map<String, Object> attribute) {
        return ofGoogle(userAttributeName, attribute);
    }

    private static OauthAttribute ofGoogle(String userAttributeName, Map<String, Object> attribute) {
        return OauthAttribute.builder()
                .name((String) attribute.get("name"))
                .email((String) attribute.get("email"))
                .picture((String) attribute.get("picture"))
                .attribute(attribute)
                .nameAttributeKey(userAttributeName)
                .build();
    }

    public User getConvertedUser() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
