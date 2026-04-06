package com.example.DABS.dto.common;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String gender;
    private String phone;
    private String profilePic;

    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String role, String gender, String phone, String profilePic) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.phone = phone;
        this.profilePic = profilePic;
    }

    public static UserDTOBuilder builder() {
        return new UserDTOBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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

    public static class UserDTOBuilder {
        private Long id;
        private String name;
        private String email;
        private String role;
        private String gender;
        private String phone;
        private String profilePic;

        public UserDTOBuilder id(Long id) { this.id = id; return this; }
        public UserDTOBuilder name(String name) { this.name = name; return this; }
        public UserDTOBuilder email(String email) { this.email = email; return this; }
        public UserDTOBuilder role(String role) { this.role = role; return this; }
        public UserDTOBuilder gender(String gender) { this.gender = gender; return this; }
        public UserDTOBuilder phone(String phone) { this.phone = phone; return this; }
        public UserDTOBuilder profilePic(String profilePic) { this.profilePic = profilePic; return this; }
        public UserDTO build() { return new UserDTO(id, name, email, role, gender, phone, profilePic); }
    }
}
