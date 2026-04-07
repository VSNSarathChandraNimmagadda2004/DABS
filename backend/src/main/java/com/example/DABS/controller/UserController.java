package com.example.DABS.controller;

import com.example.DABS.dto.common.UserDTO;
import com.example.DABS.dto.request.CreateUserRequest;
import com.example.DABS.dto.response.ApiResponse;
import com.example.DABS.model.User;
import com.example.DABS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createProfile(@RequestBody CreateUserRequest request) {
        User user = userService.createOrUpdateProfile(request);
        return ResponseEntity.ok(ApiResponse.<UserDTO>builder()
                .success(true)
                .message("Profile created/updated successfully")
                .data(convertToDTO(user))
                .build());
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDTO>> getCurrentUser() {
        User user = userService.getOrCreateUser();
        return ResponseEntity.ok(ApiResponse.<UserDTO>builder()
                .success(true)
                .message("User profile retrieved")
                .data(convertToDTO(user))
                .build());
    }

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .gender(user.getGender().name())
                .phone(user.getPhone())
                .profilePic(user.getProfilePic())
                .build();
    }
}
