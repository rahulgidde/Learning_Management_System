package com.bridgelabz.lmsapplication.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalInfoDto {
    private Date birthDate;
    private String verifyBirthDate;
    private String parentName;
    private String parentOccupation;
    private int parentMobileNumber;
    private int parentAnnualSalary;
    private String localAddress;
    private String permanentAddress;
    private String photoPath;
}
