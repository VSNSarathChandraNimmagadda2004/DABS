package com.example.DABS.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firebase_uid", unique = true, nullable = false)
    private String firebaseUid;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phone;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    public User() {}

    public User(Long id, String firebaseUid, String email, String name, Role role, Gender gender, String phone, String profilePic, java.time.LocalDateTime createdAt) {
        this.id = id;
        this.firebaseUid = firebaseUid;
        this.email = email;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.phone = phone;
        this.profilePic = profilePic;
        this.createdAt = createdAt;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // Standard Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirebaseUid() { return firebaseUid; }
    public void setFirebaseUid(String firebaseUid) { this.firebaseUid = firebaseUid; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getProfilePic() { return profilePic; }
    public void setProfilePic(String profilePic) { this.profilePic = profilePic; }
    public java.time.LocalDateTime getCreatedAt() { return createdAt; }

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
    }

    public enum Role { PATIENT, DOCTOR, ADMIN }
    public enum Gender { MALE, FEMALE, OTHER }

    // Minimal manual builder
    public static class UserBuilder {
        private Long id;
        private String firebaseUid;
        private String email;
        private String name;
        private Role role;
        private Gender gender;
        private String phone;
        private String profilePic;

        public UserBuilder id(Long id) { this.id = id; return this; }
        public UserBuilder firebaseUid(String uid) { this.firebaseUid = uid; return this; }
        public UserBuilder email(String email) { this.email = email; return this; }
        public UserBuilder name(String name) { this.name = name; return this; }
        public UserBuilder role(Role role) { this.role = role; return this; }
        public UserBuilder gender(Gender gender) { this.gender = gender; return this; }
        public UserBuilder phone(String phone) { this.phone = phone; return this; }
        public UserBuilder profilePic(String pic) { this.profilePic = pic; return this; }
        public User build() { return new User(id, firebaseUid, email, name, role, gender, phone, profilePic, null); }
    }
}
