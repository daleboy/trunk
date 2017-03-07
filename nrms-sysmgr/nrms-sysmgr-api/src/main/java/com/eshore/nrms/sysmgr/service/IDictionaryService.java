package com.eshore.nrms.sysmgr.service;

import java.util.List;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.Dictionary;
import com.eshore.nrms.vo.PageVo;

public interface IDictionaryService extends IBaseService<Dictionary>{
	
	/**
     * 查询所有申请
     * @return	部门职位（dictionary）集合
     */
    public List<Dictionary> getAllDictionarys();

    /**
     * 通过查询条件apply 得到满足条件的申请集合
     * 支持模糊查询
     * @param apply	查询条件
     * @return	申请集合
     */
    public PageVo<Dictionary> getDictionaryByPage(Dictionary dictionary, PageConfig page);
    
    public List<Dictionary> getDictionarys(Dictionary dictionary);
    
    /**
     * 通过查询条件 和 分页条件	得到满足条件的字典集合
     * 支持模糊查询
     * @param dictionary	查询条件
     * @param pc 分页条件
     * @return	字典集合
     */
    public List<Dictionary> getDictionarys(Dictionary dictionary, PageConfig pc);
    
    /**
     * 查询满足条件的字典条数
     * @param dictionary 条件
     * @return 条数
     */
    public Integer getCountOfDictionary(Dictionary dictionary);
    
    Dictionary getDictionaryByDickey(Dictionary dictionary);
    
    Dictionary getDictionaryByDicValue(String dicvalue);
    
    

}
