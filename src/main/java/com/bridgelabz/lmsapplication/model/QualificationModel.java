package com.bridgelabz.lmsapplication.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "candidate_qualification")
@Table(name = "candidate_qualification")
public class QualificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long candidateId;
    private String diploma;
    private String degreeName;
    private String isDegreeNameVerified;
    private String employeeDiscipline;
    private String isEmployeeDisciplinedVerified;
    private long passingYear;
    private String isPassingYearVerified;
    private double aggregatePercentage;
    private double finalYearPercentage;
    private String isFinalYearPercentageVerified;
    private String trainingInstitute;
    private String isTrainingInstituteVerified;
    private long trainingDurationMonth;
    private String isTrainingDurationMonthVerified;
    private String otherTraining;
    private String isOtherTrainingVerified;
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

