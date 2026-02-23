package com.aidost.mock.model;
import jakarta.persistence.*;import lombok.*;
@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor public class MockAnswer { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private Long sessionId; @Column(length=4000) private String answerText; @Column(length=8000) private String transcript; @Column(length=3000) private String aiEvaluation; private Integer score; }
