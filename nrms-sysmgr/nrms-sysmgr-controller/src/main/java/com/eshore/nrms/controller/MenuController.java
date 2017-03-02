package com.eshore.nrms.controller;

import com.eshore.nrms.sysmgr.pojo.Menu;
import com.eshore.nrms.sysmgr.service.IMenuService;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.vo.MenuVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
    @RequestMapping("/menu/getJson")
    public ModelAndView getMenuJsonByRoleId() {
        ModelAndView view = new ModelAndView("menu/getJson");
        List<Menu> list = menuService.queryAllMenu();
        JSONArray jsonArray = new JSONArray();
        for(Menu menu : list){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",menu.getId());
            jsonObject.put("pId",menu.getPid());
            jsonObject.put("name",menu.getMenuName());
            jsonObject.put("open",true);
            jsonObject.put("checked",true);
            jsonArray.add(jsonObject);
        }
        String data = jsonArray.toString();
        view.addObject("data", data);
        return view;
    }




}
