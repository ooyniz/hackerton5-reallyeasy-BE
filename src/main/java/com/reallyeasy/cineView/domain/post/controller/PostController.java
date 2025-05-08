package com.reallyeasy.cineView.domain.post.controller;

import com.reallyeasy.cineView.common.enums.Category;
import com.reallyeasy.cineView.domain.post.dto.request.PostCreateRequest;
import com.reallyeasy.cineView.domain.post.dto.response.PostApi;
import com.reallyeasy.cineView.domain.post.dto.response.PostListResponse;
import com.reallyeasy.cineView.domain.post.dto.response.PostResponse;
import com.reallyeasy.cineView.domain.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final Long userId = 1L; //TODO

    private final PostService postService;

    @PostMapping
    public PostApi<PostResponse> creat(@Valid @RequestBody PostCreateRequest request) {
        PostResponse response = postService.create(request, userId);

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
    public PostApi<PostResponse> detail(
            @PathVariable Long id
    ) {
        PostResponse response = postService.getDetail(id);
        return PostApi.<PostResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .body(response)
                .build();
    }

    @PatchMapping("/{id}")
    public PostApi<PostResponse> update(
            @Valid @RequestBody PostCreateRequest request,
            @PathVariable Long id
    ) {
        PostResponse response = postService.update(request, id, userId);
        return PostApi.<PostResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .body(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public PostApi<PostResponse> delete(
            @PathVariable Long id
    ) {
        postService.delete(id, userId);
        return PostApi.<PostResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .body(null)
                .build();
    }

}
