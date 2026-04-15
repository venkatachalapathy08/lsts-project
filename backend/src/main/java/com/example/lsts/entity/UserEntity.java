package com.example.lsts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false , length = 225)
    private String name;

    @Column(nullable = false , unique = true , length = 225)
    private String email;

    @Column(nullable = false , unique = true)
    private String phoneNumber;

    @Column(nullable = false , length = 225)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime userCreatedAt;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ComplaintEntity> complaints;

    @OneToMany(mappedBy = "user")
    private List<AssignmentEntity> assignments;

    public enum Role{
        USER,
        ADMIN
    }
}