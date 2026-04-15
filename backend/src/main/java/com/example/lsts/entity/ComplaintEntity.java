package com.example.lsts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    @ManyToOne
    @JoinColumn(name = "userId" , nullable = false)
    private UserEntity user;

    @Column(nullable = false , length =225)
    private String title;

    @Column(nullable = false , columnDefinition = "TEXT")
    private String description;

    private String status="PENDING";

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime complaintCreatedAt;

    @OneToMany(mappedBy = "complaint")
    private List<AssignmentEntity> assignments;

    @OneToMany(mappedBy = "complaint")
    private List<StatusEntity> statuses;
}
