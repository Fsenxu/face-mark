package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MegDevMaintenance {
    private static final String MODULE_NAME = "device_maintenance";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_SUPPORT_MODULE, GET_CAP, GET_EQUIPMENT_CAP, GET_IO_CAP, RESTORE_DEVICE, REBOOT_DEVICE, QUERY_DEVICE_INFO, SET_DEVICE_INFO, QUERY_LOGS,
        UUPGRADE, GET_UPGRADE_STATUS, START_UPGRADE, TRANSFER_UPGRADE, STOP_UPGRADE, EXPORT_PARAM, IMPORT_PARAM, GET_AUTO_MAINTAIN, SET_AUTO_MAINTAIN,
        QUERY_ONLINE_USERS, START_EXPORT_LOG_FILE, STOP_EXPORT_LOG_FILE, EXPORT_SIMPLE_FILE, IMPORT_SIMPLE_FILE, TRANSPARENT_TRANSMISSION,
        GET_DEVICE_RUNTIME_STATUS, SET_UPGRADE_PROGRESS_CB, QUERY_SUPPORT_TRANSFER_FILE_TYPE, OPEN_IMPORT_FILE, TRANSFER_IMPORT_FILE, CLOSE_IMPORT_FILE,
        IMPORT_FILE, OPEN_EXPORT_FILE, TRANSFER_EXPORT_FILE, CLOSE_EXPRT_FILE, GET_CURRENT_WORK_MODE, SET_CURRENT_WORK_MODE, GET_WORK_MODE_CAP
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取运维模块能力集
     *
     * @param device 设备
     * @param outJson 运维模块能力集
     * @return 错误码
     */
    public static int getSupportModule(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_SUPPORT_MODULE.ordinal(), outJson);
    }

    /**
     * 获取运维模块能力集
     *
     * @param device 设备
     * @param outJson 运维模块能力集
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
     * 获取物理设备能力集
     *
     * @param device 设备
     * @param outJson 物理设备能力集
     * @return 错误码
     */
    public static int getEquipment(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_EQUIPMENT_CAP.ordinal(), outJson);
    }

    /**
     * 获取IO能力集
     *
     * @param device 设备
     * @param outJson IO能力集
     * @return 错误码
     */
    public static int getIoCap(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_IO_CAP.ordinal(), outJson);
    }

    /**
     * 恢复设备
     *
     * @param device 设备
     * @param restoreType 恢复类型
     * @return 错误码
     */
    public static int restoreDevice(MegDevice device, int restoreType) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.RESTORE_DEVICE.ordinal(), new Object[]{funcPointer, restoreType});
    }

    /**
     * 重启设备
     *
     * @param device 设备
     * @return 错误码
     */
    public static int rebootDevice(MegDevice device) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.REBOOT_DEVICE.ordinal(), new Object[]{funcPointer});
    }

    /**
     * 查询设备信息
     *
     * @param device 设备
     * @param outJson 查询结果
     * @return 错误码
     */
    public static int queryDeviceInfo(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.QUERY_DEVICE_INFO.ordinal(), outJson);
    }

    /**
     * 设置设备信息
     *
     * @param device 设备
     * @param inJson 设备信息
     * @return 错误码
     */
    public static int setDeviceInfo(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_DEVICE_INFO.ordinal(), inJson);
    }

    /**
     * 查询设备日志
     *
     * @param device 设备
     * @param inJson  查询参数
     * @param outJson 查询结果
     * @return 错误码
     */
    public static int queryLogs(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_LOGS.ordinal(), inJson, outJson);
    }

    /**
     * 设备升级
     *
     * @param device 设备
     * @param path    文件路径 字符串
     * @param inJson 文件信息json字符串
     * @return 错误码
     */
    public static int upgrade(MegDevice device, String path, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.UUPGRADE.ordinal(), new Object[]{funcPointer, path, inJson});
    }

    /**
     * 获取设备升级状态
     *
     * @param device 设备
     * @param status    升级状态  [0,100]
     * @return 错误码
     */
    public static int getUpgradeStatus(MegDevice device, StringBuilder status) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.GET_UPGRADE_STATUS.ordinal(), new Object[]{funcPointer, status});
    }

    /**
     * 发送升级文件 若transferUpgrade中path为空则使用分片的方式循环传输，否则无需调用本接口
     *
     * @param device 设备
     * @param inJson       分片参数
     * @param buff          升级文件分片地址
     * @param buffSize      升级文件分片长度
     * @return 错误码
     */
    public static int transferUpgrade(MegDevice device, String inJson, Pointer buff, int buffSize) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.TRANSFER_UPGRADE.ordinal(), new Object[]{funcPointer, inJson, buff, buffSize});
    }

    /**
     * 停止设备升级
     *
     * @param device 设备
     * @return 错误码
     */
    public static int stopUpgrade(MegDevice device) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.STOP_UPGRADE.ordinal(), new Object[]{funcPointer});
    }

    /**
     * 参数导出
     *
     * @param device       设备
     * @param inJson       导出参数，指定需要导出参数的模块信息
     * @param outJson      export file md5 ... info
     * @param buff          导出文件指针，buff内部申请，由调用者释放
     * @param buffSize      导出文件大小
     * @return 错误码
     */
    public static int exportParam(MegDevice device, String inJson, StringBuilder outJson, Pointer buff, int buffSize) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();

        int ret = ComponentFactory.invokeNativeInt(funcPointer,
                                  funcTable.EXPORT_PARAM.ordinal(),
                                  new Object[]{funcPointer, inJson, outJson, buff, buffSize});

        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 参数导入
     *
     * @param device       设备
     * @param inJson       导入参数，指定需要导出参数的模块信息
     * @param buff          导出文件指针，buff内部申请，由调用者释放
     * @param buffSize      导出文件大小
     * @return 错误码
     */
    public static int importParam(MegDevice device, String inJson, Pointer buff, int buffSize) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.IMPORT_PARAM.ordinal(), new Object[]{funcPointer, inJson, buff, buffSize});
    }

    /**
     * 获取自动维护参数
     *
     * @param device       设备
     * @param outJson      自动维护参数
     * @return 错误码
     */
    public static int getAutoMaintain(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_AUTO_MAINTAIN.ordinal(), outJson);
    }

    /**
     * 设置自动维护参数
     *
     * @param device      设备
     * @param inJson      自动维护参数
     * @return 错误码
     */
    public static int setAutoMaintain(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_AUTO_MAINTAIN.ordinal(), inJson);
    }

    /**
     * 获取在线用户
     *
     * @param device       设备
     * @param outJson      在线用户信息参数
     * @return 错误码
     */
    public static int queryOnlineUsers(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.QUERY_ONLINE_USERS.ordinal(), outJson);
    }

    /**
     * 开始日志导出
     *
     * @param device                设备
     * @param inJson                日志导出请求参数
     * @param outJson               日志导出请求应答参数
     * @param exportLogCb           日志导出请求应答参数
     * @param userArg               用户自定义数据指针
     * @param mediaStreamSendCb     固件端流导出进度回调函数(目前固件端专用,由固件注册)
     * @param sendUserArg           固件端流导出用户自定义数据指针(目前固件端专用，由固件注册)
     * @return 错误码
     */
    public static int startExportLogFile(MegDevice device, String inJson, StringBuilder outJson, ExportLogCb exportLogCb, Pointer userArg, PointerByReference mediaStreamSendCb,  PointerByReference sendUserArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();

        int ret = ComponentFactory.invokeNativeInt(funcPointer,
                                  funcTable.START_EXPORT_LOG_FILE.ordinal(),
                                  new Object[]{funcPointer, inJson, outJson, exportLogCb, userArg, mediaStreamSendCb, sendUserArg});

        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 停止日志导出
     *
     * @param device                设备
     * @param inJson                日志导出停止请求参数
     * @return 错误码
     */
    public static int stopExportLogFile(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.STOP_EXPORT_LOG_FILE.ordinal(), inJson);
    }

    /**
     * 简单文件导出
     *
     * @param device                设备
     * @param inJson                请求参数
     * @param outJson               导出参数
     * @param buff                  导出文件指针，buff内部申请，由调用者释放
     * @param buffSize              导出文件大小
     * @return 错误码
     */
    public static int exportSimpleFile(MegDevice device, String inJson, StringBuilder outJson, Pointer buff, int buffSize) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();

        int ret = ComponentFactory.invokeNativeInt(funcPointer,
                                  funcTable.EXPORT_SIMPLE_FILE.ordinal(),
                                  new Object[]{funcPointer, inJson, outJson, buff, buffSize});
        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 简单文件导入
     *
     * @param device                设备
     * @param inJson                请求参数
     * @param buff                  导出文件指针，buff内部申请，由调用者释放
     * @param buffSize              导出文件大小
     * @return 错误码
     */
    public static int importSimpleFile(MegDevice device, String inJson, Pointer buff, int buffSize) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.IMPORT_SIMPLE_FILE.ordinal(), new Object[]{funcPointer, inJson, buff, buffSize});
    }

    /**
     * 透传(仅在临时项目方案中使用)
     *
     * @param device                设备
     * @param inJson                请求参数
     * @param inJson                透传参数
     * @param outJson               透传结果
     * @return 错误码
     */
    public static int transparentTransmission(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.TRANSPARENT_TRANSMISSION.ordinal(), inJson, outJson);
    }

    /**
     * 获取设备运行时状态（cpu、gpu、磁盘、内存、网络的状态信息）
     *
     * @param device                设备
     * @param outJson               设备内部硬件状态信息参数
     * @return 错误码
     */
    public static int getDeviceRuntimeStatus(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_DEVICE_RUNTIME_STATUS.ordinal(), outJson);
    }

    /**
     * 设置升级进度上报回调
     *
     * @param device           设备
     * @param cb               设备升级进度通知回调
     * @param userArg          自定义数据指针
     * @return 错误码
     */
    public static int setUpgradeProgressCb(MegDevice device, UpgradeProgressNotifyCb cb, Pointer userArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.SET_UPGRADE_PROGRESS_CB.ordinal(), new Object[]{funcPointer, cb, userArg});
    }

    /**
     * 查询设备支持导入导出的文件类型
     *
     * @param device           设备
     * @param inJson           查询请求参数
     * @param outJson          查询返回结果
     * @return 错误码
     */
    public static int querySupportTransferFileType(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_SUPPORT_TRANSFER_FILE_TYPE.ordinal(), inJson, outJson);
    }

    /**
     * 开始导入文件
     *
     * @param device                             设备
     * @param inJson                             开始导入文件请求参数
     * @param outJson                            开始导入文件应答参数
     * @param importFileProgressCb               导入文件进度回调函数
     * @param userArg                            用户自定义数据指针
     * @return 错误码
     */
    public static int openImportFile(MegDevice device, String inJson, StringBuilder outJson, ImportFileProgressCb importFileProgressCb, Pointer userArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();

        int ret = ComponentFactory.invokeNativeInt(funcPointer,
                                  funcTable.OPEN_IMPORT_FILE.ordinal(),
                                  new Object[]{funcPointer, inJson, outJson, importFileProgressCb, userArg});
        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 传输导入文件(片段)
     *
     * @param device                设备
     * @param inJson                导入文件请求参数
     * @param buff                  导入文件(片段)内存缓冲区
     * @param buffSize              导入文件(片段)大小
     * @return 错误码
     */
    public static int transferImportFile(MegDevice device, String inJson, Pointer buff, int buffSize) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.TRANSFER_IMPORT_FILE.ordinal(), new Object[]{funcPointer, inJson, buff, buffSize});
    }

    /**
     * 停止导入文件
     *
     * @param device                设备
     * @param fileId                文件唯一标识符
     * @return 错误码
     */
    public static int closeImportFile(MegDevice device, int fileId) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.CLOSE_IMPORT_FILE.ordinal(), new Object[]{funcPointer, fileId});
    }

    /**
     * 导入文件
     *
     * @param device                         设备
     * @param inJson                         导入文件请求参数
     * @param outJson                        导入文件请求应答参数
     * @param filePath                       导入文件所在路径
     * @param importFileProgressCb           导入文件进度回调函数
     * @param userArg                        用户自定义数据指针
     * @return 错误码
     */
    public static int importFile(MegDevice device, String inJson, StringBuilder outJson, String filePath, ImportFileProgressCb importFileProgressCb, Pointer userArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();

        int ret = ComponentFactory.invokeNativeInt(funcPointer,
                                  funcTable.IMPORT_FILE.ordinal(),
                                  new Object[]{funcPointer, inJson, outJson, filePath, importFileProgressCb, userArg});
        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 打开导出文件
     *
     * @param device                设备
     * @param inJson                开始导出文件请求参数
     * @param outJson               开始导出文件应答参数
     * @return 错误码
     */
    public static int openExportFile(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.OPEN_EXPORT_FILE.ordinal(), inJson, outJson);
    }

    /**
     * 开始导出文件
     *
     * @param device                设备
     * @param fileId                文件唯一标识符
     * @param cb                    导入文件(片段)内存缓冲区
     * @param userArg               导入文件(片段)大小
     * @return 错误码
     */
    public static int transferExportFile(MegDevice device, int fileId, ExportFileProgressCb cb, Pointer userArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.TRANSFER_IMPORT_FILE.ordinal(), new Object[]{funcPointer, fileId, cb, userArg});
    }

    /**
     * 关闭导出文件
     *
     * @param device                设备
     * @param fileId                文件唯一标识符
     * @return 错误码
     */
    public static int closeExportFile(MegDevice device, int fileId) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.TRANSFER_IMPORT_FILE.ordinal(), new Object[]{funcPointer, fileId});
    }

    /**
     * 获取当前工作模式
     *
     * @param device                设备
     * @param inJson                设备id
     * @param outJson               工作模式参数
     * @return 错误码
     */
    public static int getCurrentWorkMode(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_CURRENT_WORK_MODE.ordinal(), inJson, outJson);
    }

    /**
     * 设置当前工作模式
     *
     * @param device                设备
     * @param inJson                工作模式参数
     * @return 错误码
     */
    public static int setCurrentWorkMode(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_CURRENT_WORK_MODE.ordinal(), inJson);
    }

    /**
     * 获取工作模式能力集
     *
     * @param device                设备
     * @param outJson               工作模式能力集
     * @return 错误码
     */
    public static int getWorkModeCap(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_WORK_MODE_CAP.ordinal(), outJson);
    }

    public interface ExportLogCb extends Callback {
        void invoke(String logInfo, int packetSize, Pointer packetData, Pointer userArg);
    }

    public interface UpgradeProgressNotifyCb extends Callback {
        void invoke(String fileInfo, Pointer userArg);
    }

    public interface ExportFileProgressCb extends Callback {
        void invoke(String logInfo, int packetSize, Pointer packetData, Pointer userArg);
    }

    public interface ImportFileProgressCb extends Callback {
        void invoke(String fileInfo, Pointer userArg);
    }
}
