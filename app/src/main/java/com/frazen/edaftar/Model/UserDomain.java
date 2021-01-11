package com.frazen.edaftar.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDomain {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("policies")
    @Expose
    private List<Policy> policies = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserDomain{" +
                "user=" + user +
                ", policies=" + policies +
                '}';
    }
}
