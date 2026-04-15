package com.example.lsts.controller;

import com.example.lsts.dto.complaintdto.ComplaintRequestDTO;
import com.example.lsts.dto.complaintdto.ComplaintResponseDTO;
import com.example.lsts.service.ComplaintServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final ComplaintServiceImpl complaintService;

    public ComplaintController(ComplaintServiceImpl complaintService) {

        this.complaintService = complaintService;
    }

    @GetMapping("/my")
    public ResponseEntity<List<ComplaintResponseDTO>> getMyComplaints() {
        List<ComplaintResponseDTO> myComplaints = complaintService.getMyComplaints();
        return ResponseEntity.ok(myComplaints);
    }

    @GetMapping
    public ResponseEntity<List<ComplaintResponseDTO>> getAllComplaints() {
        List<ComplaintResponseDTO> complaints = complaintService.getAllComplaint();
        return ResponseEntity.ok(complaints);
    }

    @PostMapping
    public ResponseEntity<ComplaintResponseDTO> createComplaint(@RequestBody ComplaintRequestDTO dto) {
        return ResponseEntity.ok(complaintService.createComplaint(dto));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ComplaintResponseDTO> updateComplaintStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        ComplaintResponseDTO updatedComplaint = complaintService.updateComplaintStatus(id, status);
        return ResponseEntity.ok(updatedComplaint);
    }
}