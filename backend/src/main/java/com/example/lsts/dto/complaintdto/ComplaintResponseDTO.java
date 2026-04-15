package com.example.lsts.dto.complaintdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponseDTO {

    private Long complaintId;

    private Long userId;

    private String title;

    private String description;

    private String status;

    private LocalDateTime complaintCreatedAt;

    private String userName;

    private String userEmail;
}