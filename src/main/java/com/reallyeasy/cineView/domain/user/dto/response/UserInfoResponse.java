package com.reallyeasy.cineView.domain.user.dto.response;

import com.reallyeasy.cineView.domain.user.entity.User;

public record UserInfoResponse(Long userId, String userName, String name, String bio, Character gender) {

    public static UserInfoResponse of(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getBio(),
                user.getGender()
        );
    }
}
