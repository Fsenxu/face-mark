package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;

import com.heqichao.springBootDemo.megprotocol.MegCommon.StreamCloseCb;


@Slf4j
public abstract class MegDeviceAlarm {
    private static final String MODULE_NAME = "device_alarm";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, SUBSCRIBE_STREAM, UNSUBSCRIBE_STREAM, SUBSCRIBE_ALARM_TYPE,
        GET_ALARM_INPUT_CONFIG, SET_ALARM_INPUT_CONFIG, GET_ALARM_OUTPUT_CONFIG, SET_ALARM_OUTPUT_CONFIG, GET_MOTION_DETECTION_CONFIG,
        SET_MOTION_DETECTION_CONFIG, GET_EXCEPTION_ALARM_CONFIG, SET_EXCEPTION_ALARM_CONFIG, GET_ALARM_TYPE_INFO, SET_ALARM_TYPE_INFO,
        GET_ALARM_CAP, QUERY_ALARM_HISTORY, MANUAL_TRIGGER_ALARM_OUTPUT, GET_ALARM_HISTORY_CONFIG, SET_ALARM_HISTORY_CONFIG,
        DELETE_ALARM_HISTORY, GET_NETWORK_ALARM_SOURCE_CONFIG, SET_NETWORK_ALARM_SOURCE_CONFIG, TRIGGER_NETWORK_ALARM_SOURCE, SET_STREAM_CLOSE_CB
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取设备报警模块能力集
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
     * 打开报警通道
     *
     * @param device 设备
     * @param outJson                  订阅返回参数
     * @param deviceAlarmCb            报警回调
     * @param userArg                  流回调用户参数
     * @param pSendAlarmCb             发送成功回调函数
     * @param pSendUserArg             发送成功回调函数用户参数
     * @return 错误码
     */
    public static int subscribeStream(MegDevice device, StringBuilder outJson,
                               MediaDeviceAlarmCb deviceAlarmCb,
                               Pointer userArg,
                               PointerByReference pSendAlarmCb,
                               PointerByReference pSendUserArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();

        int ret = ComponentFactory.invokeNativeInt(funcPointer,
                                  funcTable.SUBSCRIBE_STREAM.ordinal(),
                                  new Object[]{funcPointer, outJsonPtr, deviceAlarmCb, userArg, pSendAlarmCb, pSendUserArg});
        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 关闭报警通道
     *
     * @param device     设备
     * @param inJson     退订参数
     * @return 错误码
     */
    public static int unsubscribeStream(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.UNSUBSCRIBE_STREAM.ordinal(), inJson);
    }

