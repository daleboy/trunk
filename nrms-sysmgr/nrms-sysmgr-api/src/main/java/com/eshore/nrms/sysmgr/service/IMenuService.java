package com.eshore.nrms.sysmgr.service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.Menu;
import com.eshore.nrms.vo.MenuVo;

import java.util.List;

/**
 * Created by forgeeks at 2017-02-27 17:11
 */
public interface IMenuService extends IBaseService<Menu>{

    /**
     * 通过用户角色来显示不同用户菜单项
     * @param roleId
     * @return
     */
    public List<Menu> queryMenuListByRoleId(String roleId);

    /***
     * 可拓展成单独的菜单管理
     * @param menu
     * @param pageConfig
     * @return
     */
//    public List<Menu> querymenuListByPage(Menu menu, PageConfig pageConfig);

    /**
     * 通过pid查询子菜单项
     * @param pId
     * @return
     */
    public List<Menu> queryMenuListByPId(String pId, String roleId);

    /**
     * 获取某角色菜单vo，供前台显示
     * @param pId
     * @param roleId
     * @return
     */
    public List<MenuVo> queryMenuVoList(String roleId) ;
}
