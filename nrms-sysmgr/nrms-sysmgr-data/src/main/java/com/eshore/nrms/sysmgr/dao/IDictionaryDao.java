package com.eshore.nrms.sysmgr.dao;

import java.util.List;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.Dictionary;

public interface IDictionaryDao extends IBaseDao<Dictionary> {
	
	List<Dictionary> queryAllDictionarys();
	
	List<Dictionary> queryDictionarys(Dictionary dictionary);
	
	List<Dictionary> queryDictionarys(Dictionary dictionary, PageConfig pc);
	

}
