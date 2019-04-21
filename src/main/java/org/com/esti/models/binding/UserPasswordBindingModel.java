package org.com.esti.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserPasswordBindingModel {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    public UserPasswordBindingModel() {
    }

    @NotNull
    @NotEmpty(message = "Current password is required.")
    public String getOldPassword() {
        return oldPassword;
    }


    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @NotNull
    @NotEmpty(message = "Password is required.")
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @NotNull
    @NotEmpty(message = "Password confirm is required.")
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
