package com.eshore.nrms.sysmgr.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "a_place")
public class Place implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 5585766872870542848L;

    @Id
    @GenericGenerator(name="uuidGen",strategy="uuid")
    @GeneratedValue(generator="uuidGen")
    @Column(name = "id")
    private String id;		//主键

    @Column(name = "place_name")
    private String placeName;		//会议室名

    @Column(name = "place_desc")
    private String placeDesc;		//会议是描述信息

    @Column(name = "place_state")
    private Integer placeState;		//会议室状态  0：逻辑删除    1：正常

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDesc() {
        return placeDesc;
    }

    public void setPlaceDesc(String placeDesc) {
        this.placeDesc = placeDesc;
    }

    public Integer getPlaceState() {
        return placeState;
    }

    public void setPlaceState(Integer placeState) {
        this.placeState = placeState;
    }

}
