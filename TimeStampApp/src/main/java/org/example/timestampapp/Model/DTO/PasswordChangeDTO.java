package org.example.timestampapp.Model.DTO;

public class PasswordChangeDTO {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    @Override
    public String toString() {
        return "PasswordChangeDTO [currentPassword=" + currentPassword + ", newPassword="+newPassword+", confirmPassword="+confirmPassword+"]";
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
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
}
