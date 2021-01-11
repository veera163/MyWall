package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment {

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
    @SerializedName("objId")
    @Expose
    private String objId;

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

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }
}
