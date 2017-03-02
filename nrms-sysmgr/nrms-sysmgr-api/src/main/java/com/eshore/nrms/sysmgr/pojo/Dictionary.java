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
@Table(name = "c_dictionary")
public class Dictionary implements Serializable{
	
	
	private String id;
	//1：部门    2：工作（web工程师）  3:职位(普通员工）
	private int dicType;
	private String dictype;
	//编码
	private String dicKey;
	//部门值，或者职位值 工作
	private String dicValue;
	//部门或职位描述述
	private String dicDesc;
	//状态
	private int dicState;
	
	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "dic_type")
	public int getDicType() {
		return dicType;
	}

	public void setDicType(int dicType) {
		this.dicType = dicType;
	}
	@Column(name = "dic_key")
	public String getDicKey() {
		return dicKey;
	}

	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}
	@Column(name = "dic_value")
	public String getDicValue() {
		return dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}
	@Column(name = "dic_desc")
	public String getDicDesc() {
		return dicDesc;
	}

	public void setDicDesc(String dicDesc) {
		this.dicDesc = dicDesc;
	}

	
	@Column(name = "dic_state")
	public int getDicState() {
		return dicState;
	}

	public void setDicState(int dicState) {
		this.dicState = dicState;
	}

	@Transient
	public String getDictype() {
		return dictype;
	}

	public void setDictype(String dictype) {
		this.dictype = dictype;
	}

	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", dicType=" + dicType + ", dicKey=" + dicKey + ", dicValue=" + dicValue
				+ ", dicDesc=" + dicDesc + ", dicState=" + dicState + "]";
	}
	
	
	
	
	

}
