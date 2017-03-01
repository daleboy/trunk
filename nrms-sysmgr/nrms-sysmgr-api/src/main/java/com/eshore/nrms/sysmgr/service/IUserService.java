package com.eshore.nrms.sysmgr.service;

import java.util.List;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.Place;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.vo.PageVo;

/**
 * Created by forgeeks at 2017-02-24 9:47
 */
public interface  IUserService extends IBaseService<User> {
	/**
	 * 通过loginName查询得到userr对象象
	 * @param loginName 登录名
	 * @return
	 */
    public User userLogin(String loginName);
    
    /**
	 * 通过loginName查询得到userr对象象
	 * @param loginName 登录名
	 * @return
	 */
    public List<User> getUserById(String id);
    
    /**
     * 通过user查询,得到满足条件的user集合
     * @param user	user对象i
     * @param page	分页
     * @return	用户集合
     */
    public PageVo<User> queryUserByPage(User user, PageConfig page);
    
    
    
    /**
     * 通过查询条件user 得到满足条件的用户集合
     * 支持模糊查询
     * @param  	user 查询条件
     * @return	用户集合
     */
    public List<User> getAllUsers(User user);
    
    /**
     * 查询所有的用户集合合
     * @return   //返回所有的用户集合和
     */
    public List<User> getUsers();

    /**
     * 满足查询条件的条数
     * @param user
     * @return
     */
    public int getCount(User user);
    /**
     * 查询出所有的用户与部门门职业 
     * @return
     */
    public List<User> getallUsers();
    

}
