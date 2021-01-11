package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class ReimbrusementAndLoc {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fileNo")
    @Expose
    private String fileNo;
    @SerializedName("cmrfType")
    @Expose
    private String cmrfType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("applicationDate")
    @Expose
    private String applicationDate;
    @SerializedName("refBy")
    @Expose
    private String refBy;
    @SerializedName("refPhone")
    @Expose
    private String refPhone;
    @SerializedName("salutation")
    @Expose
    private String salutation;
    @SerializedName("patientName")
    @Expose
    private String patientName;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("relation")
    @Expose
    private String relation;
    @SerializedName("relativeName")
    @Expose
    private String relativeName;
    @SerializedName("pPhone")
    @Expose
    private String pPhone;
    @SerializedName("altPhone")
    @Expose
    private String altPhone;
    @SerializedName("aadhar")
    @Expose
    private String aadhar;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("pAddress")
    @Expose
    private String pAddress;
    @SerializedName("hamlet")
    @Expose
    private String hamlet;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("mandal")
    @Expose
    private String mandal;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("desease")
    @Expose
    private String desease;
    @SerializedName("hospitalName")
    @Expose
    private String hospitalName;
    @SerializedName("hospAddress")
    @Expose
    private String hospAddress;
    @SerializedName("additionalRequest")
    @Expose
    private Boolean additionalRequest;
    @SerializedName("previousSanctionedAmt")
    @Expose
    private Integer previousSanctionedAmt;
    @SerializedName("previousAmtInWords")
    @Expose
    private String previousAmtInWords;
    @SerializedName("treatmentAmount")
    @Expose
    private double treatmentAmount;
    @SerializedName("amtInWords")
    @Expose
    private String amtInWords;
    @SerializedName("peshiLrNo")
    @Expose
    private String peshiLrNo;
    @SerializedName("peshiDate")
    @Expose
    private String peshiDate;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("signedDate")
    @Expose
    private String signedDate;
    @SerializedName("fileLocation")
    @Expose
    private String fileLocation;
    @SerializedName("sanctionedAmt")
    @Expose
    private Integer sanctionedAmt;
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

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getCmrfType() {
        return cmrfType;
    }

    public void setCmrfType(String cmrfType) {
        this.cmrfType = cmrfType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getRefBy() {
        return refBy;
    }

    public void setRefBy(String refBy) {
        this.refBy = refBy;
    }

    public String getRefPhone() {
        return refPhone;
    }

    public void setRefPhone(String refPhone) {
        this.refPhone = refPhone;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    public String getpPhone() {
        return pPhone;
    }

    public void setpPhone(String pPhone) {
        this.pPhone = pPhone;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getHamlet() {
        return hamlet;
    }

    public void setHamlet(String hamlet) {
        this.hamlet = hamlet;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDesease() {
        return desease;
    }

    public void setDesease(String desease) {
        this.desease = desease;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospAddress() {
        return hospAddress;
    }

    public void setHospAddress(String hospAddress) {
        this.hospAddress = hospAddress;
    }

    public Boolean getAdditionalRequest() {
        return additionalRequest;
    }

    public void setAdditionalRequest(Boolean additionalRequest) {
        this.additionalRequest = additionalRequest;
    }

    public Integer getPreviousSanctionedAmt() {
        return previousSanctionedAmt;
    }

    public void setPreviousSanctionedAmt(Integer previousSanctionedAmt) {
        this.previousSanctionedAmt = previousSanctionedAmt;
    }

    public String getPreviousAmtInWords() {
        return previousAmtInWords;
    }

    public void setPreviousAmtInWords(String previousAmtInWords) {
        this.previousAmtInWords = previousAmtInWords;
    }

    public double getTreatmentAmount() {
        return treatmentAmount;
    }

    public void setTreatmentAmount(double treatmentAmount) {
        this.treatmentAmount = treatmentAmount;
    }

    public String getAmtInWords() {
        return amtInWords;
    }

    public void setAmtInWords(String amtInWords) {
        this.amtInWords = amtInWords;
    }

    public String getPeshiLrNo() {
        return peshiLrNo;
    }

    public void setPeshiLrNo(String peshiLrNo) {
        this.peshiLrNo = peshiLrNo;
    }

    public String getPeshiDate() {
        return peshiDate;
    }

    public void setPeshiDate(String peshiDate) {
        this.peshiDate = peshiDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(String signedDate) {
        this.signedDate = signedDate;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Integer getSanctionedAmt() {
        return sanctionedAmt;
    }

    public void setSanctionedAmt(Integer sanctionedAmt) {
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
        return "ReimbrusementAndLoc{" +
                "id='" + id + '\'' +
                ", fileNo='" + fileNo + '\'' +
                ", cmrfType='" + cmrfType + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", refBy='" + refBy + '\'' +
                ", refPhone='" + refPhone + '\'' +
                ", salutation='" + salutation + '\'' +
                ", patientName='" + patientName + '\'' +
                ", sex='" + sex + '\'' +
                ", relation='" + relation + '\'' +
                ", relativeName='" + relativeName + '\'' +
                ", pPhone='" + pPhone + '\'' +
                ", altPhone='" + altPhone + '\'' +
                ", aadhar='" + aadhar + '\'' +
                ", age='" + age + '\'' +
                ", pAddress='" + pAddress + '\'' +
                ", hamlet='" + hamlet + '\'' +
                ", village='" + village + '\'' +
                ", mandal='" + mandal + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode='" + pincode + '\'' +
                ", desease='" + desease + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospAddress='" + hospAddress + '\'' +
                ", additionalRequest=" + additionalRequest +
                ", previousSanctionedAmt=" + previousSanctionedAmt +
                ", previousAmtInWords='" + previousAmtInWords + '\'' +
                ", treatmentAmount=" + treatmentAmount +
                ", amtInWords='" + amtInWords + '\'' +
                ", peshiLrNo='" + peshiLrNo + '\'' +
                ", peshiDate='" + peshiDate + '\'' +
                ", remarks='" + remarks + '\'' +
                ", signedDate='" + signedDate + '\'' +
                ", fileLocation='" + fileLocation + '\'' +
                ", sanctionedAmt=" + sanctionedAmt +
                ", sactinedAmtInWords='" + sactinedAmtInWords + '\'' +
                ", sanctionedDate='" + sanctionedDate + '\'' +
                ", sanctionedNo='" + sanctionedNo + '\'' +
                ", chequeNo='" + chequeNo + '\'' +
                ", chequeDate='" + chequeDate + '\'' +
                ", sanctionRemarks='" + sanctionRemarks + '\'' +
                ", additionalFund='" + additionalFund + '\'' +
                ", cmrfObjId='" + cmrfObjId + '\'' +
                '}';
    }
}
