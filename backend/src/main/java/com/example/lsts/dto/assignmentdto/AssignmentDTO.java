package com.example.lsts.dto.assignmentdto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AssignmentDTO {

    private Long assignmentId;

    private Long complaintId;

    private LocalDateTime assignedAt;
}