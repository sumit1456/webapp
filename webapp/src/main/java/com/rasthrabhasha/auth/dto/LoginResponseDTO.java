package com.rasthrabhasha.auth.dto;

public class LoginResponseDTO {
    private String token;
    private String role;
    private String username;
    private long userId;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String role, String username, long userId) {
        this.token = token;
        this.role = role;
        this.username = username;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
