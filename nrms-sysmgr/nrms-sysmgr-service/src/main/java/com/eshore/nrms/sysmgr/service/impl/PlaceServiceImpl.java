package com.eshore.nrms.sysmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import com.eshore.nrms.sysmgr.dao.IPlaceDAO;
import com.eshore.nrms.sysmgr.pojo.Place;
import com.eshore.nrms.sysmgr.service.IPlaceService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PlaceServiceImpl extends BaseServiceImpl<Place> implements IPlaceService{

	@Autowired 
	private IPlaceDAO placeDAO;
	
	@Override
	public List<Place> getAllPlaces() {
		return placeDAO.queryAllPlaces();
	}

	@Override
	public List<Place> getPlaces(Place place) {
		return placeDAO.queryPlaces(place);
	}

	@Override
	public List<Place> getPlaces(Place place, PageConfig pc) {
		return placeDAO.queryPlaces(place, pc);
	}

	@Override
	public IBaseDao<Place> getDao() {
		return placeDAO;
	}

	@Override
	public int getCountOfName(String placeName) {
		return placeDAO.countOfName(placeName);
	}

	@Override
	public Place getByPlaceName(String placeName) {
		return placeDAO.queryByPlaceName(placeName);
	}

	@Override
	public int getCount(Place place) {
		return placeDAO.queryCount(place);
	}

}
