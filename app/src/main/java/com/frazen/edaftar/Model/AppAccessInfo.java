package com.frazen.edaftar.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramakanth on 20-12-2019.
 */

public class AppAccessInfo implements Parcelable {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("expires_in")
    private String expiresIn;
    private String scope;

    public AppAccessInfo(Parcel in){
        accessToken = in.readString();
        tokenType = in.readString();
        refreshToken=in.readString();
        expiresIn = in.readString();
        scope = in.readString();
    }

    public String getAccessToken() {
       return accessToken;
    }

    public String getTokenType() {
        if(!Character.isUpperCase(tokenType.charAt(0))){
            tokenType = Character.toString(tokenType.charAt(0)).toUpperCase()+tokenType.substring(1);
        }
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public String getScope() {
        return scope;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accessToken);
        dest.writeString(tokenType);
        dest.writeString(refreshToken);
        dest.writeString(expiresIn);
        dest.writeString(scope);
    }

    public static final Parcelable.Creator<AppAccessInfo> CREATOR = new Parcelable.Creator<AppAccessInfo>(){
        public AppAccessInfo createFromParcel(Parcel in){
            return new AppAccessInfo(in);
        }

        public AppAccessInfo[] newArray(int size){
            return new AppAccessInfo[size];
        }
    };
}
