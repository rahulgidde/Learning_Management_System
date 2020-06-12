package com.bridgelabz.lmsapplication.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer contactNumber;
    private Date creatorStamp;
    private String creatorUser;
}