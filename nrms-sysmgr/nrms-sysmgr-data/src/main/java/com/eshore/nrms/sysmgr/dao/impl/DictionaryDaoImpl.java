package com.eshore.nrms.sysmgr.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IDictionaryDao;
import com.eshore.nrms.sysmgr.pojo.Dictionary;
import com.eshore.nrms.sysmgr.pojo.Place;
@Repository
public class DictionaryDaoImpl extends JpaDaoImpl<Dictionary> implements IDictionaryDao {

	@Override
	public List<Dictionary> queryAllDictionarys() {
		// TODO Auto-generated method stub
		return this.query("from Dictionary where dicState=1", null);
	}

	@Override
	public List<Dictionary> queryDictionarys(Dictionary dictionary) {
		// TODO Auto-generated method stub
		if(dictionary==null)
			return queryAllDictionarys();
		StringBuilder hql = new StringBuilder("from Dictionary where dicState=1");
		ArrayList<Object> params = new ArrayList<Object>();
		builderHqlAndParams(dictionary, hql, params);
		System.out.println("打印hql:"+hql.toString());
		return this.query(hql.toString(), params.toArray());
	}

	@Override
	public List<Dictionary> queryDictionarys(Dictionary dictionary, PageConfig pc) {
		// TODO Auto-generated method stub
		//this.querySql(sql, null);
		return null;
	}
	
    private void builderHqlAndParams(Dictionary dictionary, StringBuilder hql, ArrayList<Object> params) {
        if(StringUtils.isNotBlank(dictionary.getId())){
            hql.append(" and id=?");
            params.add(dictionary.getId());
        }
        if(StringUtils.isNotBlank(dictionary.getDicKey())){
            hql.append(" and dicKey=?");
            params.add(dictionary.getDicKey());
        }
        if(StringUtils.isNotBlank(dictionary.getDicDesc())){
            hql.append(" and dicDesc=?");
            params.add(dictionary.getDicDesc());
        }
        if(dictionary.getDicType()!=0){
            hql.append(" and dicType=?");
            params.add(dictionary.getDicType());
        }
        if(StringUtils.isNotBlank(dictionary.getDicValue())){
            hql.append(" and dicValue like ?");
            params.add("%"+dictionary.getDicValue()+"%");
        }
        
        
    }

	@Override
	public Dictionary queryDictionaryByDickey(String dickey) {
		// TODO Auto-generated method stub
		String hql = "from Dictionary d where d.dicKey = ?";
        return this.getPojo(hql, new Object[]{dickey});
	}

	@Override
	public Dictionary queryDictionaryByDicValue(String dicvalue) {
		// TODO Auto-generated method stub
		String hql = "from Dictionary d where d.dicValue = ?";
        return this.getPojo(hql, new Object[]{dicvalue});
	}

	

}
