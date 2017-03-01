package com.eshore.nrms.sysmgr.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "c_dictionary")
public class Dictionary implements Serializable{
	
	@Id
	@GenericGenerator(name="uuidGen",strategy="uuid")
	@GeneratedValue(generator="uuidGen")
	@Column(name = "id")
	private String id;
	
	@Column(name = "dic_type")		//1：部门    2：工作（web工程师）  3:职位(普通员工）
	private int dicType;
	
	@Column(name = "dic_key")		//编码
	private String dicKey;
	
	@Column(name = "dic_value")		//部门值，或者职位值 工作
	private String dicValue;
	
	@Column(name = "dic_desc")		//部门或职位描述述
	private String dicDesc;
	
	@Column(name = "dic_state")		//状态
	private String dicState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDicType() {
		return dicType;
	}

	public void setDicType(int dicType) {
		this.dicType = dicType;
	}

	public String getDicKey() {
		return dicKey;
	}

	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}

	public String getDicValue() {
		return dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	public String getDicDesc() {
		return dicDesc;
	}

	public void setDicDesc(String dicDesc) {
		this.dicDesc = dicDesc;
	}

	public String getDicState() {
		return dicState;
	}

	public void setDicState(String dicState) {
		this.dicState = dicState;
	}

	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", dicType=" + dicType + ", dicKey=" + dicKey + ", dicValue=" + dicValue
				+ ", dicDesc=" + dicDesc + ", dicState=" + dicState + "]";
	}
	
	
	
	
	

}
