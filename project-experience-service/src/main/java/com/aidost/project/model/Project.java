package com.aidost.project.model;
import jakarta.persistence.*;import lombok.*;
@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor public class Project { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private Long userId; private String title; @Column(length=2000) private String description; private String techStack; private String repoLinks; private String status;}
