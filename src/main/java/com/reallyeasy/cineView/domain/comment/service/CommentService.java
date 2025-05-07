package com.reallyeasy.cineView.domain.comment.service;

import com.reallyeasy.cineView.domain.comment.dto.request.CommentRequest;
import com.reallyeasy.cineView.domain.comment.dto.response.CommentForUserResponse;
import com.reallyeasy.cineView.domain.comment.dto.response.CommentResponse;
import com.reallyeasy.cineView.domain.comment.entity.Comment;
import com.reallyeasy.cineView.domain.comment.repository.CommentRepository;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentResponse createComment(CommentRequest request, Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        // todo post
        Comment comment = Comment.builder()
                .user(user)
                .postId(postId)
                .content(request.getContent()).build();
        return new CommentResponse(commentRepository.save(comment));
    }

    public CommentResponse updateComment(CommentRequest request, Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if (!comment.getUser().getId().equals(userId)) throw new IllegalArgumentException("댓글 수정 권한이 없습니다.");

        comment.updateContent(request.getContent());
        return new CommentResponse(comment);
    }

    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (!comment.getUser().getId().equals(userId)) throw new IllegalArgumentException("댓글 삭제 권한이 없습니다.");
        comment.markDeleted();
    }

    public CommentResponse getComment(Long commentId) {
        Comment comment = commentRepository.findByIdAndDeletedAtIsNull(commentId);
        return new CommentResponse(comment);
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostIdAndDeletedAtIsNull(postId);

        return comments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    public List<CommentForUserResponse> getCommentsByUser(Long userId) {
        List<Comment> comments = commentRepository.findAllByUserIdAndDeletedAtIsNull(userId);
        return comments.stream()
                .map(CommentForUserResponse::new)
                .collect(Collectors.toList());
    }
}
