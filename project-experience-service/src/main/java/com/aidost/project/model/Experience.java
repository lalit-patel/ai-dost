package com.aidost.project.model;
import jakarta.persistence.*;import lombok.*;
@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor public class Experience { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private Long userId; private String company; private String role; private String duration; @Column(length=3000) private String responsibilities; @Column(length=3000) private String impactBullets;}
