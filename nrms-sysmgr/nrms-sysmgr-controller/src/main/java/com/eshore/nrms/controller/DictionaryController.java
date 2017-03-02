package com.eshore.nrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.nrms.sysmgr.pojo.Dictionary;
import com.eshore.nrms.sysmgr.service.IDictionaryService;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
	
	@Autowired
	private IDictionaryService dictionarySrvice;
	
	/**
	 * 获得部门 ，工作，职位
	 * @param dicType  1：部门  2：工作（web工程师）3:职位(普通员工）
	 * @return     
	 */
	@RequestMapping("/getDictionary")
	@ResponseBody
	public ModelAndView getDictionary(Dictionary dictionary){
		System.out.println("打印dictype:"+dictionary.getDicType());
		ModelAndView view = new ModelAndView("user/userList");
		List<Dictionary> dictionarylist = dictionarySrvice.getDictionarys(dictionary);
		for (Dictionary dictionary2 : dictionarylist) {
			System.out.println(dictionary2.getDicValue());
		}
		view.addObject("dictionarylist", dictionarylist);
		return view;
	}
	
	
	
	

}
