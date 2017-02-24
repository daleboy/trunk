package com.eshore.nrms.sysmgr.service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.vo.PageVo;

/**
 * Created by forgeeks at 2017-02-24 9:47
 */
public interface  IUserService extends IBaseService<User> {

    public User userLogin(String loginName);

    public PageVo<User> queryUserByPage(User user , PageConfig page);



}
