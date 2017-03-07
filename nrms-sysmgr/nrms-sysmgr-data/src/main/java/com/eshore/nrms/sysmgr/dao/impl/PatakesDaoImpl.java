package com.eshore.nrms.sysmgr.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IPartakesDao;
import com.eshore.nrms.sysmgr.pojo.Partake;

@Repository
public class PatakesDaoImpl extends JpaDaoImpl<Partake> implements IPartakesDao {

	@Override
	public void addAll(String aid, String[] partakesId) {
		if (partakesId == null || aid == null)
			return;
		Partake p = null;
		for (String uid : partakesId) {
			p = new Partake();
			p.setUserId(uid);
			p.setAppId(aid);
			super.save(p);
		}

	}

	@Override
	public void deleteByAppId(String aid) {
		Query query = super.entityManager.createQuery("delete from Partake where appId=?");
		query.setParameter(1, aid);
		query.executeUpdate();
	}

}
