package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;

public class InitMeetingDetails {

    @SerializedName("meetingInfo")
    @Expose
    private MeetingInfo meetingInfo;
    @SerializedName("typesList")
    @Expose
    private List<TypesList> typesList = null;
    @SerializedName("statusList")
    @Expose
    private List<StatusList> statusList = null;
    @SerializedName("momStatusList")
    @Expose
    private List<MomStatusList> momStatusList = null;
    @SerializedName("meetingIdIdx")
    @Expose
    private Integer meetingIdIdx;

    public MeetingInfo getMeetingInfo() {
        return meetingInfo;
    }

    public void setMeetingInfo(MeetingInfo meetingInfo) {
        this.meetingInfo = meetingInfo;
    }

    public List<TypesList> getTypesList() {
        return typesList;
    }

    public void setTypesList(List<TypesList> typesList) {
        this.typesList = typesList;
    }

    public List<StatusList> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusList> statusList) {
        this.statusList = statusList;
    }

    public List<MomStatusList> getMomStatusList() {
        return momStatusList;
    }

    public void setMomStatusList(List<MomStatusList> momStatusList) {
        this.momStatusList = momStatusList;
    }

    public Integer getMeetingIdIdx() {
        return meetingIdIdx;
    }

    public void setMeetingIdIdx(Integer meetingIdIdx) {
        this.meetingIdIdx = meetingIdIdx;
    }

    @NonNull
    @Override
    public String toString() {
        return "InitMeetingDetails{" +
                "meetingInfo=" + meetingInfo +
                ", typesList=" + typesList +
                ", statusList=" + statusList +
                ", momStatusList=" + momStatusList +
                ", meetingIdIdx=" + meetingIdIdx +
                '}';
    }
}
