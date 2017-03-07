package com.eshore.nrms.sysmgr.dao;

import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.Partake;

public interface IPartakesDao extends IBaseDao<Partake> {
	public void addAll(String aid, String[] partakesId);
	
	public void deleteByAppId(String aid);
}
