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
import com.eshore.nrms.sysmgr.dao.IDictionaryDao;
import com.eshore.nrms.sysmgr.pojo.Dictionary;
import com.eshore.nrms.sysmgr.service.IDictionaryService;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements IDictionaryService {

	@Autowired
	private IDictionaryDao dictionaryDao;
	
	
	@Override
	public List<Dictionary> getAllDictionarys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dictionary> getDictionarys(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return dictionaryDao.queryDictionarys(dictionary);
	}

	@Override
	public List<Dictionary> getDictionarys(Dictionary dictionary, PageConfig pc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountOfDictionary(Dictionary dictionary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBaseDao<Dictionary> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
