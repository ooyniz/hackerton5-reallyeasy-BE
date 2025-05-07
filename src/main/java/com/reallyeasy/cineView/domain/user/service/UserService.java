package com.reallyeasy.cineView.domain.user.service;

import com.reallyeasy.cineView.common.jwt.JwtKey;
import com.reallyeasy.cineView.domain.user.dto.request.UserCreateRequest;
import com.reallyeasy.cineView.domain.user.dto.request.UserLoginRequest;
import com.reallyeasy.cineView.domain.user.dto.response.UserCreateResponse;
import com.reallyeasy.cineView.domain.user.dto.response.UserLoginResponse;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;

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

        return new UserCreateResponse(user.getUsername(),
                user.getName(), user.getBio(), user.getGender());
    }

    public Boolean checkUserNameDuplicate(String userName) {
        return userRepository.existsByUserName(userName); // TODO:Exception 처리
    }

    public UserLoginResponse login(UserLoginRequest request) {

        User user = userRepository.findByUserName(request.userName()).orElseThrow();

        if(!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new RuntimeException();

        Pair<String, Key> pair = JwtKey.getRandomKey();
        String kid = pair.getFirst();
        Key key = pair.getSecond();

        String token = Jwts.builder()
                .setHeaderParam("kid", kid)
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new UserLoginResponse(user.getUsername(), user.getName(), user.getBio(), token, user.getGender());
    }
}
