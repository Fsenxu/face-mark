package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import com.heqichao.springBootDemo.megprotocol.MegCommon.DownloadStreamCb;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MegMediaPlayback {
    private static final String MODULE_NAME = "media_playback";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, QUERY_REC_CALENDAR, QUERY_REC_FILELIST, START_PLATBACK, SEEK, CHANGE_MODE, GET_SINGLE_FRAME, PAUSE,
        RESUME, STOP_PLAYBACK, DOWNLOAD_OPEN, DOWNLOAD_CLOSE, START_DOWNLOAD, STOP_DOWNLOAD
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
     * 按条件查询录像日历，查询指定的月份里哪天有录像
     *
     * @param device     设备
     * @param inJson     查询条件，查询录像日历输入参数，json串
     * @param outJson    查询录像日历输出参数，json串
     * @return 错误码
     */
    public static int queryRecCalendar(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_REC_CALENDAR.ordinal(), inJson, outJson);
    }

    /**
     * 按条件查询录像文件列表
     *
     * @param device     设备
     * @param inJson     查询条件，查询录像文件列表输入参数，json串
     * @param outJson    查询录像文件列表输出参数，json串
     * @return 错误码
     */
    public static int queryRecFilelist(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_REC_FILELIST.ordinal(), inJson, outJson);
    }

    /**
     * 开始回放
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
    public static int startPlayback(MegDevice device,
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
                                  funcTable.START_PLATBACK.ordinal(),
                                  new Object[]{funcPointer, inJson, outJsonPtr, videoStreamCb, userArg, pSendStreamCb, pSendUserArg});

        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 该操作可以实现视频跳到指定位置
     *
     * @param device     设备
     * @param inJson     seek输入参数，json串
     * @return 错误码
     */
    public static int seek(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SEEK.ordinal(), inJson);
    }

    /**
     * 切换回放模式
     *
     * @param device     设备
     * @param inJson     切换回放模式输入参数，json串
     * @return 错误码
     */
    public static int changeMode(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.CHANGE_MODE.ordinal(), inJson);
    }

    /**
     * 获取单帧
     *
     * @param device     设备
     * @param inJson     切换回放模式输入参数，json串
     * @return 错误码
     */
    public static int getSingleFrame(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.GET_SINGLE_FRAME.ordinal(), inJson);
    }

    /**
     * 暂停回放
     *
     * @param device     设备
     * @param inJson     暂停回放输入参数，json串
     * @return 错误码
     */
    public static int pause(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.PAUSE.ordinal(), inJson);
    }

    /**
     * 恢复回放
     *
     * @param device     设备
     * @param inJson     恢复回放输入参数，json串
     * @return 错误码
     */
    public static int resume(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.RESUME.ordinal(), inJson);
    }

    /**
     * 停止回放
     *
     * @param device     设备
     * @param inJson     停止回放输入参数，json串
     * @return 错误码
     */
    public static int stopPlayback(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.STOP_PLAYBACK.ordinal(), inJson);
    }

    /**
     * 打开下载句柄
     *
     * @param device     设备
     * @param inJson     打开下载句柄输入参数，json串
     * @param outJson    打开下载句柄输出参数，json串
     * @return 错误码
     */
    public static int downloadOpen(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.DOWNLOAD_OPEN.ordinal(), inJson, outJson);
    }

    /**
     * 关闭下载句柄
     *
     * @param device     设备
     * @param inJson     关闭下载句柄输入参数，json串
     * @return 错误码
     */
    public static int downloadClose(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.DOWNLOAD_CLOSE.ordinal(), inJson);
    }

    /**
     * 开始下载录像
     *
     * @param device                   设备
     * @param inJson                   订阅参数
     * @param outJson                  订阅返回参数
     * @param downloadStreamCb         流函数回调
     * @param userArg                  流回调用户参数
     * @param pSendStreamCb            发送成功回调函数
     * @param pSendUserArg             发送成功回调函数用户参数
     * @return 错误码
     */
    public static int startDownload(MegDevice device,
                             String inJson,
                             StringBuilder outJson,
                             DownloadStreamCb downloadStreamCb,
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
                                  funcTable.START_DOWNLOAD.ordinal(),
                                  new Object[]{funcPointer, inJson, outJsonPtr, downloadStreamCb, userArg, pSendStreamCb, pSendUserArg});

        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 停止下载录像
     *
     * @param device     设备
     * @param inJson     停止回放输入参数，json串
     * @return 错误码
     */
    public static int stopDownload(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.STOP_DOWNLOAD.ordinal(), inJson);
    }

    public interface MediaVideoStreamCb extends Callback {
        void invoke(MegCommon.MediaFrame.ByReference frame, Pointer userArg);
    }
}
