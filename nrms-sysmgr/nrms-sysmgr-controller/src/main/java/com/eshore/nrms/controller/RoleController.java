package com.eshore.nrms.controller;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.nrms.sysmgr.pojo.Role;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.vo.ExecResult;
import com.eshore.nrms.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;
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
    @ResponseBody
    public ExecResult add(Role role , String[] ids) {
        ExecResult result = new ExecResult();
        Integer count = roleService.queryCountByRoleName(role.getRoleName());
        if (count >= 1) {
            result.setMsg("同名角色不合法！");
            result.setSuccess(false);
            return result;
        }
        role.setId(UUID.randomUUID().toString().substring(0, 31));
        roleService.save(role);
        roleService.distributeAccees(role,ids);
        result.setMsg("保存成功");
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/role/toedit")
    public ModelAndView toEdit(String id) {
        ModelAndView view = new ModelAndView("role/edit");
        view.addObject("role",roleService.get(id));
        return view;
    }

    @RequestMapping("/role/edit")
    @ResponseBody
    public ExecResult Edit(Role role ,String[]  ids) {
        ExecResult result= new ExecResult();
        roleService.update(role);
        roleService.updateAccess(role,ids);
        result.setMsg("更新成功");
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/role/delete")
    @ResponseBody
    public ExecResult delete(String id){
        ExecResult result= new ExecResult();
        if(roleService.queryCountOfRoleById(id)>=1){
            result.setMsg("删除失败！尚有用户属于该角色，请重新查看！");
            result.setSuccess(false);
            return  result;
        }
        roleService.delete(id);
        result.setMsg("删除成功！");
        result.setSuccess(false);
        return  result;
    }

}
