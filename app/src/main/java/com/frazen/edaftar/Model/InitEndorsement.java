package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InitEndorsement {

    @SerializedName("endorsement")
    @Expose
    private Endorsement endorsement;
    @SerializedName("status")
    @Expose
    private List<Status> status = null;
    @SerializedName("districts")
    @Expose
    private List<District> districts = null;
    @SerializedName("mandals")
    @Expose
    private List<Mandals> mandals = null;
    @SerializedName("villages")
    @Expose
    private List<Villages> villages = null;
    @SerializedName("activities")
    @Expose
    private List<String> activities = null;
    @SerializedName("positionsList")
    @Expose
    private List<PositionsList> positionsList = null;
    @SerializedName("namesByPositionList")
    @Expose
    private String namesByPositionList;
    @SerializedName("fileSerialNo")
    @Expose
    private Integer fileSerialNo;
    @SerializedName("uploadedFiles")
    @Expose
    private List<String> uploadedFiles = null;
    @SerializedName("attachments")
    @Expose
    private List<String> attachments = null;
    @SerializedName("endorsementRemarks")
    @Expose
    private List<String> endorsementRemarks = null;
    @SerializedName("deptsList")
    @Expose
    private List<DeptsList> deptsList = null;


    public List<DeptsList> getDeptsList() {
        return deptsList;
    }

    public void setDeptsList(List<DeptsList> deptsList) {
        this.deptsList = deptsList;
    }

    public Endorsement getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(Endorsement endorsement) {
        this.endorsement = endorsement;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Mandals> getMandals() {
        return mandals;
    }

    public void setMandals(List<Mandals> mandals) {
        this.mandals = mandals;
    }

    public List<Villages> getVillages() {
        return villages;
    }

    public void setVillages(List<Villages> villages) {
        this.villages = villages;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public List<PositionsList> getPositionsList() {
        return positionsList;
    }

    public void setPositionsList(List<PositionsList> positionsList) {
        this.positionsList = positionsList;
    }

    public String getNamesByPositionList() {
        return namesByPositionList;
    }

    public void setNamesByPositionList(String namesByPositionList) {
        this.namesByPositionList = namesByPositionList;
    }

    public Integer getFileSerialNo() {
        return fileSerialNo;
    }

    public void setFileSerialNo(Integer fileSerialNo) {
        this.fileSerialNo = fileSerialNo;
    }

    public List<String> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<String> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public List<String> getEndorsementRemarks() {
        return endorsementRemarks;
    }

    public void setEndorsementRemarks(List<String> endorsementRemarks) {
        this.endorsementRemarks = endorsementRemarks;
    }

    @Override
    public String toString() {
        return "CreateEndrosement{" +
                "endorsement=" + endorsement +
                ", status=" + status +
                ", districts=" + districts +
                ", mandals=" + mandals +
                ", villages=" + villages +
                ", activities=" + activities +
                ", positionsList=" + positionsList +
                ", namesByPositionList=" + namesByPositionList +
                ", fileSerialNo=" + fileSerialNo +
                ", uploadedFiles=" + uploadedFiles +
                ", attachments=" + attachments +
                ", endorsementRemarks=" + endorsementRemarks +
                '}';
    }
}
