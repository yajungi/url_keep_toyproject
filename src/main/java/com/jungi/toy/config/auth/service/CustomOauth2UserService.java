package com.jungi.toy.config.auth.service;

import com.jungi.toy.config.auth.dto.OauthAttribute;
import com.jungi.toy.config.auth.dto.SessionUser;
import com.jungi.toy.user.domain.User;
import com.jungi.toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);

        String registrationId = oAuth2UserRequest.getClientRegistration()
                                                .getRegistrationId();

        String userAttributeName = oAuth2UserRequest.getClientRegistration()
                                                .getProviderDetails()
                                                .getUserInfoEndpoint()
                                                .getUserNameAttributeName();

        OauthAttribute oAuthAttribute = OauthAttribute.of(registrationId,
                userAttributeName,
                oAuth2User.getAttributes());

        User user = saveOrUpdate(oAuthAttribute);

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                oAuthAttribute.getAttribute(),
                oAuthAttribute.getNameAttributeKey());
    }

    private User saveOrUpdate(OauthAttribute oauthAttribute) {
        User user = userRepository.findByEmail(oauthAttribute.getEmail())
                                .map(entity -> entity.updateUser(oauthAttribute.getName(), oauthAttribute.getPicture()))
                                .orElse(oauthAttribute.getConvertedUser());

        return userRepository.save(user);
    }
}
