package com.example.application.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String username;
    private String password;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String role;

    public User(String id, String name, String username, String password, String email, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
