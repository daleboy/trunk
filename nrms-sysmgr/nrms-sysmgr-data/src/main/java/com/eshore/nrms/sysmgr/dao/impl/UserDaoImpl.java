package com.eshore.nrms.sysmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IUserDao;
import com.eshore.nrms.sysmgr.pojo.Dictionary;
import com.eshore.nrms.sysmgr.pojo.Role;
import com.eshore.nrms.sysmgr.pojo.User;

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
        if (user==null) 
			return this.queryPage(null, page, null);
        StringBuilder hql = new StringBuilder("from User u,Dictionary dept,Dictionary job,Dictionary posi ,Role r where u.userState=1 ");
        hql.append("and u.deptKey=dept.dicKey and u.jobKey=job.dicKey and u.positionKey=posi.dicKey and u.roleId=r.id");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(user, hql, params);
        List list = this.queryPage(hql.toString(), page, params.toArray());
        List<User> userlist = new ArrayList<User>();
        Object[] arr;
        User usertemp;
        Dictionary d;
        Role role;
        for (Object object : list) {
        	arr = (Object[]) object;
        	//获得所有属性
        	usertemp = (User) arr[0];
        	//获得部门属性
        	d = (Dictionary) arr[1];
        	usertemp.setDept(d.getDicValue());
        	//获得工作属性
        	d = (Dictionary) arr[2];
        	usertemp.setJob(d.getDicValue());
        	//获得职位属性
        	d = (Dictionary) arr[3];
        	usertemp.setPosi(d.getDicValue());
        	//设置角色role
        	role = (Role) arr[4];
        	usertemp.setRole(role.getRoleName());
        	userlist.add(usertemp);
		}
       // builderHqlAndParams(user, hql, params);
        return userlist;
    }

	@Override
	public List<User> queryAllUsers(User user) {

		if(user == null)
            return queryUsers();
        StringBuilder hql = new StringBuilder("from User where userState=1");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(user, hql, params);
        return this.query(hql.toString(), params.toArray());
	}
	
	

	@Override
	public int countOfName(String userName) {
		// TODO Auto-generated method stub
		return getCount("select count(*) from c_user where user_state=1 and user_name='"+userName+"'");
	}

	@Override
	public int queryCount(User user) {
		StringBuilder hql = new StringBuilder("from User where userState=1");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(user, hql, params);
		return super.queryCount(hql.toString(), params.toArray());
	}

	@Override
	public List<User> queryUsers() {
		// TODO Auto-generated method stub
		return this.query("from User where userState=1", null);
	}
    
	private void builderHqlAndParams(User user, StringBuilder hql, ArrayList<Object> params) {
        if(StringUtils.isNotBlank(user.getId())){
            hql.append(" and u.id=?");
            params.add(user.getId());
        }
        if(StringUtils.isNotBlank(user.getDeptKey())){
            hql.append(" and u.deptKey=?");
            params.add(user.getDeptKey());
        }
        if(StringUtils.isNotBlank(user.getRoleId())){
            hql.append(" and u.roleId=?");
            params.add(user.getRoleId());
        }
        if(StringUtils.isNotBlank(user.getPositionKey())){
            hql.append(" and u.positionKey=?");
            params.add(user.getPositionKey());
        }
        if(StringUtils.isNotBlank(user.getUname())){
            hql.append(" and u.uname like ?");
            params.add("%" + user.getUname() + "%");
        }
        
        
    }

	@Override
	public User queryUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


    
}
