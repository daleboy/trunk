package com.eshore.nrms.controller;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.nrms.sysmgr.pojo.Menu;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.service.IMenuService;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;
import com.eshore.nrms.vo.PageVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    /**
     * 根据角色获取权限菜单json数据
     *
     * @return
     */
    @RequestMapping("/menu/getJsonForIndex")
    public ModelAndView getMenuJsonForIndex() {
        ModelAndView view = new ModelAndView("menu/getJson");
        List<Menu> list = menuService.queryAllMenu();
        JSONArray jsonArray = new JSONArray();
        for (Menu menu : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", menu.getId());
            jsonObject.put("pId", menu.getPid());
            jsonObject.put("name", menu.getMenuName());
            if(menu.getIsLeaf()==1) {
                jsonObject.put("dropInner", false);
                jsonObject.put("dropRoot", false);
            }else {
                jsonObject.put("open", true);
                jsonObject.put("dropRoot", false);
                jsonObject.put("childOrder", false);
                jsonObject.put("childOuter", false);
            }
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
    @ResponseBody
    public ExecResult edit(HttpServletRequest request,Menu menu,String ids){
        ExecResult result = new ExecResult();
        Menu oldMenu= menuService.get(menu.getId());
        if( !oldMenu.getMenuName().equals(menu.getMenuName()) &&  menuService.queryCountByName(menu.getMenuName())>0){
            result.setMsg("更新失败，菜单不可重名！");
            result.setSuccess(false);
            return result;
        }else {
            menuService.update(menu);
            updateMenuIndex(ids);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Conts.USER_SESSION_KEY);
            session.setAttribute("volist", menuService.queryMenuVoList(user.getRoleId()));
            result.setMsg("更新成功！");
            result.setSuccess(true);
            return result;
        }
    }

    public void  updateMenuIndex(String ids){
        String[] idsArrary = ids.split(",");
        for(int i=0;i<idsArrary.length;i++){
                Menu menu= menuService.get(idsArrary[i]);
                menu.setMenuIndex(i+1);
                menuService.update(menu);
        }

    }


}
