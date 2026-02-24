package com.aidost.auth.controller;
import com.aidost.auth.dto.AuthDtos.*;import com.aidost.auth.service.AuthService;import lombok.RequiredArgsConstructor;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/auth") @RequiredArgsConstructor
public class AuthController {
 private final AuthService service;
 @PostMapping("/register") public AuthResponse register(@RequestBody RegisterRequest request){ return service.register(request);} 
 @PostMapping("/login") public AuthResponse login(@RequestBody LoginRequest request){ return service.login(request);} 
 @GetMapping("/me") public String me(){ return "JWT introspection stub"; }
}
