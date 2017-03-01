package com.eshore.nrms.controller;

import com.eshore.nrms.sysmgr.pojo.Menu;
import com.eshore.nrms.sysmgr.service.IMenuService;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.vo.MenuVo;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by forgeeks at 2017-02-27 17:39
 */
@Controller
public class MenuController {

    @Autowired
    IMenuService menuService;
    @Autowired
    IRoleService roleService;

    /**
     * 根据角色获取权限菜单json数据
     * @param roleId
     * @return
     */
    @RequestMapping("/menu/getjson")
    public ModelAndView getMenuJsonByRoleId(String roleId) {
        ModelAndView view = new ModelAndView("menu/getJson");

        List<Menu> list = menuService.queryMenuListByRoleId(roleId);
        JSONArray jsonArray = JSONArray.fromObject(list);
        String data = jsonArray.toString();
        view.addObject("data", data);
        return view;
    }

    /**
     * 测试menu显示是否正常
     * @param roleId
     * @return
     */
    @RequestMapping("/menu/list")
    public ModelAndView getMenuByRoleId(String roleId) {
        String menuId="0sabdvldkjchgbeiubjkn";
        List<MenuVo> voList = new ArrayList<MenuVo>();
        List<Menu> menulist = menuService.queryMenuListByPId(menuId);
        for(Menu menu: menulist){
            MenuVo vo= new MenuVo();
            vo.setThisMenu(menu);
            List<Menu> childlist = menuService.queryMenuListByPId(menu.getId());
            vo.setChildMenus(childlist==null?new ArrayList<Menu>():childlist);
            voList.add(vo);
        }
        ModelAndView view = new ModelAndView("mainPage");
        view.addObject("volist",voList);
        return view;
    }


}
