package com.eshore.nrms.sysmgr.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.Serializable;

/**
 * Created by forgeeks at 2017-02-24 8:42
 */

@Entity
@Table(name="c_user")
public class User implements  Serializable{

    private  String id ;
    private String roleId;
    private String role;	//角色名
    
    private String loginName;
    private String loginPw;
    
    private String deptKey;
    private String dept;	//部门名
    
    private String jobKey;
    private String job;	//部门名
    
    private String positionKey;
    private String posi;	//职位名
    
    private String uname;
    private String email;
    private Integer userState;
    
    
    public User(){
    	
    }
    
    public User(String id,String uname) {
		this.id = id;
		this.uname = uname;
	}

    public User(String id, String uname, String email) {
		super();
		this.id = id;
		this.uname = uname;
		this.email = email;
	}

	@Id
    @Column(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name="role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    @Column(name="login_name")
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    @Column(name="login_pw")
    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }
    @Column(name="dept_key")
    public String getDeptKey() {
        return deptKey;
    }

    public void setDeptKey(String deptKey) {
        this.deptKey = deptKey;
    }
    @Column(name="job_key")
    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }
    @Column(name="uname")
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name="user_state")
    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }
    
    @Column(name="position_key")
    public String getPositionKey() {
		return positionKey;
	}

	public void setPositionKey(String positionKey) {
		this.positionKey = positionKey;
	}
	
	
	@Transient
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	@Transient
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Transient
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	@Transient
	public String getPosi() {
		return posi;
	}

	public void setPosi(String posi) {
		this.posi = posi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPw='" + loginPw + '\'' +
                ", deptKey='" + deptKey + '\'' +
                ", jobKey='" + jobKey + '\'' +
                ", uname='" + uname + '\'' +
                ", email='" + email + '\'' +
                ", userState='" + userState + '\'' +
                '}';
    }
	
}
