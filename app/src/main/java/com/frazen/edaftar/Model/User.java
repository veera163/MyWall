package com.frazen.edaftar.Model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
     @SerializedName("id")
     @Expose
     private String id;
     @SerializedName("username")
     @Expose
     private String username;
     @SerializedName("password")
     @Expose
     private String password;
     @SerializedName("firstTimeLogin")
     @Expose
     private Boolean firstTimeLogin;
     @SerializedName("active")
     @Expose
     private Boolean active;
     @SerializedName("roles")
     @Expose
     private List<String> roles = null;

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     public Boolean getFirstTimeLogin() {
         return firstTimeLogin;
     }

     public void setFirstTimeLogin(Boolean firstTimeLogin) {
         this.firstTimeLogin = firstTimeLogin;
     }

     public Boolean getActive() {
         return active;
     }

     public void setActive(Boolean active) {
         this.active = active;
     }

     public List<String> getRoles() {
         return roles;
     }

     public void setRoles(List<String> roles) {
         this.roles = roles;
     }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstTimeLogin=" + firstTimeLogin +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
