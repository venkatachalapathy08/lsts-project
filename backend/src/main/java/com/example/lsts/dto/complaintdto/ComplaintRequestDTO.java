package com.example.lsts.dto.complaintdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

}