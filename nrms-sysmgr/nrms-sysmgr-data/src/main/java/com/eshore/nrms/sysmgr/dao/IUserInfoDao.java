package com.eshore.nrms.sysmgr.dao;

import java.util.List;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.UserInfo;

public interface IUserInfoDao extends IBaseDao<UserInfo> {

	public UserInfo queryUserByLoginName(String loginName);
	
	public List<UserInfo> queryUserList(UserInfo userInfo ,  PageConfig page);
	
	public Integer getUserCountByLoginName(String loginName, String userId);
	
	public Integer getUserCountByFullIp(String fullIp , String userId);
	
	
}
