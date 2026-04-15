package com.example.lsts.service;

import java.util.List;

import com.example.lsts.dto.userdto.UserRequestDTO;
import com.example.lsts.dto.userdto.UserResponseDTO;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO requestDTO);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO);

    void deleteUser(Long id);
}

