package com.eshore.nrms.web.controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshore.nrms.sysmgr.pojo.Menu;
import com.eshore.nrms.sysmgr.service.IMenuService;
import com.eshore.nrms.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.pojo.UserInfo;
import com.eshore.nrms.sysmgr.service.IUserInfoService;
import com.eshore.nrms.sysmgr.service.IUserService;
import com.eshore.nrms.util.RandomValidateCodeGenerator;
import com.eshore.nrms.util.SecurityUtil;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IMenuService menuService;


	@RequestMapping({"" , "/"})
	public String toIndex(HttpSession session){
		
		User user = (User) session.getAttribute(Conts.USER_SESSION_KEY);
		System.out.println("toindex："+user);
		if(user == null){
			return "login";
		}else{
			return "redirect:view/mainPage";
		}
	}
	
	
	@RequestMapping({"/doLogin"})
	@ResponseBody
	public ExecResult doLogin(String loginName , String pwd , String validateCode, HttpSession session){
		System.out.println(loginName);
		ExecResult result = new ExecResult();
		
		String sessionValidateCode = (String) session.getAttribute(Conts.VALIDATE_CODE_KEY);
		if(!sessionValidateCode.equalsIgnoreCase(validateCode)){
			result.setSuccess(false);
			result.setMsg("验证码错误");
			return result;
		}
		
		User user = this.userService.userLogin(loginName);
		System.out.println("userservice:"+user);
		if(user == null){
			result.setSuccess(false);
			result.setMsg("帐号不存在");
			return result;
		}
		
		String pwdMD5 = SecurityUtil.md5(pwd);
		if(!user.getLoginPw().equalsIgnoreCase(pwdMD5)){
			result.setSuccess(false);
			result.setMsg("密码错误");
			return result;
		}
		
		session.setAttribute(Conts.USER_SESSION_KEY, user);
		User tUser = (User) session.getAttribute(Conts.USER_SESSION_KEY);


		String menuId="0sabdvldkjchgbeiubjkn";
		List<MenuVo> voList = new ArrayList<MenuVo>();
		List<Menu> menulist = menuService.queryMenuListByPId(menuId);
		for(Menu menu: menulist){
			MenuVo vo= new MenuVo();
			vo.setThisMenu(menu);
			List<Menu> childlist = menuService.queryMenuListByPId(menu.getId());
			vo.setChildMenus(childlist==null?new ArrayList<Menu>():childlist);
			voList.add(vo);
		}
		session.setAttribute("volist", voList);

		System.out.println("session:"+tUser);
		result.setSuccess(true);
		result.setMsg("登录成功");
		return result;
	}
	
	
	@RequestMapping("/validate")
    public void validate(HttpSession session ,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        String key = RandomValidateCodeGenerator.randKey(4);
        
        try {
            Map.Entry<String, BufferedImage> randCode = new RandomValidateCodeGenerator().getRandCode(key);
            session.setAttribute(Conts.VALIDATE_CODE_KEY, randCode.getKey());
            
            ImageIO.write(randCode.getValue(), "JPEG", response.getOutputStream());

        } catch (Exception e) {
        	System.out.println("生成验证码图片失败了!");
        	e.printStackTrace();
        }
    }
	
	
	@RequestMapping({"/logOut"})
	public String logOut(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
	
}
