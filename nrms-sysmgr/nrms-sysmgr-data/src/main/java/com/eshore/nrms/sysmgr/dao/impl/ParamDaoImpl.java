package com.eshore.nrms.sysmgr.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IParamDao;
import com.eshore.nrms.sysmgr.pojo.Param;

@Repository
public class ParamDaoImpl extends JpaDaoImpl<Param> implements IParamDao {


	@Override
	public List<Param> queryParamByParamType(String paramType) {
		String hql = "from Param where paramType = ? and state = 1 order by paramName";
		return this.query(hql, new Object[]{paramType});
	}

}
