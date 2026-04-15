package com.example.lsts.controller;

import com.example.lsts.dto.statusdto.StatusDTO;
import com.example.lsts.service.StatusService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/status")
public class StatusController {

    private final StatusService statusService;

    // ✅ Constructor Injection
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    // ================= UPDATE STATUS =================
    @PostMapping("/update")
    public ResponseEntity<StatusDTO> updateStatus(
            @RequestParam Long complaintId,
            @RequestParam Long userId,
            @RequestParam String status) {

        StatusDTO response =
                statusService.updateStatus(complaintId, userId, status);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ================= STATUS HISTORY =================
    @GetMapping("/history/{complaintId}")
    public ResponseEntity<List<StatusDTO>> getStatusHistory(
            @PathVariable Long complaintId) {

        List<StatusDTO> list =
                statusService.getStatusHistoryByComplaintId(complaintId);

        return ResponseEntity.ok(list);
    }

    // ================= LATEST STATUS =================
    @GetMapping("/latest/{complaintId}")
    public ResponseEntity<StatusDTO> getLatestStatus(
            @PathVariable Long complaintId) {

        StatusDTO response =
                statusService.getLatestStatus(complaintId);

        return ResponseEntity.ok(response);
    }
}