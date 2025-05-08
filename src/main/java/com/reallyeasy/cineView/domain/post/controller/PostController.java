package com.reallyeasy.cineView.domain.post.controller;

import com.reallyeasy.cineView.common.enums.Category;
import com.reallyeasy.cineView.domain.post.dto.request.PostCreateRequest;
import com.reallyeasy.cineView.domain.post.dto.response.PostApi;
import com.reallyeasy.cineView.domain.post.dto.response.PostListResponse;
import com.reallyeasy.cineView.domain.post.dto.response.PostResponse;
import com.reallyeasy.cineView.domain.post.dto.response.PostWithCommentResponse;
import com.reallyeasy.cineView.domain.post.service.PostService;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping
    public PostApi<PostResponse> creat(@Valid @RequestBody PostCreateRequest request, @AuthenticationPrincipal User user) {
        PostResponse response = postService.create(request, user.getId());

        return PostApi.<PostResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .body(response)
                .build();
    }

    @GetMapping
    public PostApi<PostListResponse> list(
            @RequestParam(required = true) Category category,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PostListResponse response =  postService.getList(category, pageable);
        return PostApi.<PostListResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .body(response)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostWithCommentResponse> detail(
            @PathVariable Long id
    ) {
        PostWithCommentResponse response = postService.getDetail(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public PostApi<PostResponse> update(
            @Valid @RequestBody PostCreateRequest request,
            @PathVariable Long id, @AuthenticationPrincipal User user
    ) {
        PostResponse response = postService.update(request, id, user.getId());
        return PostApi.<PostResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .body(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public PostApi<PostResponse> delete(
            @PathVariable Long id, @AuthenticationPrincipal User user
    ) {
        postService.delete(id, user.getId());
        return PostApi.<PostResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .body(null)
                .build();
    }

}
