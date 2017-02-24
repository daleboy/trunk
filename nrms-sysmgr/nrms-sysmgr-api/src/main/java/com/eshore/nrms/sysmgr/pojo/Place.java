package com.eshore.nrms.sysmgr.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by forgeeks at 2017-02-24 9:41
 */

@Entity
@Table(name = "a_place")
public class Place implements Serializable {
    private String id;
    private String placeName;
    private String placeDesc;
    private Integer placeState;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "place_name")
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Column(name = "place_desc")
    public String getPlaceDesc() {
        return placeDesc;
    }

    public void setPlaceDesc(String placeDesc) {
        this.placeDesc = placeDesc;
    }

    @Column(name = "place_state")
    public Integer getPlaceState() {
        return placeState;
    }

    public void setPlaceState(Integer placeState) {
        this.placeState = placeState;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeDesc='" + placeDesc + '\'' +
                ", placeState=" + placeState +
                '}';
    }
}
