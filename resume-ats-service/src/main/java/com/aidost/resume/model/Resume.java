package com.aidost.resume.model;
import jakarta.persistence.*;import lombok.*;
@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor public class Resume { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private Long userId; @Column(length=3000) private String education; @Column(length=3000) private String skills; @Column(length=3000) private String achievements; @Column(length=5000) private String linkedProjectIds; @Column(length=5000) private String linkedExperienceIds;}
