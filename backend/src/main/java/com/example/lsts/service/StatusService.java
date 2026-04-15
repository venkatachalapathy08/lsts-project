package com.example.lsts.service;

import com.example.lsts.dto.statusdto.StatusDTO;

import java.util.List;

public interface StatusService {

    StatusDTO updateStatus(Long complaintId, Long userId, String status);

    List<StatusDTO> getStatusHistoryByComplaintId(Long complaintId);

    StatusDTO getLatestStatus(Long complaintId);
}