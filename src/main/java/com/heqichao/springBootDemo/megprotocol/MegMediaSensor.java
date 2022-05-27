package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;

/**
 * 传感器功能模块用于配置传感器相关的参数
 */
@Slf4j
public abstract class MegMediaSensor {
    private static final String MODULE_NAME = "media_sensor";

    /**
     * 仅用于定位函数指针位置，必须与SDK C接口结构体内函数先后顺序保持一致！
     */
    enum funcTable {
        GET_SENSOR_NUM, GET_SENSOR_CAP, GET_WHITE_BALANCE, SET_SHITE_BALANCE, GET_EXPOSURE, SET_EXPOSURE,
        GET_IMAGE_SCENE,SET_IMAGE_SCENE,GET_IMAGE_STYLE,SET_IMAGE_STYLE,GET_LAMP_CTRL,SET_LAMP_CTRL,
        GET_FOCUS,SET_FOCUS,GET_DAY_NIGHT,SET_DAY_NIGHT,GET_BACK_LIGHT,SET_BACK_LIGHT,GET_IMAGE_ENHANCE,
        SET_IMAGE_ENHANCE,GET_AF,SET_AF,GET_IMAGE_SCENE_TRANSFORMATION,SET_IMAGE_SCENE_TRANSFORMATION
    }

    public static String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * 获取传感器数量
     *
     * @param device  设备
     * @param outJson 传感器通道数及各通道下的传感器数量
     * @return 错误码
     */
    public static int getSensorNum(MegDevice device,StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc2(funcPointer, funcTable.GET_SENSOR_NUM.ordinal(), outJson);
    }

    /**
     * 获取传感器能力集
     *
     * @param device  设备
     * @param inJson  查询参数
     * @param outJson 传感器能力集
     * @return 错误码
     */
    public static int getSensorCap(MegDevice device,String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }


        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_SENSOR_CAP.ordinal(), inJson, outJson);
    }

    /**
     * 获取白平衡参数
     *
     * @param device   设备
     * @param inJson   查询参数
     * @param outJson  白平衡参数
     * @return  错误码
     */
    public static int getWhiteBalance(MegDevice device,String inJson, StringBuilder outJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_WHITE_BALANCE.ordinal(), inJson, outJson);
    }

    /**
     * 设置白平衡参数
     *
     * @param device   设备
     * @param inJson   白平衡参数
     * @return 错误码
     */
    public static int setWhiteBalance(MegDevice device,String inJson) {
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_SHITE_BALANCE.ordinal(), inJson);
    }

    /**
     * 获取曝光参数
     *
     * @param device   设备
     * @param inJson   查询参数
     * @param outJson  曝光参数
     * @return  错误码
     */
    public static int getExposure(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_EXPOSURE.ordinal(), inJson, outJson);
    }

    /**
     * 设置曝光参数
     *
     * @param device   设备
     * @param inJson   曝光参数
     * @return  错误码
     */
    public static int setExposure(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_EXPOSURE.ordinal(), inJson);
    }

    /**
     * 获取图像场景参数
     *
     * @param device   设备
     * @param inJson   查询参数
     * @param outJson  图像场景参数
     * @return  错误码
     */
    public static int getImageScene(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_IMAGE_SCENE.ordinal(), inJson, outJson);
    }

    public static int setImageScene(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_IMAGE_SCENE.ordinal(), inJson);
    }


    public static int getImageStyle(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_IMAGE_STYLE.ordinal(), inJson, outJson);
    }

    public static int setImageStyle(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_IMAGE_STYLE.ordinal(), inJson);
    }

    public static int getLampCtrl(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_LAMP_CTRL.ordinal(), inJson, outJson);
    }

    public static int setLampCtrl(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_LAMP_CTRL.ordinal(), inJson);
    }

    public static int getFocus(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_FOCUS.ordinal(), inJson, outJson);
    }

    public static int setFocus(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_FOCUS.ordinal(), inJson);
    }

    public static int getDayNight(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_DAY_NIGHT.ordinal(), inJson, outJson);
    }

    public static int setDayNight(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_DAY_NIGHT.ordinal(), inJson);
    }

    public static int getBackLigh(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_BACK_LIGHT.ordinal(), inJson, outJson);
    }

    public static int setBackLigh(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_BACK_LIGHT.ordinal(), inJson);
    }

    public static int getImageEnhance(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_IMAGE_ENHANCE.ordinal(), inJson, outJson);
    }

    public static int setImageEnhance(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_IMAGE_ENHANCE.ordinal(), inJson);
    }

    public static int getAf(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_AF.ordinal(), inJson, outJson);
    }

    public static int setAf(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_AF.ordinal(), inJson);
    }

    public static int getImageSceneTransformation(MegDevice device, String inJson, StringBuilder outJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc3(funcPointer, funcTable.GET_IMAGE_SCENE_TRANSFORMATION.ordinal(), inJson, outJson);
    }

    public static int setImageSceneTransformation(MegDevice device, String inJson){
        Pointer funcPointer = device.getModule(MODULE_NAME);
        if (funcPointer == null)
        {
            log.warn("device is disconnect!");
            return MegError.ERROR_DISCONNECT.getCode();
        }

        return ComponentFactory.methodFunc1(funcPointer, funcTable.SET_IMAGE_SCENE_TRANSFORMATION.ordinal(), inJson);
    }
}
