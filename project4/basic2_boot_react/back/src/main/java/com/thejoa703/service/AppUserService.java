package com.thejoa703.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.dto.request.LoginRequest;
import com.thejoa703.dto.request.UserRequestDto;
import com.thejoa703.dto.response.UserResponseDto;
import com.thejoa703.entity.AppUser;
import com.thejoa703.repository.AppUserRepository;
import com.thejoa703.util.FileStorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final FileStorageService fileStorageService;
    private final PasswordEncoder passwordEncoder;
    
    private static final String DEFAULT_PROFILE_IMAGE = "uploads/default.png"; 

    // 로그인 (정지 유저 체크 포함)
    public UserResponseDto login(LoginRequest request) {
        AppUser user = appUserRepository.findByEmailAndProvider(request.getEmail(), "local")
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        // ✅ 정지 여부 확인
        if (user.isDeleted()) {
            throw new RuntimeException("BANNED_USER"); 
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return UserResponseDto.fromEntity(user);
    }

    // 회원가입
    public UserResponseDto signup(UserRequestDto request, MultipartFile profileImage) {
        String provider = request.getProvider() != null ? request.getProvider() : "local";
        if (appUserRepository.findByEmailAndProvider(request.getEmail(), provider).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        String image = DEFAULT_PROFILE_IMAGE;
        if (profileImage != null && !profileImage.isEmpty()) {
            image = fileStorageService.storeFile(profileImage);
        }
        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .provider(provider)
                .ufile(image)
                .role("ROLE_USER")
                .deleted(false)
                .build();
        return UserResponseDto.fromEntity(appUserRepository.save(user));
    }

    public AppUser saveOrUpdateOAuth2User(String email, String provider, String providerId, String nickname, String image) {
        return appUserRepository.findByEmailAndProvider(email, provider)
                .map(existingUser -> {
                    existingUser.setNickname(nickname);
                    existingUser.setUfile(image);
                    return appUserRepository.save(existingUser);
                })
                .orElseGet(() -> appUserRepository.save(AppUser.builder()
                        .email(email).provider(provider).providerId(providerId)
                        .nickname(nickname).ufile(image).role("ROLE_USER").deleted(false).build()));
    }
    
    public String findRoleByUserId(Long userId) {
        return appUserRepository.findById(userId).map(AppUser::getRole).orElse("ROLE_USER");
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return appUserRepository.findAll().stream().map(UserResponseDto::fromEntity).collect(Collectors.toList());
    }
    
    public void forceDeleteUser(Long userId) {
        appUserRepository.deleteById(userId); 
    }

    public void toggleUserBlock(Long userId) {
        AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        user.setDeleted(!user.isDeleted());
    }
}