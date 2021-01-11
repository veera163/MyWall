package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class ViewPeshi {

    @SerializedName("peshiFiles")
    @Expose
    private Peshi peshiFiles;
    @SerializedName("fileNo")
    @Expose
    private Integer fileNo;

    @SerializedName("uploadedFiles")
    @Expose
    private List<UploadedFile> uploadedFiles = null;

    public Peshi getPeshiFiles() {
        return peshiFiles;
    }

    public void setPeshiFiles(Peshi peshiFiles) {
        this.peshiFiles = peshiFiles;
    }

    public Integer getFileNo() {
        return fileNo;
    }

    public void setFileNo(Integer fileNo) {
        this.fileNo = fileNo;
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
        return "ViewPeshi{" +
                "peshiFiles=" + peshiFiles +
                ", fileNo=" + fileNo +
                ", uploadedFiles=" + uploadedFiles +
                '}';
    }
}
