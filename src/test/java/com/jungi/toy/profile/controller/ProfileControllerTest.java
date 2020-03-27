package com.jungi.toy.profile.controller;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerTest {

    @Test
    public void getRealProfile() {
        //given
        String expectedProfile = "real";

        MockEnvironment mockEnvironment = new MockEnvironment();

        mockEnvironment.addActiveProfile(expectedProfile);
        mockEnvironment.addActiveProfile("oauth");
        mockEnvironment.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(mockEnvironment);

        //when
        String profile = profileController.getProfile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void getEmptyRealProfile() {
        //given
        String expectedProfile = "oauth";

        MockEnvironment mockEnvironment = new MockEnvironment();

        mockEnvironment.addActiveProfile(expectedProfile);
        mockEnvironment.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(mockEnvironment);

        //when
        String profile = profileController.getProfile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void getDefaultProfile() {
        //given
        String expectedProfile = "default";

        MockEnvironment mockEnvironment = new MockEnvironment();

        ProfileController profileController = new ProfileController(mockEnvironment);

        //when
        String profile = profileController.getProfile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }
}
