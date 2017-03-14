package com.eshore.nrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.nrms.sysmgr.pojo.Dictionary;
import com.eshore.nrms.sysmgr.pojo.Role;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.service.IDictionaryService;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.sysmgr.service.IUserService;
import com.eshore.nrms.util.SecurityUtil;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;
import com.eshore.nrms.vo.PageVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDictionaryService dictionarySrvice;

    @Autowired
    private IRoleService roleService;

    /**
     * 返回userList列表
     * 支持模糊查询
     *
     * @param user 模糊查询参数
     * @param page 分页参数
     * @return
     */
    @RequestMapping("/userList")
    public ModelAndView userList(User user, PageConfig page) throws ParseException {
        ModelAndView view = new ModelAndView("user/userList");
        PageVo<User> userList = userService.queryUserByPage(user, page);
        getInformation(view);
        view.addObject("page", userList);
        view.addObject("searchParam", user);
        return view;
    }


    /**
     * 菜单url  编辑用户信息 和 重置密码
     * @param request
     * @param oper
     * @return
     */
    @RequestMapping("/toEdit")
    public ModelAndView toEdit(HttpServletRequest request, String oper) {
        HttpSession session = request.getSession();
        ModelAndView view = new ModelAndView();
        User user = (User) session.getAttribute(Conts.USER_SESSION_KEY);
        view.addObject("user", user);
        if(oper.equals("1"))     view.setViewName("user/updateMe");
        else if( oper.equals("3") )  view.setViewName("user/resetMyPwd");
        view.addObject("id", user.getId());
        return view;
    }


    /**
     * 新增用户
     *
     * @param user 用户的数据
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public ExecResult addUser(User user) {
        System.out.println("访问addUser");
        ExecResult er = new ExecResult();
        if (userService.userLogin(user.getLoginName()) != null) {
            er.setMsg("新增用户失败！登陆帐号名已经存在，请换个帐号名试试");
            return er;
        }
        user.setLoginPw(SecurityUtil.md5("123456"));
        user.setUserState(1);
        userService.save(user);
        er.setSuccess(true);
        er.setMsg("新增用户成功");
        return er;
    }

    /**
     * 通过 id 删除用户 软删除，把用户状态变为2  //用户状态 1:正常  2:注销，
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public ExecResult deleteUser(String id) {
        ExecResult er = new ExecResult();
        if (StringUtils.isBlank(id)) {
            er.setMsg("删除用户失败！因为未获取id，请刷新页面试试");
            return er;
        }
        User user = userService.get(id);
        user.setUserState(2);
        userService.update(user);
        er.setSuccess(true);
        er.setMsg("删除成功");
        return er;
    }

    /**
     * 跳转到编辑 用户 ，新增用户 ，修改用户 信息 的view视图层
     *
     * @param id   用户id
     * @param oper 1:修改个人信息邮箱  ，3：修改个人密码码  4:新增用户
     * @return
     */
    @RequestMapping("/toAddOrEditUsers")
    public ModelAndView toAddOrEditUser(String id, String oper) {
        ModelAndView view = new ModelAndView();
        User user = null;
        if ("1".equals(oper)) {
            List<User> userlist = userService.getUserById(id);
            if (!userlist.isEmpty()) {
                user = userlist.get(0);
                //System.out.println("获取到user的个人信息"+user);
            }
            view.addObject("user", user);
            getInformation(view);
            view.setViewName("user/addUser");
        }
        if ("3".equals(oper)) {
            view.setViewName("user/resetpwd");
        }
        if ("4".equals(oper)) {
            getInformation(view);
            view.setViewName("user/addUser");
        }
        view.addObject("id", id);
        return view;
    }

    /**
     * 更改用户的密码
     *
     * @param id         用户id
     * @param loginPw    用户旧密码
     * @param newLoginPw 用户新密码
     * @return
     */
    @RequestMapping("/resetUserPwd")
    @ResponseBody
    public ExecResult resetUserPwd(String id, String loginPw, String newLoginPw,HttpSession session) {
        ExecResult er = new ExecResult();
        User user = null;
        if (StringUtils.isNotBlank(loginPw)&&StringUtils.isNotBlank(newLoginPw)) {
        	if (StringUtils.isBlank(id)) {
                er.setMsg("修改密码失败！请刷新页面试试");
                return er;
            }
            user = userService.get(id);
            loginPw = SecurityUtil.md5(loginPw);
            if (!user.getLoginPw().equals(loginPw)) {
                er.setMsg("旧密码错误，修改失败");
                return er;
            }
            if (user.getLoginPw().equals(SecurityUtil.md5(newLoginPw))) {
                er.setMsg("新密码不允许与旧密码一样，修改失败");
                return er;
            }
            user.setLoginPw(SecurityUtil.md5(newLoginPw));
            userService.update(user);
            er.setSuccess(true);
            er.setMsg("修改成功");
            session.invalidate();
		}else {
			//管理员在用户列表重置自己的密码后，需要重新登陆
			user = userService.get(id);
			user.setLoginPw(SecurityUtil.md5("123456"));
			userService.update(user);
			if (((User)session.getAttribute(Conts.USER_SESSION_KEY)).getId().equals(id)) {
				session.invalidate();
			}
			er.setSuccess(true);
			er.setMsg("重置成功");
		}
        return er;
    }

    /**
     * 修改用户的信息
     *
     * @param id       用户id
     * @param user   更改用户的信息
     * @return
     */
    @RequestMapping("/resetUserInfo")
    @ResponseBody
    public ExecResult resetUserInfo(User user , HttpSession session) {
        ExecResult er = new ExecResult();
        if (StringUtils.isBlank(user.getId())) {
            er.setMsg("修改用户失败！请刷新页面试试");
            return er;
        }
        User usertemp = userService.get(user.getId());
        if (user.getRoleId()!=null&&!"".equals(user.getRoleId())) {
        	//管理员修改用户信息
        	usertemp.setRoleId(user.getRoleId());
            usertemp.setDeptKey(user.getDeptKey());
            usertemp.setJobKey(user.getJobKey());
            usertemp.setPositionKey(user.getPositionKey());
		}
        //用户修改自己的邮箱箱
        usertemp.setEmail(user.getEmail());
        userService.update(usertemp);
        User userSession = (User) session.getAttribute(Conts.USER_SESSION_KEY);
        if (userSession.getId().equals(user.getId())) {
			userSession = userService.userLogin(usertemp.getLoginName());
			session.setAttribute(Conts.USER_SESSION_KEY, userSession);
		}
        er.setSuccess(true);
        er.setMsg("修改成功");
        return er;
    }


    /**
     * 获取所有的部门，角色，职位，工作  的相关信息放入select下拉框a
     *
     * @param view 视图
     */
    private void getInformation(ModelAndView view) {
        Dictionary dictionary = new Dictionary();
        List<Dictionary> dictionarylist = dictionarySrvice.getDictionarys(dictionary);
        ArrayList<Dictionary> departlist = new ArrayList<Dictionary>();
        ArrayList<Dictionary> jobtlist = new ArrayList<Dictionary>();
        ArrayList<Dictionary> positionlist = new ArrayList<Dictionary>();
        List<Role> rolelist = roleService.list(new Role(), null);
        for (Dictionary diction : dictionarylist) {
            switch (diction.getDicType()) {
                case 1:
                    departlist.add(diction);
                    break;
                case 2:
                    jobtlist.add(diction);
                    break;
                case 3:
                    positionlist.add(diction);
                    break;
                default:
                    break;
            }
        }
        view.addObject("departlist", departlist);
        view.addObject("rolelist", rolelist);
        view.addObject("jobtlist", jobtlist);
        view.addObject("positionlist", positionlist);
    }


}






