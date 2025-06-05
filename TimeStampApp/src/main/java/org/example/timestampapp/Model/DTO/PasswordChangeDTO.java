package org.example.timestampapp.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import org.example.timestampapp.Validation.ValidPassword;

public class PasswordChangeDTO {
    @NotBlank(message = "{password.blank}")
    private String currentPassword;
    @NotBlank(message = "{password.blank}")
    @ValidPassword
    private String newPassword;
    @NotBlank(message = "{password.blank}")
    @ValidPassword
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
