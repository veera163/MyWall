package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class MeetingInfo {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("meetingId")
    @Expose
    private Integer meetingId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("agenda")
    @Expose
    private String agenda;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("momStatus")
    @Expose
    private String momStatus;
    @SerializedName("participants")
    @Expose
    private List<Participant> participants;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMomStatus() {
        return momStatus;
    }

    public void setMomStatus(String momStatus) {
        this.momStatus = momStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @NonNull
    @Override
    public String toString() {
        return "MeetingInfo{" +
                "id='" + id + '\'' +
                ", meetingId=" + meetingId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", agenda='" + agenda + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", location='" + location + '\'' +
                ", department='" + department + '\'' +
                ", momStatus='" + momStatus + '\'' +
                ", participants=" + participants +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
