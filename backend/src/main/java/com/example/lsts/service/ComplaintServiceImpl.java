package com.example.lsts.service;

import com.example.lsts.dto.complaintdto.ComplaintRequestDTO;
import com.example.lsts.dto.complaintdto.ComplaintResponseDTO;
import com.example.lsts.entity.ComplaintEntity;
import com.example.lsts.entity.UserEntity;
import com.example.lsts.exception.ResourceNotFoundException;
import com.example.lsts.repository.ComplaintRepository;
import com.example.lsts.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                UserRepository userRepository) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }

    // CREATE COMPLAINT
    @Override
    @Transactional
    public ComplaintResponseDTO createComplaint(ComplaintRequestDTO requestDTO) {

        String email = Objects.requireNonNull(
                SecurityContextHolder.getContext().getAuthentication()
        ).getName();

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ComplaintEntity complaint = mapToEntity(requestDTO, user);

        ComplaintEntity saved = complaintRepository.save(complaint);

        return mapToDTO(saved);
    }

    // GET COMPLAINT BY ID
    @Override
    public ComplaintResponseDTO getComplaintById(Long id) {
        ComplaintEntity complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        return mapToDTO(complaint);
    }

    // GET ALL COMPLAINTS
    @Override
    public List<ComplaintResponseDTO> getAllComplaint() {
        return complaintRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET MY COMPLAINTS
    @Override
    public List<ComplaintResponseDTO> getMyComplaints() {
        String email = Objects.requireNonNull(
                SecurityContextHolder.getContext().getAuthentication()
        ).getName();

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return complaintRepository.findByUser(user)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE COMPLAINT STATUS
    @Override
    @Transactional
    public ComplaintResponseDTO updateComplaintStatus(Long id, String status) {

        ComplaintEntity complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        complaint.setStatus(status);

        ComplaintEntity updated = complaintRepository.save(complaint);

        return mapToDTO(updated);
    }

    // MAP DTO TO ENTITY
    private ComplaintEntity mapToEntity(ComplaintRequestDTO dto, UserEntity user) {
        ComplaintEntity complaint = new ComplaintEntity();

        complaint.setTitle(dto.getTitle());
        complaint.setDescription(dto.getDescription());
        complaint.setStatus("PENDING");
        complaint.setUser(user);
        complaint.setComplaintCreatedAt(LocalDateTime.now());

        return complaint;
    }

    // MAP ENTITY TO DTO
    private ComplaintResponseDTO mapToDTO(ComplaintEntity complaint) {
        ComplaintResponseDTO dto = new ComplaintResponseDTO();

        dto.setComplaintId(complaint.getComplaintId());
        dto.setTitle(complaint.getTitle());
        dto.setDescription(complaint.getDescription());
        dto.setStatus(complaint.getStatus());
        dto.setComplaintCreatedAt(complaint.getComplaintCreatedAt());

        if (complaint.getUser() != null) {
            dto.setUserName(complaint.getUser().getName());
            dto.setUserId(complaint.getUser().getUserId());
        }

        return dto;
    }
}