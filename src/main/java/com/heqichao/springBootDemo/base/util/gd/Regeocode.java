package com.heqichao.springBootDemo.base.util.gd;

/**
 * Created by heqichao on 2019-8-10.
 */
public class Regeocode {

    /**
     * 结构化地址信息
     */
    private String formatted_address;

    /**
     * 地址元素列表
     */
    private AddressComponent addressComponent;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    @Override
    public String toString() {
        return "Regeocode{" +
                "formatted_address='" + formatted_address + '\'' +
                ", addressComponent=" + addressComponent +
                '}';
    }
}
