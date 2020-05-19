package com.bridgelabz.lmsapplication.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "hired_candidate")
@Table
public class HiredCandidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String first_name;
    @Column
    private String middle_name;
    @Column
    private String last_name;
    @Column
    private String email;
    @Column
    private String hired_city;
    @Column
    private String degree;
    @Column
    private Date hired_date;
    @Column
    private long mobile_number;
    @Column
    private long permanent_pincode;
    @Column
    private String hired_lab;
    @Column
    private String attitude;
    @Column
    private String communication_remark;
    @Column
    private String knowledge_remark;
    @Column
    private String aggregate_remark;
    @Column
    private String status;
    @Column
    private Date creator_stamp;
    @Column
    private String creator_user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHired_city() {
        return hired_city;
    }

    public void setHired_city(String hired_city) {
        this.hired_city = hired_city;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getHired_date() {
        return hired_date;
    }

    public void setHired_date(Date hired_date) {
        this.hired_date = hired_date;
    }

    public long getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(long mobile_number) {
        this.mobile_number = mobile_number;
    }

    public long getPermanent_pincode() {
        return permanent_pincode;
    }

    public void setPermanent_pincode(long permanent_pincode) {
        this.permanent_pincode = permanent_pincode;
    }

    public String getHired_lab() {
        return hired_lab;
    }

    public void setHired_lab(String hired_lab) {
        this.hired_lab = hired_lab;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getCommunication_remark() {
        return communication_remark;
    }

    public void setCommunication_remark(String communication_remark) {
        this.communication_remark = communication_remark;
    }

    public String getKnowledge_remark() {
        return knowledge_remark;
    }

    public void setKnowledge_remark(String knowledge_remark) {
        this.knowledge_remark = knowledge_remark;
    }

    public String getAggregate_remark() {
        return aggregate_remark;
    }

    public void setAggregate_remark(String aggregate_remark) {
        this.aggregate_remark = aggregate_remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreator_stamp() {
        return creator_stamp;
    }

    public void setCreator_stamp(Date creator_stamp) {
        this.creator_stamp = creator_stamp;
    }

    public String getCreator_user() {
        return creator_user;
    }

    public void setCreator_user(String creator_user) {
        this.creator_user = creator_user;
    }
}