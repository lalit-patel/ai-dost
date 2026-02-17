package com.aidost.profile.model;
import jakarta.persistence.*;import lombok.*;
@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class UserProfile {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long userId; private String name; private String email; private String currentRole; private Integer yearsOfExperience;
 private String primaryTechStack; private String targetRoleGoals; @Column(length=2000) private String preferences;
}
