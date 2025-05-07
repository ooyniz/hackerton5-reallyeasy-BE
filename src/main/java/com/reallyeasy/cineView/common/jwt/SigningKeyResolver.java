package com.reallyeasy.cineView.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;

import java.security.Key;

// JWT를 검증할 때 헤더의 kid로 알맞은 키를 반환하는 역할
public class SigningKeyResolver extends SigningKeyResolverAdapter {

    // 싱글톤 인스턴스 사용 (편의성)
    public static SigningKeyResolver instance = new SigningKeyResolver();

    @Override
    public Key resolveSigningKey(JwsHeader jwsHeader, Claims claims) {
        // JWT 헤더에서 kid 추출
        String kid = jwsHeader.getKeyId();
        if (kid == null)
            return null;

        // kid에 해당하는 서명 키 반환
        return JwtKey.getKey(kid);
    }
}
