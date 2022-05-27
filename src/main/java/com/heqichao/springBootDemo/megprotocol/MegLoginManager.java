package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MegLoginManager {
    private static final String MODULE_NAME = "login_manager";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        SET_ACTIVE_PARAM, QUERY_ACTIEVE_PARAM, GET_CAP, SET_LOGIN_POLICY, GET_LOGIN_POLICY
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 设置主动登录模式参数
     *
     * @param device 设备
     * @param inJson JSON串中包含主动登录模式参数，可参考 /ref active_param_json 的详细描述
     * @return 错误码
     */
    public static int setActiveParam(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_ACTIVE_PARAM.ordinal(), inJson);
    }

    /**
     * 获取主动登录模式参数
     *
     * @param device 设备
     * @param outJson JSON串中包含主动登录模式参数，可参考 /ref active_param_json 的详细描述
     * @return 错误码
     */
    public static int queryActiveParam(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.QUERY_ACTIEVE_PARAM.ordinal(), outJson);
    }

    /**
     * 获取模块能力集
     *
     * @param device 设备
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
     * 设置用户登录策略参数
     *
     * @param device 设备
     * @param inJson 登录管理策略
     * @return 错误码
     */
    public static int setLoginManagerPolicy(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_LOGIN_POLICY.ordinal(), inJson);
    }

    /**
     * 获取用户登录策略参数
     *
     * @param device 设备
     * @param outJson 登录管理策略
     * @return 错误码
     */
    public static int getLoginManagerPolicy(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_LOGIN_POLICY.ordinal(), outJson);
    }
}
