package com.reallyeasy.cineView.domain.user.dto.request;

public record UserLoginRequest(
        String userName,
        String password
) {
}
