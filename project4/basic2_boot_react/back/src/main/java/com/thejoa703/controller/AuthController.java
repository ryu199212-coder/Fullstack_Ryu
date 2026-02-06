package com.thejoa703.controller;

import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.thejoa703.dto.request.LoginRequest;
import com.thejoa703.dto.response.UserResponseDto;
import com.thejoa703.security.JwtProvider;
import com.thejoa703.security.TokenStore;
import com.thejoa703.service.AppUserService;
import com.thejoa703.security.JwtProperties;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;
    private final JwtProvider jwtProvider;
    private final TokenStore tokenStore;
    private final JwtProperties props;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        try {
            UserResponseDto userDto = appUserService.login(request);
            
            // 토큰 생성 및 쿠키 설정
            String userIdStr = String.valueOf(userDto.getId());
            String access = jwtProvider.createAccessToken(userIdStr, Map.of("role", userDto.getRole()));
            String refresh = jwtProvider.createRefreshToken(userIdStr);

            tokenStore.saveRefreshToken(userIdStr, refresh, (long) props.getRefreshTokenExpSeconds());

            ResponseCookie cookie = ResponseCookie.from("refreshToken", refresh)
                    .httpOnly(true).path("/").maxAge(props.getRefreshTokenExpSeconds()).sameSite("Strict").build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok(Map.of("accessToken", access, "user", userDto));

        } catch (RuntimeException e) {
            // ✅ 정지 유저 예외 처리 (302 방지)
            if ("BANNED_USER".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("message", "해당 계정은 이용이 정지되었습니다."));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }
}