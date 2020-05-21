package com.bridgelabz.lmsapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "userdetail")
@Table
public class UserDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;
    String firstname;
    String lastname;
    String email;
    Integer contact_Number;
    Date creator_Stamp;
    String creator_User;
}
