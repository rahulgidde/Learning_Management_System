package com.bridgelabz.lmsapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity(name = "fellowship_candidate")
@Table
public class FellowshipModel {
    @Id
    Long id;
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    Integer contactNumber;
    Date creatorStamp;
    String creatorUser;
    Date birthDate;
    String verifyBirthDate;
    String parentName;
    String parentOccupation;
    int parentMobileNumber;
    int parentAnnualSalary;
    String localAddress;
    String permanentAddress;
    String photoPath;
    Date joiningDate;
    String candidateStatus;
    String documentStatus;
    String remark;
}
