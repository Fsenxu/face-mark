package com.heqichao.springBootDemo.base.util.gd;

/**
 * Created by heqichao on 2019-8-10.
 */
public class StreetNumber {
    /**
     * 街道名称
     */
    private String street;

    /**
     * 门牌号
     */
    private String number;

    /**
     * 坐标点
     */
    private String location;

    /**
     * 方向
     */
    private String direction;

    /**
     * 门牌地址到请求坐标的距离
     */
    private String distance;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
