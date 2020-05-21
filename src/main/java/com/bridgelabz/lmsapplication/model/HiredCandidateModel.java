package com.bridgelabz.lmsapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "hired_Candidate")
@Table
public class HiredCandidateModel {
    @Id
    private Long id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String email_id;
    private String hired_city;
    private String degree;
    private Date hired_date;
    private long mobile_number;
    private long permanent_pincode;
    private String hired_lab;
    private String attitude;
    private String communication_remark;
    private String knowledge_remark;
    private String aggregate_remark;
    private String status;
    private Date creator_stamp;
    private String creator_user;
}