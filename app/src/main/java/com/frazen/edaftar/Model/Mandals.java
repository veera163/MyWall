package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mandals {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mandalName")
    @Expose
    private String mandalName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
    }

}
