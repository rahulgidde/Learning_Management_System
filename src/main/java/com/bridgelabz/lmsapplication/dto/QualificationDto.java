package com.bridgelabz.lmsapplication.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QualificationDto {
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
}
