package com.bridgelabz.lmsapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "candidate_bank_details")
@Table
public class BankDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long candidateId;
    private String name;
    private int accountNumber;
    private String accountNumberVerified;
    private String ifscCode;
    private String ifscCodeVerified;
    private int panNumber;
    private int addhaarNumber;
    private String addhaarNumberVerified;
    private LocalDateTime creatorStamp;
    private long creatorUser;

    public LocalDateTime getCreatorStamp() {
        return creatorStamp;
    }

    public void setCreatorStamp(LocalDateTime creatorStamp) {
        this.creatorStamp = LocalDateTime.now();
    }

    public long getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(long creatorUser) {
        this.creatorUser = this.candidateId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateId", referencedColumnName = "id", insertable = false, updatable = false)
    private FellowshipModel fellowshipModel;
}
