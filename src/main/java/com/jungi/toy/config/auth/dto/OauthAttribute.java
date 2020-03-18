package com.jungi.toy.config.auth.dto;

import com.jungi.toy.user.domain.Role;
import com.jungi.toy.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OauthAttribute {
    private static final String NAVER = "naver";

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
        if (NAVER.equals(registrationId)) {
            return ofNaver("id", attribute);
        }

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

    private static OauthAttribute ofNaver(String userAttributeName, Map<String, Object> attribute) {
        Map<String, Object> response = (Map<String, Object>) attribute.get("response");

        return OauthAttribute.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attribute(response)
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
