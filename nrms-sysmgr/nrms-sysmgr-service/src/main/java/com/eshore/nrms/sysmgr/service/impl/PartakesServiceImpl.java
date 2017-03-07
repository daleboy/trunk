package com.eshore.nrms.sysmgr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import com.eshore.nrms.sysmgr.dao.IPartakesDao;
import com.eshore.nrms.sysmgr.pojo.Partake;
import com.eshore.nrms.sysmgr.service.IPartakesService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PartakesServiceImpl extends BaseServiceImpl<Partake> implements IPartakesService {

	@Autowired
	private IPartakesDao partakesDao;
	

	@Override
	public void update(String aid, String[] partakes) {
		partakesDao.deleteByAppId(aid);
		partakesDao.addAll(aid, partakes);
	}

	@Override
	public IBaseDao<Partake> getDao() {
		return partakesDao;
	}

	@Override
	public void deleteByAppId(String aid) {
		partakesDao.deleteByAppId(aid);
	}


}
