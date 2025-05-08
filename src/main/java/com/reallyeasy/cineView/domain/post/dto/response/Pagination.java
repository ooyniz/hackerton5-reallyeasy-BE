package com.reallyeasy.cineView.domain.post.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Pagination {

    private Integer page;

    private Integer size;

    private Integer totalPage;

    private Integer currentElements;

    private Long totalElements;
}
