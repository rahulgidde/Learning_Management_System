package com.bridgelabz.lmsapplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity(name = "fellowship_candidate")
@Table
public class FellowshipModel {
    @Id
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
    private String hiredCity;
    private String Degree;
    private Date hiredDate;
    private long mobileNumber;
    private long permanentPincode;
    private String hiredLab;
    private String attitude;
    private String communicationRemark;
    private String knowledgeRemark;
    private String aggregateRemark;
    private String status;
    private LocalDateTime creatorStamp;
    private long creatorUser;
    private Date birthDate;
    private String verifyBirthDate;
    private String parentName;
    private String parentOccupation;
    private int parentMobileNumber;
    private int parentAnnualSalary;
    private String localAddress;
    private String permanentAddress;
    private String photoPath;
    private Date joiningDate;
    private String candidateStatus;
    private String documentStatus;
    private String remark;
}
