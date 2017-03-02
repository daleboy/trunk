package com.eshore.nrms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.service.IViewAndAuditService;
import com.eshore.nrms.vo.Conts;
import com.eshore.nrms.vo.ExecResult;
import com.eshore.nrms.vo.FileValidateCode;

@Controller
@RequestMapping("/file")
public class FileController {
	
	private final String PATH = "D:\\";

	@Autowired
	private IViewAndAuditService appService;
	
	/**
	 * 
	 * @param app_file	input的name属性必须为file
	 * @param aid	会议申请的id
	 * @param fileType 文件类型 1：会议附件      2：会议纪要附件
	 * @param session
	 * @return
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public ExecResult uploadFile(@RequestParam(required=true,value="file") MultipartFile app_file,String aid,Integer fileType,HttpSession session){
		ExecResult er = new ExecResult();
		
		if(app_file == null || fileType == null){
			er.setMsg("操作异常，请试试刷新页面");
			return er;
		}
		
		Application app = appService.get(aid);
		if(app == null){
			er.setMsg("无该会议");
			return er;
		}else{
			//检查用户是否具有上传该类型文件的权限
			User u = (User) session.getAttribute(Conts.USER_SESSION_KEY);
			if(fileType == 1 && !u.getId().equals(app.getUidApplicant())){
				er.setMsg("无上传会议附件的权限");
				return er;
			}
			if(fileType == 2 && !u.getId().equals(app.getUidMinutes())){
				er.setMsg("无上传会议纪要附件权限");
				return er;
			}
			
		}
		
		String fileName = app_file.getOriginalFilename();
		if(StringUtils.isBlank(fileName)){
			er.setMsg("文件名为空");
			return er;
		}
		
		saveFile(app_file, app, fileType, er, fileName);
		
		return er;
	}

	/**
	 * 保存文件
	 * @param app_file
	 * @param aid 会议id
	 * @param fileType 文件类型 1：会议附件      2：会议纪要附件
	 * @param er
	 * @param fileName
	 */
	private void saveFile(MultipartFile app_file, Application app, Integer fileType, ExecResult er, String fileName) {
		String fileGenName = UUID.randomUUID().toString();
		File targetFile = new File(PATH,fileGenName);
		
		Application deleteApp = new Application();
		if(fileType == 1){
			deleteApp.setAppUuidName(app.getAppUuidName());
			app.setAppFileName(fileName);
			app.setAppUuidName(fileGenName);
		}else if(fileType == 2){
			deleteApp.setMinutesUuidName(app.getMinutesUuidName());
			app.setMinutesFileName(fileName);
			app.setMinutesUuidName(fileGenName);
		}
		
		if(!appService.deleteFile(deleteApp)){	//更新前做删除旧文件操作
			er.setMsg("旧文件占用中，不允许覆盖，请稍后再上传");
			return;
		}
		appService.update(app);
		
		try {//保存文件到目标
			app_file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		er.setSuccess(true);
		er.setMsg("上传成功");
	}
	
	/**
	 * 
	 * @param aid	会议申请的id
	 * @param fileType 文件类型 1：会议附件      2：会议纪要附件
	 * @return
	 */
	@RequestMapping("/ensureFileExist")
	@ResponseBody
	public ExecResult ensureFileExist(String aid,Integer fileType){
		ExecResult er = new ExecResult();
		if(StringUtils.isNotBlank(aid)){
			Application app = appService.get(aid);
			if(fileType == 1 ){
				if((StringUtils.isBlank(app.getAppFileName()) || StringUtils.isBlank(app.getAppUuidName()))){
					er.setMsg("暂无会议附件");
					return er;
				}else{
					File f = new File(PATH,app.getAppUuidName());
					if(!f.exists()){
						er.setMsg("会议附件附件丢失");
						return er;
					}
				}
			}else if(fileType == 2){
				if( (StringUtils.isBlank(app.getMinutesFileName()) || StringUtils.isBlank(app.getMinutesUuidName()))){
					er.setMsg("暂无纪要附件");
					return er;
				}else{
					File f = new File(PATH,app.getMinutesUuidName());
					if(!f.exists()){
						er.setMsg("会议纪要附件丢失");
						return er;
					}
				}
			}
		}
		er.setSuccess(true);
		er.setMsg(FileValidateCode.genRandomCode().getCode());
		return er;
	}
	
	/**
	 * 
	 * @param aid	会议申请的id
	 * @param fileType	文件类型 1：会议附件      2：会议纪要附件
	 * @param response
	 */
	@RequestMapping("/downloadFile")
	public void downloadFile(String code,String aid,Integer fileType,HttpServletResponse response){
		
		if(!FileValidateCode.isActiveCode(code)){//文件验证码
			return;
		}
		
		if(StringUtils.isBlank(aid) || fileType == null){
			return;
		}
		Application app = appService.get(aid);
		if(app == null)
			return ;
		
		response.setContentType("multipart/form-data");
		String fileName = null;
		String fileUUidName = null;
		if(fileType == 1){
			fileName = app.getAppFileName();
			fileUUidName = app.getAppUuidName();
		}else if(fileType == 2){
			fileName = app.getMinutesFileName();
			fileUUidName = app.getMinutesUuidName();
		}
		try {
			fileName = new String(fileName.getBytes(),"ISO-8859-1");//header只支持ASCII码	所以需要转码
		} catch (UnsupportedEncodingException e1) {
		}		
		response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(PATH,fileUUidName));
			IOUtils.copy(fis, response.getOutputStream());				//响应下载
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
