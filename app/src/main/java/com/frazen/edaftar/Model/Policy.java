package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Policy {
     @SerializedName("id")
     @Expose
     private String id;
     @SerializedName("String")
     @Expose
     private String String;
     @SerializedName("canCreate")
     @Expose
     private Boolean canCreate;
     @SerializedName("canView")
     @Expose
     private Boolean canView;
     @SerializedName("roleId")
     @Expose
     private String roleId;
     @SerializedName("editable")
     @Expose
     private Boolean editable;

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getString() {
         return String;
     }

     public void setString(String String) {
         this.String = String;
     }

     public Boolean getCanCreate() {
         return canCreate;
     }

     public void setCanCreate(Boolean canCreate) {
         this.canCreate = canCreate;
     }

     public Boolean getCanView() {
         return canView;
     }

     public void setCanView(Boolean canView) {
         this.canView = canView;
     }

     public String getRoleId() {
         return roleId;
     }

     public void setRoleId(String roleId) {
         this.roleId = roleId;
     }

     public Boolean getEditable() {
         return editable;
     }

     public void setEditable(Boolean editable) {
         this.editable = editable;
     }
}
