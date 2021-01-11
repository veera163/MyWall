package com.frazen.edaftar.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EndorsementDetails implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("serialNo")
    @Expose
    private Integer serialNo;
    @SerializedName("endorsementNo")
    @Expose
    private String endorsementNo="";
    @SerializedName("status")
    @Expose
    private String status="";
    @SerializedName("petitionerCategory")
    @Expose
    private String petitionerCategory="";
    @SerializedName("petitionerName")
    @Expose
    private String petitionerName="";
    @SerializedName("petitionerPortFolio")
    @Expose
    private String petitionerPortFolio;
    @SerializedName("constituency")
    @Expose
    private String constituency;
    @SerializedName("petitionerMobile")
    @Expose
    private String petitionerMobile;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("receivedDate")
    @Expose
    private String receivedDate;
    @SerializedName("referredBy")
    @Expose
    private String referredBy;
    @SerializedName("petitionDetails")
    @Expose
    private String petitionDetails;
    @SerializedName("endorsedOfficer")
    @Expose
    private String endorsedOfficer;
    @SerializedName("department")
    @Expose
    private String department="";
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("hamlet")
    @Expose
    private String hamlet;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("mandal")
    @Expose
    private String mandal;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("replyReceived")
    @Expose
    private String replyReceived;
    @SerializedName("refByPhone")
    @Expose
    private String refByPhone;
    @SerializedName("hasAttachment")
    @Expose
    private Boolean hasAttachment;
    @SerializedName("hasFiles")
    @Expose
    private Boolean hasFiles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getEndorsementNo() {
        return endorsementNo;
    }

    public void setEndorsementNo(String endorsementNo) {
        this.endorsementNo = endorsementNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPetitionerPortFolio() {
        return petitionerPortFolio;
    }

    public void setPetitionerPortFolio(String petitionerPortFolio) {
        this.petitionerPortFolio = petitionerPortFolio;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
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

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(Boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public Boolean getHasFiles() {
        return hasFiles;
    }

    public void setHasFiles(Boolean hasFiles) {
        this.hasFiles = hasFiles;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", serialNo=" + serialNo +
                ", endorsementNo='" + endorsementNo + '\'' +
                ", status='" + status + '\'' +
                ", petitionerCategory='" + petitionerCategory + '\'' +
                ", petitionerName='" + petitionerName + '\'' +
                ", petitionerPortFolio='" + petitionerPortFolio + '\'' +
                ", constituency='" + constituency + '\'' +
                ", petitionerMobile='" + petitionerMobile + '\'' +
                ", createDate='" + createDate + '\'' +
                ", receivedDate='" + receivedDate + '\'' +
                ", referredBy='" + referredBy + '\'' +
                ", petitionDetails='" + petitionDetails + '\'' +
                ", endorsedOfficer='" + endorsedOfficer + '\'' +
                ", department='" + department + '\'' +
                ", address='" + address + '\'' +
                ", hamlet='" + hamlet + '\'' +
                ", village='" + village + '\'' +
                ", mandal='" + mandal + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", remarks='" + remarks + '\'' +
                ", replyReceived='" + replyReceived + '\'' +
                ", refByPhone='" + refByPhone + '\'' +
                ", hasAttachment=" + hasAttachment +
                ", hasFiles=" + hasFiles +
                '}';
    }
}
