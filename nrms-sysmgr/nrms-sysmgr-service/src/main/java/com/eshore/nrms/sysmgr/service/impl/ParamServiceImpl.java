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
import com.eshore.nrms.sysmgr.dao.IParamDao;
import com.eshore.nrms.sysmgr.pojo.Param;
import com.eshore.nrms.sysmgr.service.IParamService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ParamServiceImpl extends BaseServiceImpl<Param> implements
		IParamService {
	
	@Autowired
	private IParamDao paramDao;
	
	@Override
	public IBaseDao<Param> getDao() {
		return paramDao;
	}

	@Override
	public List<Param> queryParamByParamType(String paramType) {
		return paramDao.queryParamByParamType(paramType);
	}

	

}
