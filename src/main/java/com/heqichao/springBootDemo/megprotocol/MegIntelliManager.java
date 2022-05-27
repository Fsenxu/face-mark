package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class MegIntelliManager {
    private static final String MODULE_NAME = "intelli_manager";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        QUERY_CAP, QUERY_ALGO_CFG_CAP, ADD_MONITOR, SET_MONITOR, QUERY_MONITOR,  DELETE_MONITOR,
        QUERY_ALG_WAREHOUSE_CAP, START_UPLOAD_ALG_WAREHOUSE, TRANSFER_ALG_WAREHOUSE, DELETE_ALG_WAREHOUSE,
        UPDATE_ALG_WAREHOUSE, INSTALL_ALG_WAREHOUSE, UNINSTALL_ALG_WAREHOUSE, QUERY_ALG_WAREHOUSE_OPERA_RECORD
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取模块能力集
     *
     * @param device 设备
     * @param outJson 模块能力集
     * @return 错误码
     */
    public static int queryCap(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.QUERY_CAP.ordinal(), outJson);
    }

    /**
     * 获取算法配置能力集
     *
     * @param device 设备
     * @param outJson 算法配置能力集参数
     * @return 错误码
     */
    public static int queryAlgCfgCap(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.QUERY_ALGO_CFG_CAP.ordinal(), outJson);
    }

    /**
     * 添加布控通道
     *
     * @param device 设备
     * @param inJson 布控参数
     * @return 错误码
     */
    public static int addMonitor(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.ADD_MONITOR.ordinal(), inJson);
    }

    /**
     * 修改布控通道
     *
     * @param device 设备
     * @param inJson 布控参数
     * @return 错误码
     */
    public static int setMonitor(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_MONITOR.ordinal(), inJson);
    }

    /**
     * 查询布控通道
     *
     * @param device 设备
     * @param inJson     查询参数
     * @param outJson    布控参数
     * @return 错误码
     */
    public static int queryMonitor(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_MONITOR.ordinal(), inJson, outJson);
    }

    /**
     * 删除布控通道
     *
     * @param device 设备
     * @param inJson 删除参数
     * @return 错误码
     */
    public static int deleteMonitor(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.DELETE_MONITOR.ordinal(), inJson);
    }
}
