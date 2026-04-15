package com.example.lsts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="work_assignment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @ManyToOne
    @JoinColumn(name = "complaintId")
    private ComplaintEntity complaint;

    // 🔥 ADD THIS FIELD
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime assignedAt;
}
