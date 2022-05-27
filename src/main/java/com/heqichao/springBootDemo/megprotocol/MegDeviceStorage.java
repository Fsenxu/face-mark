package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;

/**
 * 设备存储管理模块，定义了存储介质、布防和录像相关配置函数指针操作
 */
@Slf4j
public abstract class MegDeviceStorage {
    private static final String MODULE_NAME = "device_storage";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, GET_INFO, FORMAT, SET_REC_SCHEDULE, GET_REC_SCHEDULE, SET_RECORD_CFG, GET_RECORD_CFG, GET_IMAGE
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取设备存储管理能力集
     *
     * @param device 设备
     * @param outJson 能力集参数
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
     * 获取硬盘或SD卡信息
     *
     * @param device 设备
     * @param outJson 信息参数
     * @return 错误码
     */
    public static int getInfo(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_INFO.ordinal(), outJson);
    }

    /**
     * 格式化硬盘或SD卡
     *
     * @param device 设备
     * @param inJson 格式化参数
     * @return 错误码
     */
    public static int format(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.FORMAT.ordinal(), inJson);
    }

    /**
     * 设置录像布防配置
     *
     * @param device 设备
     * @param inJson 布防配置参数
     * @return 错误码
     */
    public static int setRecSchedule(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_REC_SCHEDULE.ordinal(), inJson);
    }

    /**
     * 获取录像布防配置
     *
     * @param device 设备
     * @param outJson 布防配置参数
     * @return 错误码
     */
    public static int getRecSchedule(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_REC_SCHEDULE.ordinal(), outJson);
    }

    /**
     * 设置录像配置
     *
     * @param device 设备
     * @param inJson 录像配置参数
     * @return 错误码
     */
    public static int setRecordCfg(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_RECORD_CFG.ordinal(), inJson);
    }

    /**
     * 获取录像配置
     *
     * @param device 设备
     * @param outJson 录像配置参数
     * @return 错误码
     */
    public static int getRecordCfg(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_RECORD_CFG.ordinal(), outJson);
    }

    /**
     * 获取图片
     *
     * @param device 设备
     * @param inJson  图片参数  json格式
     * @param imgSize 图片文件大小
     * @param imgData 图片文件数据(记得在外部调用MEG_CONN_LIBRARY.INSTANTCE.meg_conn_free 释放此空间！)
     * @return 错误码
     */
    public static int getImage(MegDevice device, String inJson, IntByReference imgSize, PointerByReference imgData) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.GET_IMAGE.ordinal(), new Object[]{funcPointer, inJson, imgSize, imgData});
    }
}
