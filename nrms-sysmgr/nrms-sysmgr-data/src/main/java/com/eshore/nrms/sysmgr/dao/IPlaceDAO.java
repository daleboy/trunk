package com.eshore.nrms.sysmgr.dao;

import java.util.List;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.nrms.sysmgr.pojo.Place;

public interface IPlaceDAO extends IBaseDao<Place>{

    public List<Place> queryAllPlaces();

    public List<Place> queryPlaces(Place place);

    public List<Place> queryPlaces(Place place, PageConfig pc);
    
    public int countOfName(String placeName);
    
    public int queryCount(Place place);
    
    public Place queryByPlaceName(String placeName);
    
}
