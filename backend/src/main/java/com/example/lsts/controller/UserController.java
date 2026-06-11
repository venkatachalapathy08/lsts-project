package com.example.lsts.controller;

import com.example.lsts.dto.userdto.UserRequestDTO;
import com.example.lsts.dto.userdto.UserResponseDTO;
import com.example.lsts.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    // ✅ Register User
    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO requestDTO) {

        UserResponseDTO response = userService.registerUser(requestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ✅ Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        UserResponseDTO response = userService.getUserById(id);

        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    // ✅ Get All Users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<UserResponseDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    // ✅ Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO requestDTO) {

        UserResponseDTO updatedUser = userService.updateUser(id, requestDTO);

        return ResponseEntity.ok(updatedUser);
    }

    // ✅ Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully");
    }
}