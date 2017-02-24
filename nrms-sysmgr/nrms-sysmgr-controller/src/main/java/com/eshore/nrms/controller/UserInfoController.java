package com.eshore.nrms.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.nrms.sysmgr.pojo.Param;
import com.eshore.nrms.sysmgr.pojo.UserInfo;
import com.eshore.nrms.sysmgr.service.IUserInfoService;
import com.eshore.nrms.util.SecurityUtil;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;
import com.eshore.nrms.vo.InitData;
import com.eshore.nrms.vo.PageVo;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private IUserInfoService userInfoService;
	
	@RequestMapping("/userInfoList")
	public ModelAndView  userInfoList(UserInfo userInfo , PageConfig page){
		ModelAndView view = new ModelAndView("userInfo/userList");
		PageVo<UserInfo> userInfoList = this.userInfoService.queryUserInfoByPage(userInfo, page);
		
		view.addObject("page" , userInfoList);
		view.addObject("searchParam" , userInfo);
		return view;
	}
	
	@RequestMapping("/toAddOrEditUser") 
	public ModelAndView  toAddOrEditUser(String id){
		ModelAndView view = new ModelAndView("userInfo/addOrEditUser");
		
		if(StringUtils.isNotBlank(id)){
			UserInfo user = this.userInfoService.get(id);
			view.addObject("user" , user);
		}
		
		view.addObject("siteList" , InitData.paramListTable.get("site"));
		view.addObject("ipList" , InitData.paramListTable.get("user_ip"));
		return view;
	}
	
	
	
	@RequestMapping("/saveOrUpdateUser") 
	@ResponseBody
	public ExecResult saveOrUpdateUser(UserInfo userInfo){
		ExecResult result = new ExecResult();
		
		Param param = InitData.paramMapTable.get("user_ip").get(userInfo.getIpSegmentId());
		String ipSegment = param.getParamValue();
		if(!ipSegment.endsWith(".")){
			ipSegment = ipSegment+".";
		}
		userInfo.setIpSegment(ipSegment);
		userInfo.setFullIp(ipSegment + userInfo.getIpNum());
		
		int count = this.userInfoService.getUserCountByLoginName(userInfo.getLoginName(), userInfo.getId());
		if(count > 0){
			result.setMsg("帐号已经存在");
			result.setSuccess(false);
		}
		
		count = this.userInfoService.getUserCountByFullIp(userInfo.getFullIp(), userInfo.getId());
		if(count > 0){
			result.setMsg("IP已被使用");
			result.setSuccess(false);
		}
		
		
		
		if(StringUtils.isBlank(userInfo.getId())){
			userInfo.setId(null);
			String pwd = SecurityUtil.md5("123456");
			userInfo.setPwd(pwd);
			userInfo.setState(Conts.STATE_OK);
			this.userInfoService.save(userInfo);
		}else{
			UserInfo user = this.userInfoService.get(userInfo.getId());
			userInfo.setState(user.getState());
			userInfo.setPwd(user.getPwd());
			this.userInfoService.update(userInfo);
		}
		result.setMsg("保存成功");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping("/deleteUser") 
	@ResponseBody
	public ExecResult deleteUser(String id){
		UserInfo user = this.userInfoService.get(id);
		user.setState(Conts.STATE_DELETE);
		this.userInfoService.update(user);

		ExecResult result = new ExecResult();
		result.setMsg("删除成功");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping("/resetUserPwd") 
	@ResponseBody
	public ExecResult resetUserPwd(String id){
		UserInfo user = this.userInfoService.get(id);
		user.setPwd(SecurityUtil.md5("123456"));
		this.userInfoService.update(user);

		ExecResult result = new ExecResult();
		result.setMsg("重置密码成功");
		result.setSuccess(true);
		return result;
	}
	
	
	@RequestMapping("/viewUserInfo") 
	public ModelAndView viewUserInfo(String id){
		
		ModelAndView view = new ModelAndView("userInfo/viewUser");
		UserInfo user = this.userInfoService.get(id);
		
		view.addObject("user" , user);
		return view;
	}
	
	
	
}
