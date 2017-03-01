package com.eshore.nrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.nrms.sysmgr.pojo.Place;
import com.eshore.nrms.sysmgr.service.IPlaceService;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.vo.ExecResult;

@Controller
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private IPlaceService placeService;

    @RequestMapping("/placeList")
    public ModelAndView placeList(Place place,PageConfig pc){
        ModelAndView view = new ModelAndView("place/placeList");

        List<Place> list = placeService.getPlaces(place, pc);

        view.addObject("page" , PageUtil.getPageList(pc, list));
        view.addObject("searchParam" , place);

        return view;
    }

    @RequestMapping("/toAddOrEditPlace")
    public ModelAndView toAddOrEditPlace(String id){
        ModelAndView view = new ModelAndView("place/addOrEditPlace");

        if(StringUtils.isNotBlank(id)){
            view.addObject("place", placeService.get(id));
        }

        return view;
    }

    @RequestMapping("/deletePlace")
    @ResponseBody
    public ExecResult deletePlace(String id){
        ExecResult er = new ExecResult();

        if(StringUtils.isBlank(id)){
            er.setMsg("删除会议失败！因为未获取id，请刷新页面试试");
            return er;
        }

        Place place = placeService.get(id);
        place.setPlaceState(0);
        placeService.update(place);
        er.setSuccess(true);
        er.setMsg("删除成功");

        return er;
    }

    @RequestMapping("/saveOrUpdatePlace")
    @ResponseBody
    public ExecResult saveOrUpdatePlace(Place place){
        ExecResult er = new ExecResult();
        place.setPlaceState(1);
        
        if(StringUtils.isBlank(place.getPlaceName())){
        	er.setMsg("请填写会议室名称");
        	return er;
        }
        if(StringUtils.isBlank(place.getId())){
        	int count = placeService.getCountOfName(place.getPlaceName());
        	if(count > 0){		//新增的时候，不允许有一条重复名
        		er.setMsg("会议室名重复");
        		return er;
        	}
            place.setId(null);
            placeService.save(place);
            er.setMsg("保存成功");
            er.setSuccess(true);
            return er;
        }
        
        Place place2 = placeService.getByPlaceName(place.getPlaceName());
        if(place2 != null && !place.getId().equals(place2.getId())){		//更新的时候，不允许有重复名
        	er.setMsg("会议室名重复");
    		return er;
        }
        placeService.update(place);
        er.setSuccess(true);
        er.setMsg("保存成功");

        return er;
    }
}
