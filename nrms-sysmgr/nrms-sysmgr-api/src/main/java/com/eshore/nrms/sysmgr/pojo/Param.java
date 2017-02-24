package com.eshore.nrms.sysmgr.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_param")
public class Param implements Serializable {

	private String id;
	private String paramType;
	private String paramName;
	private String paramValue;
	private Integer ipBegin;
	private Integer ipEnd;
	private Integer state;
	
	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "param_type")
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	
	@Column(name = "param_name")
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	@Column(name = "param_value")
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	@Column(name = "ip_begin")
	public Integer getIpBegin() {
		return ipBegin;
	}
	public void setIpBegin(Integer ipBegin) {
		this.ipBegin = ipBegin;
	}
	
	@Column(name = "ip_end")
	public Integer getIpEnd() {
		return ipEnd;
	}
	public void setIpEnd(Integer ipEnd) {
		this.ipEnd = ipEnd;
	}
	
	@Column(name = "state")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	
	
}
