package com.frazen.edaftar.Model;

import java.io.Serializable;
import java.util.Date;

public class NewCalendarEvent implements Serializable {
    private String eventId;
    private String eventDate;
    private String title;
    private String meetingTypeId;
    private String meetingType;
    private String notes;
    private Date startDateObj;
    private String startDate;
    private String startTime;
    private String startTimeText;
    private Date endDateObj;
    private String endDate;
    private String endTime;
    private String endTimeText;
    private boolean isAllDay;
    private boolean isRecurrenceEvent;
    private String recurrenceRulesStr;

    public NewCalendarEvent() {
        this.eventId = "";
        this.eventDate = "";
        this.title = "";
        this.meetingTypeId = "0";
        this.meetingType = "";
        this.notes = "";
        this.startDateObj = null;
        this.startDate = "";
        this.startTime = "";
        this.startTimeText = "";
        this.endDateObj = null;
        this.endDate = "";
        this.endTime = "";
        this.endTimeText = "";
        this.isAllDay = false;
        this.isRecurrenceEvent = false;
        this.recurrenceRulesStr = "";
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMeetingTypeId() {
        return meetingTypeId;
    }

    public void setMeetingTypeId(String meetingTypeId) {
        this.meetingTypeId = meetingTypeId;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getStartDateObj() {
        return startDateObj;
    }

    public void setStartDateObj(Date startDateObj) {
        this.startDateObj = startDateObj;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeText() {
        return startTimeText;
    }

    public void setStartTimeText(String startTimeText) {
        this.startTimeText = startTimeText;
    }

    public Date getEndDateObj() {
        return endDateObj;
    }

    public void setEndDateObj(Date endDateObj) {
        this.endDateObj = endDateObj;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeText() {
        return endTimeText;
    }

    public void setEndTimeText(String endTimeText) {
        this.endTimeText = endTimeText;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public boolean isRecurrenceEvent() {
        return isRecurrenceEvent;
    }

    public void setRecurrenceEvent(boolean recurrenceEvent) {
        isRecurrenceEvent = recurrenceEvent;
    }

    public String getRecurrenceRulesStr() {
        return recurrenceRulesStr;
    }

    public void setRecurrenceRulesStr(String recurrenceRulesStr) {
        this.recurrenceRulesStr = recurrenceRulesStr;
    }


    @Override
    public String toString() {
        return "NewCalendarEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", title='" + title + '\'' +
                ", meetingTypeId='" + meetingTypeId + '\'' +
                ", meetingType='" + meetingType + '\'' +
                ", notes='" + notes + '\'' +
                ", startDateObj=" + startDateObj +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", startTimeText='" + startTimeText + '\'' +
                ", endDateObj=" + endDateObj +
                ", endDate='" + endDate + '\'' +
                ", endTime='" + endTime + '\'' +
                ", endTimeText='" + endTimeText + '\'' +
                ", isAllDay=" + isAllDay +
                ", isRecurrenceEvent=" + isRecurrenceEvent +
                ", recurrenceRulesStr='" + recurrenceRulesStr + '\'' +
                '}';
    }
}
