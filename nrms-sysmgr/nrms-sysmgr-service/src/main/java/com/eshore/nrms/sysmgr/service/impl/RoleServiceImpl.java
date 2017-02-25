package com.eshore.nrms.sysmgr.service.impl;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import com.eshore.nrms.sysmgr.dao.IRoleDao;
import com.eshore.nrms.sysmgr.pojo.Role;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by forgeeks at 2017-02-25 16:48
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Override
    public IBaseDao<Role> getDao() {
        return roleDao;
    }

    @Override
    public PageVo<Role> queryRoleListByPage(Role role, PageConfig page) {
        List<Role> list = roleDao.queryRoleListByPage(role, page);
        return PageUtil.getPageList(page,list);
    }
}
