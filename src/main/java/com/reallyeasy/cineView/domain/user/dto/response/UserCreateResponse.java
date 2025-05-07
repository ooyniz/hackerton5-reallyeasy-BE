package com.reallyeasy.cineView.domain.user.dto.response;

public record UserCreateResponse(
        String userName,
        String name,
        String bio,
        char gender
) {
}
