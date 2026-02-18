package com.aidost.mock.model;
import jakarta.persistence.*;import lombok.*;
@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor public class MockQuestion { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private Long sessionId; @Column(length=2000) private String questionText; private String category; private String difficulty; }
