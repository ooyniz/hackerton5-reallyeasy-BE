package com.reallyeasy.cineView.domain.post.service;

import com.reallyeasy.cineView.domain.post.dto.response.PostResponse;
import com.reallyeasy.cineView.domain.post.entity.Post;
import org.springframework.stereotype.Service;

@Service
public class PostConverter {

    public PostResponse toResponse(Post entity) {
        return PostResponse.builder()
                .id(entity.getId())
                .category(entity.getCategory())
                .categoryName(entity.getCategory().getDisplayName())
                .title(entity.getTitle())
                .content(entity.getContent())
                .userId(entity.getUser().getId())
                .userName(entity.getUser().getUsername())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
