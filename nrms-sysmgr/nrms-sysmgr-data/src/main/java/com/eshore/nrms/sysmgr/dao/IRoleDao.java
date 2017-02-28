package com.eshore.nrms.sysmgr.dao;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.Role;

import java.util.List;

/**
 * Created by forgeeks at 2017-02-25 16:31
 */
public interface IRoleDao extends IBaseDao<Role> {


    /**
     * 分页查询
     * @param role
     * @param page
     * @return
     */

    public List<Role> queryRoleListByPage(Role role, PageConfig page);


    /**
     * 查询使用某角色名的角色个数
     * @param roleName
     * @return
     */
    public Integer queryCountByRoleName(String roleName);


    /**
     * 计算某角色下用户名数量
     * @param id
     * @return
     */
    public Integer queryCountOfRoleById(String id);


    /**
     * 为角色分配权限
     * @param role
     * @param the403Menus
     */
    public void  distributeAccees(Role role , String[]  the403Menus );

    /***
     * 更新角色权限
     * @param role
     * @param menus
     */
    public void updateAccess(Role role, String[] menus);

}
