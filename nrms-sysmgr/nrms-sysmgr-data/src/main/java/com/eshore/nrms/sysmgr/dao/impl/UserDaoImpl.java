package com.eshore.nrms.sysmgr.dao.impl;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IUserDao;
import com.eshore.nrms.sysmgr.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forgeeks at 2017-02-24 10:34
 */

@Repository
public class UserDaoImpl extends JpaDaoImpl<User> implements IUserDao {

    @Override
    public User queryUserByLoginName(String loginName) {
        String hql = "from User u where u.loginName = ?";
        return this.getPojo(hql, new Object[]{loginName});
    }

    @Override
    public List<User> queryUserList(User user, PageConfig page) {

        StringBuilder hql = new StringBuilder("from User u where 1=1 ");
        List params = new ArrayList();
        if (StringUtils.isNotBlank(user.getLoginName())) {
            hql.append(" and u.loginName like ? ");
            params.add("%" + user.getLoginName() + "%");
        }

        hql.append(" and u.userState = 1");
        hql.append(" order by loginName ");

        return this.queryPage(hql.toString(), page, params.toArray());
    }


}
