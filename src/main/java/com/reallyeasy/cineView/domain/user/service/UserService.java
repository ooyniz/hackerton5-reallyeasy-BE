package com.reallyeasy.cineView.domain.user.service;

import com.reallyeasy.cineView.common.jwt.JwtKey;
import com.reallyeasy.cineView.domain.user.dto.request.UserCreateRequest;
import com.reallyeasy.cineView.domain.user.dto.request.UserUpdateRequest;
import com.reallyeasy.cineView.domain.user.dto.response.UserCreateResponse;
import com.reallyeasy.cineView.domain.user.dto.response.UserInfoResponse;
import com.reallyeasy.cineView.domain.user.dto.response.UserUpdateResponse;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCreateResponse join(UserCreateRequest request) {
        Optional<User> savedUser = userRepository.findByUserName(request.userName());

        if (savedUser.isPresent()) throw new IllegalArgumentException();

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

    public UserUpdateResponse update(UserUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        String newUserName = request.newUserName() == null ? user.getUsername() : request.newUserName();
        String newPassword = request.newPassword() == null || request.newPassword().isBlank()
                ? user.getPassword() : passwordEncoder.encode(request.newPassword());
        String newName = request.newName() == null ? user.getName() : request.newName();
        String newBio = request.newBio() == null ? user.getBio() : request.newBio();
        Character newGender = request.newGender() == null ? user.getGender() : request.newGender();

        user.updateUser(newUserName, newPassword, newName, newBio, newGender);
        userRepository.save(user);

        return UserUpdateResponse.of(newUserName, newName, newBio, newGender);
    }

    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.markDeleted();
    }

    public UserInfoResponse getUserInfo(Long userId) {
        log.info(userId.toString());
        User user = userRepository.findByIdAndDeletedAtIsNull(userId).orElseThrow();
        return UserInfoResponse.of(user);
    }
}
