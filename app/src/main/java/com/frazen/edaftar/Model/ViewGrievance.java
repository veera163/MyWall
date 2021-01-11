package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class ViewGrievance {
    @SerializedName("twitterGrievance")
    @Expose
    private Grievance grievance;
    @SerializedName("uploadedFiles")
    @Expose
    private List<UploadedFile> uploadedFiles = null;
    @SerializedName("username")
    @Expose
    private String username;

    public Grievance getGrievance() {
        return grievance;
    }

    public void setGrievance(Grievance grievance) {
        this.grievance = grievance;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NonNull
    @Override
    public String toString() {
        return "ViewGrievance{" +
                "grievance=" + grievance +
                ", uploadedFiles=" + uploadedFiles +
                ", username='" + username + '\'' +
                '}';
    }
}
