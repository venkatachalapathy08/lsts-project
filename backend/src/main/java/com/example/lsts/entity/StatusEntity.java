package com.example.lsts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @ManyToOne
    @JoinColumn(name = "complaintId" , nullable = false)
    private ComplaintEntity complaint;

    @ManyToOne
    @JoinColumn(name = "userId" , nullable = false)
    private UserEntity updatedBy;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;

    public enum Status {
        PENDING, IN_PROGRESS, COMPLETED
    }
}
