package com.eshore.nrms.sysmgr.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "a_partakes")
public class Partake implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6860725014179899399L;

	@Id
    @GenericGenerator(name="uuidGen",strategy="uuid")
    @GeneratedValue(generator="uuidGen")
    @Column(name = "id")
	private String id;
	
	@Column(name = "app_id")
	private String appId;
	
	@Column(name = "user_id")
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
