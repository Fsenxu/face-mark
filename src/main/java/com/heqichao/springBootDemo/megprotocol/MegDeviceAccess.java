package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class MegDeviceAccess {
    private static final String MODULE_NAME = "device_access";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, ADD_DEVICE, REMOVE_DEVICE, SET_DEVICE_CONFIG, GET_DEVICE_CONFIG, GET_DEVICE_STATE, SUBSCRIBE_DEVICE_STATE, UNSUBSCRIBE_DEVICE_STATE,
        GET_DEVICE_CAP, CFG_FORWARDING, TRIGGER_SEARCH_DEVICE, GET_SEARCH_DEVICE_INFO
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
     * 添加远程接入设备
     *
     * @param device 设备
     * @param inJson 远程设备参数
     * @return 错误码
     */
    public static int addDevice(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.ADD_DEVICE.ordinal(), inJson);
    }

    /**
     * 删除远程接入设备
     *
     * @param device 设备
     * @param inJson 设备标识字符串
     * @return 错误码
     */
    public static int removeDevice(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.REMOVE_DEVICE.ordinal(), inJson);
    }

    /**
     * 修改远程接入设备
     *
     * @param device 设备
     * @param inJson 远程设备参数
     * @return 错误码
     */
    public static int setDeviceConfig(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_DEVICE_CONFIG.ordinal(), inJson);
    }

    /**
     * 查询远程接入设备
     *
     * @param device 设备
     * @param outJson 远程设备参数集合
     * @return 错误码
     */
    public static int getDeviceConfig(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_DEVICE_CONFIG.ordinal(), outJson);
    }

    /**
     * 获取远程设备状态
     *
     * @param device 设备
     * @param outJson 远程设备状态集合
     * @return 错误码
     */
    public static int getDeviceState(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_DEVICE_STATE.ordinal(), outJson);
    }

    /**
     * 订阅远程设备状态
     *
     * @param device    设备
     * @param cb        订阅远程设备状态回调函数
     * @param userArg   自定义数据指针
     * @return 错误码
     */
    public static int subscribeDeviceState(MegDevice device, DeviceStateCb cb, Pointer userArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.SUBSCRIBE_DEVICE_STATE.ordinal(), new Object[]{funcPointer, cb, userArg});
    }

    /**
     * 退订远程设备状态
     *
     * @param device    设备
     * @param cb        订阅远程设备状态回调函数
     * @param userArg   自定义数据指针
     * @return 错误码
     */
    public static int unsubscribeDeviceState(MegDevice device, DeviceStateCb cb, Pointer userArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.UNSUBSCRIBE_DEVICE_STATE.ordinal(), new Object[]{funcPointer, cb, userArg});
    }

    /**
     * 获取远程设备接入能力集
     *
     * @param device    设备
     * @param inJson    设备标识字符串
     * @param outJson   远程设备能力接入集
     * @return 错误码
     */
    public static int getDeviceCap(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_DEVICE_CAP.ordinal(), inJson, outJson);
    }

    /**
     * 转发信令
     *
     * @param device    设备
     * @param inJson    远程设备请求参数
     * @param outJson   远程设备应答参数
     * @return 错误码
     */
    public static int cfgForwarding(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.CFG_FORWARDING.ordinal(), inJson, outJson);
    }

    /**
     * 触发远程设备发现（要求端设备立即返回，异步执行设备发现）
     *
     * @param device    设备
     * @param inJson    远程发现请求参数
     * @return 错误码
     */
    public static int triggerSearchDevice(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.TRIGGER_SEARCH_DEVICE.ordinal(), inJson);
    }

    /**
     * 获取远程设备发现的结果
     *
     * @param device    设备
     * @param outJson   远程发现结果
     * @return 错误码
     */
    public static int getSearchDeviceInfo(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_DEVICE_CAP.ordinal(), outJson);
    }

    public interface DeviceStateCb extends Callback {
        void invoke(Pointer data, Pointer userArg);
    }

    public interface GetNextDeviceRouteCb extends Callback {
        void invoke(int deviceId);
    }
}
