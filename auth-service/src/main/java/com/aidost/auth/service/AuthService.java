package com.aidost.auth.service;
import com.aidost.auth.dto.AuthDtos.*;
public interface AuthService { AuthResponse register(RegisterRequest request); AuthResponse login(LoginRequest request); }
