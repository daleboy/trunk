package com.eshore.nrms.sysmgr.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_user")
public class UserInfo implements Serializable {

	private String id;  //主键
	private String loginName; //登陆账号
	private String userName;  //姓名
	private String userRole;  //角色   1:管理员  2:中心经理  3:员工
	private String email;     //邮箱
	private String pwd;       //登录密码
	private String ipSegmentId; //IP段信息ID
	private String ipSegment;    //IP段参数值
	private Integer ipNum;       //IP值
	private String fullIp;        //完整IP信息
	private String site;          //所在区域参数值
	private String seatNum;       //座位号
	private String userCode;      //工号
	private Integer state;        //状态 0删除   1正常
	
	@Id
	@Column(name="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="login_name")
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="user_role")
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="pwd")
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Column(name="ip_segment_id")
	public String getIpSegmentId() {
		return ipSegmentId;
	}
	public void setIpSegmentId(String ipSegmentId) {
		this.ipSegmentId = ipSegmentId;
	}
	
	@Column(name="ip_segment")
	public String getIpSegment() {
		return ipSegment;
	}
	public void setIpSegment(String ipSegment) {
		this.ipSegment = ipSegment;
	}
	
	@Column(name="ip_num")
	public Integer getIpNum() {
		return ipNum;
	}
	public void setIpNum(Integer ipNum) {
		this.ipNum = ipNum;
	}
	
	@Column(name="full_ip")
	public String getFullIp() {
		return fullIp;
	}
	public void setFullIp(String fullIp) {
		this.fullIp = fullIp;
	}
	
	@Column(name="site")
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
	@Column(name="seat_num")
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	
	@Column(name="user_code")
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Column(name="state")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
	
	
	
}
