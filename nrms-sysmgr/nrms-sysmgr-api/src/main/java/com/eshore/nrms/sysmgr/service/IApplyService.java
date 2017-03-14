package com.eshore.nrms.sysmgr.service;

import java.util.List;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.Application;
import com.eshore.nrms.sysmgr.pojo.User;

public interface IApplyService extends IBaseService<Application> {
	 /**
     * 查询所有申请
     * @return	申请（Application）集合
     */
    public List<Application> getAllApply();

    /**
     * 通过查询条件apply 得到满足条件的申请集合
     * 支持模糊查询
     * @param apply	查询条件
     * @return	申请集合
     */
    public List<Application> getApply(Application apply);
    
    /**
     * 通过查询条件 和 分页条件	得到满足条件的申请集合
     * 支持模糊查询
     * @param apply	查询条件
     * @param pc 分页条件
     * @return	申请集合
     */
    public List<Application> getApplys(Application apply, PageConfig pc);
    
    /**
     * 获取与我相关的申请
     * @param user 
     * @param app
     * @param pc
     * @return
     */
    public List<Application> getApplysByUid(User user, Application app, PageConfig pc);
    
    /**
     * 查询满足条件的申请条数
     * @param app 条件
     * @return 条数
     */
    public Integer getCountOfApp(Application app);
}
