package com.reallyeasy.cineView.domain.user.dto.response;

public record UserUpdateResponse(
        String newUserName,
        String newName,
        String newBio,
        Character newGender
) {
    public static UserUpdateResponse of(String newUserName, String newName, String newBio, Character newGender) {
        return new UserUpdateResponse(newUserName, newName, newBio, newGender);
    }
}
