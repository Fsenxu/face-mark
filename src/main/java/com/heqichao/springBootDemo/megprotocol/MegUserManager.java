package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户管理模块，定义了用户管理相关的函数指针操作，对用户的增删改查
 */
@Slf4j
public abstract class MegUserManager {
    private static final String MODULE_NAME = "user_manager";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_CAP, ADD_USER, REMOVE_USER, SET_USER, QUERY_USERS, QUERY_USER, ADD_GROUP, REMOVE_GROUP, SET_GROUP,
        QUERY_GROUPS, QUERY_GROUP, QUERY_GROUP_USERS, QUERY_AUTH_LIST
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取用户管理模块能力集
     *
     * @param device 设备
     * @param outJson 用户管理模块能力集参数
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
     * 添加用户
     *
     * @param device 设备
     * @param inJson 用户信息
     * @return 错误码
     */
    public static int addUser(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.ADD_USER.ordinal(), inJson);
    }

    /**
     * 删除用户
     *
     * @param device 设备
     * @param inJson 用户信息
     * @return 错误码
     */
    public static int removeUser(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.REMOVE_USER.ordinal(), inJson);
    }

    /**
     * 修改用户
     *
     * @param device 设备
     * @param inJson 用户信息
     * @return 错误码
     */
    public static int setUser(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_USER.ordinal(), inJson);
    }

    /**
     * 查询用户
     *
     * @param device 设备
     * @param inJson  查询参数
     * @param outJson 曝光参数
     * @return 错误码
     */
    public static int queryUsers(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_USERS.ordinal(), inJson, outJson);
    }

    /**
     * 查询指定用户
     *
     * @param device 设备
     * @param inJson  指定用户id
     * @param outJson 查询结果
     * @return 错误码
     */
    public static int queryUser(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_USER.ordinal(), inJson, outJson);
    }

    /**
     * 添加用户组
     *
     * @param device 设备
     * @param inJson 用户组信息
     * @return 错误码
     */
    public static int addGroup(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.ADD_GROUP.ordinal(), inJson);
    }

    /**
     * 删除用户组
     *
     * @param device 设备
     * @param inJson 用户组信息
     * @return 错误码
     */
    public static int removeGroup(MegDevice device, String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.REMOVE_GROUP.ordinal(), inJson);
    }

    /**
     * 修改用户组
     *
     * @param device 设备
     * @param inJson 用户组信息
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
     * 查询用户组
     *
     * @param device 设备
     * @param inJson  查询参数
     * @param outJson 查询结果
     * @return 错误码
     */
    public static int queryGroups(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_GROUPS.ordinal(), inJson, outJson);
    }

    /**
     * 查询指定用户组
     *
     * @param device 设备
     * @param inJson  用户组id
     * @param outJson 查询结果
     * @return 错误码
     */
    public static int queryGroup(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_GROUP.ordinal(), inJson, outJson);
    }

    /**
     * 查询用户组下的用户列表
     *
     * @param device 设备
     * @param inJson  查询条件
     * @param outJson 查询结果
     * @return 错误码
     */
    public static int queryGroupUsers(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_GROUP_USERS.ordinal(), inJson, outJson);
    }

    /**
     * 查询权限列表
     *
     * @param device 设备
     * @param inJson  查询条件
     * @param outJson 查询结果
     * @return 错误码
     */
    public static int queryAuthList(MegDevice device, String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.QUERY_AUTH_LIST.ordinal(), inJson, outJson);
    }
}
