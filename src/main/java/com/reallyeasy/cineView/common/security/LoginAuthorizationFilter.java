package com.reallyeasy.cineView.common.security;

import com.reallyeasy.cineView.common.jwt.JwtProperties;
import com.reallyeasy.cineView.common.jwt.JwtUtils;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

public class LoginAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    // 생성자 주입: AuthenticationManager를 통해 실제 인증 수행
    public LoginAuthorizationFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 필터 실행 (모든 요청마다 실행됨)
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = null;

        // 요청에서 JWT 쿠키를 찾음
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                token = Arrays.stream(cookies)
                        .filter(cookie -> JwtProperties.COOKIE_NAME.equals(cookie.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);
            }
        } catch (Exception ignored) {}

        // JWT가 존재하면 사용자 인증 시도
        if (token != null && token.split("\\.").length == 3) {
            try {
                Authentication authentication = getUsernamePasswordAuthenticationToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (ExpiredJwtException e) {
                // 만료된 경우 → 로그아웃 처리 or 401 응답
                System.out.println("🔴 JWT expired");
                Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }catch (Exception e) {
                e.printStackTrace();
                Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }

    // JWT로부터 사용자 이름을 추출하고 DB에서 사용자 조회하여 Authentication 객체 생성
    private Authentication getUsernamePasswordAuthenticationToken(String token) {
        String userName = JwtUtils.getUsername(token);
        if (userName != null) {
            User user = userRepository.findByUserName(userName).orElseThrow();
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }
}
