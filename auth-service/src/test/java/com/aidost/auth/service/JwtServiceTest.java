package com.aidost.auth.service;

import com.aidost.auth.model.Plan;
import com.aidost.auth.model.Role;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldExtractUserIdFromBearerToken() {
        String token = jwtService.generateToken(42L, Set.of(Role.CANDIDATE), Plan.FREE);
        Long userId = jwtService.extractUserId("Bearer " + token);
        assertEquals(42L, userId);
    }
}
