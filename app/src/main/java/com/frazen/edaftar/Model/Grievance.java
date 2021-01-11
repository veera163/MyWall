package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Grievance {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tgId")
    @Expose
    private String tgId;
    @SerializedName("orgName")
    @Expose
    private String orgName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("tweetId")
    @Expose
    private String tweetId;
    @SerializedName("tweetDesc")
    @Expose
    private String tweetDesc;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("orginatorName")
    @Expose
    private String orginatorName;
    @SerializedName("twitterLink")
    @Expose
    private String twitterLink;
    @SerializedName("assignedTo")
    @Expose
    private String assignedTo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tweetTime")
    @Expose
    private String tweetTime;
    @SerializedName("person")
    @Expose
    private String person;
    @SerializedName("personPhone")
    @Expose
    private String personPhone;
    @SerializedName("relationToContact")
    @Expose
    private String relationToContact;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("contactNo")
    @Expose
    private String contactNo;
    @SerializedName("contractTId")
    @Expose
    private String contractTId;
    @SerializedName("contactEmail")
    @Expose
    private String contactEmail;
    @SerializedName("gistOfCase")
    @Expose
    private String gistOfCase;
    @SerializedName("address")
    @Expose
    private String address;
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
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("document")
    @Expose
    private String document;
    @SerializedName("ministerTweet")
    @Expose
    private String ministerTweet;
    @SerializedName("minTweetTime")
    @Expose
    private String minTweetTime;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("resolution")
    @Expose
    private String resolution;
    @SerializedName("contactId")
    @Expose
    private String contactId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTgId() {
        return tgId;
    }

    public void setTgId(String tgId) {
        this.tgId = tgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public String getTweetDesc() {
        return tweetDesc;
    }

    public void setTweetDesc(String tweetDesc) {
        this.tweetDesc = tweetDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOrginatorName() {
        return orginatorName;
    }

    public void setOrginatorName(String orginatorName) {
        this.orginatorName = orginatorName;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTweetTime() {
        return tweetTime;
    }

    public void setTweetTime(String tweetTime) {
        this.tweetTime = tweetTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getRelationToContact() {
        return relationToContact;
    }

    public void setRelationToContact(String relationToContact) {
        this.relationToContact = relationToContact;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContractTId() {
        return contractTId;
    }

    public void setContractTId(String contractTId) {
        this.contractTId = contractTId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getGistOfCase() {
        return gistOfCase;
    }

    public void setGistOfCase(String gistOfCase) {
        this.gistOfCase = gistOfCase;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getMinisterTweet() {
        return ministerTweet;
    }

    public void setMinisterTweet(String ministerTweet) {
        this.ministerTweet = ministerTweet;
    }

    public String getMinTweetTime() {
        return minTweetTime;
    }

    public void setMinTweetTime(String minTweetTime) {
        this.minTweetTime = minTweetTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @NonNull
    @Override
    public String toString() {
        return "Grievance{" +
                "id='" + id + '\'' +
                ", tgId='" + tgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", title='" + title + '\'' +
                ", tweetId='" + tweetId + '\'' +
                ", tweetDesc='" + tweetDesc + '\'' +
                ", desc='" + desc + '\'' +
                ", createDate='" + createDate + '\'' +
                ", orginatorName='" + orginatorName + '\'' +
                ", twitterLink='" + twitterLink + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", status='" + status + '\'' +
                ", tweetTime='" + tweetTime + '\'' +
                ", person='" + person + '\'' +
                ", personPhone='" + personPhone + '\'' +
                ", relationToContact='" + relationToContact + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", contractTId='" + contractTId + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", gistOfCase='" + gistOfCase + '\'' +
                ", address='" + address + '\'' +
                ", hamlet='" + hamlet + '\'' +
                ", village='" + village + '\'' +
                ", mandal='" + mandal + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode='" + pincode + '\'' +
                ", source='" + source + '\'' +
                ", document='" + document + '\'' +
                ", ministerTweet='" + ministerTweet + '\'' +
                ", minTweetTime='" + minTweetTime + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", resolution='" + resolution + '\'' +
                ", contactId='" + contactId + '\'' +
                '}';
    }
}
