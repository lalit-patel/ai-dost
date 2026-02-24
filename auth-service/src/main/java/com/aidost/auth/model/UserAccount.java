package com.aidost.auth.model;
import jakarta.persistence.*;import lombok.*;import java.util.Set;
@Entity @Table(name="users") @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class UserAccount {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String email;
 @Column(nullable=false) private String passwordHash;
 @ElementCollection(fetch=FetchType.EAGER) @Enumerated(EnumType.STRING) private Set<Role> roles;
 @Enumerated(EnumType.STRING) private Plan plan;
 private String displayName;
}
