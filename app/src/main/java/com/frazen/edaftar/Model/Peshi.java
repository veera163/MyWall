package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Peshi {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fileNo")
    @Expose
    private String fileNo;
    @SerializedName("fileNoString")
    @Expose
    private String fileNoString;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("assignedTo")
    @Expose
    private String assignedTo;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("receivedFrom")
    @Expose
    private String receivedFrom;
    @SerializedName("receivedDate")
    @Expose
    private String receivedDate;
    @SerializedName("returnedTo")
    @Expose
    private String returnedTo;
    @SerializedName("returnedDate")
    @Expose
    private String returnedDate;
    @SerializedName("ministerRemarks")
    @Expose
    private String ministerRemarks;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("fileLocation")
    @Expose
    private String fileLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileNoString() {
        return fileNoString;
    }

    public void setFileNoString(String fileNoString) {
        this.fileNoString = fileNoString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getReceivedFrom() {
        return receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom) {
        this.receivedFrom = receivedFrom;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getReturnedTo() {
        return returnedTo;
    }

    public void setReturnedTo(String returnedTo) {
        this.returnedTo = returnedTo;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getMinisterRemarks() {
        return ministerRemarks;
    }

    public void setMinisterRemarks(String ministerRemarks) {
        this.ministerRemarks = ministerRemarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @NonNull
    @Override
    public String toString() {
        return "Peshi{" +
                "id='" + id + '\'' +
                ", fileNo='" + fileNo + '\'' +
                ", fileNoString='" + fileNoString + '\'' +
                ", status='" + status + '\'' +
                ", subject='" + subject + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", department='" + department + '\'' +
                ", receivedFrom='" + receivedFrom + '\'' +
                ", receivedDate='" + receivedDate + '\'' +
                ", returnedTo='" + returnedTo + '\'' +
                ", returnedDate='" + returnedDate + '\'' +
                ", ministerRemarks='" + ministerRemarks + '\'' +
                ", remarks='" + remarks + '\'' +
                ", fileLocation='" + fileLocation + '\'' +
                '}';
    }
}
