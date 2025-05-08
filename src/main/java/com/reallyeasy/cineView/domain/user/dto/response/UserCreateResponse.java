package com.reallyeasy.cineView.domain.user.dto.response;

public record UserCreateResponse(
        Long id,
        String userName,
        String name,
        String bio,
        char gender
) {
}
