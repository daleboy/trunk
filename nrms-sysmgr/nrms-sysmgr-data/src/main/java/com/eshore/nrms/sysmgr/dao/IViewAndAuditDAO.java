package com.eshore.nrms.sysmgr.dao;

import java.util.List;
import java.util.Map;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.pojo.User;

public interface IViewAndAuditDAO extends IBaseDao<Application>{

	/**
	 * 查询会议申请的全部信息
	 * @param app	查询条件
	 * @return
	 */
	public List<Application> queryFull(Application app);
	
	public List<Application> queryFullPage(Map<String, Object> map, PageConfig pc);
	
	/**
	 * 分页查询会议申请的全部信息
	 * @param app	查询条件
	 * @param pc	分页条件
	 * @return	
	 */
	public List<Application> queryFullPage(Application app, PageConfig pc);
	
	/**
	 * 查询会议申请全部信息
	 * @param uid	申请人的id
	 * @param app	查询条件
	 * @param pc	分页条件
	 * @return
	 */
	public List<Application> queryFullPageByUid(String uid, Application app, PageConfig pc);
	
	/**
	 * 返回满足查询条件的记录条数
	 * @param app
	 * @return
	 */
	public Integer queryCount(Application app);
	
	/**
	 * 查询参与人    user中只有id和uname
	 * @param appId
	 * @return
	 */
	public List<User> queryUserInApplication(String appId);
	
	/**
	 * 根据用户id查询用户名
	 * @param uids
	 * @return
	 */
	public List<String> getUsersByIds(List<String> uids);
	
	/**
	 * 验证时间段是否冲突
	 * 		传入参数： startTime  endTime
	 * 		原理： 	        开始时间 <startTime 并且   结束时间<= startTime
	 * 				或者
	 * 				        开始时间 >= endTime 并且 而结束时间 > endTime
	 * @param app
	 * @return
	 */
	public boolean verifyTimeConflict(Application app);
	
}
