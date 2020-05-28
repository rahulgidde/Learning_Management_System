package com.bridgelabz.lmsapplication.dto;

import java.util.Date;

public class QualificationDto {
    private Long id;
    private Long candidateId;
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
    private Date creatorStamp;
    private String creatorUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getIsDegreeNameVerified() {
        return isDegreeNameVerified;
    }

    public void setIsDegreeNameVerified(String isDegreeNameVerified) {
        this.isDegreeNameVerified = isDegreeNameVerified;
    }

    public String getEmployeeDescipline() {
        return employeeDiscipline;
    }

    public void setEmployeeDescipline(String employeeDiscipline) {
        this.employeeDiscipline = employeeDiscipline;
    }

    public String getIsEmployeeDisciplinedVerified() {
        return isEmployeeDisciplinedVerified;
    }

    public void setIsEmployeeDisciplinedVerified(String isEmployeeDisciplinedVerified) {
        this.isEmployeeDisciplinedVerified = isEmployeeDisciplinedVerified;
    }

    public long getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(long passingYear) {
        this.passingYear = passingYear;
    }

    public String getIsPassingYearVerified() {
        return isPassingYearVerified;
    }

    public void setIsPassingYearVerified(String isPassingYearVerified) {
        this.isPassingYearVerified = isPassingYearVerified;
    }

    public double getAggregatePercentage() {
        return aggregatePercentage;
    }

    public void setAggregatePercentage(double aggregatePercentage) {
        this.aggregatePercentage = aggregatePercentage;
    }

    public double getFinalYearPercentage() {
        return finalYearPercentage;
    }

    public void setFinalYearPercentage(double finalYearPercentage) {
        this.finalYearPercentage = finalYearPercentage;
    }

    public String getIsFinalYearPercentageVerified() {
        return isFinalYearPercentageVerified;
    }

    public void setIsFinalYearPercentageVerified(String isFinalYearPercentageVerified) {
        this.isFinalYearPercentageVerified = isFinalYearPercentageVerified;
    }

    public String getTrainingInstitute() {
        return trainingInstitute;
    }

    public void setTrainingInstitute(String trainingInstitute) {
        this.trainingInstitute = trainingInstitute;
    }

    public String getIsTrainingInstituteVerified() {
        return isTrainingInstituteVerified;
    }

    public void setIsTrainingInstituteVerified(String isTrainingInstituteVerified) {
        this.isTrainingInstituteVerified = isTrainingInstituteVerified;
    }

    public long getTrainingDurationMonth() {
        return trainingDurationMonth;
    }

    public void setTrainingDurationMonth(long trainingDurationMonth) {
        this.trainingDurationMonth = trainingDurationMonth;
    }

    public String getIsTrainingDurationMonthVerified() {
        return isTrainingDurationMonthVerified;
    }

    public void setIsTrainingDurationMonthVerified(String isTrainingDurationMonthVerified) {
        this.isTrainingDurationMonthVerified = isTrainingDurationMonthVerified;
    }

    public String getOtherTraining() {
        return otherTraining;
    }

    public void setOtherTraining(String otherTraining) {
        this.otherTraining = otherTraining;
    }

    public String getIsOtherTrainingVerified() {
        return isOtherTrainingVerified;
    }

    public void setIsOtherTrainingVerified(String isOtherTrainingVerified) {
        this.isOtherTrainingVerified = isOtherTrainingVerified;
    }

    public Date getCreatorStamp() {
        return creatorStamp;
    }

    public void setCreatorStamp(Date creatorStamp) {
        this.creatorStamp = creatorStamp;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }
}
