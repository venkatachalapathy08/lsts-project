package com.example.lsts.service;

import com.example.lsts.dto.assignmentdto.AssignmentDTO;
import com.example.lsts.entity.AssignmentEntity;
import com.example.lsts.entity.ComplaintEntity;
import com.example.lsts.exception.ResourceNotFoundException;
import com.example.lsts.repository.AssignmentRepository;
import com.example.lsts.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final ComplaintRepository complaintRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository , ComplaintRepository complaintRepository){
        this.assignmentRepository=assignmentRepository;
        this.complaintRepository=complaintRepository;
    }

    // ================= ASSIGN COMPLAINT =================
    @Override
    public AssignmentDTO assignComplaint(Long complaintId) {

        ComplaintEntity complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found with id: " + complaintId));

        AssignmentEntity assignment = new AssignmentEntity();

        assignment.setComplaint(complaint);
        assignment.setAssignedAt(LocalDateTime.now());

        AssignmentEntity saved = assignmentRepository.save(assignment);

        return mapToDTO(saved);
    }

    // ================= GET BY ID =================
    @Override
    public AssignmentDTO getAssignmentById(Long id) {

        AssignmentEntity assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignment not found with id: " + id));

        return mapToDTO(assignment);
    }

    // ================= GET ALL =================
    @Override
    public List<AssignmentDTO> getAllAssignments() {

        return assignmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ================= MAPPING =================
    private AssignmentDTO mapToDTO(AssignmentEntity entity) {

        AssignmentDTO dto = new AssignmentDTO();

        dto.setAssignmentId(entity.getAssignmentId());

        dto.setComplaintId(entity.getComplaint().getComplaintId());

        dto.setAssignedAt(entity.getAssignedAt());

        return dto;
    }
}