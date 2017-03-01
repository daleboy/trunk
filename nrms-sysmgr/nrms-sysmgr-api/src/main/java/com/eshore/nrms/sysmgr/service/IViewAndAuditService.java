package com.eshore.nrms.sysmgr.service;

import java.util.List;
import java.util.Map;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.pojo.User;

public interface IViewAndAuditService extends IBaseService<Application>{
	
	String PATH = "";

	/**
	 * 通过会议申请id查询全部改会议申请的全部信息
	 * @param aid
	 * @return
	 */
	public Application getFull(String aid);

	/**
	 * 查询全部会议信息
	 * @param app 	查询条件
	 * @return
	 */
	public List<Application> getFull(Application app);
	
	/**
	 * 分页查询全部会信息
	 * @param map 	查询条件
	 * @param pc	分页条件
	 * @return
	 */
	public List<Application> getFullPage(Map<String, Object> map, PageConfig pc);
	
	/**
	 * 分页查询全部会信息
	 * @param app 	查询条件
	 * @param pc	分页条件
	 * @return
	 */
	public List<Application> getFullPage(Application app, PageConfig pc);
	
	/**
	 * 与我相关的会议信息
	 * @param user
	 * @param app
	 * @param pc
	 * @return
	 */
	public List<Application> getFullPageWithMe(User user, Application app, PageConfig pc);
	
	/**
	 * 返回满足查询条件的记录条数
	 * @param app
	 * @return
	 */
	public Integer getCount(Application app);
	
	/**
	 * 删除该条申请下的所有文件，若本来就不存文件返回true
	 * @param app
	 */
	public boolean deleteFile(Application app);
	
	/**
	 * 查询参与人
	 * @param appId
	 * @return
	 */
	public List<User> getUsersInApplication(String appId);
	
	/**
	 * 验证时间段是否冲突
	 * @param app
	 * @return
	 */
	public boolean verifyTimeConflict(Application app);
}
