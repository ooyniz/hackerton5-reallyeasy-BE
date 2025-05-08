package com.reallyeasy.cineView.domain.user.dto.request;

public record UserUpdateRequest(
        String newUserName,
        String newPassword,
        String newName,
        String newBio,
        Character newGender
) {
}
