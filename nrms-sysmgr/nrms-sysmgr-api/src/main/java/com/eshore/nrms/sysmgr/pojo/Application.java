package com.eshore.nrms.sysmgr.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by forgeeks at 2017-02-24 9:07
 */
@Entity
@Table(name="a_application")
public class Application implements Serializable {
    private String id;
    private String placeId;
    private String uidApplicat;
    private String subject;
    private String startDate;
    private String startTime;
    private String endTime;
    private  Integer appState;
    private  String appRemark;
    private  Date submitDate;
    private  String uidMinutes;
    private  String meetingMinutes;
    private  Integer jiyaoState;
    private  String uidAuditor;
    private  Date  auditingDate;
    private  String  auditingFeedback;
    private  String  appFileName;
    private  String  appFileUuid;
    private  String minutesFileName;
    private  String minutesFileUuid;

    @Id
    @Column(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="place_id")
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Column(name="uid_applicat")
    public String getUidApplicat() {
        return uidApplicat;
    }

    public void setUidApplicat(String uidApplicat) {
        this.uidApplicat = uidApplicat;
    }

    @Column(name="subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name="start_date")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Column(name="start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Column(name="end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Column(name="app_state")
    public Integer getAppState() {
        return appState;
    }

    public void setAppState(Integer appState) {
        this.appState = appState;
    }

    @Column(name="app_remark")
    public String getAppRemark() {
        return appRemark;
    }

    public void setAppRemark(String appRemark) {
        this.appRemark = appRemark;
    }

    @Column(name="submit_date")
    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getUidMinutes() {
        return uidMinutes;
    }

    @Column(name="uid_minutes")
    public void setUidMinutes(String uidMinutes) {
        this.uidMinutes = uidMinutes;
    }

    public String getMeetingMinutes() {
        return meetingMinutes;
    }

    @Column(name="meeting_minutes")
    public void setMeetingMinutes(String meetingMinutes) {
        this.meetingMinutes = meetingMinutes;
    }

    public Integer getJiyaoState() {
        return jiyaoState;
    }

    @Column(name="jiyao_state")
    public void setJiyaoState(Integer jiyaoState) {
        this.jiyaoState = jiyaoState;
    }

    public String getUidAuditor() {
        return uidAuditor;
    }

    @Column(name="uid_auditor")
    public void setUidAuditor(String uidAuditor) {
        this.uidAuditor = uidAuditor;
    }

    public Date getAuditingDate() {
        return auditingDate;
    }

    @Column(name="auditing_date")
    public void setAuditingDate(Date auditingDate) {
        this.auditingDate = auditingDate;
    }

    public String getAuditingFeedback() {
        return auditingFeedback;
    }

    @Column(name="auditing_feedback")
    public void setAuditingFeedback(String auditingFeedback) {
        this.auditingFeedback = auditingFeedback;
    }

    public String getAppFileName() {
        return appFileName;
    }

    @Column(name="app_file_name")
    public void setAppFileName(String appFileName) {
        this.appFileName = appFileName;
    }

    public String getAppFileUuid() {
        return appFileUuid;
    }

    @Column(name="app_file_uuid")
    public void setAppFileUuid(String appFileUuid) {
        this.appFileUuid = appFileUuid;
    }

    public String getMinutesFileName() {
        return minutesFileName;
    }

    @Column(name="minutes_file_name")
    public void setMinutesFileName(String minutesFileName) {
        this.minutesFileName = minutesFileName;
    }

    @Column(name="minutes_file_uuid")
    public String getMinutesFileUuid() {
        return minutesFileUuid;
    }

    public void setMinutesFileUuid(String minutesFileUuid) {
        this.minutesFileUuid = minutesFileUuid;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", placeId='" + placeId + '\'' +
                ", uidApplicat='" + uidApplicat + '\'' +
                ", subject='" + subject + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", appState=" + appState +
                ", appRemark='" + appRemark + '\'' +
                ", submitDate=" + submitDate +
                ", uidMinutes='" + uidMinutes + '\'' +
                ", meetingMinutes='" + meetingMinutes + '\'' +
                ", jiyaoState=" + jiyaoState +
                ", uidAuditor='" + uidAuditor + '\'' +
                ", auditingDate=" + auditingDate +
                ", auditingFeedback='" + auditingFeedback + '\'' +
                ", appFileName='" + appFileName + '\'' +
                ", appFileUuid='" + appFileUuid + '\'' +
                ", minutesFileName='" + minutesFileName + '\'' +
                ", minutesFileUuid='" + minutesFileUuid + '\'' +
                '}';
    }
}
