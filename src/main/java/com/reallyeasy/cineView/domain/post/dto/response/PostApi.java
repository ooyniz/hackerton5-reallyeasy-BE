package com.reallyeasy.cineView.domain.post.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostApi<T> {

    private String resultCode;

    private String resultMessage;

    private T body;

    private Pagination pagination;
}
