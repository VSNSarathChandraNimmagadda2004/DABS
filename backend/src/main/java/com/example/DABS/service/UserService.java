package com.example.DABS.service;

import com.example.DABS.dto.request.CreateUserRequest;
import com.example.DABS.model.User;
import com.example.DABS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    @org.springframework.context.annotation.Lazy
    private final DoctorService doctorService;

    public User getOrCreateUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String firebaseUid = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");
        String picture = jwt.getClaimAsString("picture");

        Optional<User> existingUser = userRepository.findByFirebaseUid(firebaseUid);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        // Create new user if not found
        User newUser = User.builder()
                .firebaseUid(firebaseUid)
                .email(email)
                .name(name != null ? name : "User")
                .profilePic(picture)
                .role(User.Role.PATIENT) // Default role
                .gender(User.Gender.OTHER) // Default gender
                .build();

        return userRepository.save(newUser);
    }

    public User createOrUpdateProfile(CreateUserRequest request) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String firebaseUid = jwt.getSubject();

        User user = userRepository.findByFirebaseUid(firebaseUid)
                .orElse(User.builder().firebaseUid(firebaseUid).email(request.getEmail()).build());

        user.setName(request.getName());
        user.setRole(User.Role.valueOf(request.getRole()));
        user.setGender(User.Gender.valueOf(request.getGender()));
        user.setPhone(request.getPhone());
        user.setProfilePic(request.getProfilePic());

        User savedUser = userRepository.save(user);

        // If the user is a DOCTOR, ensure a Doctor record exists
        if (savedUser.getRole() == User.Role.DOCTOR) {
            doctorService.ensureDoctorExists(savedUser);
        }

        return savedUser;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
