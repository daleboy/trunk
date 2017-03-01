package com.eshore.nrms.sysmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IApplyDao;
import com.eshore.nrms.sysmgr.pojo.Application;

@Repository
public class ApplyDaoImpl extends JpaDaoImpl<Application> implements IApplyDao {

	@Override
	public List<Application> queryAllApply() {
		return this.query("from Application where 1=1",null);
	}

	@Override
	public List<Application> getApply(Application apply) {
		if(apply == null)
            return queryAllApply();
        StringBuilder hql = new StringBuilder("from Application where 1=1");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(apply, hql, params);

        return this.query(hql.toString(), params.toArray());
	}
	
	private void builderHqlAndParams(Application apply, StringBuilder hql, ArrayList<Object> params) {
		System.out.println("这里是builderHqlAndParams "+apply.getId());
        if(StringUtils.isNotBlank(apply.getPlaceName())){
            hql.append(" and placeName like ?");
            params.add(apply.getPlaceId());
        }
        if(StringUtils.isNotBlank(apply.getSubject())){
            hql.append(" and subject like ?");
            params.add("%" + apply.getSubject() + "%");
        }
        if(apply.getAppState() != null){
            hql.append(" and appState=?");
            params.add("%"+apply.getAppState()+"%");
        }
    }

	@Override
	public List<Application> queryApplys(Application apply, PageConfig pc) {
		if(apply == null)
            return this.queryPage(null,pc,null);

		StringBuilder hql = new StringBuilder("from Application where 1=1");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(apply, hql, params);

        return this.queryPage(hql.toString(), pc, params.toArray());
	}

	@Override
	public Integer getCountOfApp(Application app) {
		
		StringBuilder hql = new StringBuilder("select count(*) from Application where 1=1");
		
		if(app == null){
			return this.queryCount(hql.toString(),null);
		}
		
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(app, hql, params);
		
        return this.queryCount(hql.toString(), params.toArray());
	}

}
