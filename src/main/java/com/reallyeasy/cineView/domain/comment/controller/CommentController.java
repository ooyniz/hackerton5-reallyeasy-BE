package com.reallyeasy.cineView.domain.comment.controller;

import com.reallyeasy.cineView.domain.comment.dto.request.CommentRequest;
import com.reallyeasy.cineView.domain.comment.dto.response.CommentForUserResponse;
import com.reallyeasy.cineView.domain.comment.dto.response.CommentResponse;
import com.reallyeasy.cineView.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {
    // todo
    private final Long userId = 1L;
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId, @Valid @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.createComment(request, postId, userId));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(request, commentId, userId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<CommentForUserResponse>> getCommentsByUser() {
        return ResponseEntity.ok(commentService.getCommentsByUser(userId));
    }
}
