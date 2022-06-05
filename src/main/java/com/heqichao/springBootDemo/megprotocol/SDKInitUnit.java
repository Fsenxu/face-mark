package com.heqichao.springBootDemo.megprotocol;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SDKInitUnit {
    private final static String g_active_svr_url = "tcp://0.0.0.0:8199";
    private final static int g_need_active_server = 1;
    private static Boolean sdkInited = false;
    private static final Map<String, MegDevice> devPool = new HashMap<String, MegDevice>();

    public static int sdkInit() {
        if (sdkInited) {//是否重复连接
            return MegError.ERROR_MULTIPLIE.getCode();
        }

        log.info("sdk_version: " + MegConnectLibrary.INSTANTCE.meg_conn_get_sdk_version());

        //日志配置
        MegConnectLibrary.MegConnLogParamStruct logparm = new MegConnectLibrary.MegConnLogParamStruct();
        logparm.enable = 1;
        logparm.lv = MegConnectLibrary.MEG_LOG_LEVEL_DEBUG;
        logparm.file_num = 2;
        logparm.file_size = 12 * 1024 * 1024;

        byte[] log_path = "./".getBytes();
        byte[] log_prefix = "meg_client".getBytes();
        System.arraycopy(log_path, 0, logparm.log_path, 0, log_path.length);
        System.arraycopy(log_prefix, 0, logparm.log_prefix, 0, log_prefix.length);
        MegConnectLibrary.INSTANTCE.meg_set_log_param(logparm);//设置日志参数

        
        MegConnectLibrary.MegConnInitParamStruct init = new MegConnectLibrary.MegConnInitParamStruct();
        init.type = MegConnectLibrary.MEG_CONN_INIT_CLIENT;
        init.need_active_server = g_need_active_server;
        init.cli_status_cb = (status, url_or_ssid) -> {
            log.info("status=" + status + " url=" + url_or_ssid);
        };

        init.onvif_multicast_ip = null;
        init.rpc_svr_url = null;
        init.svr_status_cb = null;
        init.http_svr_url = null;
        init.com_free = (p) -> Clibrary.INSTANCE.free(p);
        init.authority_cb = null;

        init.active_svr_url = g_active_svr_url;
        init.reg_check_cb = (url, active_id) -> {
            log.info("url=" + url + " active_id=" + active_id);
            MegDevice megDevice = new MegDevice(url);
			megDevice.setConnected(true);
            devPool.put(url, megDevice);
            return 1;
        };

        int ret = MegConnectLibrary.INSTANTCE.meg_conn_init(init);
        log.info("sdk init ret:" + ret);

        sdkInited = true;

        return ret;
    }

    public static void sdkUnInit() {
        if (sdkInited) {
        	if(devPool.size()>0) {//有连接清连接
        		Set<String> keySet = devPool.keySet();
        		for(String url:keySet) {
        			MegConnectLibrary.INSTANTCE.meg_conn_logout(url);
        			devPool.remove(url);
        		}
        	}
            MegConnectLibrary.INSTANTCE.meg_conn_uninit();

            sdkInited = false;
        }
    }

    public static MegDevice getCurrDevice(String url,String moduleName) throws MegException {

    		MegDevice megDevice = new MegDevice(url);
    			megDevice.init(moduleName);
    			return megDevice;
    }
    public static MegDevice getDevice(String snCode,String moduleName) throws MegException {
    	
    	String url = "active://"+snCode;
    	if(devPool.containsKey(url)) {
    		MegDevice megDevice = devPool.get(url);
    		if(megDevice.getConnected()) {//初始化失败的话不返回
    			megDevice.init(moduleName);
    			return megDevice;
    		}
    		
    	}
    	return null;
    }
    public static int getRandomNumberInRange() {	
		Random r = new Random();
		int min = 0;
		int max = 65534;
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();		
	}
}
