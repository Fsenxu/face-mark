package com.heqichao.springBootDemo.megprotocol;

import lombok.extern.slf4j.Slf4j;

import static com.heqichao.springBootDemo.megprotocol.Utils.CommonFunction.waitMs;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SDKInitUnit {
    private static String g_url = "tcp://10.235.102.29:9090?user=admin&password=admin123";
    private final static String g_active_svr_url = "tcp://0.0.0.0:8199";
    private final static int g_need_active_server = 1;
    private static Boolean sdkInited = false;
    private static Boolean connected = false;

    public static int sdkInit() {
        if (sdkInited) {//是否重复连接
            return MegError.ERROR_MULTIPLIE.getCode();
        }

        log.debug("sdk_version: " + MegConnectLibrary.INSTANTCE.meg_conn_get_sdk_version());

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
            log.debug("status=" + status + " url=" + url_or_ssid);
        };

        init.onvif_multicast_ip = null;
        init.rpc_svr_url = null;
        init.svr_status_cb = null;
        init.http_svr_url = null;
        init.com_free = (p) -> Clibrary.INSTANCE.free(p);
        init.authority_cb = null;

        if (g_need_active_server == 0)
        {
            init.reg_check_cb = null;
            init.active_svr_url = null;
        }
        else
        {
            init.active_svr_url = g_active_svr_url;
            init.reg_check_cb = (url, active_id) -> {
                log.info("url=" + url + " active_id=" + active_id);
                connected = true;
                g_url = url;
                return 1;
            };
        }

        int ret = MegConnectLibrary.INSTANTCE.meg_conn_init(init);
        log.debug("sdk init ret:" + ret);

        sdkInited = true;

        return ret;
    }

    public static void sdkUnInit() {
        if (sdkInited) {
            MegConnectLibrary.INSTANTCE.meg_conn_logout(g_url);
            MegConnectLibrary.INSTANTCE.meg_conn_uninit();

            sdkInited = false;
        }
    }

    public static MegDevice getDevice() throws MegException {
        if (g_need_active_server == 0)
        {
            MegConnectLibrary.INSTANTCE.meg_conn_login(g_url);
        }
        else
        {//不断检查重连
            while (true)
            {
                waitMs(1000);

                if (connected) {
                    break;
                }
            }
        }

        MegDevice megDevice = new MegDevice(g_url);
        megDevice.init();
        return megDevice;
    }
}
