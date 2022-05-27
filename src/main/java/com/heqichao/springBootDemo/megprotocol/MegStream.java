package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MegStream extends ComponentFactory {
    private static final String MODULE_NAME = "media_stream";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, QUERY_STREAM_CONFIG
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取流管理模块能力集
     *
     * @param device  设备
     * @param outJson 流管理模块能力集参数
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
     * 获取流配置
     *
     * @param device  设备
     * @param outJson 流参数信息
     * @return 错误码
     */
    public static int queryStreamConfig(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.QUERY_STREAM_CONFIG.ordinal(), outJson);
    }
}
