package com.reallyeasy.cineView.domain.post.service;

import com.reallyeasy.cineView.common.enums.Category;
import com.reallyeasy.cineView.domain.post.dto.request.PostCreateRequest;
import com.reallyeasy.cineView.domain.post.dto.response.*;
import com.reallyeasy.cineView.domain.post.entity.Post;
import com.reallyeasy.cineView.domain.post.repository.PostRepository;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    /**
     * 게시글 작성
     */
    public PostResponse create(PostCreateRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Post post = request.toEntity(user);
        Post savedPost = postRepository.save(post);

        return PostResponse.toDto(savedPost);
    }

    /**
     * 게시글 목록 조회
     */
    public Page<PostResponse> getList(Category category, Pageable pageable) {
        Page<Post> pageList = postRepository.findByCategory(category, pageable);
        return pageList.map(PostResponse::toDto);
    }

    /**
     * 게시글 상세 조회
     */
    public PostWithCommentResponse getDetail(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));
        return PostWithCommentResponse.toDto(post);
    }

    /**
     * 게시글 수정
     */
    public PostResponse update(PostCreateRequest request, Long id, Long userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("작성자가 아닙니다.");
        }

        post.update(request.getTitle(), request.getContent());

        return PostResponse.toDto(post);
    }

    /**
     * 게시글 삭제
     */
    public PostResponse delete(Long id, Long userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("작성자가 아닙니다.");
        }

        postRepository.delete(post);

        return PostResponse.toDto(post);
    }
}
