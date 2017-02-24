package com.eshore.nrms.sysmgr.service;

import org.springframework.stereotype.Service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.UserInfo;
import com.eshore.nrms.vo.PageVo;

public interface IUserInfoService extends IBaseService<UserInfo> {

	public UserInfo userLogin(String loginName);
	
	public PageVo<UserInfo> queryUserInfoByPage(UserInfo userInfo , PageConfig page);
	
	public Integer getUserCountByLoginName(String loginName , String userId);
	
	public Integer getUserCountByFullIp(String fullIp , String userId);
}
