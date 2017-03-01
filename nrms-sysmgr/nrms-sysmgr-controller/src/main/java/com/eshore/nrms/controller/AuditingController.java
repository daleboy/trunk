package com.eshore.nrms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.pojo.Place;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.service.IPlaceService;
import com.eshore.nrms.sysmgr.service.IViewAndAuditService;
import com.eshore.nrms.util.MailUtil;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;

@Controller
@RequestMapping("/auditing")
public class AuditingController {
	
	@Autowired
	private IViewAndAuditService appService;
	
	@Autowired
	private IPlaceService placeService;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@RequestMapping("/apps")
	public ModelAndView appList(Application app,PageConfig pc){
		ModelAndView view = new ModelAndView("auditingApps/appList");
		
		if(app == null){//默认选中待审核
			app = new Application();
			app.setAppState(1);
		}else{//只能展示待审核 和 申请通过 两种状态的
			Integer s = app.getAppState();
			if(s == null || (s!=1 && s != 2))
				app.setAppState(1);
		}
		List<Application> allApp = appService.getFullPage(app, pc); 
		view.addObject("page", PageUtil.getPageList(pc, allApp));
		view.addObject("searchParam" , app);
		
		List<Place> allPlace = placeService.getAllPlaces();
		view.addObject("places", allPlace);
		return view;
	}
	@RequestMapping("/viewApp")
	public ModelAndView viewMyApps(String id){
		ModelAndView view = new ModelAndView("auditingApps/viewApp");
		
		view.addObject("app", appService.getFull(id));
		view.addObject("partakes",appService.getUsersInApplication(id));
		
		return view;
	}
	@RequestMapping("/auditingApp")
	public ModelAndView auditingApps(String id){
		ModelAndView view = new ModelAndView("auditingApps/auditingApp");
		
		view.addObject("app", appService.getFull(id));
		view.addObject("partakes",appService.getUsersInApplication(id));
		
		return view;
	}
	
	@RequestMapping("/auditing")
	@ResponseBody
	public ExecResult auditing(String id,Integer appState,String auditingFeedBack,HttpSession session){
		ExecResult er = new ExecResult();
		
		if(appState == null || !(appState ==2 || appState == 3)){
			er.setMsg("无效审批意见");
			return er;
		}
		
		User u = (User)session.getAttribute(Conts.USER_SESSION_KEY);
		Application app_ = appService.get(id);
		
		if(app_ == null){
			er.setMsg("未知会议");
			return er;
		}
		if(appState == 2 && appService.verifyTimeConflict(app_)){
			er.setMsg("时间冲突");
			return er;
		}
		
		app_.setAuditingDate(sdf.format(new Date()));
		app_.setUidAuditor(u.getId());
		app_.setAppState(appState);
		app_.setAuditingFeedBack(auditingFeedBack);
		appService.update(app_);
		
		try {
			List<String> targetList = new ArrayList<String>();
			List<User> list = appService.getUsersInApplication(id);
			for (User user : list) {
				targetList.add(user.getEmail());
			}
			MailUtil.sendByDefault(app_.getSubject(), app_.getStartDate()+" "+app_.getStartTime(), placeService.get(app_.getPlaceId()).getPlaceName(), targetList);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		er.setMsg("审核完成");
		er.setSuccess(true);
		
		return er;
	}
}
