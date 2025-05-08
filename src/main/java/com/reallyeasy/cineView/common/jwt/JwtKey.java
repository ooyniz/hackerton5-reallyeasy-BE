package com.reallyeasy.cineView.common.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Component
public class JwtKey implements InitializingBean {

    private final Environment env;

    private static final Map<String, String> SECRET_KEY_SET = new HashMap<>();
    private static String[] KID_SET;
    private static final Random random = new Random();

    public JwtKey(Environment env) {
        this.env = env;
    }

    @Override
    public void afterPropertiesSet() {
        for (int i = 1; i <= 3; i++) {
            String envKey = "JWT_KEY_KEY" + i;
            String value = env.getProperty(envKey);
            if (value != null && !value.isBlank()) {
                SECRET_KEY_SET.put("key" + i, value);
            }
        }
        KID_SET = SECRET_KEY_SET.keySet().toArray(new String[0]);
    }

    public static Pair<String, Key> getRandomKey() {
        String kid = KID_SET[random.nextInt(KID_SET.length)];
        String secretKey = SECRET_KEY_SET.get(kid);
        return Pair.of(kid, Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)));
    }

    public static Key getKey(String kid) {
        String key = SECRET_KEY_SET.get(kid);
        if (key == null) return null;
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }
}
