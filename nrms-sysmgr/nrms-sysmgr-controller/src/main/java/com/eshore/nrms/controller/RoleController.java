package com.eshore.nrms.controller;
import com.eshore.nrms.util.MailUtil;
import  com.eshore.nrms.util.SendMail;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.nrms.sysmgr.pojo.Role;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;
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
    public ModelAndView list(Role role, PageConfig page) throws MessagingException, IOException  {
        MailUtil.sendByDefault("实习生会议管理课题工作汇报", "今日16:30", "会议室B");

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

//    @RequestMapping("/role/add")
//    public ModelAndView add(Role role) {
//
//        role.setId(UUID.randomUUID().toString().substring(0,31));
//        roleService.save(role);
//        return list(null, null);
//    }


}
