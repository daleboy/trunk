package com.eshore.nrms.controller;

import com.eshore.nrms.sysmgr.pojo.Menu;
import com.eshore.nrms.sysmgr.pojo.Role;
import com.eshore.nrms.sysmgr.service.IMenuService;
import com.eshore.nrms.sysmgr.service.IRoleService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by forgeeks at 2017-02-27 17:39
 */
@Controller
public class MenuController {

    @Autowired
     IMenuService menuService;
    @Autowired
    IRoleService roleService;


    @RequestMapping("/menu/getjson")
    public ModelAndView getMenuJsonByRoleId(String roleId){
        ModelAndView view= new ModelAndView("menu/getJson");
        roleId="0b75d80e-6db3-4b61-936a-2eb7bae";
        List<Menu>  list=menuService.queryMenuListByRoleId(roleId);
        JSONArray jsonArray=JSONArray.fromObject(list);
        String data= jsonArray.toString();
        view.addObject("data",data);
        return view;
    }


    @RequestMapping("/menu/list")
    public ModelAndView getMenuByRoleId(String roleId){

        List<Role> roleList =roleService.list(new Role() , null);
        for(Role role : roleList){
        }

        List<Menu>  list=menuService.queryMenuListByRoleId(roleId);

        ModelAndView view= new ModelAndView("/common/left");
        roleId="0b75d80e-6db3-4b61-936a-2eb7bae";

        return view;
    }




}
