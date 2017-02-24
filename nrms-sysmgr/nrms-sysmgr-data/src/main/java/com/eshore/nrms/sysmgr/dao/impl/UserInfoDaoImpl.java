package com.eshore.nrms.sysmgr.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IUserInfoDao;
import com.eshore.nrms.sysmgr.pojo.UserInfo;

@Repository
public class UserInfoDaoImpl extends JpaDaoImpl<UserInfo> implements
		IUserInfoDao {


	@Override
	public UserInfo queryUserByLoginName(String loginName) {
		
		String hql = "from UserInfo u where u.loginName = ?";
		
		return this.getPojo(hql, new Object[]{loginName});
	}
	
	@Override
	public List<UserInfo> queryUserList(UserInfo userInfo, PageConfig page) {
		
		StringBuilder hql = new StringBuilder("from UserInfo u where 1=1 ");
		List params = new ArrayList();
		if(StringUtils.isNotBlank(userInfo.getLoginName())){
			hql.append(" and u.loginNmae like ? ");
			params.add("%" + userInfo.getLoginName() + "%");
		}
		
		if(StringUtils.isNotBlank(userInfo.getUserName())){
			hql.append(" and u.userName like ? ");
			params.add("%" + userInfo.getUserName() + "%");
		}
		
		if(StringUtils.isNotBlank(userInfo.getFullIp())){
			hql.append(" and u.fullIp like ? ");
			params.add("%" + userInfo.getFullIp() + "%");
		}
		
		hql.append(" and u.state = 1");
		hql.append(" order by loginName ");
		
		return this.queryPage(hql.toString(), page, params.toArray());
		
	}

	@Override
	public Integer getUserCountByLoginName(String loginName, String userId) {
		StringBuilder hql = new StringBuilder("select count(*) from UserInfo u where u.state = 1 and u.loginName = ? ");
		ArrayList params = new ArrayList();
		params.add(loginName);
		
		if(StringUtils.isNotBlank(userId)){
			hql.append(" and u.id != ? ");
			params.add(userId);
		}
		
		return this.queryCount(hql.toString(), params.toArray());
	}

	@Override
	public Integer getUserCountByFullIp(String fullIp, String userId) {
		StringBuilder hql = new StringBuilder("select count(*) from UserInfo u where u.state = 1 and u.fullIp = ? ");
		ArrayList params = new ArrayList();
		params.add(fullIp);
		
		if(StringUtils.isNotBlank(userId)){
			hql.append(" and u.id != ? ");
			params.add(userId);
		}
		
		return this.queryCount(hql.toString(), params.toArray());
	}

}
