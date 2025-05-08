package com.reallyeasy.cineView.domain.post.controller;

import com.reallyeasy.cineView.common.enums.Category;
import com.reallyeasy.cineView.domain.post.dto.request.PostCreateRequest;
import com.reallyeasy.cineView.domain.post.dto.response.PostResponse;
import com.reallyeasy.cineView.domain.post.dto.response.PostWithCommentResponse;
import com.reallyeasy.cineView.domain.post.service.PostService;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<PostResponse> create(@Valid @RequestBody PostCreateRequest request, @AuthenticationPrincipal User user) {
        PostResponse response = postService.create(request, user.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<PostResponse>> list(
            @RequestParam(name = "category", required = true) Category category,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<PostResponse> responses = postService.getList(category, pageable);
        return ResponseEntity.ok(responses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostWithCommentResponse> detail(@PathVariable("id") Long id) {
        PostWithCommentResponse response = postService.getDetail(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponse> update(@Valid @RequestBody PostCreateRequest request, @PathVariable Long id, @AuthenticationPrincipal User user) {
        PostResponse response = postService.update(request, id, user.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
        postService.delete(id, user.getId());
        return ResponseEntity.ok().build();
    }

}
