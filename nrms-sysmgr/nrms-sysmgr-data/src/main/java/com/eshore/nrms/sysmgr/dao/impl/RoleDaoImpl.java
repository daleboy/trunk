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

    /**
     * 分页查询
     * @param role
     * @param page
     * @return
     */

    @Override
    public List<Role> queryRoleListByPage(Role role, PageConfig page) {
        StringBuilder hql = new StringBuilder("from Role r where 1=1 ");
        List params = new ArrayList();
        if (StringUtils.isNotBlank(role.getRoleName())) {
            hql.append(" and r.roleName like ? ");
            params.add("%" + role.getRoleName() + "%");
        }
        hql.append(" order by roleName ");
        return this.queryPage(hql.toString(), page, params.toArray());
    }

    /**
     * 查询使用某角色名的角色个数
     * @param roleName
     * @return
     */
    @Override
    public Integer queryCountByRoleName(String roleName) {
        return getCount("select count(*) from c_role where role_name='" + roleName + "'");
    }

    /**
     *计算某角色下用户名数量
     * @param id
     * @return
     */
    @Override
    public Integer queryCountOfRoleById(String id) {
        return getCount("select count(*) from c_user  where role_id='" + id + "'");
    }


}