    /**
     * 设置订阅报警类型
     *
     * @param device 设备
     * @param inJson json格式，参见报警类型订阅参数
     * @return 错误码
     */
    public static int subscribeAlarmType(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SUBSCRIBE_ALARM_TYPE.ordinal(), inJson);
    }

    /**
     * 获取报警输入参数
     *
     * @param device    设备
     * @param inJson    json格式，查询条件
     * @param outJson   报警输入参数
     * @return 错误码
     */
    public static int getAlarmInputConfig(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_ALARM_INPUT_CONFIG.ordinal(), inJson, outJson);
    }

    /**
     * 设置报警输入参数
     *
     * @param device 设备
     * @param inJson 报警输入参数
     * @return 错误码
     */
    public static int setAlarmInputConfig(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_ALARM_INPUT_CONFIG.ordinal(), inJson);
    }

    /**
     * 获取报警输出参数
     *
     * @param device    设备
     * @param inJson    json格式，查询条件
     * @param outJson   报警输出参数
     * @return 错误码
     */
    public static int getAlarmOutputConfig(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_ALARM_OUTPUT_CONFIG.ordinal(), inJson, outJson);
    }

    /**
     * 设置报警输出参数
     *
     * @param device 设备
     * @param inJson 报警输出参数
     * @return 错误码
     */
    public static int setAlarmOutputConfig(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_ALARM_OUTPUT_CONFIG.ordinal(), inJson);
    }

    /**
     * 获取移动侦测参数
     *
     * @param device    设备
     * @param inJson    json格式，查询条件
     * @param outJson   移动侦测参数
     * @return 错误码
     */
    public static int getMotionDetectionConfig(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_MOTION_DETECTION_CONFIG.ordinal(), inJson, outJson);
    }

    /**
     * 设置移动侦测参数
     *
     * @param device 设备
     * @param inJson 移动侦测参数
     * @return 错误码
     */
    public static int setMotionDetectionConfig(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_MOTION_DETECTION_CONFIG.ordinal(), inJson);
    }

    /**
     * 获取异常报警参数
     *
     * @param device    设备
     * @param inJson    json格式，查询条件
     * @param outJson   异常报警参数
     * @return 错误码
     */
    public static int getExceptionAlarmConfig(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_EXCEPTION_ALARM_CONFIG.ordinal(), inJson, outJson);
    }

    /**
     * 设置异常报警参数
     *
     * @param device 设备
     * @param inJson 异常报警参数
     * @return 错误码
     */
    public static int setExceptionAlarmConfig(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_EXCEPTION_ALARM_CONFIG.ordinal(), inJson);
    }

    /**
     * 获取报警类型信息
     *
     * @param device    设备
     * @param inJson    json格式，查询条件
     * @param outJson   请求应答参数
     * @return 错误码
     */
    public static int getAlarmTypeInfo(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_ALARM_TYPE_INFO.ordinal(), inJson, outJson);
    }

    /**
     * 设置报警类型信息
     *
     * @param device 设备
     * @param inJson 设备报警类型相关信息，参见报警类型参数
     * @return 错误码
     */
    public static int setAlarmTypeInfo(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_ALARM_TYPE_INFO.ordinal(), inJson);
    }

    /**
     * 获取报警能力集
     *
     * @param device    设备
     * @param outJson   报警能力集应答参数
     * @return 错误码
     */
    public static int getAlarmCap(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_ALARM_CAP.ordinal(), outJson);
    }

    /**
     * 查询报警记录
     *
     * @param device    设备
     * @param inJson    查询参数
     * @param outJson   查询结果
     * @return 错误码
     */
    public static int queryAlarmHistory(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_ALARM_HISTORY.ordinal(), inJson, outJson);
    }

    /**
     * 手动触发报警输出
     *
     * @param device   设备
     * @param inJson   手动触发报警输出参数
     * @return 错误码
     */
    public static int manualTriggerAlarmOutput(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.MANUAL_TRIGGER_ALARM_OUTPUT.ordinal(), inJson);
    }

    /**
     * 获取报警记录配置参数
     *
     * @param device    设备
     * @param outJson   报警记录配置参数
     * @return 错误码
     */
    public static int getAlarmHistoryConfig(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_ALARM_HISTORY_CONFIG.ordinal(), outJson);
    }

    /**
     * 设置报警记录配置参数
     *
     * @param device 设备
     * @param inJson 报警记录配置参数
     * @return 错误码
     */
    public static int setAlarmHistoryConfig(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_ALARM_HISTORY_CONFIG.ordinal(), inJson);
    }

    /**
     * 删除报警记录
     *
     * @param device    设备
     * @param inJson    删除报警记录参数
     * @param timeout   超时参数，单位ms
     * @return 错误码
     */
    public static int deleteAlarmHistory(MegDevice device, String inJson, int timeout) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc4(funcPointer, funcTable.DELETE_ALARM_HISTORY.ordinal(), inJson, timeout);
    }

    /**
     * 获取网络报警源参数
     *
     * @param device    设备
     * @param inJson    设备id
     * @param outJson   网络报警源配置参数
     * @return 错误码
     */
    public static int getNetworkAlarmSourceConfig(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_NETWORK_ALARM_SOURCE_CONFIG.ordinal(), inJson, outJson);
    }

    /**
     * 设置网络报警源参数
     *
     * @param device   设备
     * @param inJson   网络报警源配置参数
     * @return 错误码
     */
    public static int setNetworkAlarmSourceConfig(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_NETWORK_ALARM_SOURCE_CONFIG.ordinal(), inJson);
    }

    /**
     * 触发网络报警源
     *
     * @param device   设备
     * @param inJson   触发网络报警参数
     * @return 错误码
     */
    public static int triggerNetworkAlarmSource(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.TRIGGER_NETWORK_ALARM_SOURCE.ordinal(), inJson);
    }

    /**
     * 设置流断线回调
     *
     * @param device          设备
     * @param streamCloseCb   流断线回调
     * @return 错误码
     */
    public static int setStreamCloseCb(MegDevice device, StreamCloseCb streamCloseCb) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.SET_STREAM_CLOSE_CB.ordinal(), new Object[]{funcPointer, streamCloseCb});
    }

    public interface MediaDeviceAlarmCb extends Callback {
        void invoke(MegCommon.AlarmMsg.ByReference alarmMsg, Pointer userArg);
    }
}
