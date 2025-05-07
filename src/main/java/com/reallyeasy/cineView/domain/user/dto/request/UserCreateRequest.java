package com.reallyeasy.cineView.domain.user.dto.request;

public record UserCreateRequest(
        String userName,
        String password,
        String name,
        String bio,
        char gender
) {
}
