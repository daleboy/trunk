package com.eshore.nrms.sysmgr.dao;

import java.util.List;

import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.Param;

public interface IParamDao extends IBaseDao<Param> {

	public List<Param> queryParamByParamType(String paramType);
	
}
