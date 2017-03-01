package com.eshore.nrms.controller;

import java.util.List;

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
import com.eshore.nrms.sysmgr.service.IApplyService;
import com.eshore.nrms.sysmgr.service.IPlaceService;
import com.eshore.nrms.sysmgr.service.IUserService;
import com.eshore.nrms.sysmgr.service.IViewAndAuditService;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.vo.ExecResult;

@Controller
@RequestMapping("/application")
public class ApplyController {

	@Autowired
	private IApplyService applyService;
	@Autowired
	private IPlaceService placeService;
	@Autowired
	private IViewAndAuditService viewAndAuditService;
	@Autowired
	private IUserService userService;

	//分页显示申请信息
	@RequestMapping("/applicationList")
	public ModelAndView applyList(Application app,PageConfig pc){
		ModelAndView view = new ModelAndView("/application/applicationList");

		List<Application> appList = applyService.getApply(app);
		List<Place> placeList = placeService.getAllPlaces();

		pc.setRowCount(applyService.getCountOfApp(app));
		view.addObject("page" , PageUtil.getPageList(pc, appList));
		view.addObject("places" , placeList);
		view.addObject("searchParam" , app);;
		return view;
	}

	//申请详情页
	@RequestMapping("/applicationInfo")
	public ModelAndView applicationInfo(String id){
		ModelAndView view = new ModelAndView("/application/applicationInfo");

		Application application = applyService.get(id);
		List<User> userList = viewAndAuditService.getUsersInApplication(id);
		Place place = placeService.get(application.getPlaceId());
		User minutes = userService.get(application.getUidMinutes());
		User applicant = userService.get(application.getUidApplicant());

		view.addObject("app",application);
		view.addObject("users",userList);
		view.addObject("place",place);
		view.addObject("minutes",minutes);
		view.addObject("applicant",applicant);
		return view;
	}

	//编辑页
	@RequestMapping("/editApplication")
	public ModelAndView editApplication(String id){
		ModelAndView view = new ModelAndView("/application/editApplication");

		if(StringUtils.isNotBlank(id)){
			Application application = applyService.get(id);
			List<Place> placeList = placeService.getAllPlaces();
			List<User> userList = userService.getallUsers();
			Place place = placeService.get(application.getPlaceId());

			view.addObject("app",application);
			view.addObject("places",placeList);
			view.addObject("users",userList);
			view.addObject("place",place);
		}
		return view;
	}

	//会议室使用情况页
	@RequestMapping("/placeInfo")
	public ModelAndView placeInfo(Application apply,PageConfig pc){
		ModelAndView view = new ModelAndView("/application/placeInfo");

		List<Application> appList = applyService.getApplys(apply, pc);
		List<Place> placeList = placeService.getAllPlaces();

		view.addObject("page" , PageUtil.getPageList(pc, appList));
		view.addObject("searchParam" , apply);
		view.addObject("places",placeList);
		return view;
	}

	@RequestMapping("/submitMyApp")
	@ResponseBody
	public ExecResult submitMyApp(Application app){
		ExecResult er = new ExecResult();

		if(StringUtils.isBlank(app.getId())){
			er.setMsg("请填写会议室名称");
			return er;
		}
		applyService.update(app);
		er.setSuccess(true);
		er.setMsg("保存成功");

		return er;
	}

	@RequestMapping("/deleteApplication")
	@ResponseBody
	public ExecResult deleteApplication(String id){
		ExecResult er = new ExecResult();

		if(StringUtils.isBlank(id)){
			er.setMsg("删除申请失败！因为未获取id，请刷新页面试试");
			return er;
		}

		applyService.delete(id);
		er.setSuccess(true);
		er.setMsg("删除成功");

		return er;
	}

	@RequestMapping("/cancelApplication")
	@ResponseBody
	public ExecResult cancelApplication(String id){
		ExecResult er = new ExecResult();

		if(StringUtils.isBlank(id)){
			er.setMsg("取消申请失败！因为未获取id，请刷新页面试试");
			return er;
		}

		Application app = applyService.get(id);
		app.setAppState(0);
		applyService.update(app);
		er.setSuccess(true);
		er.setMsg("取消成功");
		return er;
	}

}
