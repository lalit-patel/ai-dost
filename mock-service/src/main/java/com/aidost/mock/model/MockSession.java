package com.aidost.mock.model;
import jakarta.persistence.*;import lombok.*;import java.time.LocalDateTime;
@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class MockSession { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private Long userId; private String type; private String techStack; private LocalDateTime scheduledTime; private String status; }
