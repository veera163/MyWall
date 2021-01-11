package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class UploadedFile {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("filePath")
    @Expose
    private String filePath;
    @SerializedName("uploadedDate")
    @Expose
    private String uploadedDate;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("tgId")
    @Expose
    private String tgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTgId() {
        return tgId;
    }

    public void setTgId(String tgId) {
        this.tgId = tgId;
    }

    @NonNull
    @Override
    public String toString() {
        return "UploadedFile{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", uploadedDate='" + uploadedDate + '\'' +
                ", userName='" + userName + '\'' +
                ", tgId='" + tgId + '\'' +
                '}';
    }
}

