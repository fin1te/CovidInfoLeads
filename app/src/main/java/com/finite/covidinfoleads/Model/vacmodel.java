package com.finite.covidinfoleads.Model;

public class vacmodel {
    String centName,centAddress,vacTime,vacName,vacrate,minAge,fromTime,toTime;
    public vacmodel() {

    }

    public vacmodel(String centName, String centAddress, String vacTime, String vacName, String vacrate, String minAge, String fromTime, String toTime) {
        this.centName = centName;
        this.centAddress = centAddress;
        this.vacTime = vacTime;
        this.vacName = vacName;
        this.vacrate = vacrate;
        this.minAge = minAge;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
    String centerName,  centerAddress,  centerFromTime,  centerToTime, feeType,  vaccineName;
    int ageLimit,dose1,dose2;

    public vacmodel(String centerName, String centerAddress, String centerFromTime, String centerToTime, String feeType, int ageLimit, String vaccineName, int dose1, int dose2) {
        this.centerName = centerName;
        this.centAddress = centerAddress;
        this.centerFromTime = centerFromTime;
        this.centerToTime = centerToTime;
        this.feeType = feeType;
        this.ageLimit = ageLimit;
        this.vaccineName = vaccineName;
        this.dose1 = dose1;
        this.dose2 = dose2;
    }

    public int getDose1() {
        return dose1;
    }

    public void setDose1(int dose1) {
        this.dose1 = dose1;
    }

    public int getDose2() {
        return dose2;
    }

    public void setDose2(int dose2) {
        this.dose2 = dose2;
    }

    public String getCentName() {
        return centName;
    }

    public void setCentName(String centName) {
        this.centName = centName;
    }

    public String getCentAddress() {
        return centAddress;
    }

    public void setCentAddress(String centAddress) {
        this.centAddress = centAddress;
    }

    public String getVacTime() {
        return vacTime;
    }

    public void setVacTime(String vacTime) {
        this.vacTime = vacTime;
    }

    public String getVacName() {
        return vacName;
    }

    public void setVacName(String vacName) {
        this.vacName = vacName;
    }

    public String getVacrate() {
        return vacrate;
    }

    public void setVacrate(String vacrate) {
        this.vacrate = vacrate;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getCenterFromTime() {
        return centerFromTime;
    }

    public void setCenterFromTime(String centerFromTime) {
        this.centerFromTime = centerFromTime;
    }

    public String getCenterToTime() {
        return centerToTime;
    }

    public void setCenterToTime(String centerToTime) {
        this.centerToTime = centerToTime;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}
