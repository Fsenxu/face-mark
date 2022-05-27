package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MegMediaAudio {
    private static final String MODULE_NAME = "media_audio";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, SET_ENCODE_PARAM, GET_ENCODE_PARAM, SUBSCRIBE_STREAM, UNSUBSCRIBE_STREAM
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取音频能力集
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
     * 设置音频编码参数
     *
     * @param device 设备
     * @param inJson 编码参数
     * @return 错误码
     */
    public static int setEncodeParam(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_ENCODE_PARAM.ordinal(), inJson);
    }

    /**
     * 获取音频编码参数
     *
     * @param device     设备
     * @param inJson     设备通道、码流通道
     * @param outJson    编码参数
     * @return 错误码
     */
    public static int getEncodeParam(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_ENCODE_PARAM.ordinal(), inJson, outJson);
    }

    /**
     * 订阅音频流
     *
     * @param device                   设备
     * @param inJson                   订阅参数
     * @param outJson                  订阅返回参数
     * @param audioStreamCb            流函数回调
     * @param userArg                  流回调用户参数
     * @param pSendStreamCb            发送成功回调函数
     * @param pSendUserArg             发送成功回调函数用户参数
     * @return 错误码
     */
    public static int subscribeStream(MegDevice device,
                               String inJson,
                               StringBuilder outJson,
                               MediaAudioStreamCb audioStreamCb,
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
                                  new Object[]{funcPointer, inJson, outJsonPtr, audioStreamCb, userArg, pSendStreamCb, pSendUserArg});

        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 退订音频流
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

    public interface MediaAudioStreamCb extends Callback {
        void invoke(MegCommon.MediaFrame.ByReference frame, Pointer userArg);
    }
}
