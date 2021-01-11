package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class ViewReimbrusementAndLoc {

    @SerializedName("reimbursement")
    @Expose
    private ReimbrusementAndLoc reimbrusementAndLoc;
    @SerializedName("sanctionedDetails")
    @Expose
    private List<SanctionedDetail> sanctionedDetails = null;
    @SerializedName("fileIdxNo")
    @Expose
    private Integer fileIdxNo;
    @SerializedName("uploadedFiles")
    @Expose
    private List<UploadedFile> uploadedFiles = null;

    public ReimbrusementAndLoc getReimbrusementAndLoc() {
        return reimbrusementAndLoc;
    }

    public void setReimbrusementAndLoc(ReimbrusementAndLoc reimbrusementAndLoc) {
        this.reimbrusementAndLoc = reimbrusementAndLoc;
    }

    public List<SanctionedDetail> getSanctionedDetails() {
        return sanctionedDetails;
    }

    public void setSanctionedDetails(List<SanctionedDetail> sanctionedDetails) {
        this.sanctionedDetails = sanctionedDetails;
    }

    public Integer getFileIdxNo() {
        return fileIdxNo;
    }

    public void setFileIdxNo(Integer fileIdxNo) {
        this.fileIdxNo = fileIdxNo;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    @NonNull
    @Override
    public String toString() {
        return "ViewReimbrusementAndLoc{" +
                "reimbrusementAndLoc=" + reimbrusementAndLoc +
                ", sanctionedDetails=" + sanctionedDetails +
                ", fileIdxNo=" + fileIdxNo +
                ", uploadedFiles=" + uploadedFiles +
                '}';
    }
}
