package com.bridgelabz.lmsapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity(name = "hired_Candidate")
@Table
public class HiredCandidateModel {
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
}
