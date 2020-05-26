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
    private Date creatorStamp;
    private String creatorUser;
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
