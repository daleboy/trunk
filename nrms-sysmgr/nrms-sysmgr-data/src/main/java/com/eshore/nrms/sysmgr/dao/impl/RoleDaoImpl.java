package com.eshore.nrms.sysmgr.dao.impl;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IRoleDao;
import com.eshore.nrms.sysmgr.pojo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forgeeks at 2017-02-25 16:41
 */
@Repository
public class RoleDaoImpl extends JpaDaoImpl<Role> implements IRoleDao {
    @Override
    public List<Role> queryRoleListByPage(Role role, PageConfig page) {

        StringBuilder hql = new StringBuilder("from Role r where 1=1 ");
        List params = new ArrayList();
        if (StringUtils.isNotBlank(role.getRoleName())) {
            hql.append(" and r.roleName like ? ");
            params.add("%" + role.getRoleName() + "%");
        }
        hql.append(" and r.roleState=1");
        hql.append(" order by roleName ");

        return this.queryPage(hql.toString(), page, params.toArray());

    }
}
