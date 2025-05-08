package com.reallyeasy.cineView.common.util;

public class ApiPath {

    public static final String BASE_USER = "/api/v1/users";
    public static final String BASE_POST = "/api/v1/posts";
    public static final String BASE_REVIEW = "/api/v1/reviews";
    public static final String BASE_FAVORITE = "/api/v1/favorite-movies";
    public static final String BASE_COMMENT = "/api/v1/comments";
    public static final String BASE_MOVIE = "/api/v1/movies";

    public static final String LOGIN = BASE_USER + "/login";
    public static final String JOIN = BASE_USER + "/join";
    public static final String CHECK_USERNAME = BASE_USER + "/{username}/exists";
    public static final String ERROR_PAGE = "/error";

    public static final String[] ALL_USER_API_PATHS = {
            JOIN,
            CHECK_USERNAME,
            ERROR_PAGE
    };

    public static final String CREATE_POST = BASE_POST;
    public static final String LIST_POSTS = BASE_POST;
    public static final String DETAIL_POST = BASE_POST + "/{id}";
    public static final String UPDATE_POST = BASE_POST + "/{id}";
    public static final String DELETE_POST = BASE_POST + "/{id}";

    public static final String REVIEW_API = BASE_REVIEW;
    public static final String FAVORITE_MOVIES_API = BASE_FAVORITE;
    public static final String COMMENTS_API = BASE_COMMENT;

    public static final String MOVIE_API = BASE_MOVIE;

    public static final String[] NEED_GRANT_API_PATHS = {
            CREATE_POST,
            LIST_POSTS,
            DETAIL_POST,
            UPDATE_POST,
            DELETE_POST,
            REVIEW_API,
            FAVORITE_MOVIES_API,
            COMMENTS_API,
            MOVIE_API,
            LOGIN
    };

    private ApiPath() {
    }
}
