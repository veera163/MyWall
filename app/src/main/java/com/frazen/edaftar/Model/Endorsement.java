package com.frazen.edaftar.Model;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Endorsement implements Serializable {

    String serialNo;
    String endorsementNo;
    String petitionerCategory;
    String petitionerName;
    String petitionerMobile;
    String createDate;
    String referredBy;
    String petitionDetails;
    String endorsedOfficer;
    String department;
    String address;
    String hamlet;
    String village;
    String mandal;
    String district;
    String remarks;
    String replyReceived;
    String refByPhone;
    String status;
    String username;
    String actTime;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getEndorsementNo() {
        return endorsementNo;
    }

    public void setEndorsementNo(String endorsementNo) {
        this.endorsementNo = endorsementNo;
    }

    public String getPetitionerCategory() {
        return petitionerCategory;
    }

    public void setPetitionerCategory(String petitionerCategory) {
        this.petitionerCategory = petitionerCategory;
    }

    public String getPetitionerName() {
        return petitionerName;
    }

    public void setPetitionerName(String petitionerName) {
        this.petitionerName = petitionerName;
    }

    public String getPetitionerMobile() {
        return petitionerMobile;
    }

    public void setPetitionerMobile(String petitionerMobile) {
        this.petitionerMobile = petitionerMobile;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getPetitionDetails() {
        return petitionDetails;
    }

    public void setPetitionDetails(String petitionDetails) {
        this.petitionDetails = petitionDetails;
    }

    public String getEndorsedOfficer() {
        return endorsedOfficer;
    }

    public void setEndorsedOfficer(String endorsedOfficer) {
        this.endorsedOfficer = endorsedOfficer;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHamlet() {
        return hamlet;
    }

    public void setHamlet(String hamlet) {
        this.hamlet = hamlet;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReplyReceived() {
        return replyReceived;
    }

    public void setReplyReceived(String replyReceived) {
        this.replyReceived = replyReceived;
    }

    public String getRefByPhone() {
        return refByPhone;
    }

    public void setRefByPhone(String refByPhone) {
        this.refByPhone = refByPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "Endorsement{" +
                ", serialNo='" + serialNo + '\'' +
                ", endorsementNo='" + endorsementNo + '\'' +
                ", petitionerCategory='" + petitionerCategory + '\'' +
                ", petitionerName='" + petitionerName + '\'' +
                ", petitionerMobile='" + petitionerMobile + '\'' +
                ", createDate='" + createDate + '\'' +
                ", referredBy='" + referredBy + '\'' +
                ", petitionDetails='" + petitionDetails + '\'' +
                ", endorsedOfficer='" + endorsedOfficer + '\'' +
                ", department='" + department + '\'' +
                ", address='" + address + '\'' +
                ", hamlet='" + hamlet + '\'' +
                ", village='" + village + '\'' +
                ", mandal='" + mandal + '\'' +
                ", district='" + district + '\'' +
                ", remarks='" + remarks + '\'' +
                ", replyReceived='" + replyReceived + '\'' +
                ", refByPhone='" + refByPhone + '\'' +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", actTime='" + actTime + '\'' +
                '}';
    }
}
