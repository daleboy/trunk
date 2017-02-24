package com.eshore.nrms.vo;

public class Conts {
	
	public static final String USER_SESSION_KEY = "LOGIN_USER";
	public static final String VALIDATE_CODE_KEY = "VALIDATE_CODE";
	
	public static final String[] PARAM_TYPE = new String[]{
		"user_ip",   //用户IP
		"server_ip", //服务器IP
		"vm_ip",     //虚拟机IP
		"os",        //操作系统
		"site" ,     //区域
		"ip_require_msg"  //IP要求
	};
	
	public static final Integer STATE_OK = 1;
	public static final Integer STATE_DELETE = 0;

}
