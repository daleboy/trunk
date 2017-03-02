package com.eshore.nrms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.nrms.sysmgr.pojo.Dictionary;
import com.eshore.nrms.sysmgr.pojo.Param;
import com.eshore.nrms.sysmgr.pojo.Place;
import com.eshore.nrms.sysmgr.pojo.Role;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.pojo.UserInfo;
import com.eshore.nrms.sysmgr.service.IDictionaryService;
import com.eshore.nrms.sysmgr.service.IRoleService;
import com.eshore.nrms.sysmgr.service.IUserService;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.util.SecurityUtil;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;
import com.eshore.nrms.vo.InitData;
import com.eshore.nrms.vo.PageVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDictionaryService dictionarySrvice;
	
	@Autowired
	private IRoleService roleService;
	
	
	
    @RequestMapping("/userList")
    public ModelAndView userList(User user,PageConfig page){
    	System.out.println("访问userlist啦");
        ModelAndView view = new ModelAndView("user/userList");
        PageVo<User> userList = userService.queryUserByPage(user, page);
        List<User> l = userList.getDataList();
        for (User user2 : l) {
        	System.out.println("查询userlist:"+user2);
		}
        getInformation(view);
        view.addObject("page", userList);
        view.addObject("searchParam", user);
        return view;
    }
    
    
    @RequestMapping("/addUser")
    @ResponseBody
    public ExecResult addUser(User user){
    	System.out.println("访问addUser");
        ExecResult er = new ExecResult();
        if(userService.userLogin(user.getLoginName())!=null){
            er.setMsg("新增用户失败！登陆帐号名已经存在，请换个帐号名试试");
            return er;
        }
        user.setLoginPw(SecurityUtil.md5("123456"));
        user.setUserState(1);
        userService.save(user);
        er.setSuccess(true);
        er.setMsg("新增用户功");
        return er;
    }
    
    
	
    /**
     * 通过userid删除用户 软删除，把用户状态变为2  //用户状态 1:正常  2:注销，
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public ExecResult deleteUser(String id){
        ExecResult er = new ExecResult();

        if(StringUtils.isBlank(id)){
            er.setMsg("删除用户失败！因为未获取id，请刷新页面试试");
            return er;
        }
        User user = userService.get(id);
        //用户状态 1:正常  2:注销
        user.setUserState(2);
        userService.update(user);
        er.setSuccess(true);
        er.setMsg("删除成功");
        return er;
    }
    
    /**
     * 
     * @param id      用户id
     * @param oper   1:修改个人信息邮箱  ，3：修改个人密码码  4:新增用户
     * @return
     */
    @RequestMapping("/toAddOrEditUsers")
    public ModelAndView toAddOrEditUser(String id,String oper){
    	ModelAndView view = new ModelAndView();
    	User user = null ;
    	if ("1".equals(oper)) {
    		List<User> userlist = userService.getUserById(id);
    		if (!userlist.isEmpty()) {
    			user = userlist.get(0);
    			System.out.println("获取到user的个人信息"+user);
			}
    		
    		view.addObject("user", user);
			view.setViewName("user/addUser");
		}
    	if ("3".equals(oper)) {
			view.setViewName("user/resetpwd");
		}
    	if ("4".equals(oper)) {
    		getInformation(view);
    		view.setViewName("user/addUser");
		}
    	//System.out.println("修改密码啦");
    	view.addObject("id", id);
    	return view;
    }
    
    /**
     * 通过userid    更改用户的密码
     * @param id    用户id
     * @return
     */
    @RequestMapping("/resetUserPwd")
    @ResponseBody
    public ExecResult resetUserPwd(String id , String loginPw , String newLoginPw){
        ExecResult er = new ExecResult();

        if(StringUtils.isBlank(id)){
            er.setMsg("修改密码失败！请刷新页面试试");
            return er;
        }
        User user = userService.get(id);
        loginPw = SecurityUtil.md5(loginPw); 
        if (!user.getLoginPw().equals(loginPw)) {
			er.setMsg("旧密码错误，修改失败");
			return er;
		}
        user.setLoginPw(SecurityUtil.md5(newLoginPw));
        userService.update(user);
        er.setSuccess(true);
        er.setMsg("修改成功");
        return er;
    }
    
    /**
     * 修改用户的邮箱地址
     * @param id
     * @param newEmail
     * @return
     */
    @RequestMapping("/resetUserEmail")
    @ResponseBody
    public ExecResult resetUserEmail(String id , String newEmail ){
        ExecResult er = new ExecResult();

        if(StringUtils.isBlank(id)){
            er.setMsg("修改邮箱失败！请刷新页面试试");
            return er;
        }
        User user = userService.get(id);
        user.setEmail(newEmail);
        userService.update(user);
        er.setSuccess(true);
        er.setMsg("修改成功");
        return er;
    }
    
    
    /**
     * 获取所有的部门，角色，职位的相关信息放入select下拉框a
     */
    private void getInformation(ModelAndView view){
    	//获取部门相关信息
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
    	//return view;
    }
    
    
	
    /**
     * 更新用户信息或者新增用户
     * @param id
     * @return
     */
    /*@RequestMapping("/saveOrUpdateUser") 
	@ResponseBody
	public ExecResult saveOrUpdateUser(User user){
		ExecResult result = new ExecResult();
		
		
		
		int count = this.userService.getUserCountByLoginName(userInfo.getLoginName(), userInfo.getId());
		if(count > 0){
			result.setMsg("帐号已经存在");
			result.setSuccess(false);
		}
		
		count = this.userInfoService.getUserCountByFullIp(userInfo.getFullIp(), userInfo.getId());
		if(count > 0){
			result.setMsg("IP已被使用");
			result.setSuccess(false);
		}
		
		
		
		if(StringUtils.isBlank(userInfo.getId())){
			userInfo.setId(null);
			String pwd = SecurityUtil.md5("123456");
			userInfo.setPwd(pwd);
			userInfo.setState(Conts.STATE_OK);
			this.userInfoService.save(userInfo);
		}else{
			UserInfo user = this.userInfoService.get(userInfo.getId());
			userInfo.setState(user.getState());
			userInfo.setPwd(user.getPwd());
			this.userInfoService.update(userInfo);
		}
		result.setMsg("保存成功");
		result.setSuccess(true);
		return result;
	}*/
	

}
