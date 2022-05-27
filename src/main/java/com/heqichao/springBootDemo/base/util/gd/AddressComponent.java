package com.heqichao.springBootDemo.base.util.gd;

/**
 * Created by heqichao on 2019-8-10.
 */
public class AddressComponent {
    /**
     * 坐标点所在省名称
     */
    private String province;

    /**
     * 坐标点所在城市名称
     */
    //private String city;

    /**
     * 城市编码
     */
    private String citycode;

    /**
     * 坐标点所在区
     */
    private String district;

    /**
     * 行政区编码
     */
    private String adcode;

    /**
     * 坐标点所在乡镇/街道（此街道为社区街道，不是道路信息）
     */
 //  private String township;

    /**
     * 乡镇街道编码
     */
  //  private String towncode;

    /**
     * 社区信息列表
     */
   // private Neighborhood neighborhood;

    /**
     * 楼信息列表
     */
    //private Building building ;

    /**
     * 门牌信息列表
     */
  //  private StreetNumber streetNumber ;
    /**
     * 所属海域信息
     */
   // private String seaArea ;

    /**
     * 经纬度所属商圈列表
     */
    //private BusinessAreas[] businessAreas ;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

//    public String getTownship() {
//        return township;
//    }
//
//    public void setTownship(String township) {
//        this.township = township;
//    }

//    public String getTowncode() {
//        return towncode;
//    }
//
//    public void setTowncode(String towncode) {
//        this.towncode = towncode;
//    }

//    public Neighborhood getNeighborhood() {
//        return neighborhood;
//    }
//
//    public void setNeighborhood(Neighborhood neighborhood) {
//        this.neighborhood = neighborhood;
//    }

//    public Building getBuilding() {
//        return building;
//    }
//
//    public void setBuilding(Building building) {
//        this.building = building;
//    }
//
//    public StreetNumber getStreetNumber() {
//        return streetNumber;
//    }
//
//    public void setStreetNumber(StreetNumber streetNumber) {
//        this.streetNumber = streetNumber;
//    }
//
//    public String getSeaArea() {
//        return seaArea;
//    }
//
//    public void setSeaArea(String seaArea) {
//        this.seaArea = seaArea;
//    }

//    public BusinessAreas[] getBusinessAreas() {
//        return businessAreas;
//    }
//
//    public void setBusinessAreas(BusinessAreas[] businessAreas) {
//        this.businessAreas = businessAreas;
//    }


    @Override
    public String toString() {
        return "AddressComponent{" +
                "province='" + province + '\'' +
                ", citycode='" + citycode + '\'' +
                ", district='" + district + '\'' +
                ", adcode='" + adcode + '\'' +
                '}';
    }
}
