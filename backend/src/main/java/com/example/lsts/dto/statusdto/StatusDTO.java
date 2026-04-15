package com.example.lsts.dto.statusdto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StatusDTO {

    private Long statusId;

    private Long complaintId;

    private Long userId;

    private String status;

    private LocalDateTime updatedAt;
}