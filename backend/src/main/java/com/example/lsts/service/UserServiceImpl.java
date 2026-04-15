package com.example.lsts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.example.lsts.dto.userdto.UserRequestDTO;
import com.example.lsts.dto.userdto.UserResponseDTO;
import com.example.lsts.entity.UserEntity;
import com.example.lsts.exception.ResourceNotFoundException;
import com.example.lsts.exception.DuplicateResourceException;
import com.example.lsts.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    // ✅ Register User
    @Override
    public UserResponseDTO registerUser(UserRequestDTO requestDTO) {

        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if(userRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())){
            throw new DuplicateResourceException("Phone number already exists");
        }

        UserEntity user = mapToEntity(requestDTO);

        UserEntity savedUser = userRepository.save(user);

        return mapToResponseDTO(savedUser);
    }

    // ✅ Get User by ID
    @Override
    public UserResponseDTO getUserById(Long id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        return mapToResponseDTO(user);
    }

    // ✅ Get All Users
    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // ✅ Update User
    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPhoneNumber(requestDTO.getPhoneNumber());

        UserEntity updatedUser = userRepository.save(user);

        return mapToResponseDTO(updatedUser);
    }

    // ✅ Delete User
    @Override
    public void deleteUser(Long id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

    // =========================
    // 🔁 Mapping Methods
    // =========================

    private UserEntity mapToEntity(UserRequestDTO dto) {
        UserEntity user = new UserEntity();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(UserEntity.Role.valueOf(dto.getRole()));

        return user;
    }

    private UserResponseDTO mapToResponseDTO(UserEntity user) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole().toString());
        dto.setUserCreatedAt(user.getUserCreatedAt());
        return dto;
    }
}