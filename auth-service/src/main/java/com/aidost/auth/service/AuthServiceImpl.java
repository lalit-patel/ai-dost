package com.aidost.auth.service;

import com.aidost.auth.dto.AuthDtos.*;
import com.aidost.auth.model.*;
import com.aidost.auth.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserAccountRepository repo;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthResponse register(RegisterRequest req) {
        if (repo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        UserAccount u = UserAccount.builder()
                .email(req.getEmail())
                .passwordHash(encoder.encode(req.getPassword()))
                .displayName(req.getDisplayName())
                .roles(Set.of(Role.CANDIDATE))
                .plan(Plan.FREE)
                .build();

        u = repo.save(u);
        return AuthResponse.builder()
                .userId(u.getId())
                .roles(u.getRoles())
                .plan(u.getPlan())
                .token(jwtService.generateToken(u.getId(), u.getRoles(), u.getPlan()))
                .build();
    }

    public AuthResponse login(LoginRequest req) {
        UserAccount u = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new NoSuchElementException("No account found for this email"));
        if (!encoder.matches(req.getPassword(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return AuthResponse.builder()
                .userId(u.getId())
                .roles(u.getRoles())
                .plan(u.getPlan())
                .token(jwtService.generateToken(u.getId(), u.getRoles(), u.getPlan()))
                .build();
    }

    public AuthMeResponse me(String authorizationHeader) {
        Long userId = jwtService.extractUserId(authorizationHeader);
        UserAccount user = userId == null
                ? repo.findAll().stream().findFirst().orElseThrow(() -> new NoSuchElementException("No users found"))
                : repo.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        return AuthMeResponse.builder()
                .userId(user.getId())
                .name(user.getDisplayName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .plan(user.getPlan())
                .build();
    }
}
