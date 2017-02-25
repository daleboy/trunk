package com.eshore.nrms.controller;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.nrms.sysmgr.pojo.Role;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * Created by forgeeks at 2017-02-25 17:10
 */
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/role/list")
    public ModelAndView list(Role role, PageConfig page) {
        ModelAndView view = new ModelAndView("role/roleList");
        PageVo<Role> list = roleService.queryRoleListByPage(role, page);
        view.addObject("page", list);
        view.addObject("searchParam", role);
        return view;
    }

    @RequestMapping("/role/toadd")
    public ModelAndView toAdd() {
        ModelAndView view = new ModelAndView("role/add");
        return view;
    }

    @RequestMapping("/role/add")
    public ModelAndView add(Role role) {
        role.setId(UUID.randomUUID().toString().substring(0,31));
        roleService.save(role);
        return list(null, null);
    }


}
