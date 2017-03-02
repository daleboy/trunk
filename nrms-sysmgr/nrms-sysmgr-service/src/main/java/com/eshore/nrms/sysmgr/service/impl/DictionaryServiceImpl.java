package com.eshore.nrms.sysmgr.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.vo.PageVo;
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
	public PageVo<Dictionary> getDictionaryByPage(Dictionary dictionary , PageConfig page) {
		// TODO Auto-generated method stub
		List<Dictionary> list = dictionaryDao.queryDictionarys(dictionary);
		List<Dictionary> dictionarieList = new ArrayList<Dictionary>();
		for (Dictionary dictionary2 : list) {
			switch (dictionary2.getDicType()) {
			/**
			 * 1：部门
			 * 2：工作（web工程师）  
			 * 3:职位(普通员工）
			 */
				case 1:
					dictionary2.setDictype("部门");
					break;
				case 2:
					dictionary2.setDictype("工作");
					break;
				case 3:
					dictionary2.setDictype("职位");
					break;
				default:
					break;
			}
			dictionarieList.add(dictionary2);
		}
		return PageUtil.getPageList(page, dictionarieList);
	}

	@Override
	public List<Dictionary> getDictionarys(Dictionary dictionary ) {
		// TODO Auto-generated method stub
		List<Dictionary> list = dictionaryDao.queryDictionarys(dictionary);
		List<Dictionary> dictionarieList = new ArrayList<Dictionary>();
		for (Dictionary dictionary2 : list) {
			switch (dictionary2.getDicType()) {
			/**
			 * 1：部门
			 * 2：工作（web工程师）  
			 * 3:职位(普通员工）
			 */
				case 1:
					dictionary2.setDictype("部门");
					break;
				case 2:
					dictionary2.setDictype("工作");
					break;
				case 3:
					dictionary2.setDictype("职位");
					break;
				default:
					break;
			}
			dictionarieList.add(dictionary2);
		}
		return dictionarieList;
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
		return dictionaryDao;
	}

	@Override
	public Dictionary getDictionaryByDickey(String dickey) {
		// TODO Auto-generated method stub
		return dictionaryDao.queryDictionaryByDickey(dickey);
	}

	@Override
	public Dictionary getDictionaryByDicValue(String dicvalue) {
		// TODO Auto-generated method stub
		return dictionaryDao.queryDictionaryByDicValue(dicvalue);
	}

	

}
