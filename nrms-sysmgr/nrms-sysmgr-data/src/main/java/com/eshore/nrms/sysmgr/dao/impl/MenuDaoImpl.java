package com.eshore.nrms.sysmgr.dao.impl;

import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IMenuDao;
import com.eshore.nrms.sysmgr.pojo.Menu;
import org.apache.commons.lang3.StringUtils;
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
     * @param roleId
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
    public List<Menu> queryMenuListByRoleId(String roleId) {
        String str = " select menu.id , menu.pid,menu.menu_name,menu.menu_url,menu.is_leaf,menu.menu_index      " +
                "from c_menu menu    " +
                "JOIN   (    select menu_id from c_role_menu where role_id= ? ) rm   " +
                "on    menu.id = rm.menu_id     " +
                "order by  menu.id  ";
        StringBuilder hql = new StringBuilder(str);
        List params = new ArrayList();
        if (StringUtils.isNotBlank(roleId))     params.add(roleId);
        else   return null;

        List<Object[]> list = this.querySql(hql.toString(), params.toArray());
        List<Menu> menuList=new ArrayList<Menu>();
        for (int i=0;i<list.size();i++){
            Object[] obj=list.get(i);
            Menu menu= new Menu() ;
            menu.setId(obj[0].toString());
            menu.setPid(obj[1].toString());
            menu.setMenuName(obj[2].toString());
            menu.setMenuUrl(obj[3].toString());
//            menu.setIsLeaf(Integer.valueOf(obj[4].toString()) );
//            menu.setMenuIndex(Integer.valueOf(obj[5].toString() ) );
            menuList.add(menu);
        }
        return menuList;

    }


}