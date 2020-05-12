package com.bridgelabz.lmsapplication.dto;

import java.util.Date;

public class UserDto {
    Long id;
    String username;
    String password;
    String firstname;
    String lastname;
    String email;
    Integer contact_Number;
    Date creator_Stamp;
    String creator_User;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContact_Number() {
        return contact_Number;
    }

    public void setContact_Number(Integer contact_Number) {
        this.contact_Number = contact_Number;
    }

    public Date getCreator_Stamp() {
        return creator_Stamp;
    }

    public void setCreator_Stamp(Date creator_Stamp) {
        this.creator_Stamp = creator_Stamp;
    }

    public String getCreator_User() {
        return creator_User;
    }

    public void setCreator_User(String creator_User) {
        this.creator_User = creator_User;
    }
}
