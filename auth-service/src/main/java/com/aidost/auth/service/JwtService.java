package com.aidost.auth.service;
import com.aidost.auth.model.Plan;import com.aidost.auth.model.Role;import io.jsonwebtoken.Jwts;import io.jsonwebtoken.security.Keys;import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;import java.nio.charset.StandardCharsets;import java.util.Date;import java.util.Set;
@Service
public class JwtService {
 private final SecretKey key = Keys.hmacShaKeyFor("aidost-super-secret-key-for-jwt-signing-123456".getBytes(StandardCharsets.UTF_8));
 public String generateToken(Long userId, Set<Role> roles, Plan plan){
  return Jwts.builder().subject(String.valueOf(userId)).claim("roles", roles).claim("plan", plan.name())
   .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+86400000)).signWith(key).compact();
 }
}
