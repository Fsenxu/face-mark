package com.heqichao.springBootDemo.megprotocol;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MegDevice {
    private final String url;
    private final Map<String, PointerByReference> moduleTable = new HashMap<>();

    public MegDevice(String url) {
        this.url = url;
    }

    private void registerModule(String moduleName) throws MegException {
        PointerByReference ppModule;

        try{
            ppModule = ComponentFactory.createModule(moduleName, url);
        } catch (MegException megException) {
            log.error("createModule {} fail!", moduleName);
            throw new MegException(MegError.ERROR_INVALID_RES);
        }

        moduleTable.put(moduleName, ppModule);
    }

    public void init() throws MegException {
        registerModule(MegDeviceAccess.getModuleName());
        registerModule(MegDeviceAlarm.getModuleName());
        registerModule(MegDeviceStorage.getModuleName());
        registerModule(MegDevMaintenance.getModuleName());
        registerModule(MegDevParamManager.getModuleName());
        registerModule(MegDevRules.getModuleName());
        registerModule(MegUserManager.getModuleName());
        registerModule(MegLoginManager.getModuleName());
        registerModule(MegMediaAudio.getModuleName());
        registerModule(MegMediaDisplay.getModuleName());
        registerModule(MegMediaMixStream.getModuleName());
        registerModule(MegMediaVideo.getModuleName());
        registerModule(MegIntelliManager.getModuleName());
        registerModule(MegMediaPlayback.getModuleName());
        registerModule(MegFaceManager.getModuleName());
        registerModule(MegMediaSensor.getModuleName());
    }

    public void deInit() throws MegException {
        for (Map.Entry<String, PointerByReference> entry : moduleTable.entrySet()) {
            PointerByReference ppModule = entry.getValue();
            ComponentFactory.destroyModule(MegDeviceAccess.getModuleName(), ppModule);
        }
    }

    Pointer getModule(String moduleName) {
        PointerByReference ppModule = moduleTable.get(moduleName);
        if (ppModule == null)
        {
            log.error("Fail to get module {}!", moduleName);
            return null;
        }

        return ppModule.getValue();
    }
}
