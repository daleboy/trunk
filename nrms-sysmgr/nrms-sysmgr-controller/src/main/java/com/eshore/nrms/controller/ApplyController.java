package com.eshore.nrms.controller;

import java.util.ArrayList;
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
import com.eshore.nrms.sysmgr.service.IApplyService;
import com.eshore.nrms.sysmgr.service.IPartakesService;
import com.eshore.nrms.sysmgr.service.IPlaceService;
import com.eshore.nrms.sysmgr.service.IUserService;
import com.eshore.nrms.sysmgr.service.IViewAndAuditService;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.vo.Conts;
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
	@Autowired
	private IPartakesService partakesService;
	
	//分页显示申请信息
	@RequestMapping("/applicationList")
	public ModelAndView applyList(Application app,PageConfig pc){
		ModelAndView view = new ModelAndView("/application/applicationList");
		
		List<Application> allApp = viewAndAuditService.getFullPage(app, pc); 
		view.addObject("page", PageUtil.getPageList(pc, allApp));
		view.addObject("searchParam" , app);
		
		List<Place> allPlace = placeService.getAllPlaces();
		view.addObject("places", allPlace);
		return view;
	}
	
	//申请详情页
	@RequestMapping("/applicationInfo")
	public ModelAndView applicationInfo(String id){
		System.out.println("id "+id);
		ModelAndView view = new ModelAndView("/application/applicationInfo");
		
		view.addObject("app", viewAndAuditService.getFull(id));
		view.addObject("partakes",viewAndAuditService.getUsersInApplication(id));
		return view;
	}
	
	//编辑页
	@RequestMapping("/addOrEditApplication")
	public ModelAndView addOrEditApplication(String id){
		ModelAndView view = new ModelAndView("/application/addOrEditApplication");
		
		if(StringUtils.isNotBlank(id)){
			Application application = applyService.get(id);
			view.addObject("app",application);
			
			Place place = placeService.get(id);
			view.addObject("place",place);
        }
		
		String []time_points = {"08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30"};
		
		List<Place> placeList = placeService.getAllPlaces();
		List<User> userList = userService.getUsers();
		
		view.addObject("places",placeList);
		view.addObject("users",userList);
		view.addObject("time_points", time_points);
		return view;
	}
	
	//会议室使用情况页
	@RequestMapping("/placeInfo")
	public ModelAndView placeInfo(Application apply,PageConfig pc){
		ModelAndView view = new ModelAndView("/application/placeInfo");
		
		List<Application> appList = viewAndAuditService.getFullPage(apply, pc);
		List<Place> placeList = placeService.getAllPlaces();
 		
		view.addObject("page" , PageUtil.getPageList(pc, appList));
	    view.addObject("searchParam" , apply);
	    view.addObject("places",placeList);
		return view;
	}
	
	@RequestMapping("/submitMyApp")
	@ResponseBody
	public ExecResult submitMyApp(Application application,HttpSession session,String []partakes){
		ExecResult er = new ExecResult();
		
		if(viewAndAuditService.verifyTimeConflict(application)){
			er.setMsg("该会议申请和其他申请时间冲突");
			return er;
		}
		application.setAppState(1);
		//如果appid为空，则为新增
		if(StringUtils.isBlank(application.getId())){
			application.setId(null);
			applyService.save(application);
		}else{//否则，则为更新
			applyService.update(application);
		}
		partakesService.update(application.getId(), partakes);
        er.setSuccess(true);
        er.setMsg("保存成功");
        
        List<String> dataList =  new ArrayList<String>();
        dataList.add(application.getId());
		er.setDataList(dataList );
        return er;
	}
	
	@RequestMapping("/saveOrUpdateMyApp")
	@ResponseBody
	public ExecResult saveOrUpdateMyApp(Application application,String []partakes){
		ExecResult er = new ExecResult();
		
		application.setAppState(0);
		if(StringUtils.isBlank(application.getId())){
			application.setId(null);
			applyService.save(application);
		}else{
			applyService.update(application);
		}
		partakesService.update(application.getId(), partakes);
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
        
        partakesService.deleteByAppId(id);
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
