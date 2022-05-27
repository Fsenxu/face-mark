package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MegMediaVideo {
    private static final String MODULE_NAME = "media_video";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, SET_ENCODE, GET_ENCODE, SET_IMAGE_PARAM, GET_IMAGE_PARAM, SET_OSD, GET_OSD, SET_ROI,
        GET_ROI, SET_PRIVATE_AREA, GET_PRIVATE_AREA, SUBSCRIBE_STREAM, UNSUBSCRIBE_STREAM, SUBSCRIBE_PARAM, UNSUBSCRIBE_PARAM,
        SUBSCRIBE_INTELLIGENT, UNSUBSCRIBE_INTERLLIGENT, SET_VIDEO_SOURCES_CFG, GET_VIDEO_SOURCES_CFG, GET_VIDEO_SOURCES
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取设备存储管理能力集
     *
     * @param device  设备
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
     * 设置编码参数
     *
     * @param device 设备
     * @param inJson 编码参数
     * @return 错误码
     */
    public static int setEncode(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_ENCODE.ordinal(), inJson);
    }

    /**
     * 获取编码参数
     *
     * @param device     设备
     * @param inJson     设备通道、码流通道
     * @param outJson    编码参数
     * @return 错误码
     */
    public static int getEncode(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_ENCODE.ordinal(), inJson, outJson);
    }

    /**
     * 设置图像属性参数
     *
     * @param device 设备
     * @param inJson 图像属性参数
     * @return 错误码
     */
    public static int setImageParam(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_IMAGE_PARAM.ordinal(), inJson);
    }

    /**
     * 获取图像属性参数
     *
     * @param device     设备
     * @param inJson     设备通道、码流通道
     * @param outJson    图像属性参数
     * @return 错误码
     */
    public static int getImageParam(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_IMAGE_PARAM.ordinal(), inJson, outJson);
    }

    /**
     * 设置OSD参数
     *
     * @param device 设备
     * @param inJson OSD参数
     * @return 错误码
     */
    public static int setOsd(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_OSD.ordinal(), inJson);
    }

    /**
     * 获取OSD参数
     *
     * @param device     设备
     * @param inJson     设备通道、码流通道
     * @param outJson    OSD参数
     * @return 错误码
     */
    public static int getOsd(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_OSD.ordinal(), inJson, outJson);
    }

    /**
     * 设置ROI参数
     *
     * @param device 设备
     * @param inJson ROI参数
     * @return 错误码
     */
    public static int setRoi(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_ROI.ordinal(), inJson);
    }

    /**
     * 获取ROI参数
     *
     * @param device     设备
     * @param inJson     设备通道、码流通道
     * @param outJson    ROI参数
     * @return 错误码
     */
    public static int getRoi(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_ROI.ordinal(), inJson, outJson);
    }

    /**
     * 设置隐私遮蔽参数
     *
     * @param device 设备
     * @param inJson 隐私遮蔽参数
     * @return 错误码
     */
    public static int setPrivateArea(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_PRIVATE_AREA.ordinal(), inJson);
    }

    /**
     * 获取隐私遮蔽参数
     *
     * @param device     设备
     * @param inJson     设备通道、码流通道
     * @param outJson    隐私遮蔽参数
     * @return 错误码
     */
    public static int getPrivateArea(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_PRIVATE_AREA.ordinal(), inJson, outJson);
    }

    /**
     * 订阅视频流
     *
     * @param device                   设备
     * @param inJson                   订阅参数
     * @param outJson                  订阅返回参数
     * @param videoStreamCb            流函数回调
     * @param userArg                  流回调用户参数
     * @param pSendStreamCb            发送成功回调函数
     * @param pSendUserArg             发送成功回调函数用户参数
     * @return 错误码
     */
    public static int subscribeStream(MegDevice device,
                               String inJson,
                               StringBuilder outJson,
                               MediaVideoStreamCb videoStreamCb,
                               Pointer userArg,
                               PointerByReference pSendStreamCb,
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
                                  new Object[]{funcPointer, inJson, outJsonPtr, videoStreamCb, userArg, pSendStreamCb, pSendUserArg});

        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 退订视频流
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
     * 订阅智能帧
     *
     * @param device                   设备
     * @param inJson                   订阅参数
     * @param outJson                  订阅返回参数
     * @param videoStreamCb            智能帧发送回调
     * @param userArg                  回调用户数据
     * @param pSendCb                  智能帧发送成功回调
     * @param pSendUserArg             数据发送成功回调用户参数
     * @return 错误码
     */
    public static int subscribeIntelligent(MegDevice device,
                                    String inJson,
                                    StringBuilder outJson,
                                    MediaVideoStreamCb videoStreamCb,
                                    Pointer userArg,
                                    PointerByReference pSendCb,
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
                                  new Object[]{funcPointer, inJson, outJsonPtr, videoStreamCb, userArg, pSendCb, pSendUserArg});

        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 退订参数修改通知
     *
     * @param device     设备
     * @param inJson     退订参数
     * @return 错误码
     */
    public static int unsubscribeIntelligent(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.UNSUBSCRIBE_INTERLLIGENT.ordinal(), inJson);
    }

    public interface MediaVideoStreamCb extends Callback {
        void invoke(MegCommon.MediaFrame.ByReference frame, Pointer userArg);
    }
}
