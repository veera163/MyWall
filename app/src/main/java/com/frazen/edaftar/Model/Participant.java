package com.frazen.edaftar.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Participant {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("dept")
    @Expose
    private String dept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @NonNull
    @Override
    public String toString() {

        return "Participant{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
