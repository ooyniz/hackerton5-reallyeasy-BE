package com.reallyeasy.cineView.common.jwt;

import com.reallyeasy.cineView.domain.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import org.springframework.data.util.Pair;

import java.security.Key;
import java.util.Date;

public class JwtUtils {
    public static String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKeyResolver(SigningKeyResolver.instance) // kid 기반으로 키 찾기
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // username 추출
    }

    /**
     * 유저 정보를 기반으로 JWT 생성
     */
    public static String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername()); // 사용자 ID 저장
        Date now = new Date();

        // 랜덤한 key/kid 선택
        Pair<String, Key> key = JwtKey.getRandomKey();

        // JWT 생성
        return Jwts.builder()
                .setClaims(claims)                             // 사용자 정보
                .setIssuedAt(now)                              // 발급 시각
                .setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME)) // 만료 시간
                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst()) // kid 설정
                .signWith(key.getSecond())                      // 선택된 키로 서명
                .compact();                                     // 최종 문자열로 변환
    }
}
