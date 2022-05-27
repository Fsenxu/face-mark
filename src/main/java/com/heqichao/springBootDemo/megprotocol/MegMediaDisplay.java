package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;

/**
 * 显示输出功能模块用于配置设备显示输出模式如HDMI、VGA、DP、TYPE-C等
 */
@Slf4j
public abstract class MegMediaDisplay {
    private static final String MODULE_NAME = "media_display";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_DISPLAY_CAP, GET_DISPLAY_CFG, SET_DISPLAY_CFG
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取显示输出模块能力集
     *
     * @param device  设备
     * @param inJson  查询参数
     * @param outJson 显示输出模块能力集
     * @return 错误码
     */
    public static int getDisplayCap(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_DISPLAY_CAP.ordinal(), inJson, outJson);
    }

    /**
     * 获取显示输出配置
     *
     * @param device  设备
     * @param inJson  请求配置参数
     * @param outJson 显示输出配置参数
     * @return 错误码
     */
    public static int getDisplayCfg(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_DISPLAY_CFG.ordinal(), inJson, outJson);
    }

    /**
     * 设置显示输出配置
     *
     * @param device  设备
     * @param inJson  显示输出配置参数
     * @return 错误码
     */
    public static int setDisplayCfg(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_DISPLAY_CFG.ordinal(), inJson);
    }
}
