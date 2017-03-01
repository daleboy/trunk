package com.eshore.nrms.sysmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IViewAndAuditDAO;
import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.pojo.Place;
import com.eshore.nrms.sysmgr.pojo.User;

@Repository
public class ViewAndAuditDAOImpl extends JpaDaoImpl<Application> implements IViewAndAuditDAO{

public List<Application> queryFull(Map<String, Object> map) {
		
		return queryFullPage(map, null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Application> queryFull(Application app) {
		StringBuilder hql = new StringBuilder("from Application a,Place p where a.placeId=p.id ");
		List<Object> params = new ArrayList<Object>();
		initializeQueryCondition(app, hql, params);
		List list = super.query(hql.toString(), params.toArray());
		return initData(list);
	}

	@SuppressWarnings({"rawtypes" })
	@Override
	public List<Application> queryFullPage(Map<String, Object> map, PageConfig pc) {
		StringBuilder hql = new StringBuilder("from Application a,Place p where a.placeId=p.id ");
		
		List<Object> params = new ArrayList<Object>();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			hql.append(" and ").append(key).append(" = ?");
			params.add(map.get(key));
		}
		
		List list ;
		if(pc == null)
			list = query(hql.toString(), params.toArray());
		else 
			list = queryPage(hql.toString(), pc, params.toArray());
		
		return initData(list);
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

	@SuppressWarnings("rawtypes")
	@Override
	public List<Application> queryFullPage(Application app, PageConfig pc) {
		StringBuilder hql = new StringBuilder("from Application a,Place p where a.placeId=p.id ");
		List<Object> params = new ArrayList<Object>();
		initializeQueryCondition(app, hql, params);
		List list = super.queryPage(hql.toString(), pc, params.toArray());
		return initData(list);
	}

	@Override
	public List<Application> queryFullPageByUid(String uid, Application app, PageConfig pc) {
		StringBuilder hql = new StringBuilder("from Application a,Place p where a.placeId=p.id and a.appState = 2 and a.id in (select appId from Partake where userId=?)");
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
		initializeQueryCondition(app, hql, params);
		return initData(super.queryPage(hql.toString(), pc, params.toArray()));
	}

	private void initializeQueryCondition(Application app,StringBuilder sb,List<Object> params){
		if(app == null)
			return;
		
		if(StringUtils.isNotBlank(app.getId())){
			sb.append(" and a.id=?");
			params.add(app.getId());
		}
		if(StringUtils.isNotBlank(app.getUidApplicant())){
			sb.append(" and a.uidApplicant=?");
			params.add(app.getUidApplicant());
		}
		if(app.getAppState() != null){
			sb.append(" and a.appState=?");
			params.add(app.getAppState());
		}
		if(StringUtils.isNotBlank(app.getPlaceId())){
			sb.append(" and a.placeId=?");
			params.add(app.getPlaceId());
		}
		if(StringUtils.isNotBlank(app.getSubject())){
			sb.append(" and a.subject like ?");
			params.add("%"+app.getSubject()+"%");
		}
		
	}

	@Override
	public Integer queryCount(Application app) {
		StringBuilder hql = new StringBuilder("select count(id) from Application a where 1=1");
		List<Object> params = new ArrayList<Object>();
		initializeQueryCondition(app, hql, params);
		return super.queryCount(hql.toString(), params.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryUserInApplication(String appId) {
		String hql = "select new User(u.id,u.uname) from User u where u.id in (select p.userId from Partake p where p.appId=?)";
		Query query = super.entityManager.createQuery(hql);
		query.setParameter(1, appId);
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<String> getUsersByIds(List<String> uids) {
		StringBuilder hql = new StringBuilder("select uname from User where id in ('qwe'");
		for (String uid : uids) {
			hql.append(",?");
		}
		hql.append(")");
		Query query = super.entityManager.createQuery(hql.toString());
		for (int i = 1; i <= uids.size(); i++) {
			query.setParameter(i, uids.get(i-1));
		}
		return query.getResultList();
	}

	@Override
	public boolean verifyTimeConflict(Application app) {
		String hql = "select count(a.id) from Application a where a.appState=2 and  a.startDate=? and ((a.startTime <= ? and a.endTime > ?) or (a.startTime < ? and a.endTime >=?))";
		int count = super.queryCount(hql, new Object[]{app.getStartDate(),app.getStartTime(),app.getStartTime(),app.getEndTime(),app.getEndTime()});
		return count > 0;
	}

}
