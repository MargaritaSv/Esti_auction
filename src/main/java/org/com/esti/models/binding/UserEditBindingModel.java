package org.com.esti.models.binding;

import org.com.esti.domain.entities.enums.Gender;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserEditBindingModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthday;
    private Gender gender;
    private String primaryAddress;
    private String secondAddress;
    private String country;
    private String email;
    private String cardNumber;
    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;

    public UserEditBindingModel() {
    }

    @Size(min = 2, max = 65, message = "Name must be between 2 and 65 characters.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 2, max = 65, message = "Name must be between 2 and 65 characters.")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Size(min = 2, max = 65, message = "Name must be between 2 and 65 characters.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @NotNull
    @NotEmpty(message = "Gender is required")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getSecondAddress() {
        return secondAddress;
    }

    public void setSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @NotNull
    @NotEmpty(message = "Password is required")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @NotEmpty(message = "Confirm password is required")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Pattern(regexp = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,64}")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
