package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class SanctionedDetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sanctionedAmt")
    @Expose
    private double sanctionedAmt;
    @SerializedName("sactinedAmtInWords")
    @Expose
    private String sactinedAmtInWords;
    @SerializedName("sanctionedDate")
    @Expose
    private String sanctionedDate;
    @SerializedName("sanctionedNo")
    @Expose
    private String sanctionedNo;
    @SerializedName("chequeNo")
    @Expose
    private String chequeNo;
    @SerializedName("chequeDate")
    @Expose
    private String chequeDate;
    @SerializedName("sanctionRemarks")
    @Expose
    private String sanctionRemarks;
    @SerializedName("additionalFund")
    @Expose
    private String additionalFund;
    @SerializedName("cmrfObjId")
    @Expose
    private String cmrfObjId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSanctionedAmt() {
        return sanctionedAmt;
    }

    public void setSanctionedAmt(double sanctionedAmt) {
        this.sanctionedAmt = sanctionedAmt;
    }

    public String getSactinedAmtInWords() {
        return sactinedAmtInWords;
    }

    public void setSactinedAmtInWords(String sactinedAmtInWords) {
        this.sactinedAmtInWords = sactinedAmtInWords;
    }

    public String getSanctionedDate() {
        return sanctionedDate;
    }

    public void setSanctionedDate(String sanctionedDate) {
        this.sanctionedDate = sanctionedDate;
    }

    public String getSanctionedNo() {
        return sanctionedNo;
    }

    public void setSanctionedNo(String sanctionedNo) {
        this.sanctionedNo = sanctionedNo;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(String chequeDate) {
        this.chequeDate = chequeDate;
    }

    public String getSanctionRemarks() {
        return sanctionRemarks;
    }

    public void setSanctionRemarks(String sanctionRemarks) {
        this.sanctionRemarks = sanctionRemarks;
    }

    public String getAdditionalFund() {
        return additionalFund;
    }

    public void setAdditionalFund(String additionalFund) {
        this.additionalFund = additionalFund;
    }

    public String getCmrfObjId() {
        return cmrfObjId;
    }

    public void setCmrfObjId(String cmrfObjId) {
        this.cmrfObjId = cmrfObjId;
    }

    @NonNull
    @Override
    public String toString() {
        return "SanctionedDetail{" +
                "id='" + id + '\'' +
                ", sanctionedAmt=" + sanctionedAmt +
                ", sactinedAmtInWords='" + sactinedAmtInWords + '\'' +
                ", sanctionedDate='" + sanctionedDate + '\'' +
                ", sanctionedNo='" + sanctionedNo + '\'' +
                ", chequeNo='" + chequeNo + '\'' +
                ", chequeDate='" + chequeDate + '\'' +
                ", sanctionRemarks=" + sanctionRemarks +
                ", additionalFund='" + additionalFund + '\'' +
                ", cmrfObjId='" + cmrfObjId + '\'' +
                '}';
    }
}
