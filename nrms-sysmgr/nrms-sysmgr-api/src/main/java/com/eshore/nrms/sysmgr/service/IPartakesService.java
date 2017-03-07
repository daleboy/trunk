package com.eshore.nrms.sysmgr.service;

import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.Partake;

public interface IPartakesService extends IBaseService<Partake> {
	
	public void update(String aid, String[] partakes);
	
	public void deleteByAppId(String aid);
}
