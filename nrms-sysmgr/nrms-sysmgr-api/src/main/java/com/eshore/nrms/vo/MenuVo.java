package com.eshore.nrms.vo;

import com.eshore.nrms.sysmgr.pojo.Menu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by forgeeks at 2017-02-28 19:11
 */
public class MenuVo implements Serializable{
    private Menu thisMenu;
    private List<Menu> childMenus;

    public Menu getThisMenu() {
        return thisMenu;
    }

    public void setThisMenu(Menu thisMenu) {
        this.thisMenu = thisMenu;
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }
}
