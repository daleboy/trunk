package com.eshore.nrms.sysmgr.service.impl;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import com.eshore.nrms.sysmgr.dao.IUserDao;
import com.eshore.nrms.sysmgr.pojo.User;
import com.eshore.nrms.sysmgr.service.IUserService;
import com.eshore.nrms.util.PageUtil;
import com.eshore.nrms.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by forgeeks at 2017-02-24 10:45
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    @Autowired
    IUserDao userDao;

    @Override
    public IBaseDao<User> getDao() {
        return userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public User userLogin(String loginName) {
        return userDao.queryUserByLoginName(loginName);
    }

    @Override
    public PageVo<User> queryUserByPage(User user, PageConfig page) {
        List<User> list = this.userDao.queryUserList(user, page);
        return PageUtil.getPageList(page, list);
    }
}
