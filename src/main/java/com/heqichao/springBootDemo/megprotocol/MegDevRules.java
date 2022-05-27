package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MegDevRules {
    private static final String MODULE_NAME = "device_rules";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_RULES_CAP, ADD_TRIGGER, REMOVE_TRIGGER, SET_TRIGGER, QUERY_TRIGGER, ADD_SHEDULE_PLAN, REMOVE_SHEDULE_PLAN, SET_SCHEDULE_PLAN, QUERY_SCHDULE_PLAN, GET_CAP
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 查询联动规则能力集
     *
     * @param device 设备
     * @param outJson 联动规则能力集
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
     * 查询联动规则模块能力集
     *
     * @param device 设备
     * @param outJson 联动规则模块能力集
     * @return 错误码
     */
    public static int getRulesCap(MegDevice device, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_RULES_CAP.ordinal(), outJson);
    }

    /**
     * 添加时间计划规则
     *
     * @param device 设备
     * @param inJson 时间计划规则参数
     * @return 错误码
     */
    public static int addSchedulePlan(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.ADD_SHEDULE_PLAN.ordinal(), inJson);
    }

    /**
     * 删除时间计划规则
     *
     * @param device 设备
     * @param inJson 删除时间计划则参数
     * @return 错误码
     */
    public static int removeSchedulePlan(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.REMOVE_SHEDULE_PLAN.ordinal(), inJson);
    }

    /**
     * 修改时间计划规则
     *
     * @param device 设备
     * @param inJson 时间计划规则参数
     * @return 错误码
     */
    public static int setSchedulePlan(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_SCHEDULE_PLAN.ordinal(), inJson);
    }

    /**
     * 查询时间计划规则
     *
     * @param device 设备
     * @param inJson 时间计划规则参数
     * @param outJson 查询时间计划规则返回参数
     * @return 错误码
     */
    public static int querySchedulePlan(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_SCHDULE_PLAN.ordinal(), inJson, outJson);
    }
}
