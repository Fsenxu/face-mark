package com.heqichao.springBootDemo.base.util.gd;

/**
 * Created by heqichao on 2019-8-10.
 */
public class Neighborhood {
    /**
     * 社区名称
     */
    private String[] name;

    /**
     * POI类型
     */
    private String[] type;

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }
}
