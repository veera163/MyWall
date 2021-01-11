package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class ViewLetterAndNotes {

    @SerializedName("letter")
    @Expose
    private LettersAndNotes letter;

    @SerializedName("uploadedFiles")
    @Expose
    private List<UploadedFile> uploadedFiles = null;
      @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = null;
    @SerializedName("serialNoIdx")
    @Expose
    private Integer serialNoIdx;

    public LettersAndNotes getLetter() {
        return letter;
    }

    public void setLetter(LettersAndNotes letter) {
        this.letter = letter;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Integer getSerialNoIdx() {
        return serialNoIdx;
    }

    public void setSerialNoIdx(Integer serialNoIdx) {
        this.serialNoIdx = serialNoIdx;
    }

    @NonNull
    @Override
    public String toString() {
        return "ViewLetterAndNotes{" +
                "letter=" + letter +
                ", uploadedFiles=" + uploadedFiles +
                ", attachments=" + attachments +
                ", serialNoIdx=" + serialNoIdx +
                '}';
    }
}
