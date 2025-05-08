package com.reallyeasy.cineView.domain.user.dto.response;

public record UserUpdateResponse(
        String newUserName,
        String newName,
        String newBio,
        Character newGender,
        boolean result
) {
}
