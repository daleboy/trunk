package com.eshore.nrms.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshore.nrms.sysmgr.pojo.Menu;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eshore.nrms.vo.Conts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forgeeks at 2017-3-1 19:13
 * url访问权限验证拦截器
 */
public class RoleAccessInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    IMenuService menuService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String basePath = request.getContextPath();
        HttpSession session = request.getSession();
        String url = request.getRequestURL().toString();
        List accessModels = new ArrayList();

        //初始化session菜单list
        if (session.getAttribute("accessModels") == null) {
            List<Menu> list = menuService.queryMenuListByRoleId( ((User) session.getAttribute(Conts.USER_SESSION_KEY)).getRoleId());
            for (Menu m : list) accessModels.add(m.getMenuUrl());
            session.setAttribute("accessModels", accessModels);
        } else accessModels = (List) session.getAttribute("accessModels");

        boolean flag = false;
        for (int i = 0; i < accessModels.size(); i++) {
            String model= (String) accessModels.get(i)  ;
            if(model.indexOf("?oper")>-1)    model=model.split("\\?oper")[0];
            if (model.indexOf("/")>-1 && url.indexOf(model) > -1) {
                flag = true;   break;
            }
        }
        if (flag) return true;    else {       response.sendRedirect(basePath);       return false; }
    }
}
