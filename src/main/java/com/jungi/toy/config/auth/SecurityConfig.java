package com.jungi.toy.config.auth;

import com.jungi.toy.config.auth.service.CustomOauth2UserService;
import com.jungi.toy.user.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOauth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
            .and()
                .authorizeRequests()
                    .antMatchers("/", "/resources/css/**", "/resources/images/**", "/resources/js/**", "/h2-console/**", "/profile")
                            .permitAll()
                    .antMatchers("/api/**")
                        .hasRole(Role.USER.name())
                            .anyRequest()
                                .authenticated()
            .and()
                .logout()
                    .logoutSuccessUrl("/")
            .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOauth2UserService);
    }
}
