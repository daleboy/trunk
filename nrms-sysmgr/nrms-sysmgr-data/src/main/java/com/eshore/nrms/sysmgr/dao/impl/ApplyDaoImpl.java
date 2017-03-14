package com.eshore.nrms.sysmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IApplyDao;
import com.eshore.nrms.sysmgr.dao.IViewAndAuditDAO;
import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.pojo.Partake;
import com.eshore.nrms.sysmgr.pojo.Place;

@Repository
public class ApplyDaoImpl extends JpaDaoImpl<Application> implements IApplyDao {
	
	@Override
	public List<Application> queryAllApply() {
		return this.query("from Application where 1=1",null);
	}

	@Override
	public List<Application> getApply(Application apply) {
		if(apply == null){
			System.out.println("return queryAllApply()");
            return queryAllApply();
		}
        StringBuilder hql = new StringBuilder("from Application a,Place p where a.placeId=p.id");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(apply, hql, params);

        return this.query(hql.toString(), params.toArray());
	}
	
	@SuppressWarnings("rawtypes")
	private List<Application> initData(List list) {
		List<Application> result = new ArrayList<Application>();
		Object[] arr;
		Application app;
		Place place;
		for (Object obj : list) {
			if(obj instanceof Object[]){
				arr = (Object[]) obj;
				app = (Application) arr[0];
				place = (Place) arr[1];
				app.setPlaceName(place.getPlaceName());
				result.add(app);
			}
		}
		
		return result;
	}
	
	
	private void builderHqlAndParams(Application app,StringBuilder hql, ArrayList<Object> params) {
		if(app == null)
			return;
		
		if(StringUtils.isNotBlank(app.getId())){
			hql.append(" and a.id=?");
			params.add(app.getId());
		}
		if(app.getAppState() != null){
			hql.append(" and a.appState=?");
			params.add(app.getAppState());
		}
		if(StringUtils.isNotBlank(app.getPlaceId())){
			hql.append(" and a.placeId=?");
			params.add(app.getPlaceId());
		}
		if(StringUtils.isNotBlank(app.getSubject())){
			hql.append(" and a.subject like ?");
			params.add("%"+app.getSubject()+"%");
		}
		if(StringUtils.isNotBlank(app.getStartDate())){
			hql.append(" and a.startDate = ?");
			params.add(app.getStartDate());
		}
    }

	@Override
	public List<Application> queryApplys(Application apply, PageConfig pc) {
		if(apply == null)
            return this.queryPage(null,pc,null);

		StringBuilder hql = new StringBuilder("from Application a,Place p where a.placeId=p.id ");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(apply, hql, params);

        return initData(super.queryPage(hql.toString(), pc, params.toArray()));
	}
	
	@Override
	public List<Application> queryApplysByUid(String uid,Application apply, PageConfig pc) {
		if(apply == null)
            return this.queryPage(null,pc,null);

		StringBuilder hql = new StringBuilder("from Application a,Place p where a.placeId=p.id and a.uidApplicant=?");
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(uid);
        builderHqlAndParams(apply, hql, params);
        return initData(super.queryPage(hql.toString(), pc, params.toArray()));
	}

	@Override
	public Integer getCountOfApp(Application app) {
		
		StringBuilder hql = new StringBuilder("select count(*) from Application a,Place p where a.placeId=p.id");
		
		if(app == null){
			return this.queryCount(hql.toString(),null);
		}
		
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(app, hql, params);
		
        return this.queryCount(hql.toString(), params.toArray());
	}

}
