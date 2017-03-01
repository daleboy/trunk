package com.eshore.nrms.sysmgr.service;

import java.util.List;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.nrms.sysmgr.pojo.Place;

public interface IPlaceService extends IBaseService<Place>{

    /**
     * 得到所有的有效会议室
     * @return	会议室集合
     */
    public List<Place> getAllPlaces();

    /**
     * 通过查询条件place 得到满足条件的会议室集合
     * 支持模糊查询
     * @param place	查询条件
     * @return	会议室集合
     */
    public List<Place> getPlaces(Place place);

    /**
     * 通过查询条件 和 分页条件	得到满足条件的会议室集合
     * 支持模糊查询
     * @param place	查询条件
     * @param pc 分页条件
     * @return	会议室集合
     */
    public List<Place> getPlaces(Place place, PageConfig pc);

    /**
     * 得到该会议室名的条数  
     * @param placeName 指定的会议室名
     * @return 条数
     */
    public int getCountOfName(String placeName);
    
    /**
     * 通过会议室名得到会议室
     * @param placeName
     * @return
     */
    public Place getByPlaceName(String placeName);
    
    /**
     * 满足查询条件的条数
     * @param place
     * @return
     */
    public int getCount(Place place);
    
}
