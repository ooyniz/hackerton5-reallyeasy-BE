package com.reallyeasy.cineView.domain.user.dto.response;

import com.reallyeasy.cineView.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private final Long userId;
    private final String userName;
    private final String name;
    private final String bio;
    private final char gender;

    public UserInfoResponse(User user) {
        this.userId = user.getId();
        this.userName = user.getUsername();
        this.name = user.getName();
        this.bio = user.getBio();
        this.gender = user.getGender();
    }
}
