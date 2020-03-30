package com.jungi.toy.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private static final String DEFAULT = "default";
    private static final int FIRST_INDEX = 0;

    private final Environment environment;

    @GetMapping("/profile")
    public String getProfile() {
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());

        List<String> realProfiles = Arrays.asList("real8081", "real8082");

        String defaultProfile = profiles.isEmpty() ? DEFAULT : profiles.get(FIRST_INDEX);

        return profiles.stream()
                    .filter(realProfiles::contains)
                    .findAny()
                    .orElse(defaultProfile);
    }
}
