package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class UpdateEndorse {

    @SerializedName("endorsement")
    @Expose
    private EndorsementDetails endorsement;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("actTime")
    @Expose
    private String actTime;

    public EndorsementDetails getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(EndorsementDetails endorsement) {
        this.endorsement = endorsement;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "endorsement=" + endorsement +
                ", username='" + username + '\'' +
                ", actTime='" + actTime + '\'' +
                '}';
    }
}
