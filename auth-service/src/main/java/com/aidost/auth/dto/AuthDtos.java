package com.aidost.auth.dto;
import com.aidost.auth.model.Plan;import com.aidost.auth.model.Role;import lombok.*;import java.util.Set;
public class AuthDtos {
 @Getter @Setter public static class RegisterRequest { private String email; private String password; private String displayName; }
 @Getter @Setter public static class LoginRequest { private String email; private String password; }
 @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor public static class AuthResponse { private String token; private Long userId; private Set<Role> roles; private Plan plan; }
 @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor public static class AuthMeResponse { private Long userId; private String name; private String email; private Set<Role> roles; private Plan plan; }
}
