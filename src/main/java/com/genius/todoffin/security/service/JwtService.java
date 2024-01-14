package com.genius.todoffin.security.service;

import static com.genius.todoffin.security.constants.JwtRule.ACCESS_PREFIX;
import static com.genius.todoffin.security.constants.JwtRule.JWT_ISSUE_HEADER;
import static com.genius.todoffin.security.constants.JwtRule.REFRESH_PREFIX;

import com.genius.todoffin.security.domain.Token;
import com.genius.todoffin.security.repository.TokenRepository;
import com.genius.todoffin.user.domain.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final JwtGenerator jwtGenerator;
    private final TokenRepository tokenRepository;

    @Value("${jwt.access-secret}")
    private String ACCESS_SECRET;
    @Value("${jwt.refresh-secret}")
    private String REFRESH_SECRET;
    @Value("${jwt.access-expiration}")
    private long ACCESS_EXPIRATION;
    @Value("${jwt.refresh-expiration}")
    private long REFRESH_EXPIRATION;


    public void generateAccessToken(HttpServletResponse response, User requestUser) {
        String accessToken = jwtGenerator.generateAccessToken(ACCESS_SECRET, ACCESS_EXPIRATION, requestUser);
        ResponseCookie cookie = setTokenToCookie(ACCESS_PREFIX.getValue(), accessToken, ACCESS_EXPIRATION / 1000);
        response.addHeader(JWT_ISSUE_HEADER.getValue(), cookie.toString());
    }

    public void generateRefreshToken(HttpServletResponse response, User requestUser) {
        String refreshToken = jwtGenerator.generateRefreshToken(REFRESH_SECRET, REFRESH_EXPIRATION);
        ResponseCookie cookie = setTokenToCookie(REFRESH_PREFIX.getValue(), refreshToken, REFRESH_EXPIRATION / 1000);
        response.addHeader(JWT_ISSUE_HEADER.getValue(), cookie.toString());

        tokenRepository.save(new Token(requestUser.getIdentifier(), refreshToken));
    }

    private ResponseCookie setTokenToCookie(String tokenType, String token, long maxAgeSeconds) {
        return ResponseCookie.from(tokenType, token)
                .path("/")
                .maxAge(maxAgeSeconds)
                .httpOnly(true)
                .secure(true)
                .build();
    }
}
