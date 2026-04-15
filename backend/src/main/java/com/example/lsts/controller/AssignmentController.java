package com.example.lsts.controller;

import com.example.lsts.dto.assignmentdto.AssignmentDTO;
import com.example.lsts.service.AssignmentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    // ✅ Constructor Injection
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // ================= ASSIGN COMPLAINT =================
    @PostMapping("/{complaintId}")
    public ResponseEntity<AssignmentDTO> assignComplaint(
            @PathVariable Long complaintId) {

        AssignmentDTO response =
                assignmentService.assignComplaint(complaintId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDTO> getAssignmentById(
            @PathVariable Long id) {

        AssignmentDTO response =
                assignmentService.getAssignmentById(id);

        return ResponseEntity.ok(response);
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<List<AssignmentDTO>> getAllAssignments() {

        List<AssignmentDTO> list =
                assignmentService.getAllAssignments();

        return ResponseEntity.ok(list);
    }
}