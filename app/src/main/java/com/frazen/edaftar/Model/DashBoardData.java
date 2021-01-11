package com.frazen.edaftar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class DashBoardData {

    @SerializedName("noOfGrievances")
    @Expose
    private Integer noOfGrievances;
    @SerializedName("currentMonthGrievances")
    @Expose
    private Integer currentMonthGrievances;
    @SerializedName("currentMonthAddressedGrievances")
    @Expose
    private Integer currentMonthAddressedGrievances;
    @SerializedName("pendingGrievances")
    @Expose
    private Integer pendingGrievances;
    @SerializedName("pendingMoreThanAMonth")
    @Expose
    private Integer pendingMoreThanAMonth;
    @SerializedName("noOfNewGrievances")
    @Expose
    private Integer noOfNewGrievances;
    @SerializedName("noOfInProgress")
    @Expose
    private Integer noOfInProgress;
    @SerializedName("noOfSolved")
    @Expose
    private Integer noOfSolved;
    @SerializedName("noOfWaiting")
    @Expose
    private Integer noOfWaiting;
    @SerializedName("noOfClosed")
    @Expose
    private Integer noOfClosed;
    @SerializedName("noOfPartlySolved")
    @Expose
    private Integer noOfPartlySolved;
    @SerializedName("noOnHold")
    @Expose
    private Integer noOnHold;
    @SerializedName("noAssignedToCMRF")
    @Expose
    private Integer noAssignedToCMRF;
    @SerializedName("noAssignedToNRI")
    @Expose
    private Integer noAssignedToNRI;
    @SerializedName("noAssignedToPeshi")
    @Expose
    private Integer noAssignedToPeshi;
    @SerializedName("noAssignedToGeneral")
    @Expose
    private Integer noAssignedToGeneral;
    @SerializedName("noAssignedToGHMC")
    @Expose
    private Integer noAssignedToGHMC;
    @SerializedName("noAssignedToHMWS")
    @Expose
    private Integer noAssignedToHMWS;
    @SerializedName("totalPeshiFiles")
    @Expose
    private Integer totalPeshiFiles;
    @SerializedName("totalPeshiFilesApproved")
    @Expose
    private Integer totalPeshiFilesApproved;
    @SerializedName("totalPeshiFilesLieOver")
    @Expose
    private Integer totalPeshiFilesLieOver;
    @SerializedName("totalPeshiFilesPending")
    @Expose
    private Integer totalPeshiFilesPending;
    @SerializedName("totalPeshiFilesReturned")
    @Expose
    private Integer totalPeshiFilesReturned;
    @SerializedName("totalReimbursements")
    @Expose
    private Integer totalReimbursements;
    @SerializedName("totalReimbursementPending")
    @Expose
    private Integer totalReimbursementPending;
    @SerializedName("totalReimbursementRejected")
    @Expose
    private Integer totalReimbursementRejected;
    @SerializedName("totalReimbursementSanctioned")
    @Expose
    private Integer totalReimbursementSanctioned;
    @SerializedName("totalLOC")
    @Expose
    private Integer totalLOC;
    @SerializedName("totalLOCPending")
    @Expose
    private Integer totalLOCPending;
    @SerializedName("totalLOCRejected")
    @Expose
    private Integer totalLOCRejected;
    @SerializedName("totalLOCSanctioned")
    @Expose
    private Integer totalLOCSanctioned;
    @SerializedName("totalEndorsements")
    @Expose
    private Integer totalEndorsements;
    @SerializedName("totalEndorsementsNew")
    @Expose
    private Integer totalEndorsementsNew;
    @SerializedName("totalEndorsementsDispatched")
    @Expose
    private Integer totalEndorsementsDispatched;
    @SerializedName("totalEndorsementsHold")
    @Expose
    private Integer totalEndorsementsHold;
    @SerializedName("totalEndorsementsLieOver")
    @Expose
    private Integer totalEndorsementsLieOver;
    @SerializedName("totalEndorsementsPendng")
    @Expose
    private Integer totalEndorsementsPendng;
    @SerializedName("totalLetters")
    @Expose
    private Integer totalLetters;
    @SerializedName("totalLettersNew")
    @Expose
    private Integer totalLettersNew;
    @SerializedName("totalLettersDispatched")
    @Expose
    private Integer totalLettersDispatched;
    @SerializedName("totalLettersHold")
    @Expose
    private Integer totalLettersHold;
    @SerializedName("totalLettersLieOver")
    @Expose
    private Integer totalLettersLieOver;
    @SerializedName("totalLettersPendng")
    @Expose
    private Integer totalLettersPendng;
    @SerializedName("totalDOs")
    @Expose
    private Integer totalDOs;
    @SerializedName("totalDONew")
    @Expose
    private Integer totalDONew;
    @SerializedName("totalDODispatched")
    @Expose
    private Integer totalDODispatched;
    @SerializedName("totalDOHold")
    @Expose
    private Integer totalDOHold;
    @SerializedName("totalDOLieOver")
    @Expose
    private Integer totalDOLieOver;
    @SerializedName("totalDOPendng")
    @Expose
    private Integer totalDOPendng;
    @SerializedName("totalNotes")
    @Expose
    private Integer totalNotes;
    @SerializedName("totalNotesNew")
    @Expose
    private Integer totalNotesNew;
    @SerializedName("totalNotesDispatched")
    @Expose
    private Integer totalNotesDispatched;
    @SerializedName("totalNotesHold")
    @Expose
    private Integer totalNotesHold;
    @SerializedName("totalNotesLieOver")
    @Expose
    private Integer totalNotesLieOver;
    @SerializedName("totalNotesPendng")
    @Expose
    private Integer totalNotesPendng;
    @SerializedName("totalLettersAndNotes")
    @Expose
    private Integer totalLettersAndNotes;
    @SerializedName("totalLettersAndNotesNew")
    @Expose
    private Integer totalLettersAndNotesNew;
    @SerializedName("totalLettersAndNotesDispatched")
    @Expose
    private Integer totalLettersAndNotesDispatched;
    @SerializedName("totalLettersAndNotesHold")
    @Expose
    private Integer totalLettersAndNotesHold;
    @SerializedName("totalLettersAndNotesLieOver")
    @Expose
    private Integer totalLettersAndNotesLieOver;
    @SerializedName("totalLettersAndNotesPending")
    @Expose
    private Integer totalLettersAndNotesPending;

    public Integer getNoOfGrievances() {
        return noOfGrievances;
    }

    public void setNoOfGrievances(Integer noOfGrievances) {
        this.noOfGrievances = noOfGrievances;
    }

    public Integer getCurrentMonthGrievances() {
        return currentMonthGrievances;
    }

    public void setCurrentMonthGrievances(Integer currentMonthGrievances) {
        this.currentMonthGrievances = currentMonthGrievances;
    }

    public Integer getCurrentMonthAddressedGrievances() {
        return currentMonthAddressedGrievances;
    }

    public void setCurrentMonthAddressedGrievances(Integer currentMonthAddressedGrievances) {
        this.currentMonthAddressedGrievances = currentMonthAddressedGrievances;
    }

    public Integer getPendingGrievances() {
        return pendingGrievances;
    }

    public void setPendingGrievances(Integer pendingGrievances) {
        this.pendingGrievances = pendingGrievances;
    }

    public Integer getPendingMoreThanAMonth() {
        return pendingMoreThanAMonth;
    }

    public void setPendingMoreThanAMonth(Integer pendingMoreThanAMonth) {
        this.pendingMoreThanAMonth = pendingMoreThanAMonth;
    }

    public Integer getNoOfNewGrievances() {
        return noOfNewGrievances;
    }

    public void setNoOfNewGrievances(Integer noOfNewGrievances) {
        this.noOfNewGrievances = noOfNewGrievances;
    }

    public Integer getNoOfInProgress() {
        return noOfInProgress;
    }

    public void setNoOfInProgress(Integer noOfInProgress) {
        this.noOfInProgress = noOfInProgress;
    }

    public Integer getNoOfSolved() {
        return noOfSolved;
    }

    public void setNoOfSolved(Integer noOfSolved) {
        this.noOfSolved = noOfSolved;
    }

    public Integer getNoOfWaiting() {
        return noOfWaiting;
    }

    public void setNoOfWaiting(Integer noOfWaiting) {
        this.noOfWaiting = noOfWaiting;
    }

    public Integer getNoOfClosed() {
        return noOfClosed;
    }

    public void setNoOfClosed(Integer noOfClosed) {
        this.noOfClosed = noOfClosed;
    }

    public Integer getNoOfPartlySolved() {
        return noOfPartlySolved;
    }

    public void setNoOfPartlySolved(Integer noOfPartlySolved) {
        this.noOfPartlySolved = noOfPartlySolved;
    }

    public Integer getNoOnHold() {
        return noOnHold;
    }

    public void setNoOnHold(Integer noOnHold) {
        this.noOnHold = noOnHold;
    }

    public Integer getNoAssignedToCMRF() {
        return noAssignedToCMRF;
    }

    public void setNoAssignedToCMRF(Integer noAssignedToCMRF) {
        this.noAssignedToCMRF = noAssignedToCMRF;
    }

    public Integer getNoAssignedToNRI() {
        return noAssignedToNRI;
    }

    public void setNoAssignedToNRI(Integer noAssignedToNRI) {
        this.noAssignedToNRI = noAssignedToNRI;
    }

    public Integer getNoAssignedToPeshi() {
        return noAssignedToPeshi;
    }

    public void setNoAssignedToPeshi(Integer noAssignedToPeshi) {
        this.noAssignedToPeshi = noAssignedToPeshi;
    }

    public Integer getNoAssignedToGeneral() {
        return noAssignedToGeneral;
    }

    public void setNoAssignedToGeneral(Integer noAssignedToGeneral) {
        this.noAssignedToGeneral = noAssignedToGeneral;
    }

    public Integer getNoAssignedToGHMC() {
        return noAssignedToGHMC;
    }

    public void setNoAssignedToGHMC(Integer noAssignedToGHMC) {
        this.noAssignedToGHMC = noAssignedToGHMC;
    }

    public Integer getNoAssignedToHMWS() {
        return noAssignedToHMWS;
    }

    public void setNoAssignedToHMWS(Integer noAssignedToHMWS) {
        this.noAssignedToHMWS = noAssignedToHMWS;
    }

    public Integer getTotalPeshiFiles() {
        return totalPeshiFiles;
    }

    public void setTotalPeshiFiles(Integer totalPeshiFiles) {
        this.totalPeshiFiles = totalPeshiFiles;
    }

    public Integer getTotalPeshiFilesApproved() {
        return totalPeshiFilesApproved;
    }

    public void setTotalPeshiFilesApproved(Integer totalPeshiFilesApproved) {
        this.totalPeshiFilesApproved = totalPeshiFilesApproved;
    }

    public Integer getTotalPeshiFilesLieOver() {
        return totalPeshiFilesLieOver;
    }

    public void setTotalPeshiFilesLieOver(Integer totalPeshiFilesLieOver) {
        this.totalPeshiFilesLieOver = totalPeshiFilesLieOver;
    }

    public Integer getTotalPeshiFilesPending() {
        return totalPeshiFilesPending;
    }

    public void setTotalPeshiFilesPending(Integer totalPeshiFilesPending) {
        this.totalPeshiFilesPending = totalPeshiFilesPending;
    }

    public Integer getTotalPeshiFilesReturned() {
        return totalPeshiFilesReturned;
    }

    public void setTotalPeshiFilesReturned(Integer totalPeshiFilesReturned) {
        this.totalPeshiFilesReturned = totalPeshiFilesReturned;
    }

    public Integer getTotalReimbursements() {
        return totalReimbursements;
    }

    public void setTotalReimbursements(Integer totalReimbursements) {
        this.totalReimbursements = totalReimbursements;
    }

    public Integer getTotalReimbursementPending() {
        return totalReimbursementPending;
    }

    public void setTotalReimbursementPending(Integer totalReimbursementPending) {
        this.totalReimbursementPending = totalReimbursementPending;
    }

    public Integer getTotalReimbursementRejected() {
        return totalReimbursementRejected;
    }

    public void setTotalReimbursementRejected(Integer totalReimbursementRejected) {
        this.totalReimbursementRejected = totalReimbursementRejected;
    }

    public Integer getTotalReimbursementSanctioned() {
        return totalReimbursementSanctioned;
    }

    public void setTotalReimbursementSanctioned(Integer totalReimbursementSanctioned) {
        this.totalReimbursementSanctioned = totalReimbursementSanctioned;
    }

    public Integer getTotalLOC() {
        return totalLOC;
    }

    public void setTotalLOC(Integer totalLOC) {
        this.totalLOC = totalLOC;
    }

    public Integer getTotalLOCPending() {
        return totalLOCPending;
    }

    public void setTotalLOCPending(Integer totalLOCPending) {
        this.totalLOCPending = totalLOCPending;
    }

    public Integer getTotalLOCRejected() {
        return totalLOCRejected;
    }

    public void setTotalLOCRejected(Integer totalLOCRejected) {
        this.totalLOCRejected = totalLOCRejected;
    }

    public Integer getTotalLOCSanctioned() {
        return totalLOCSanctioned;
    }

    public void setTotalLOCSanctioned(Integer totalLOCSanctioned) {
        this.totalLOCSanctioned = totalLOCSanctioned;
    }

    public Integer getTotalEndorsements() {
        return totalEndorsements;
    }

    public void setTotalEndorsements(Integer totalEndorsements) {
        this.totalEndorsements = totalEndorsements;
    }

    public Integer getTotalEndorsementsNew() {
        return totalEndorsementsNew;
    }

    public void setTotalEndorsementsNew(Integer totalEndorsementsNew) {
        this.totalEndorsementsNew = totalEndorsementsNew;
    }

    public Integer getTotalEndorsementsDispatched() {
        return totalEndorsementsDispatched;
    }

    public void setTotalEndorsementsDispatched(Integer totalEndorsementsDispatched) {
        this.totalEndorsementsDispatched = totalEndorsementsDispatched;
    }

    public Integer getTotalEndorsementsHold() {
        return totalEndorsementsHold;
    }

    public void setTotalEndorsementsHold(Integer totalEndorsementsHold) {
        this.totalEndorsementsHold = totalEndorsementsHold;
    }

    public Integer getTotalEndorsementsLieOver() {
        return totalEndorsementsLieOver;
    }

    public void setTotalEndorsementsLieOver(Integer totalEndorsementsLieOver) {
        this.totalEndorsementsLieOver = totalEndorsementsLieOver;
    }

    public Integer getTotalEndorsementsPendng() {
        return totalEndorsementsPendng;
    }

    public void setTotalEndorsementsPendng(Integer totalEndorsementsPendng) {
        this.totalEndorsementsPendng = totalEndorsementsPendng;
    }

    public Integer getTotalLetters() {
        return totalLetters;
    }

    public void setTotalLetters(Integer totalLetters) {
        this.totalLetters = totalLetters;
    }

    public Integer getTotalLettersNew() {
        return totalLettersNew;
    }

    public void setTotalLettersNew(Integer totalLettersNew) {
        this.totalLettersNew = totalLettersNew;
    }

    public Integer getTotalLettersDispatched() {
        return totalLettersDispatched;
    }

    public void setTotalLettersDispatched(Integer totalLettersDispatched) {
        this.totalLettersDispatched = totalLettersDispatched;
    }

    public Integer getTotalLettersHold() {
        return totalLettersHold;
    }

    public void setTotalLettersHold(Integer totalLettersHold) {
        this.totalLettersHold = totalLettersHold;
    }

    public Integer getTotalLettersLieOver() {
        return totalLettersLieOver;
    }

    public void setTotalLettersLieOver(Integer totalLettersLieOver) {
        this.totalLettersLieOver = totalLettersLieOver;
    }

    public Integer getTotalLettersPendng() {
        return totalLettersPendng;
    }

    public void setTotalLettersPendng(Integer totalLettersPendng) {
        this.totalLettersPendng = totalLettersPendng;
    }

    public Integer getTotalDOs() {
        return totalDOs;
    }

    public void setTotalDOs(Integer totalDOs) {
        this.totalDOs = totalDOs;
    }

    public Integer getTotalDONew() {
        return totalDONew;
    }

    public void setTotalDONew(Integer totalDONew) {
        this.totalDONew = totalDONew;
    }

    public Integer getTotalDODispatched() {
        return totalDODispatched;
    }

    public void setTotalDODispatched(Integer totalDODispatched) {
        this.totalDODispatched = totalDODispatched;
    }

    public Integer getTotalDOHold() {
        return totalDOHold;
    }

    public void setTotalDOHold(Integer totalDOHold) {
        this.totalDOHold = totalDOHold;
    }

    public Integer getTotalDOLieOver() {
        return totalDOLieOver;
    }

    public void setTotalDOLieOver(Integer totalDOLieOver) {
        this.totalDOLieOver = totalDOLieOver;
    }

    public Integer getTotalDOPendng() {
        return totalDOPendng;
    }

    public void setTotalDOPendng(Integer totalDOPendng) {
        this.totalDOPendng = totalDOPendng;
    }

    public Integer getTotalNotes() {
        return totalNotes;
    }

    public void setTotalNotes(Integer totalNotes) {
        this.totalNotes = totalNotes;
    }

    public Integer getTotalNotesNew() {
        return totalNotesNew;
    }

    public void setTotalNotesNew(Integer totalNotesNew) {
        this.totalNotesNew = totalNotesNew;
    }

    public Integer getTotalNotesDispatched() {
        return totalNotesDispatched;
    }

    public void setTotalNotesDispatched(Integer totalNotesDispatched) {
        this.totalNotesDispatched = totalNotesDispatched;
    }

    public Integer getTotalNotesHold() {
        return totalNotesHold;
    }

    public void setTotalNotesHold(Integer totalNotesHold) {
        this.totalNotesHold = totalNotesHold;
    }

    public Integer getTotalNotesLieOver() {
        return totalNotesLieOver;
    }

    public void setTotalNotesLieOver(Integer totalNotesLieOver) {
        this.totalNotesLieOver = totalNotesLieOver;
    }

    public Integer getTotalNotesPendng() {
        return totalNotesPendng;
    }

    public void setTotalNotesPendng(Integer totalNotesPendng) {
        this.totalNotesPendng = totalNotesPendng;
    }

    public Integer getTotalLettersAndNotes() {
        return totalLettersAndNotes;
    }

    public void setTotalLettersAndNotes(Integer totalLettersAndNotes) {
        this.totalLettersAndNotes = totalLettersAndNotes;
    }

    public Integer getTotalLettersAndNotesNew() {
        return totalLettersAndNotesNew;
    }

    public void setTotalLettersAndNotesNew(Integer totalLettersAndNotesNew) {
        this.totalLettersAndNotesNew = totalLettersAndNotesNew;
    }

    public Integer getTotalLettersAndNotesDispatched() {
        return totalLettersAndNotesDispatched;
    }

    public void setTotalLettersAndNotesDispatched(Integer totalLettersAndNotesDispatched) {
        this.totalLettersAndNotesDispatched = totalLettersAndNotesDispatched;
    }

    public Integer getTotalLettersAndNotesHold() {
        return totalLettersAndNotesHold;
    }

    public void setTotalLettersAndNotesHold(Integer totalLettersAndNotesHold) {
        this.totalLettersAndNotesHold = totalLettersAndNotesHold;
    }

    public Integer getTotalLettersAndNotesLieOver() {
        return totalLettersAndNotesLieOver;
    }

    public void setTotalLettersAndNotesLieOver(Integer totalLettersAndNotesLieOver) {
        this.totalLettersAndNotesLieOver = totalLettersAndNotesLieOver;
    }

    public Integer getTotalLettersAndNotesPending() {
        return totalLettersAndNotesPending;
    }

    public void setTotalLettersAndNotesPending(Integer totalLettersAndNotesPending) {
        this.totalLettersAndNotesPending = totalLettersAndNotesPending;
    }

    @NonNull
    @Override
    public String toString() {
        return "DashBoardData{" +
                "noOfGrievances=" + noOfGrievances +
                ", currentMonthGrievances=" + currentMonthGrievances +
                ", currentMonthAddressedGrievances=" + currentMonthAddressedGrievances +
                ", pendingGrievances=" + pendingGrievances +
                ", pendingMoreThanAMonth=" + pendingMoreThanAMonth +
                ", noOfNewGrievances=" + noOfNewGrievances +
                ", noOfInProgress=" + noOfInProgress +
                ", noOfSolved=" + noOfSolved +
                ", noOfWaiting=" + noOfWaiting +
                ", noOfClosed=" + noOfClosed +
                ", noOfPartlySolved=" + noOfPartlySolved +
                ", noOnHold=" + noOnHold +
                ", noAssignedToCMRF=" + noAssignedToCMRF +
                ", noAssignedToNRI=" + noAssignedToNRI +
                ", noAssignedToPeshi=" + noAssignedToPeshi +
                ", noAssignedToGeneral=" + noAssignedToGeneral +
                ", noAssignedToGHMC=" + noAssignedToGHMC +
                ", noAssignedToHMWS=" + noAssignedToHMWS +
                ", totalPeshiFiles=" + totalPeshiFiles +
                ", totalPeshiFilesApproved=" + totalPeshiFilesApproved +
                ", totalPeshiFilesLieOver=" + totalPeshiFilesLieOver +
                ", totalPeshiFilesPending=" + totalPeshiFilesPending +
                ", totalPeshiFilesReturned=" + totalPeshiFilesReturned +
                ", totalReimbursements=" + totalReimbursements +
                ", totalReimbursementPending=" + totalReimbursementPending +
                ", totalReimbursementRejected=" + totalReimbursementRejected +
                ", totalReimbursementSanctioned=" + totalReimbursementSanctioned +
                ", totalLOC=" + totalLOC +
                ", totalLOCPending=" + totalLOCPending +
                ", totalLOCRejected=" + totalLOCRejected +
                ", totalLOCSanctioned=" + totalLOCSanctioned +
                ", totalEndorsements=" + totalEndorsements +
                ", totalEndorsementsNew=" + totalEndorsementsNew +
                ", totalEndorsementsDispatched=" + totalEndorsementsDispatched +
                ", totalEndorsementsHold=" + totalEndorsementsHold +
                ", totalEndorsementsLieOver=" + totalEndorsementsLieOver +
                ", totalEndorsementsPendng=" + totalEndorsementsPendng +
                ", totalLetters=" + totalLetters +
                ", totalLettersNew=" + totalLettersNew +
                ", totalLettersDispatched=" + totalLettersDispatched +
                ", totalLettersHold=" + totalLettersHold +
                ", totalLettersLieOver=" + totalLettersLieOver +
                ", totalLettersPendng=" + totalLettersPendng +
                ", totalDOs=" + totalDOs +
                ", totalDONew=" + totalDONew +
                ", totalDODispatched=" + totalDODispatched +
                ", totalDOHold=" + totalDOHold +
                ", totalDOLieOver=" + totalDOLieOver +
                ", totalDOPendng=" + totalDOPendng +
                ", totalNotes=" + totalNotes +
                ", totalNotesNew=" + totalNotesNew +
                ", totalNotesDispatched=" + totalNotesDispatched +
                ", totalNotesHold=" + totalNotesHold +
                ", totalNotesLieOver=" + totalNotesLieOver +
                ", totalNotesPendng=" + totalNotesPendng +
                ", totalLettersAndNotes=" + totalLettersAndNotes +
                ", totalLettersAndNotesNew=" + totalLettersAndNotesNew +
                ", totalLettersAndNotesDispatched=" + totalLettersAndNotesDispatched +
                ", totalLettersAndNotesHold=" + totalLettersAndNotesHold +
                ", totalLettersAndNotesLieOver=" + totalLettersAndNotesLieOver +
                ", totalLettersAndNotesPending=" + totalLettersAndNotesPending +
                '}';
    }
}
