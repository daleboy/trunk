package com.eshore.nrms.sysmgr.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "a_application")
public class Application implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3009673280479680502L;

	@Id
	@GenericGenerator(name="uuidGen",strategy="uuid")
	@GeneratedValue(generator="uuidGen")
	@Column(name = "id")
	private String id;		//主键
	
	@Column(name = "place_id")
	private String placeId;		//会议室id
	
	@Transient
	private String placeName;	//会议室名
	
	@Column(name = "uid_applicant")
	private String uidApplicant;	//申请人id
	
	@Transient
	private String unameApplicant;	//申请人名
	
	@Column(name = "subject")
	private String subject;		//主题
	
	@Column(name = "start_date")
	private String startDate;	//会议开始日期   yyyy-MM-dd
	
	@Column(name = "start_time")
	private String startTime;	//开始时间  HH:mm
	
	@Column(name = "end_time")
	private String endTime;		//结束时间  HH:mm
	
	@Column(name = "app_state")
	private Integer appState;	//申请状态  0:暂存	1:待审核		2:申请通过		3:申请未通过
	
	@Column(name = "app_remark")
	private String appRemark;	//申请的备注
	
	@Column(name = "submit_date")
	private String submitDate;	//提交时间    yyyy-MM-dd HH:ss
	
	@Column(name = "uid_minutes")
	private String uidMinutes;	//会议纪要人id
	
	@Transient
	private String unameMinutes;//会议纪要人名
	
	@Column(name = "meeting_minutes")
	private String meetingMinutes;	//会议纪要内容
	
	@Column(name = "minutes_state")
	private Integer minutesState;	//会议纪要状态  0：未开始      1：暂存      2：完成
	
	@Column(name = "uid_auditor")
	private String uidAuditor;		//审批人id
	
	@Transient
	private String unameAuditor;	//审批人名
	
	@Column(name = "auditing_date")
	private String auditingDate;	//审批时间
	
	@Column(name = "auditing_feedback")
	private String auditingFeedBack;	//审批反馈
	
	@Column(name = "app_file_name")
	private String appFileName;		//会议文件的原名
	
	@Column(name = "app_file_uuid")
	private String appUuidName;		//会议文件的uuid名
	
	@Column(name = "minutes_file_name")
	private String minutesFileName;		//会议纪要的原名
	
	@Column(name = "minutes_file_uuid")
	private String minutesUuidName;		//会议纪要的uuid名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getUidApplicant() {
		return uidApplicant;
	}

	public void setUidApplicant(String uidApplicant) {
		this.uidApplicant = uidApplicant;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getAppState() {
		return appState;
	}

	public void setAppState(Integer appState) {
		this.appState = appState;
	}

	public String getAppRemark() {
		return appRemark;
	}

	public void setAppRemark(String appRemark) {
		this.appRemark = appRemark;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getUidMinutes() {
		return uidMinutes;
	}

	public void setUidMinutes(String uidMinutes) {
		this.uidMinutes = uidMinutes;
	}

	public String getMeetingMinutes() {
		return meetingMinutes;
	}

	public void setMeetingMinutes(String meetingMinutes) {
		this.meetingMinutes = meetingMinutes;
	}

	public Integer getMinutesState() {
		return minutesState;
	}

	public void setMinutesState(Integer minutesState) {
		this.minutesState = minutesState;
	}

	public String getUidAuditor() {
		return uidAuditor;
	}

	public void setUidAuditor(String uidAuditor) {
		this.uidAuditor = uidAuditor;
	}

	public String getAuditingDate() {
		return auditingDate;
	}

	public void setAuditingDate(String auditingDate) {
		this.auditingDate = auditingDate;
	}

	public String getAuditingFeedBack() {
		return auditingFeedBack;
	}

	public void setAuditingFeedBack(String auditingFeedBack) {
		this.auditingFeedBack = auditingFeedBack;
	}

	public String getAppFileName() {
		return appFileName;
	}

	public void setAppFileName(String appFileName) {
		this.appFileName = appFileName;
	}

	public String getAppUuidName() {
		return appUuidName;
	}

	public void setAppUuidName(String appUuidName) {
		this.appUuidName = appUuidName;
	}

	public String getMinutesFileName() {
		return minutesFileName;
	}

	public void setMinutesFileName(String minutesFileName) {
		this.minutesFileName = minutesFileName;
	}

	public String getMinutesUuidName() {
		return minutesUuidName;
	}

	public void setMinutesUuidName(String minutesUuidName) {
		this.minutesUuidName = minutesUuidName;
	}

	public String getUnameApplicant() {
		return unameApplicant;
	}

	public void setUnameApplicant(String unameApplicant) {
		this.unameApplicant = unameApplicant;
	}

	public String getUnameMinutes() {
		return unameMinutes;
	}

	public void setUnameMinutes(String unameMinutes) {
		this.unameMinutes = unameMinutes;
	}

	public String getUnameAuditor() {
		return unameAuditor;
	}

	public void setUnameAuditor(String unameAuditor) {
		this.unameAuditor = unameAuditor;
	}
	
	
}
