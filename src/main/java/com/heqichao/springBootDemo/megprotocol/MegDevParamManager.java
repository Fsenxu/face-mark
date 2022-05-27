package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MegDevParamManager {
    private static final String MODULE_NAME = "device_parameter";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, SET_NETWORK, GET_NETWORK, SET_SYSTEM_TIME, GET_SYSTEM_TIME, SET_NTP, GET_NTP, TEST_NTP, SET_PORTS, GET_PORTS,
        SET_UPNP, GET_UPNP, SET_SNMP, GET_SNMP, SET_DDNS, GET_DDNS, SET_PPPOE, GET_PPPOE, SET_IEEE802_1X, GET_IEEE802_1X,
        SET_SMTP, GET_SMTP, TEST_SMTP, SET_ONVIF, GET_ONVIF, SET_GAT1400_PARAM, GET_GAT1400_PARAM, SET_GB28181_PARAM, GET_GB28181_PARAM,
        SET_DEVICE_WORKMODE, GET_DEVICE_WORKMODE, SET_MEGNETIC_CONTAT, GET_MEGNETIC_CONTAT, SET_WEIGAND, GET_WEIGAND, SET_OUPUT_SWITCH,
        GET_OUTPUT_SWITCH, SET_PICTURE_APP_POLICY, GET_PICTURE_APP_POLICY, DOOR_ACCESS_CONTORL, GET_FTP, SET_FTP, TEST_FTP, SET_SSH, GET_SSH,
        SET_GAT1400_SERVER_PARAM, GET_GAT1400_SERVER_PARAM, SET_PDNS, GET_PDNS
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取模块能力集参数
     *
     * @param device 设备
     * @param outJson 模块能力集参数
     * @return 错误码
     */
    public static int getCap(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_CAP.ordinal(), outJson);
    }

    /**
     * 设置网络参数
     *
     * @param device 设备
     * @param inJson 网络参数
     * @return 错误码
     */
    public static int setNetwork(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_NETWORK.ordinal(), inJson);
    }

    /**
     * 获取网络参数
     *
     * @param device 设备
     * @param outJson 网络参数
     * @return 错误码
     */
    public static int getNetwork(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_NETWORK.ordinal(), outJson);
    }

    /**
     * 设置系统时间参数
     *
     * @param device 设备
     * @param inJson 系统时间参数
     * @return 错误码
     */
    public static int setSystemTime(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_SYSTEM_TIME.ordinal(), inJson);
    }

    /**
     * 获取系统时间参数
     *
     * @param device 设备
     * @param outJson 系统时间参数
     * @return 错误码
     */
    public static int getSystemTime(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_SYSTEM_TIME.ordinal(), outJson);
    }

    /**
     * 设置NTP参数
     *
     * @param device 设备
     * @param inJson NTP参数
     * @return 错误码
     */
    public static int setNtp(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_NTP.ordinal(), inJson);
    }

    /**
     * 获取NTP参数
     *
     * @param device 设备
     * @param outJson NTP参数
     * @return 错误码
     */
    public static int getNtp(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_NTP.ordinal(), outJson);
    }

    /**
     * 测试NTP
     *
     * @param device 设备
     * @param inJson NTP参数
     * @return 错误码
     */
    public static int testNtp(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.TEST_NTP.ordinal(), inJson);
    }

    /**
     * 设置网络端口
     *
     * @param device 设备
     * @param inJson 网络端口
     * @return 错误码
     */
    public static int setPorts(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_PORTS.ordinal(), inJson);
    }

    /**
     * 获取网络端口
     *
     * @param device 设备
     * @param outJson 网络端口
     * @return 错误码
     */
    public static int getPorts(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_PORTS.ordinal(), outJson);
    }

    /**
     * 设置UPNP
     *
     * @param device 设备
     * @param inJson UPNP参数
     * @return 错误码
     */
    public static int setUpnp(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_UPNP.ordinal(), inJson);
    }

    /**
     * 获取UPNP
     *
     * @param device 设备
     * @param outJson UPNP参数
     * @return 错误码
     */
    public static int getUpnp(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_UPNP.ordinal(), outJson);
    }

    /**
     * 设置SNMP
     *
     * @param device 设备
     * @param inJson UPNP参数
     * @return 错误码
     */
    public static int setSnmp(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_SNMP.ordinal(), inJson);
    }

    /**
     * 获取SNMP
     *
     * @param device 设备
     * @param outJson UPNP参数
     * @return 错误码
     */
    public static int getSnmp(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_SNMP.ordinal(), outJson);
    }

    /**
     * 设置DDNS
     *
     * @param device 设备
     * @param inJson DDNS参数
     * @return 错误码
     */
    public static int setDdns(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_DDNS.ordinal(), inJson);
    }

    /**
     * 获取DDNS
     *
     * @param device 设备
     * @param outJson DDNS参数
     * @return 错误码
     */
    public static int getDdns(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_DDNS.ordinal(), outJson);
    }

    /**
     * 设置PPPOE
     *
     * @param device 设备
     * @param inJson PPPOE参数
     * @return 错误码
     */
    public static int setPppoe(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_PPPOE.ordinal(), inJson);
    }

    /**
     * 获取PPPOE
     *
     * @param device 设备
     * @param outJson PPPOE参数
     * @return 错误码
     */
    public static int getPppoe(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_PPPOE.ordinal(), outJson);
    }

    /**
     * 设置8021.x
     *
     * @param device 设备
     * @param inJson 8021.x参数
     * @return 错误码
     */
    public static int setIeee8021x(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_IEEE802_1X.ordinal(), inJson);
    }

    /**
     * 获取8021.x
     *
     * @param device 设备
     * @param outJson 8021.x参数
     * @return 错误码
     */
    public static int getIeee8021x(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_IEEE802_1X.ordinal(), outJson);
    }

    /**
     * 设置SMTP
     *
     * @param device 设备
     * @param inJson SMTP参数
     * @return 错误码
     */
    public static int setSmtp(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_SMTP.ordinal(), inJson);
    }

    /**
     * 获取SMTP
     *
     * @param device 设备
     * @param outJson SMTP参数
     * @return 错误码
     */
    public static int getSmtp(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_SMTP.ordinal(), outJson);
    }

    /**
     * 测试SMTP
     *
     * @param device 设备
     * @param inJson SMTP参数
     * @return 错误码
     */
    public static int testSmtp(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.TEST_SMTP.ordinal(), inJson);
    }

    /**
     * 设置ONVIF
     *
     * @param device 设备
     * @param inJson ONVIF参数
     * @return 错误码
     */
    public static int setOnvif(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_ONVIF.ordinal(), inJson);
    }

    /**
     * 获取ONVIF
     *
     * @param device 设备
     * @param outJson ONVIF参数
     * @return 错误码
     */
    public static int getOnvif(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_ONVIF.ordinal(), outJson);
    }

    /**
     * 设置GAT1400客户端参数
     *
     * @param device 设备
     * @param inJson GAT1400客户端参数
     * @return 错误码
     */
    public static int setGat1400Param(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_GAT1400_PARAM.ordinal(), inJson);
    }

    /**
     * 获取GAT1400客户端参数
     *
     * @param device 设备
     * @param outJson GAT1400客户端参数
     * @return 错误码
     */
    public static int getGat1400Param(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_GAT1400_PARAM.ordinal(), outJson);
    }

    /**
     * 设置GB28181参数
     *
     * @param device 设备
     * @param inJson GB28181参数
     * @return 错误码
     */
    public static int setGb28181Param(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_GB28181_PARAM.ordinal(), inJson);
    }

    /**
     * 获取GB28181参数
     *
     * @param device 设备
     * @param outJson GB28181参数
     * @return 错误码
     */
    public static int getGb28181Param(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_GB28181_PARAM.ordinal(), outJson);
    }

    /**
     * 设置设备工作模式
     *
     * @param device 设备
     * @param inJson 设备工作模式 参数
     * @return 错误码
     */
    public static int setDeviceWorkmode(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_DEVICE_WORKMODE.ordinal(), inJson);
    }

    /**
     * 获取设备工作模式
     *
     * @param device 设备
     * @param outJson 设备工作模式 参数
     * @return 错误码
     */
    public static int getDeviceWorkmode(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_DEVICE_WORKMODE.ordinal(), outJson);
    }

    /**
     * 设置门磁参数
     *
     * @param device 设备
     * @param inJson 门磁参数
     * @return 错误码
     */
    public static int setMegneticContact(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_MEGNETIC_CONTAT.ordinal(), inJson);
    }

    /**
     * 获取门磁参数
     *
     * @param device 设备
     * @param outJson 门磁参数
     * @return 错误码
     */
    public static int getMegneticContact(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_MEGNETIC_CONTAT.ordinal(), outJson);
    }

    /**
     * 设置韦根参数
     *
     * @param device 设备
     * @param inJson 韦根参数
     * @return 错误码
     */
    public static int setWeigand(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_WEIGAND.ordinal(), inJson);
    }

    /**
     * 获取韦根参数
     *
     * @param device 设备
     * @param outJson 韦根参数
     * @return 错误码
     */
    public static int getWeigand(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_WEIGAND.ordinal(), outJson);
    }

    /**
     * 设置开关量输出门禁参数
     *
     * @param device 设备
     * @param inJson 开关量输出门禁参数
     * @return 错误码
     */
    public static int setOutputSwitch(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_OUPUT_SWITCH.ordinal(), inJson);
    }

    /**
     * 获取开关量输出门禁参数
     *
     * @param device 设备
     * @param outJson 开关量输出门禁参数
     * @return 错误码
     */
    public static int getOutputSwitch(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_OUTPUT_SWITCH.ordinal(), outJson);
    }

    /**
     * 设置图片应用策略参数
     *
     * @param device 设备
     * @param inJson 图片应用策略参数 数组
     * @return 错误码
     */
    public static int setPictureAppPolicy(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_PICTURE_APP_POLICY.ordinal(), inJson);
    }

    /**
     * 获取图片应用策略参数
     *
     * @param device 设备
     * @param outJson 图片应用策略参数 数组
     * @return 错误码
     */
    public static int getPictureAppPolicy(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_PICTURE_APP_POLICY.ordinal(), outJson);
    }

    /**
     * 门禁控制
     *
     * @param device 设备
     * @param inJson 入参
     * @return 错误码
     */
    public static int doorAccessControl(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_PICTURE_APP_POLICY.ordinal(), inJson);
    }

    /**
     * 设置FTP
     *
     * @param device 设备
     * @param inJson FTP参数
     * @return 错误码
     */
    public static int setFtp(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_FTP.ordinal(), inJson);
    }

    /**
     * 获取FTP
     *
     * @param device 设备
     * @param outJson FTP参数
     * @return 错误码
     */
    public static int getFtp(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_FTP.ordinal(), outJson);
    }

    /**
     * 测试FTP
     *
     * @param device 设备
     * @param inJson FTP参数
     * @return 错误码
     */
    public static int testFtp(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.TEST_FTP.ordinal(), inJson);
    }

    /**
     * 设置SSH
     *
     * @param device 设备
     * @param inJson SSH参数
     * @return 错误码
     */
    public static int setSsh(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_SSH.ordinal(), inJson);
    }

    /**
     * 获取SSH
     *
     * @param device 设备
     * @param outJson SSH参数
     * @return 错误码
     */
    public static int getSsh(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_SSH.ordinal(), outJson);
    }

    /**
     * 设置GAT1400 Server参数
     *
     * @param device 设备
     * @param inJson GAT1400 Server参数
     * @return 错误码
     */
    public static int setGat1400ServerParam(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_GAT1400_SERVER_PARAM.ordinal(), inJson);
    }

    /**
     * 获取GAT1400 Server参数
     *
     * @param device 设备
     * @param outJson GAT1400 Server参数
     * @return 错误码
     */
    public static int getGat1400ServerParam(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_GAT1400_SERVER_PARAM.ordinal(), outJson);
    }

    /**
     * 设置PDNS参数
     *
     * @param device 设备
     * @param inJson PDNS参数
     * @return 错误码
     */
    public static int setPdns(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_GAT1400_SERVER_PARAM.ordinal(), inJson);
    }

    /**
     * 获取PDNS参数
     *
     * @param device 设备
     * @param outJson PDNS参数
     * @return 错误码
     */
    public static int getPdns(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_GAT1400_SERVER_PARAM.ordinal(), outJson);
    }
}
