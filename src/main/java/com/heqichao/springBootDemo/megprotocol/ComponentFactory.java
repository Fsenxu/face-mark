package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;

import static com.sun.jna.Native.POINTER_SIZE;

@Slf4j
public abstract class ComponentFactory {
    static PointerByReference createModule(String moduleName, String url) throws MegException {
        PointerByReference ppFactory = new PointerByReference();
        MegConnectLibrary.INSTANTCE.meg_find_factory(moduleName, "rpc", ppFactory);

        Pointer pFactory = ppFactory.getValue();
        if (pFactory == Pointer.NULL) {
            log.error("meg_find_factory {} fail!", moduleName);
            throw new MegException(MegError.ERROR_GENERAL);
        }

        PointerByReference ppModule = new PointerByReference();
        Function fnc = Function.getFunction(pFactory.getPointer(0 * POINTER_SIZE));
        int ret = fnc.invokeInt(new Object[]{url, ppModule});
        if (ret != MegError.ERROR_OK.getCode())
        {
            log.error("create module {} fail!", moduleName);
            throw new MegException(MegError.ERROR_GENERAL);
        }

        Pointer pModule = ppModule.getValue();
        if (pModule == null)
        {
            log.error("module {} is invalid!", moduleName);
            throw new MegException(MegError.ERROR_INVALID_RES);
        }

        return ppModule;
    }

    static void destroyModule(String moduleName, PointerByReference ppModule) throws MegException {
        PointerByReference ppFactory = new PointerByReference();
        MegConnectLibrary.INSTANTCE.meg_find_factory(moduleName, "rpc", ppFactory);

        Pointer pFactory = ppFactory.getValue();
        if (pFactory == Pointer.NULL) {
            log.error("meg_find_factory {} fail!", moduleName);
            throw new MegException(MegError.ERROR_GENERAL);
        }

        Function fnc = Function.getFunction(pFactory.getPointer(1 * POINTER_SIZE));
        fnc.invokeInt(new Object[]{ppModule.getValue()});
    }

    static int invokeNativeInt(Pointer funcPointer, long vtableId, Object[] args) {
        Function func = Function.getFunction(funcPointer.getPointer(vtableId * POINTER_SIZE));
        return func.invokeInt(args);
    }

    /**
     * 输入参数只有一个json，无出参的函数
     * @param funcPointer 功能函数指针
     * @param vtableId 函数在功能结构体的位置序号
     * @param inJson 入参
     * @return 错误码
     */
    static int methodFunc1(Pointer funcPointer, long vtableId, String inJson) {
        return invokeNativeInt(funcPointer, vtableId, new Object[]{funcPointer, inJson});
    }

    /**
     * 输出参数只有一个json，无入参的函数
     * @param funcPointer 功能函数指针
     * @param vtableId 函数在功能结构体的位置序号
     * @param outJson 出参
     * @return 错误码
      */
    static  int methodFunc2(Pointer funcPointer, long vtableId, StringBuilder outJson) {
        return methodFunc3(funcPointer, vtableId, null, outJson);
    }

    /**
     * 输出参数只有一个json，输入参数只有一个json的函数
     * @param funcPointer 功能函数指针
     * @param vtableId 函数在功能结构体的位置序号
     * @param inJson 入参
     * @param outJson 出参
     * @return 错误码
     */
    static int methodFunc3(Pointer funcPointer, long vtableId, String inJson, StringBuilder outJson) {
        int ret;
        PointerByReference outJsonPtr = new PointerByReference();

        if (inJson != null) {
            ret = invokeNativeInt(funcPointer, vtableId, new Object[]{funcPointer, inJson, outJsonPtr});
        } else {
            ret = invokeNativeInt(funcPointer, vtableId, new Object[]{funcPointer, outJsonPtr});
        }

        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }

    /**
     * 输入参数只有一个json，带超时时间，无出参的函数
     * @param funcPointer 功能函数指针
     * @param vtableId 函数在功能结构体的位置序号
     * @param inJson 入参
     * @param timeout 请求超时时间，单位ms
     * @return 错误码
     */
    static int methodFunc4(Pointer funcPointer, long vtableId, String inJson, int timeout) {
        return invokeNativeInt(funcPointer, vtableId, new Object[]{funcPointer, inJson, timeout});
    }

    /**
     * 输出参数只有一个json，带超时时间，无入参的函数
     * @param funcPointer 功能函数指针
     * @param vtableId 函数在功能结构体的位置序号
     * @param outJson 出参
     * @param timeout 请求超时时间，单位ms
     * @return 错误码
      */
    static int methodFunc5(Pointer funcPointer, long vtableId, StringBuilder outJson, int timeout) {
        return methodFunc6(funcPointer, vtableId, null, outJson, timeout);
    }

    /**
     * 输出参数只有一个json，带超时时间，输入参数只有一个json的函数
     * @param funcPointer 功能函数指针
     * @param vtableId 函数在功能结构体的位置序号
     * @param inJson 入参
     * @param outJson 出参
     * @param timeout 请求超时时间，单位ms
     * @return 错误码
     */
    static int methodFunc6(Pointer funcPointer, long vtableId, String inJson, StringBuilder outJson, int timeout) {
        int ret;
        PointerByReference outJsonPtr = new PointerByReference();

        if (inJson != null) {
            ret = invokeNativeInt(funcPointer, vtableId, new Object[]{funcPointer, inJson, outJsonPtr, timeout});
        } else {
            ret = invokeNativeInt(funcPointer, vtableId, new Object[]{funcPointer, outJsonPtr, timeout});
        }

        if (ret == MegError.ERROR_OK.getCode()) {
            String outJsonStr = outJsonPtr.getValue().getString(0);
            outJson.append(outJsonStr);
            MegConnectLibrary.INSTANTCE.meg_conn_free(outJsonPtr.getValue());
        }

        return ret;
    }
}
