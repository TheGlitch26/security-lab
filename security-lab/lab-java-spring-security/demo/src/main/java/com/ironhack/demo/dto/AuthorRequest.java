package com.ironhack.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

public class AuthorRequest {

    @Column(length = 100)
    private String fullName;

    @Email
    @Column(length = 200)
    private String email;

    @Column
    private String password;

    public AuthorRequest() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
