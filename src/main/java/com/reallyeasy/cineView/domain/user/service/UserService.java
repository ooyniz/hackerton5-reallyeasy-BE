package com.reallyeasy.cineView.domain.user.service;

import com.reallyeasy.cineView.domain.user.dto.UserCreateRequest;
import com.reallyeasy.cineView.domain.user.dto.UserCreateResponse;
import com.reallyeasy.cineView.domain.user.dto.UserLoginRequest;
import com.reallyeasy.cineView.domain.user.dto.UserLoginResponse;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserCreateResponse join(UserCreateRequest request) {
        User user = User.builder()
                .userName(request.userName())
                .password(request.password())
                .name(request.name())
                .bio(request.bio())
                .gender(request.gender())
                .build();

        userRepository.save(user);

        return new UserCreateResponse(user.getUserName(),
                user.getName(), user.getBio(), user.getGender());
    }

    public Boolean checkUserNameDuplicate(String userName) {
        return userRepository.existsUserName(userName).orElseThrow(); // TODO:Exception 처리
    }
}
