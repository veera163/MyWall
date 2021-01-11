package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewEndorsementDetails {

    @SerializedName("endorsement")
    @Expose
    private EndorsementDetails endorsement;
    @SerializedName("fileSerialNo")
    @Expose
    private Integer fileSerialNo;
    @SerializedName("uploadedFiles")
    @Expose
    private List<UploadedFile> uploadedFiles = null;
    @SerializedName("attachments")
    @Expose
    private List<Object> attachments = null;
    @SerializedName("endorsementRemarks")
    @Expose
    private List<Object> endorsementRemarks = null;

    public EndorsementDetails getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(EndorsementDetails endorsement) {
        this.endorsement = endorsement;
    }

    public Integer getFileSerialNo() {
        return fileSerialNo;
    }

    public void setFileSerialNo(Integer fileSerialNo) {
        this.fileSerialNo = fileSerialNo;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    public List<Object> getEndorsementRemarks() {
        return endorsementRemarks;
    }

    public void setEndorsementRemarks(List<Object> endorsementRemarks) {
        this.endorsementRemarks = endorsementRemarks;
    }

    @Override
    public String toString() {
        return "ViewEndorsementDetails{" +
                "endorsement=" + endorsement +
                ", fileSerialNo=" + fileSerialNo +
                ", uploadedFiles=" + uploadedFiles +
                ", attachments=" + attachments +
                ", endorsementRemarks=" + endorsementRemarks +
                '}';
    }
}
