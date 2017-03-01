package com.eshore.nrms.sysmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.common.utils.type.StringUtils;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;
import com.eshore.nrms.sysmgr.dao.IPlaceDAO;
import com.eshore.nrms.sysmgr.pojo.Place;

@Repository
public class PlaceDAOImpl extends JpaDaoImpl<Place> implements IPlaceDAO{

    @Override
    public List<Place> queryAllPlaces() {
        return this.query("from Place where placeState=1", null);
    }

    @Override
    public List<Place> queryPlaces(Place place) {
        if(place == null)
            return queryAllPlaces();
        StringBuilder hql = new StringBuilder("from Place where placeState=1");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(place, hql, params);

        return this.query(hql.toString(), params.toArray());
    }

    private void builderHqlAndParams(Place place, StringBuilder hql, ArrayList<Object> params) {
        if(StringUtils.isNotBlank(place.getId())){
            hql.append(" and id=?");
            params.add(place.getId());
        }
        if(StringUtils.isNotBlank(place.getPlaceName())){
            hql.append(" and placeName like ?");
            params.add("%" + place.getPlaceName() + "%");
        }
        if(StringUtils.isNotBlank(place.getPlaceDesc())){
            hql.append(" and placeDesc like ?");
            params.add("%" + place.getPlaceDesc() + "%");
        }
    }

    @Override
    public List<Place> queryPlaces(Place place, PageConfig pc) {
        if(place == null)
            return this.queryPage(null, pc, null);

        StringBuilder hql = new StringBuilder("from Place where placeState=1");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(place, hql, params);

        return this.queryPage(hql.toString(), pc, params.toArray());
    }

	@Override
	public int countOfName(String placeName) {
		return getCount("select count(*) from a_place where place_state=1 and place_name='"+placeName+"'");
	}

	@Override
	public Place queryByPlaceName(String placeName) {
		String hql = "from Place where placeState=1 and placeName=?";
		List<Place> list = this.query(hql, new Object[]{placeName});
		if(list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}

	@Override
	public int queryCount(Place place) {
		StringBuilder hql = new StringBuilder("select count(id) from Place where placeState=1");
        ArrayList<Object> params = new ArrayList<Object>();
        builderHqlAndParams(place, hql, params);
		return super.queryCount(hql.toString(), params.toArray());
	}

}
