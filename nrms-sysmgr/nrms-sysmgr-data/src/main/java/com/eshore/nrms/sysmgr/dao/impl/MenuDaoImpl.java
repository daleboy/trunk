package com.eshore.nrms.sysmgr.dao.impl;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IMenuDao;
import com.eshore.nrms.sysmgr.pojo.Menu;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forgeeks at 2017-02-27 17:21
 */
@Repository
public class MenuDaoImpl extends JpaDaoImpl<Menu> implements IMenuDao {



    /**
     * 根据用户角色生成菜单
     *
     * @return
     */

    /*
    ERROR HQL :

    "SELECT menu.id, menu.pid, menu.menu_name, menu.menu_url, menu.is_leaf,menu.menu_index " +
            " FROM Menu menu, JOIN(FROM RoleMenu r where r.roleId = ? )rm   " +
            "ON menu.id = rm.menuId " +
            "ORDER BY menu.id";
    */

    @Override
    public Integer queryCountByName(String menuName) {
        StringBuilder hql= new StringBuilder(" Select Count(*) From Menu m  Where m.menuName=? ");
        List params= new ArrayList();
        params.add(menuName);
        return this.queryCount(hql.toString()  ,params.toArray() );
    }

    /**
     * 获取所有menu
     * @return
     */
    public List<Menu> queryAllMenu(){
        return this.query("From Menu m ",null);
    }


    @Override
    public List<Menu> queryMenuListByRoleId(String roleId) {
        List<Object[]> list = null;
        List<Menu> menuList = null;
        if (!StringUtils.isNotBlank(roleId)) {
            String str = " select menu.id , menu.pid,menu.menu_name,menu.menu_url,menu.is_leaf,menu.menu_index      " +
                    "from c_menu menu    " +
                    "JOIN   (    select menu_id from c_role_menu) rm   " +
                    "on    menu.id = rm.menu_id     " +
                    "order by  menu.menu_index  ";
            StringBuilder hql = new StringBuilder(str);
            List params = new ArrayList();
            list = this.querySql(hql.toString(), params.toArray());
            menuList = new ArrayList<Menu>();

        } else {
            String str = " select menu.id , menu.pid,menu.menu_name,menu.menu_url,menu.is_leaf,menu.menu_index      " +
                    "from c_menu menu    " +
                    "JOIN   (    select menu_id from c_role_menu where role_id= ? ) rm   " +
                    "on    menu.id = rm.menu_id     " +
                    "order by  menu.menu_index  ";
            StringBuilder hql = new StringBuilder(str);
            List params = new ArrayList();
            params.add(roleId);
            list = this.querySql(hql.toString(), params.toArray());
            menuList = new ArrayList<Menu>();
        }
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            Menu menu = new Menu();
            menu.setId(obj[0].toString());
            menu.setPid(obj[1].toString());
            menu.setMenuName(obj[2].toString());
            menu.setMenuUrl(obj[3].toString());
            menuList.add(menu);
        }
        return menuList;

    }


    /**
     * 根据父pid生成子菜单list
     * @param pId
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> queryMenuListByPId(String pId, String roleId) {
        List<Object[]> list = null;
        List<Menu> menuList = null;
        String str = "";
        if (!StringUtils.isNotBlank(pId)) {
            str = " select menu.id , menu.pid,menu.menu_name,menu.menu_url,menu.is_leaf,menu.menu_index      " +
                    "from c_menu menu   JOIN   (    select menu_id from c_role_menu) rm  on    menu.id = rm.menu_id order by  menu.menu_index  ";
        } else {
            str = " select menu.id , menu.pid,menu.menu_name,menu.menu_url,menu.is_leaf,menu.menu_index   from c_menu menu    ,  " +
                    " (    select menu_id ,role_id from c_role_menu)  rm   " +
                    " where    menu.id = rm.menu_id     and menu.pid = '" + pId + "' and rm.role_id = '" + roleId + "'  order by  menu.menu_index";
        }
        StringBuilder hql = new StringBuilder(str);
        List params = new ArrayList();
        list = this.querySql(hql.toString(), params.toArray());
        menuList = new ArrayList<Menu>();
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            Menu menu = new Menu();
            menu.setId(obj[0].toString());
            menu.setPid(obj[1].toString());
            menu.setMenuName(obj[2].toString());
            menu.setMenuUrl(obj[3].toString());
            menuList.add(menu);
        }
        return menuList;

    }

    /**
     *
     * @param menu
     * @param pageConfig
     * @return
     */
    @Override
    public List<Menu> querymenuListByPage(Menu menu, PageConfig pageConfig) {
        StringBuilder hql= new StringBuilder(" From Menu m where 1=1 ");
        List params= new ArrayList();
        if(StringUtils.isNotBlank( menu.getMenuName() )) {
            hql.append("  and m.menuName like  ?");
            params.add("%" + menu.getMenuName() + "%");
        }
        if(menu.getIsLeaf()!=null){
            hql.append("  and m.isLeaf = ?");
            params.add(menu.getIsLeaf());
        }else {
            hql.append("  and m.isLeaf = 1");
        }
        hql.append("order by m.menuIndex");
        return this.queryPage(hql.toString(), pageConfig, params.toArray() );
    }




}