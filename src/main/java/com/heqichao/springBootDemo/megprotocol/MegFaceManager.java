package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;


/**
 * 人员底库与分组的管理，包括人员的增删改查，分组的增删改查，人员与分组的绑定解绑等操作
 */
@Slf4j
public abstract class MegFaceManager {
    public static final int MEG_MAX_PERSON_FACE = 3;
    private static final String MODULE_NAME = "face_manager";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, ADD_GROUP, REMOVE_GROUP, QUERY_GROUPS, SET_GROUP, ADD_PERSON, REMOVE_PERSON, SET_PERSON, QUERY_PERSON, START_ADD_PERSON_BATCH,
        ADD_PERSON_BATCH, STOP_ADD_PERSON_BATCH, QUERY_ADD_PERSON_BATCH_PROGRESS, ADD_PERSON_BIND, REMOVE_PERSON_BIND, MODIFY_PERSON_BIND,
        QUERY_PERSON_BIND, QUERY_FAIL_LIST, CLEAR_FAIL_LIST, ASYNC_CLEAR_FAIL_LIST, COMPARE_FACE, SEARCH_FACE
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取模块能力集
     *
     * @param device  设备
     * @param outJson 模块能力集参数
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
     * 创建人员分组
     *
     * @param device  设备
     * @param inJson  创建人员分组输入参数，JSON格式
     * @param outJson 创建人员分组输出参数，JSON格式
     * @return 错误码
     */
    public static int addGroup(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.ADD_GROUP.ordinal(), inJson, outJson);
    }

    /**
     * 删除人员分组
     *
     * @param device    设备
     * @param inJson    删除人员分组输入参数，JSON格式
     * @param timeout   请求超时时间，单位ms
     * @return 错误码
     */
    public static int removeGroup(MegDevice device, String inJson, int timeout) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc4(funcPointer, funcTable.REMOVE_GROUP.ordinal(), inJson, timeout);
    }

    /**
     * 查询人员分组
     *
     * @param device  设备
     * @param outJson 分组列表  json格式
     * @return 错误码
     */
    public static int queryGroups(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.QUERY_GROUPS.ordinal(), outJson);
    }

    /**
     * 修改人员分组
     *
     * @param device  设备
     * @param inJson 人员分组参数，json格式
     * @return 错误码
     */
    public static int setGroup(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_GROUP.ordinal(), inJson);
    }

    /**
     * 创建人员
     *
     * @param device  设备
     * @param inJson  人脸参数  json格式
     * @param face     人脸数据
     * @param outJson 创建人员结果参数
     * @return 错误码
     */
    public static int addPerson(MegDevice device, String inJson, PersonFaceStruct face, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();

        int ret = ComponentFactory.invokeNativeInt(funcPointer, funcTable.ADD_PERSON.ordinal(), new Object[]{funcPointer, inJson, face, outJsonPtr});
        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 删除人员
     *
     * @param device  设备
     * @param inJson 人脸参数
     * @param timeout 请求超时时间，单位ms
     * @return 错误码
     */
    public static int removePerson(MegDevice device, String inJson, int timeout) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc4(funcPointer, funcTable.REMOVE_PERSON.ordinal(), inJson, timeout);
    }

    /**
     * 修改人员
     *
     * @param device  设备
     * @param inJson  人脸参数  json格式
     * @param face    人脸数据
     * @param outJson 修改人员结果参数
     * @return 错误码
     */
    public static int setPerson(MegDevice device, String inJson, PersonFaceStruct face, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();
        int ret = ComponentFactory.invokeNativeInt(funcPointer, funcTable.SET_PERSON.ordinal(), new Object[]{funcPointer, inJson, face, outJsonPtr});
        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 查询人员
     *
     * @param device  设备
     * @param inJson  查询参数  json格式
     * @param outJson 人员信息  json格式
     * @return 错误码
     */
    public static int queryPerson(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_PERSON.ordinal(), inJson, outJson);
    }

    /**
     * 开始批量创建人员
     *
     * @param device  设备
     * @param inJson  批量参数
     * @param cb      批量创建人员结果函数
     * @param outJson 批量创建结果参数
     * @param userArg 自定义数据指针
     * @return 错误码
     */
    public static int startAddPersonBatch(MegDevice device, String inJson, AddPersonBatchResultCb cb, StringBuilder outJson, Pointer userArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();
        int ret = ComponentFactory.invokeNativeInt(funcPointer, funcTable.START_ADD_PERSON_BATCH.ordinal(), new Object[]{funcPointer, inJson, cb, outJsonPtr, userArg});
        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 批量创建人员
     *
     * @param device  设备
     * @param inJson  人员参数
     * @param face    人脸数据
     * @param userArg 用户数据,只在server端使用
     * @return 错误码
     */
    public static int addPersonBatch(MegDevice device, String inJson, PersonFaceStruct face, Pointer userArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.ADD_PERSON_BATCH.ordinal(), new Object[]{funcPointer, inJson, face, userArg});
    }

    /**
     * 停止批量创建人员
     *
     * @param device  设备
     * @param inJson  停止批量创建参数
     * @param cb       批量创建人员结果函数
     * @param userArg 自定义数据指针
     * @return 错误码
     */
    public static int stopAddPersonBatch(MegDevice device, String inJson, AddPersonBatchResultCb cb, Pointer userArg) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.invokeNativeInt(funcPointer, funcTable.STOP_ADD_PERSON_BATCH.ordinal(), new Object[]{funcPointer, inJson, cb, userArg});
    }

    /**
     * 查询批量导入人员进度
     *
     * @param device  设备
     * @param inJson  batch_id
     * @param outJson 批量导入人员进度参数
     * @return 错误码
     */
    public static int queryAddPersonBatchProgress(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_ADD_PERSON_BATCH_PROGRESS.ordinal(), inJson, outJson);
    }

    /**
     * 绑定人员与分组
     *
     * @param device  设备
     * @param inJson 人员绑定分组参数  json格式
     * @return 错误码
     */
    public static int addPersonBind(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.ADD_PERSON_BIND.ordinal(), inJson);
    }

    /**
     * 解绑人员与分组
     *
     * @param device  设备
     * @param inJson 人员绑定分组参数  json格式
     * @return 错误码
     */
    public static int removePersonBind(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.REMOVE_PERSON_BIND.ordinal(), inJson);
    }

    /**
     * 修改人员绑定分组
     *
     * @param device  设备
     * @param inJson 人员绑定分组参数  json格式
     * @return 错误码
     */
    public static int modifyPersonBind(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.MODIFY_PERSON_BIND.ordinal(), inJson);
    }

    /**
     * 查询分组绑定人员列表
     *
     * @param device  设备
     * @param inJson  分组绑定人员查询参数  json格式
     * @param outJson 分组绑定人员查询结果  json格式
     * @return 错误码
     */
    public static int queryPersonBind(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_PERSON_BIND.ordinal(), inJson, outJson);
    }

    /**
     * 查询入库失败列表
     *
     * @param device  设备
     * @param inJson  查询入库失败列表输入参数  json格式
     * @param outJson 查询入库失败列表输出参数  json格式
     * @return 错误码
     */
    public static int queryFailList(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_FAIL_LIST.ordinal(), inJson, outJson);
    }

    /**
     * 清除入库失败列表
     *
     * @param device  设备
     * @param inJson 清除入库失败列表输入参数  json格式
     * @return 错误码
     */
    public static int clearFailList(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.CLEAR_FAIL_LIST.ordinal(), inJson);
    }

    /**
     * 1:1人脸比较
     *
     * @param device  设备
     * @param inJson  人脸比对信息参数  json格式
     * @param faces    人脸比对数据参数
     * @param outJson 人脸比对结果参数
     * @param timeout  请求超时时间，单位ms
     * @return 错误码
     */
    public static int compareFace(MegDevice device, String inJson, FaceManagerCompareParamStruct faces, StringBuilder outJson, int timeout) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();
        int ret = ComponentFactory.invokeNativeInt(funcPointer, funcTable.COMPARE_FACE.ordinal(), new Object[]{funcPointer, inJson, faces, outJsonPtr, timeout});
        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 1:N人脸识别
     *
     * @param device  设备
     * @param inJson  人脸识别信息参数  json格式
     * @param face    人脸识别数据参数
     * @param outJson 人脸识别结果参数
     * @param timeout 请求超时时间，单位ms
     * @return 错误码
     */
    public static int searchFace(MegDevice device, String inJson, FaceManagerSearchParamStruct face, StringBuilder outJson, int timeout) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        PointerByReference outJsonPtr = new PointerByReference();
        int ret = ComponentFactory.invokeNativeInt(funcPointer, funcTable.SEARCH_FACE.ordinal(), new Object[]{funcPointer, inJson, face, outJsonPtr, timeout});
        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    public interface AddPersonBatchResultCb extends Callback {
        void invoke(String result, Pointer userArg);
    }

    public static class BinDataStruct extends Structure {
        public int data_size;   //数据大小
        public Pointer data;    //数据

        public static class ByReference extends BinDataStruct implements Structure.ByReference {
        }
        public static class ByValue extends BinDataStruct implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("data_size", "data");
        }
    }

    public static class PersonFaceStruct extends Structure {
        public BinDataStruct.ByValue[] faces = new BinDataStruct.ByValue[MEG_MAX_PERSON_FACE];

        public static class ByReference extends PersonFaceStruct implements Structure.ByReference {
        }
        public static class ByValue extends PersonFaceStruct implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("faces");
        }
    }

    public static class FaceManagerCompareParamStruct extends Structure {
        public BinDataStruct face1;
        public BinDataStruct face2;

        public static class ByReference extends FaceManagerCompareParamStruct implements Structure.ByReference {
        }
        public static class ByValue extends FaceManagerCompareParamStruct implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("face1", "face2");
        }
    }

    public static class FaceManagerSearchParamStruct extends Structure {
        public BinDataStruct face;

        public static class ByReference extends FaceManagerSearchParamStruct implements Structure.ByReference {
        }
        public static class ByValue extends FaceManagerSearchParamStruct implements Structure.ByValue {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("face");
        }
    }
}
