package com.reallyeasy.cineView.common.util;

public class ApiPath {

    public static final String BASE = "/api/v1/users";

    public static final String JOIN = BASE + "/join";
    public static final String CHECK_USERNAME = BASE + "/{username}/exists";

    public static final String[] ALL_USER_API_PATHS = {
            JOIN,
            CHECK_USERNAME
    };

    private ApiPath() {

    }
}