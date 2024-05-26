package com.ikariscraft.cyclecare.api.requests;

public class PasswordResetRequest {
    private String newPassword;
    private String confirmPassword;
    private String token;
    public PasswordResetRequest(String newPassword, String confirmPassword, String token) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.token = token;
    }
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}