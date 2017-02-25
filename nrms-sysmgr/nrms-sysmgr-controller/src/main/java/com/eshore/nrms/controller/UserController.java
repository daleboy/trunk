package com.eshore.nrms.controller;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.pojo.UserInfo;
import com.eshore.nrms.sysmgr.service.IUserService;
import com.eshore.nrms.util.RandomValidateCodeGenerator;
import com.eshore.nrms.util.SecurityUtil;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;
import com.eshore.nrms.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by forgeeks at 2017-02-24 10:56
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping({"" , "/"})
    public String toIndex(HttpSession session) {
        User user = this.userService.userLogin("forgeeks");
        user.setRoleType("1");
        session.setAttribute(Conts.USER_SESSION_KEY, user);
//        User user = (User) session.getAttribute(Conts.USER_SESSION_KEY);
        if (user == null) {
            return "login";
        } else {
            return "mainPage";
        }
    }



    @RequestMapping({"/doLogin"})
    @ResponseBody
    public ExecResult doLogin(String loginName , String pwd , String validateCode, HttpSession session){

        ExecResult result = new ExecResult();

        String sessionValidateCode = (String) session.getAttribute(Conts.VALIDATE_CODE_KEY);
        if(!sessionValidateCode.equalsIgnoreCase(validateCode)){
            result.setSuccess(false);
            result.setMsg("验证码错误");
            return result;
        }

        User user = this.userService.userLogin(loginName);
//        User user = this.userService.userLogin("forgeeks");
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
        result.setSuccess(true);
        result.setMsg("登录成功");
        return result;
    }


    @RequestMapping("/validate")
    public void validate(HttpSession session , HttpServletRequest request, HttpServletResponse response) throws Exception {

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


//用户列表
    @RequestMapping("/user/list")
    public ModelAndView list(User user , PageConfig page){
        ModelAndView view = new ModelAndView("userInfo/userList");
        PageVo<User> userList = this.userService.queryUserByPage(user,page);
        view.addObject("page" , userList);
        view.addObject("searchParam" , user);
        return view;
    }

//新增用户
//    @RequestMapping("/user/toadd")
//    public ModelAndView toadd(User user , PageConfig page){
//        ModelAndView view = new ModelAndView("userinfo/addOrEditUser");
//        PageVo<User> userList = this.userService.queryUserByPage(user,page);
//
//    }





}
