package com.eshore.nrms.sysmgr.service.impl;

import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import com.eshore.nrms.sysmgr.dao.IMenuDao;
import com.eshore.nrms.sysmgr.pojo.Menu;
import com.eshore.nrms.sysmgr.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by forgeeks at 2017-02-27 17:36
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService{

    @Autowired
    IMenuDao menuDao;

    @Override
    public IBaseDao<Menu> getDao() {
        return this.menuDao;
    }

    @Override
    public List<Menu> queryMenuListByRoleId(String roleId) {
        return  menuDao.queryMenuListByRoleId(roleId);
    }

    @Override
    public List<Menu> queryMenuListByPId(String pId) {
        return  menuDao.queryMenuListByPId(pId);
    }

}
