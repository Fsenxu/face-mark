package com.heqichao.springBootDemo.base.util.gd;

/**
 * Created by heqichao on 2019-8-10.
 */
public class BusinessAreas {
    /**
     * 商圈信息
     */
    private String businessArea;
    /**
     * 商圈中心点经纬度
     */
    private String location;
    /**
     *  商圈名称
     */
    private String name;
    /**
     *  商圈所在区域的adcode
     */
    private String id;

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
