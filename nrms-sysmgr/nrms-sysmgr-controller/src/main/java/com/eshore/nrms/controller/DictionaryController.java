package com.eshore.nrms.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.nrms.sysmgr.pojo.Dictionary;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.pojo.UserInfo;
import com.eshore.nrms.sysmgr.service.IDictionaryService;
import com.eshore.nrms.sysmgr.service.IUserService;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.util.SecurityUtil;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;
import com.eshore.nrms.vo.PageVo;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
	
	@Autowired
	private IDictionaryService dictionarySrvice;
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 获得部门 ，工作，职位
	 * @param dicType  1：部门  2：工作（web工程师）3:职位(普通员工）
	 * @return     
	 */
	@RequestMapping("/dictionaryList")
	public ModelAndView dictionaryList(Dictionary dictionary,PageConfig page){
		System.out.println("打印dictype:"+dictionary.getDicType());
		ModelAndView view = new ModelAndView("dictionary/dictionaryList");
		PageVo<Dictionary> dictionarylist = dictionarySrvice.getDictionaryByPage(dictionary ,page);
		view.addObject("page" , dictionarylist);
        view.addObject("searchParam" , dictionary);
		return view;
	}
	
	/**
	 * 通过类型的id删除类型,软删除，把类型的状态改为  0
	 * @param id	类型的id 
	 * @return
	 */
	@RequestMapping("/deleteDictionary") 
	@ResponseBody
	public ExecResult deleteDictionary(String id){
		ExecResult result = new ExecResult();
		if(!StringUtils.isNotBlank(id)){
			result.setMsg("删除类型失败，请刷新试试");
			return result;
		}
		Dictionary dictionary = dictionarySrvice.get(id);
		User user = new User();
		if (dictionary.getDicType()==1) {
			user.setDeptKey(dictionary.getDicKey());
		}
		if (dictionary.getDicType()==2) {
			user.setJobKey(dictionary.getDicKey());
		}
		if (dictionary.getDicType()==3) {
			user.setPositionKey(dictionary.getDicKey());
		}
		List<User> list = userService.getAllUsers(user);
		if (list.isEmpty()) {
			dictionary.setDicState(Conts.STATE_DELETE);
			this.dictionarySrvice.update(dictionary);
			result.setMsg("删除成功");
			result.setSuccess(true);
		}else {
			result.setMsg("删除失败，已存在该类型用户");
			result.setSuccess(false);
		}
		
		
		return result;
	}
	
	/**
	 * 编辑或者修改参数信息，新增参数信息
	 * @param dictionary	当dictionary 的id值获取到，取出dictionary,带到修改的页面
	 * @return				放回视图
	 */
	@RequestMapping("/toAddOrEditDictionary")
	public ModelAndView toAddOrEditDictionary(Dictionary dictionary,String dit){
		ModelAndView view = new ModelAndView();
		if (dictionary.getId()!=null) {
			List<Dictionary> dictionaryList = dictionarySrvice.getDictionarys(dictionary);
			dictionary = dictionaryList.get(0);
			view.addObject("dictionary", dictionary);
			view.setViewName("dictionary/addOrEditDictionary");
			return view;
		}
		view.addObject("dit", dit);
		view.setViewName("dictionary/addOrEditDictionary");
		return view;
	}
	
	/**
	 * 新增  部门，工作，职位  类型 
	 * @param dictionary	 类型参数
	 * @return
	 */
	@RequestMapping("/addDictionary") 
	@ResponseBody
	public ExecResult addDictionary(Dictionary dictionary){
        ExecResult er = new ExecResult();
/*        if(dictionarySrvice.getDictionaryByDickey(dictionary)!=null){
            er.setMsg("新增类型失败！编码已经存在，请换个编码试试");
            return er;
        }
        if(dictionarySrvice.getDictionaryByDicValue(dictionary.getDicValue())!=null){
            er.setMsg("新增类型失败！名称经存在，请换个名称试试");
            return er;
        }*/
        dictionary.setDicKey(UUID.randomUUID().toString().replace("-", ""));
        dictionary.setDicState(Conts.STATE_OK);
        dictionarySrvice.save(dictionary);
        er.setSuccess(true);
        er.setMsg("新增类型成功");
        return er;
	}
	
	/**
	 * 更新部门 信息
	 * @param id
	 * @param dicValue	(部门，职位,工作)参数值
	 * @param dicDesc	(部门，职位,工作)描述
	 * @return
	 */
	@RequestMapping("/updateDictonary") 
	@ResponseBody
	public ExecResult updateDictonary(String id , String dicValue , String dicDesc){
		ExecResult er = new ExecResult();
		Dictionary dictionary = null;
		if (StringUtils.isNotBlank(id)) {
			dictionary = dictionarySrvice.get(id);
			dictionary.setDicDesc(dicDesc);
			dictionary.setDicValue(dicValue);
		}
		dictionarySrvice.update(dictionary);
		er.setSuccess(true);
		er.setMsg("更新成功");
		return er;
	}
	
	
	
	

}
