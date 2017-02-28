package com.eshore.nrms.sysmgr.dao;

import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.Menu;

import java.util.List;

/**
 * Created by forgeeks at 2017-02-27 17:19
 */
public interface IMenuDao  extends IBaseDao<Menu> {
    public List<Menu> queryMenuListByRoleId(String roleId);
}
