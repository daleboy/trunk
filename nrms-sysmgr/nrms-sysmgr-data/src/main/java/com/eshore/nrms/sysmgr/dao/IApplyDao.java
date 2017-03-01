package com.eshore.nrms.sysmgr.dao;

import java.util.List;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.Application;

public interface IApplyDao extends IBaseDao<Application> {
	
     public List<Application> queryAllApply();
     
	 public List<Application> getApply(Application apply);
	 
	 public List<Application> queryApplys(Application apply, PageConfig pc);
 	 
	 public Integer getCountOfApp(Application app);
	 
}