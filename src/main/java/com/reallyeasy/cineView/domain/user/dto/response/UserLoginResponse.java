package com.reallyeasy.cineView.domain.user.dto.response;

public record UserLoginResponse(
        Long id,
        String userName,
        String name,
        String bio,
        String token,
        Character gender
) {
}
