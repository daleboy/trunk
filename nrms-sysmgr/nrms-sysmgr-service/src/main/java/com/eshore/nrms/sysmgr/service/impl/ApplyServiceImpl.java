package com.eshore.nrms.sysmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import com.eshore.nrms.sysmgr.dao.IApplyDao;
import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.service.IApplyService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ApplyServiceImpl extends BaseServiceImpl<Application> implements IApplyService {
	
	@Autowired
	private IApplyDao applyDao;
	
	@Override
	public List<Application> getAllApply() {
		return applyDao.queryAllApply();
	}

	@Override
	public List<Application> getApply(Application apply) {
		return applyDao.getApply(apply);
	}

	@Override
	public IBaseDao<Application> getDao() {
		return applyDao;
	}

	@Override
	public List<Application> getApplys(Application apply, PageConfig pc) {
		return applyDao.queryApplys(apply, pc);
	}

	@Override
	public Integer getCountOfApp(Application app) {
		return applyDao.getCountOfApp(app);
	}
}
