package com.duongw.carbookingapi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class DriverDTO {

    private Integer id;
    @NotEmpty
    @Size(min = 2, message = "Driver first name should have at least 2 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "Driver last name should have at least 2 characters")
    private String lastName;
    @NotEmpty
    private String phone;
    @NotEmpty
    @Email()
    private String email;
    @NotEmpty
    private String address;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public DriverDTO() {
    }

}
