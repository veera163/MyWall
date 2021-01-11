package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class LettersAndNotes  {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("serialNo")
    @Expose
    private Integer serialNo;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("lonNo")
    @Expose
    private String lonNo;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("petitionerPosition")
    @Expose
    private String petitionerPosition;
    @SerializedName("petitionerName")
    @Expose
    private String petitionerName;
    @SerializedName("petitionerPortFolio")
    @Expose
    private Object petitionerPortFolio;
    @SerializedName("constituency")
    @Expose
    private String constituency;
    @SerializedName("petitionerMobile")
    @Expose
    private String petitionerMobile;
    @SerializedName("referenceName")
    @Expose
    private String referenceName;
    @SerializedName("referenceMobile")
    @Expose
    private String referenceMobile;
    @SerializedName("petitionDetails")
    @Expose
    private String petitionDetails;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("sentToOfficer")
    @Expose
    private String sentToOfficer;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("location")
    @Expose
    private String location;
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
    @SerializedName("recievedDate")
    @Expose
    private String recievedDate;
    @SerializedName("dispatchMode")
    @Expose
    private String dispatchMode;
    @SerializedName("dispatchedDate")
    @Expose
    private String dispatchedDate;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("replyRecieved")
    @Expose
    private String replyRecieved;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLonNo() {
        return lonNo;
    }

    public void setLonNo(String lonNo) {
        this.lonNo = lonNo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPetitionerPosition() {
        return petitionerPosition;
    }

    public void setPetitionerPosition(String petitionerPosition) {
        this.petitionerPosition = petitionerPosition;
    }

    public String getPetitionerName() {
        return petitionerName;
    }

    public void setPetitionerName(String petitionerName) {
        this.petitionerName = petitionerName;
    }

    public Object getPetitionerPortFolio() {
        return petitionerPortFolio;
    }

    public void setPetitionerPortFolio(Object petitionerPortFolio) {
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

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferenceMobile() {
        return referenceMobile;
    }

    public void setReferenceMobile(String referenceMobile) {
        this.referenceMobile = referenceMobile;
    }

    public String getPetitionDetails() {
        return petitionDetails;
    }

    public void setPetitionDetails(String petitionDetails) {
        this.petitionDetails = petitionDetails;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSentToOfficer() {
        return sentToOfficer;
    }

    public void setSentToOfficer(String sentToOfficer) {
        this.sentToOfficer = sentToOfficer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getRecievedDate() {
        return recievedDate;
    }

    public void setRecievedDate(String recievedDate) {
        this.recievedDate = recievedDate;
    }

    public String getDispatchMode() {
        return dispatchMode;
    }

    public void setDispatchMode(String dispatchMode) {
        this.dispatchMode = dispatchMode;
    }

    public String getDispatchedDate() {
        return dispatchedDate;
    }

    public void setDispatchedDate(String dispatchedDate) {
        this.dispatchedDate = dispatchedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReplyRecieved() {
        return replyRecieved;
    }

    public void setReplyRecieved(String replyRecieved) {
        this.replyRecieved = replyRecieved;
    }

    @NonNull
    @Override
    public String toString() {
        return "LettersAndNotes{" +
                "id='" + id + '\'' +
                ", serialNo=" + serialNo +
                ", type='" + type + '\'' +
                ", lonNo='" + lonNo + '\'' +
                ", createDate='" + createDate + '\'' +
                ", petitionerPosition='" + petitionerPosition + '\'' +
                ", petitionerName='" + petitionerName + '\'' +
                ", petitionerPortFolio=" + petitionerPortFolio +
                ", constituency='" + constituency + '\'' +
                ", petitionerMobile='" + petitionerMobile + '\'' +
                ", referenceName='" + referenceName + '\'' +
                ", referenceMobile='" + referenceMobile + '\'' +
                ", petitionDetails='" + petitionDetails + '\'' +
                ", department='" + department + '\'' +
                ", sentToOfficer='" + sentToOfficer + '\'' +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", hamlet='" + hamlet + '\'' +
                ", village='" + village + '\'' +
                ", mandal='" + mandal + '\'' +
                ", district='" + district + '\'' +
                ", recievedDate='" + recievedDate + '\'' +
                ", dispatchMode='" + dispatchMode + '\'' +
                ", dispatchedDate='" + dispatchedDate + '\'' +
                ", remarks='" + remarks + '\'' +
                ", replyRecieved='" + replyRecieved + '\'' +
                '}';
    }
}
