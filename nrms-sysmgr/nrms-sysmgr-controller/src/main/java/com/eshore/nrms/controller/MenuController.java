package com.eshore.nrms.controller;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.nrms.sysmgr.pojo.Menu;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.service.IMenuService;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;
import com.eshore.nrms.vo.MenuVo;
import com.eshore.nrms.vo.PageVo;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     *
     * @return
     */
    @RequestMapping("/menu/getJson")
    public ModelAndView getMenuJsonByRoleId() {
        ModelAndView view = new ModelAndView("menu/getJson");
        List<Menu> list = menuService.queryAllMenu();
        JSONArray jsonArray = new JSONArray();
        for (Menu menu : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", menu.getId());
            jsonObject.put("pId", menu.getPid());
            jsonObject.put("name", menu.getMenuName());
            jsonObject.put("open", true);
            jsonObject.put("checked", false);
            jsonArray.add(jsonObject);
        }
        String data = jsonArray.toString();
        view.addObject("data", data);
        return view;
    }


    @RequestMapping("/menu/menulist")
    public ModelAndView getMenuList(Menu menu,PageConfig pageConfig) {
        ModelAndView view = new ModelAndView("menu/menuList");
        PageVo<Menu> page= menuService.querymenuListByPage(menu, pageConfig);
        view.addObject("page", page);
        view.addObject("searchParam", menu);
        return view;
    }

    @RequestMapping("/menu/toedit")
    public ModelAndView toEdit(String id) {
        ModelAndView view = new ModelAndView("menu/menuEdit");
        Menu menu= menuService.get(id);
        view.addObject("menu", menu);
        return view;
    }

    @RequestMapping("/menu/menuedit")
    public ExecResult edit(HttpServletRequest request,Menu menu){
        ExecResult result = new ExecResult();
        menuService.update(menu);
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute(Conts.USER_SESSION_KEY);
        session.setAttribute("volist",  menuService.queryMenuVoList(   user.getRoleId()) );
        result.setMsg("更新完成");
        return result;
    }

}
