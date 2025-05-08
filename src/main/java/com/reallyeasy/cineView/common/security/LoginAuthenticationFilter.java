package com.reallyeasy.cineView.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reallyeasy.cineView.common.jwt.JwtUtils;
import com.reallyeasy.cineView.domain.user.dto.response.UserLoginResponse;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // 생성자 주입: AuthenticationManager를 통해 실제 인증 수행
    public LoginAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 로그인 시도 시 실행됨
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 사용자 입력 값으로 인증 토큰 생성 (비밀번호 검증은 AuthenticationManager가 수행)
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map loginData = objectMapper.readValue(request.getInputStream(), Map.class);

            String userName = (String) loginData.get("userName");
            String password = (String) loginData.get("password");

            System.out.println("userName: " + userName);
            System.out.println("password: " + password);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>());

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException("로그인 요청 파싱 실패", e);
        }
    }

    // 인증 성공 시 실행됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 인증된 사용자 정보 획득
        User user = (User) authResult.getPrincipal();

        String token = JwtUtils.createToken(user);

        UserLoginResponse userLoginResponse = new UserLoginResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getBio(),
                token,
                user.getGender()
        );

        // 헤더에 토큰 추가
        response.addHeader("Authorization", token);

        // JSON 응답
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        ObjectMapper objectMapper = new ObjectMapper();
        String responseJson = objectMapper.writeValueAsString(userLoginResponse);

        response.getWriter().write(responseJson);
    }

    // 인증 실패 시 실행됨
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String errorMessage = switch (failed.getClass().getSimpleName()) {
            case "BadCredentialsException" -> "아이디 또는 비밀번호가 잘못되었습니다.";
            case "DisabledException" -> "비활성화된 계정입니다.";
            case "LockedException" -> "잠긴 계정입니다.";
            default -> "로그인에 실패하였습니다.";
        };

        response.getWriter().write(
                String.format("{\"error\": \"%s\"}", errorMessage)
        );
    }
}

