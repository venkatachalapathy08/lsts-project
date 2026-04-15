package com.example.lsts.service;

import com.example.lsts.dto.statusdto.StatusDTO;
import com.example.lsts.entity.ComplaintEntity;
import com.example.lsts.entity.StatusEntity;
import com.example.lsts.entity.UserEntity;
import com.example.lsts.exception.ResourceNotFoundException;
import com.example.lsts.repository.ComplaintRepository;
import com.example.lsts.repository.StatusRepository;
import com.example.lsts.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    private final ComplaintRepository complaintRepository;

    private final UserRepository userRepository;

    public StatusServiceImpl(StatusRepository statusRepository , ComplaintRepository complaintRepository , UserRepository userRepository){
        this.statusRepository=statusRepository;
        this.complaintRepository=complaintRepository;
        this.userRepository=userRepository;
    }

    // ================= UPDATE STATUS =================
    @Override
    public StatusDTO updateStatus(Long complaintId, Long userId, String status) {

        // 1. Fetch Complaint
        ComplaintEntity complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found: " + complaintId));

        // 2. Fetch User (who updates status)
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + userId));

        // 3. Convert String → Enum safely
        StatusEntity.Status enumStatus;
        try {
            enumStatus = StatusEntity.Status.valueOf(status.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        // 4. Create Status record (HISTORY ENTRY)
        StatusEntity statusEntity = new StatusEntity();

        statusEntity.setComplaint(complaint);
        statusEntity.setUpdatedBy(user);
        statusEntity.setStatus(enumStatus);
        statusEntity.setUpdatedAt(LocalDateTime.now());

        // 5. Save
        StatusEntity saved = statusRepository.save(statusEntity);

        return mapToDTO(saved);
    }

    // ================= HISTORY =================
    @Override
    public List<StatusDTO> getStatusHistoryByComplaintId(Long complaintId) {

        List<StatusEntity> list = statusRepository.findAll()
                .stream()
                .filter(s -> s.getComplaint().getComplaintId().equals(complaintId))
                .toList();

        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ================= LATEST STATUS =================
    @Override
    public StatusDTO getLatestStatus(Long complaintId) {

        StatusEntity latest = statusRepository.findAll()
                .stream()
                .filter(s -> s.getComplaint().getComplaintId().equals(complaintId))
                .max(Comparator.comparing(StatusEntity::getUpdatedAt))
                .orElseThrow(() ->
                        new ResourceNotFoundException("No status found for complaint: " + complaintId));

        return mapToDTO(latest);
    }

    // ================= MAPPING =================
    private StatusDTO mapToDTO(StatusEntity entity) {

        StatusDTO dto = new StatusDTO();

        dto.setStatusId(entity.getStatusId());

        dto.setComplaintId(entity.getComplaint().getComplaintId());

        dto.setUserId(entity.getUpdatedBy().getUserId());

        dto.setStatus(entity.getStatus().name());

        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}