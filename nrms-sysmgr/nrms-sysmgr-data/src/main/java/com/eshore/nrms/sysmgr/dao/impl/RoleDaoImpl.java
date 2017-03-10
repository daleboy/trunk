package com.eshore.nrms.sysmgr.dao.impl;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IRoleDao;
import com.eshore.nrms.sysmgr.pojo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by forgeeks at 2017-02-25 16:41
 */
@Repository
public class RoleDaoImpl extends JpaDaoImpl<Role> implements IRoleDao {

    /**
     * 分页查询
     *
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
     *
     * @param roleName
     * @return
     */
    @Override
    public Integer queryCountByRoleName(String roleName) {
        return getCount("select count(*) from c_role where role_name='" + roleName + "'");
    }

    /**
     * 计算某角色下用户名数量
     *
     * @param id
     * @return
     */
    @Override
    public Integer queryCountOfRoleById(String id) {
        return getCount("select count(*) from c_user  where user_state=1 and  role_id='" + id + "'");
    }

    /**
     * 为角色分配权限
     *
     * @param role
     * @param the403Menus
     */
    @Override
    public void distributeAccees(Role role, String[] the403Menus) {
        for (String menu : the403Menus) {
            String sql = "insert into c_role_menu ( id ,role_id,menu_id ) values ( '"
                    + UUID.randomUUID().toString().substring(0, 31)
                    + "','" + role.getId() + "' , '" + menu + "') ";
            this.executeSql(sql);
        }
    }

    @Override
    public void updateAccess(Role role, String[] menus) {
        StringBuilder sql1 = new StringBuilder("Delete From  c_role_menu Where role_id='" + role.getId() + "'");
        if(StringUtils.isNotBlank(role.getId()) ) this.executeSql(sql1.toString());
        if (menus != null) {
            StringBuilder sql2 = new StringBuilder("");
            for (String menu : menus) {
                sql2 = new StringBuilder("Insert Into  c_role_menu (id,role_id, menu_id)   values  ('"
                        + UUID.randomUUID().toString().substring(0, 31) + "','" + role.getId() + "' ,  '" + menu + "')");
                this.executeSql(sql2.toString());
            }
        }

    }


}
