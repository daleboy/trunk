package com.eshore.nrms.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.pojo.Place;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.service.IPlaceService;
import com.eshore.nrms.sysmgr.service.IViewAndAuditService;
import com.eshore.nrms.util.ApplicationComparators;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;

@Controller
@RequestMapping("/view")
public class ViewAppsController {
	
	@Autowired
	private IViewAndAuditService appService;
	
	@Autowired
	private IPlaceService placeService;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
	
	@RequestMapping("/lookupApps")				   //type 表示不同职位查看的类型   1：查看与我相关      2：所有申请
	public ModelAndView lookupApps(Application app,Integer type, PageConfig pc,HttpSession session){
		ModelAndView view = new ModelAndView("myApps/lookupApps");
		
		app.setAppState(2);
		User user = (User)session.getAttribute(Conts.USER_SESSION_KEY);
		List<Application> allApp = null;
		if(type != null && type == 2 && !user.getPositionKey().equals("100")){//只要不是普通员工 使用type = 2
			allApp= appService.getFullPage(app, pc);
		}else{
			allApp = appService.getFullPageWithMe(user, app, pc);
		}
		//时间倒序
		Collections.sort(allApp, ApplicationComparators.getStartDateComparator());
		Collections.reverse(allApp);
		
		view.addObject("page", PageUtil.getPageList(pc, allApp));
		view.addObject("searchParam" , app);
		view.addObject("type", type);
		List<Place> allPlace = placeService.getAllPlaces();
		view.addObject("places", allPlace);
		return view;
	}
	
	@RequestMapping("/mainPage")				
	public ModelAndView mainPage(PageConfig pc,HttpSession session){
		ModelAndView view = new ModelAndView("mainPage");
		
		User user = (User)session.getAttribute(Conts.USER_SESSION_KEY);
		Application app = new Application();
		app.setStartDate(sdf.format(new Date()));
		
		List<Application> allApp = appService.getFullPageNotEnd(timeSdf.format(new Date()),user.getId(), app, pc);
		view.addObject("page", PageUtil.getPageList(pc, allApp));
		
		return view;
	}
	
	@RequestMapping("/viewApp")
	public ModelAndView viewMyApps(String id){
		ModelAndView view = new ModelAndView("auditingApps/viewApp");
		
		view.addObject("app", appService.getFull(id));
		view.addObject("partakes",appService.getUsersInApplication(id));
		
		return view;
	}
	
	@RequestMapping("/addMinutes")
	public ModelAndView addMinutes(String id,HttpSession session){
		ModelAndView view = new ModelAndView("myApps/addMinutes");
		
		User user = (User)session.getAttribute(Conts.USER_SESSION_KEY);
		Application app = appService.getFull(id);
		if(user.getId() .equals(app.getUidMinutes())){//检查是否有权限录入纪要
			view.addObject("app", app);
		}
		return view;
	}
	
	@RequestMapping("/saveOrSubmitMinutes")
	@ResponseBody
	public ExecResult saveOrSubmitJiyao(String aid,String meetingMinutes,HttpSession session,Integer opType){
		ExecResult er = new ExecResult();
		
		if(StringUtils.isBlank(aid)){
			er.setMsg("会议id为空");
			return er;
		}
		User user = (User)session.getAttribute(Conts.USER_SESSION_KEY);
		Application app = appService.get(aid);
		if(user.getId().equals(app.getUidMinutes())){
			app.setMeetingMinutes(meetingMinutes);
			if(opType != null && opType == 2){
				app.setMinutesState(2);		//2 会议纪要录入完成 状态
				er.setMsg("录入成功");
			}
			else{
				app.setMinutesState(1);		//1 会议纪要暂存 状态
				er.setMsg("保存成功");
			}
			appService.update(app);
			er.setSuccess(true);
			return er;
		}
		er.setMsg("只有会议纪要人才可以录入会议纪要");
		return er;
	}
}
