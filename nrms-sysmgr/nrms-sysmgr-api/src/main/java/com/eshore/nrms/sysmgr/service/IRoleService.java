package com.eshore.nrms.sysmgr.service;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.Role;
import com.eshore.nrms.vo.PageVo;

import java.util.List;

/**
 * Created by forgeeks at 2017-02-25 16:45
 */
public interface IRoleService extends IBaseService<Role>{

    public PageVo<Role> queryRoleListByPage(Role role , PageConfig page);
    public Integer queryCountByRoleName(String roleName);
}
