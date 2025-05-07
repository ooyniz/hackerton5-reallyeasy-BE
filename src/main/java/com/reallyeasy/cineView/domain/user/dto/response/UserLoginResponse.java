package com.reallyeasy.cineView.domain.user.dto.response;

public record UserLoginResponse(
        String userName,
        String name,
        String bio,
        String token,
        char gender
) {
}
