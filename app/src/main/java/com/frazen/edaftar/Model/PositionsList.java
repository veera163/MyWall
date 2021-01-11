package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PositionsList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("listName")
    @Expose
    private String listName;
    @SerializedName("listItem")
    @Expose
    private String listItem;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListItem() {
        return listItem;
    }

    public void setListItem(String listItem) {
        this.listItem = listItem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
