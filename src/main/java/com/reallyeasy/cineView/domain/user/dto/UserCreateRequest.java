package com.reallyeasy.cineView.domain.user.dto;

public record UserCreateRequest(
        String userName,
        String password,
        String name,
        String bio,
        char gender
) {
}
