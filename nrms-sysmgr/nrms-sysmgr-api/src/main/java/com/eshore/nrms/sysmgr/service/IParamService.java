package com.eshore.nrms.sysmgr.service;

import java.util.List;

import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.Param;

public interface IParamService extends IBaseService<Param> {

	public List<Param> queryParamByParamType(String paramType);
	
}
