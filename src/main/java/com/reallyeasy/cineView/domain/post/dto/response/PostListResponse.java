package com.reallyeasy.cineView.domain.post.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResponse {

    private List<PostResponse> posts;
    private Pagination pagination;
}
