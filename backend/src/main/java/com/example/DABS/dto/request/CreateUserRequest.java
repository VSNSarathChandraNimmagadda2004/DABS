package com.example.DABS.dto.request;

public class CreateUserRequest {
    private String name;
    private String email;
    private String role;     // PATIENT / DOCTOR / ADMIN
    private String gender;
    private String phone;
    private String profilePic;

    public CreateUserRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getProfilePic() { return profilePic; }
    public void setProfilePic(String profilePic) { this.profilePic = profilePic; }
}
