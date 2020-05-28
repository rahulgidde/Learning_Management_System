package com.bridgelabz.lmsapplication.dto;

import java.util.Date;

public class PersonalInfoDto {
    private Date birthDate;
    private String verifyBirthDate;
    private String parentName;
    private String parentOccupation;
    private int parentMobileNumber;
    private int parentAnnualSalary;
    private String localAddress;
    private String permanentAddress;
    private String photoPath;

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
}
