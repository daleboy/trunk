package com.eshore.nrms.sysmgr.dao;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.Role;

import java.util.List;

/**
 * Created by forgeeks at 2017-02-25 16:31
 */
public interface  IRoleDao extends IBaseDao<Role>{

    public List<Role>  queryRoleListByPage(Role role , PageConfig page);

}
