package com.reallyeasy.cineView.domain.user.service;

import com.reallyeasy.cineView.common.jwt.JwtKey;
import com.reallyeasy.cineView.domain.user.dto.request.UserCreateRequest;
import com.reallyeasy.cineView.domain.user.dto.request.UserDeleteRequest;
import com.reallyeasy.cineView.domain.user.dto.request.UserUpdateRequest;
import com.reallyeasy.cineView.domain.user.dto.response.UserCreateResponse;
import com.reallyeasy.cineView.domain.user.dto.response.UserDeleteResponse;
import com.reallyeasy.cineView.domain.user.dto.response.UserUpdateResponse;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtKey jwtKey;

    public UserCreateResponse join(UserCreateRequest request) {
        User user = User.builder()
                .userName(request.userName())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .bio(request.bio())
                .gender(request.gender())
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        return new UserCreateResponse(user.getId(),user.getUsername(),
                user.getName(), user.getBio(), user.getGender());
    }

    public Boolean checkUserNameDuplicate(String userName) {
        return userRepository.existsByUserName(userName); // TODO:Exception 처리
    }

    public UserUpdateResponse update(UserUpdateRequest request) {
        User user = userRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        user.setUserName(request.newUserName() == null ? user.getUsername() : request.newUserName());
        user.setPassword(request.newPassword() == null || request.newPassword().isBlank()
                ? user.getPassword()
                : passwordEncoder.encode(request.newPassword()));
        user.setName(request.newName() == null ? user.getName() : request.newName());
        user.setGender(request.newGender() == null ? user.getGender() : request.newGender());

        userRepository.save(user);

        return new UserUpdateResponse(
                user.getUsername(),
                user.getUsername(),
                user.getBio(),
                user.getGender(),
                true
        );
    }

    public UserDeleteResponse delete(UserDeleteRequest request) {
        User user = userRepository.findById(request.id()).orElseThrow();

        try {
            userRepository.delete(user);
        } catch (Exception e) {
            return new UserDeleteResponse("Failed to delete user", false);
        }

        return new UserDeleteResponse("Success to delete user", true);
    }
}
