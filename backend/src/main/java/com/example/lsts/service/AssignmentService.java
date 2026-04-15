package com.example.lsts.service;

import com.example.lsts.dto.assignmentdto.AssignmentDTO;

import java.util.List;

public interface AssignmentService {

    AssignmentDTO assignComplaint(Long complaintId);

    AssignmentDTO getAssignmentById(Long id);

    List<AssignmentDTO> getAllAssignments();
}