package com.eshore.nrms.sysmgr.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import com.eshore.nrms.sysmgr.dao.IUserInfoDao;
import com.eshore.nrms.sysmgr.pojo.UserInfo;
import com.eshore.nrms.sysmgr.service.IUserInfoService;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.vo.PageVo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements
		IUserInfoService {

	@Autowired
	private IUserInfoDao userInfoDao;
	
	@Override
	public IBaseDao<UserInfo> getDao() {
		return userInfoDao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserInfo userLogin(String loginName) {
		UserInfo userInfo = this.userInfoDao.queryUserByLoginName(loginName);
		return userInfo;
	}

	@Override
	public PageVo<UserInfo> queryUserInfoByPage(UserInfo userInfo,
			PageConfig page) {
		List<UserInfo> list = this.userInfoDao.queryUserList(userInfo, page);
		return PageUtil.getPageList(page, list);
	}

	@Override
	public Integer getUserCountByLoginName(String loginName, String userId) {
		return this.userInfoDao.getUserCountByLoginName(loginName , userId);
	}

	@Override
	public Integer getUserCountByFullIp(String fullIp, String userId) {
		return this.userInfoDao.getUserCountByFullIp(fullIp, userId);
	}

	

}
