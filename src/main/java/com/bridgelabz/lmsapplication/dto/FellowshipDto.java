package com.bridgelabz.lmsapplication.dto;

import java.util.Date;

public class FellowshipDto {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getVerifyBirthDate() {
        return verifyBirthDate;
    }

    public void setVerifyBirthDate(String verifyBirthDate) {
        this.verifyBirthDate = verifyBirthDate;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentOccupation() {
        return parentOccupation;
    }

    public void setParentOccupation(String parentOccupation) {
        this.parentOccupation = parentOccupation;
    }

    public int getParentMobileNumber() {
        return parentMobileNumber;
    }

    public void setParentMobileNumber(int parentMobileNumber) {
        this.parentMobileNumber = parentMobileNumber;
    }

    public int getParentAnnualSalary() {
        return parentAnnualSalary;
    }

    public void setParentAnnualSalary(int parentAnnualSalary) {
        this.parentAnnualSalary = parentAnnualSalary;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getCandidateStatus() {
        return candidateStatus;
    }

    public void setCandidateStatus(String candidateStatus) {
        this.candidateStatus = candidateStatus;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
