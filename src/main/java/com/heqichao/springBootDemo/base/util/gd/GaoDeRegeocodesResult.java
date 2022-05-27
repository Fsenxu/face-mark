package com.heqichao.springBootDemo.base.util.gd;

/**
 *  https://lbs.amap.com/api/webservice/guide/api/georegeo
 * Created by heqichao on 2019-8-10.
 */
public class GaoDeRegeocodesResult {
    /**
     * 返回值为 0 或 1，0 表示请求失败；1 表示请求成功。
     */
    private String status;

    /**
     * 当 status 为 0 时，info 会返回具体错误原因，否则返回“OK”。详情可以参考info状态表
     */
    private String info;

    /**
     * 错误码
     */
    private String infocode;

    /**
     * 逆地理编码列表
     */
    private Regeocode regeocode;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public Regeocode getRegeocode() {
        return regeocode;
    }

    public void setRegeocode(Regeocode regeocode) {
        this.regeocode = regeocode;
    }

    @Override
    public String toString() {
        return "GaoDeRegeocodesResult{" +
                "status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", infocode='" + infocode + '\'' +
                ", regeocodes=" + regeocode +
                '}';
    }
}
