package com.rasthrabhasha.dto;

public class AdminDTO {
    private long admin_id;
    private String username;

    public AdminDTO() {
    }

    public AdminDTO(long admin_id, String username) {
        this.admin_id = admin_id;
        this.username = username;
    }

    public long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(long admin_id) {
        this.admin_id = admin_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
