package com.example.lsts.service;

import com.example.lsts.dto.complaintdto.ComplaintRequestDTO;
import com.example.lsts.dto.complaintdto.ComplaintResponseDTO;

import java.util.List;

public interface ComplaintService {
    ComplaintResponseDTO createComplaint(ComplaintRequestDTO requestDTO);
    ComplaintResponseDTO getComplaintById(Long id);
    List<ComplaintResponseDTO> getAllComplaint();
    List<ComplaintResponseDTO> getMyComplaints();
    ComplaintResponseDTO updateComplaintStatus(Long id , String status);

}
