package com.heqichao.springBootDemo.megprotocol;

import com.heqichao.springBootDemo.megprotocol.ThirdLibrary.*;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.Callback;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

//继承Library，用于加载库文件
public interface MegConnectLibrary extends Library {

    // linux平台下第三方依赖库要在meg_conn_sdk库前加载！
    SSL SSL_INSTANTCE = SSL.INSTANTCE;
    CRYPTO CRYPTO_INSTANTCE = CRYPTO.INSTANTCE;
    TARO TAR_INSTANTCE = TARO.INSTANTCE;
    MXML MXML_INSTANTCE = MXML.INSTANTCE;

    MegConnectLibrary INSTANTCE = (MegConnectLibrary) Native.load("meg_conn_sdk", MegConnectLibrary.class);

    public static final int MEG_LOG_LEVEL_DEBUG = 0;
    public static final int MEG_LOG_LEVEL_INFO = 1;
    public static final int MEG_LOG_LEVEL_TRACE = 2;
    public static final int MEG_LOG_LEVEL_WARN = 3;
    public static final int MEG_LOG_LEVEL_ERROR = 4;
    public static final int MEG_LOG_LEVEL_FATAL = 5;
    public static final int MEG_LOG_LEVEL_BUTT = 6;

    public static final int MEG_CONN_INIT_CLIENT = 0x01;

    public static final int MEG_LOG_PATH_MAX_LEN = 256;
    public static final int MEG_LOG_PREFIX_MAX_LEN = 16;
    public static final int MEG_MAX_IP_ADDRESS_SIZE = 26;

    /**
     * 客户端/设备端状态回调
     * status   状态
     * url for client, or sessionid for server
     */
    public interface MegConnStatusCb extends Callback {
        void invoke(int status, String url_or_ssid);
    }

    public interface MegConnAuthorityCb extends Callback {
        int invoke(MegConnUserInfoStruct.ByReference user_info, MegConnAuthInterfaceStruct.ByReference auth_interface_info);
    }

    public interface MegConnLoginRegCb extends Callback {
        int invoke(MegConnUserInfoStruct.ByReference user_info, String in_json, String[] out_json);
    }

    public interface MegConnGetActivationCb extends Callback {
        int invoke(String[] out_json);
    }

    public interface MegConnSetActivationCb extends Callback {
        int invoke(String user, String pwd);
    }

    public interface MegConnActiveRegCb extends Callback {
        int invoke(String url, String active_id);
    }

    public interface MegConnAppFreeCb extends Callback {
        void invoke(Pointer pointer);
    }

    public interface MegConnExceptionCb extends Callback {
        void invoke(Pointer pointer, Pointer userArg);
    }

    public static class MegConnLogParamStruct extends Structure {
        public int enable;      //使能开关
        public int lv;          // 日志等级
        public int file_num;    // log 文件个数 0为默认个数
        public int file_size; // 单个log文件大小上限   0为默认大小
        public byte[] log_path = new byte[MEG_LOG_PATH_MAX_LEN];// log存储路径 空为默认路径
        public byte[] log_prefix = new byte[MEG_LOG_PREFIX_MAX_LEN];// log文件名前缀 空为默认路径

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("enable", "lv", "file_num", "file_size", "log_path", "log_prefix");
        }

        public static class ByReference extends MegConnLogParamStruct implements Structure.ByReference {
        }

        public static class ByValue extends MegConnLogParamStruct implements Structure.ByValue {
        }
    }

    /**
     * 客户端用户信息，类型+会话id共同保证会话唯一性
     */
    public static class MegConnUserInfoStruct extends Structure {
        public int type;            // 连接类型
        public String session_id;   // 会话id
        public String user_name;    // 用户名
        public String ip;
        public String active_link_url; // 主动登录链接唯一标识， 主动登录模式下生效

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("type", "session_id", "user_name", "ip", "active_link_url");
        }

        public static class ByReference extends MegConnUserInfoStruct implements Structure.ByReference {
        }

        public static class ByValue extends MegConnUserInfoStruct implements Structure.ByValue {
        }
    }

    public static class MegConnAuthInterfaceStruct extends Structure {
        public String module_name;      //接口所在模块名称
        public String interface_name;   //接口名称
        public String in_json;          //接口鉴权所需额外参数,JSON格式,不需要则为null

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("module_name", "interface_name", "in_json");
        }

        public static class ByReference extends MegConnAuthInterfaceStruct implements Structure.ByReference {
        }

        public static class ByValue extends MegConnAuthInterfaceStruct implements Structure.ByValue {
        }
    }

    /**
     * SDK初始化参数
     */
    public static class MegConnInitParamStruct extends Structure {
        public int type;                            //初始化类型
        public MegConnAppFreeCb com_free;           // 用户自定义释放内存
        public MegConnExceptionCb excep_cb;
        public Pointer[] reserved1 = new Pointer[3];

        /******************服务端的参数**********************/
        public String rpc_svr_url;
        public String https_svr_url;
        public String http_svr_url;
        public String onvif_multicast_ip;
        public String crt_path;         // https启用时有效，表示证书路径
        public String key_path;         // https启用时有效，表示秘钥路径

        public MegConnStatusCb svr_status_cb;
        public MegConnLoginRegCb check_param_enc_cb;
        public MegConnGetActivationCb get_activation_status_cb;
        public MegConnSetActivationCb activate_device_cb;
        public MegConnAuthorityCb authority_cb;
        public MegConnStatusCb cli_status_cb;
        public Pointer[] reserved2 = new Pointer[4];
        
        /*****************客户端的参数*********************/
        public int need_active_server; // 是否启用主动模式
        public String active_svr_url;     // 主动模式下的监听的url
        public MegConnActiveRegCb reg_check_cb;       // 主动模式下的回调
        public Pointer[] reserved3 = new Pointer[4];

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("type", "com_free", "excep_cb", "reserved1", "rpc_svr_url", "https_svr_url", "http_svr_url", "onvif_multicast_ip", "crt_path", "key_path",
                    "svr_status_cb", "check_param_enc_cb", "get_activation_status_cb", "activate_device_cb", "authority_cb", "cli_status_cb", "reserved2",
                    "need_active_server", "active_svr_url", "reg_check_cb", "reserved3");
        }
    }

    //以下方法为链接库中的方法

    /**
     * 获取SDK版本号
     *
     * @return SDK版本号
     */
    String meg_conn_get_sdk_version();

    /**
     * 获取onvif版本号
     *
     * @return onvif 版本号
     */
    String meg_conn_get_onvif_version();

    /**
     * 设置日志参数
     *
     * @param param 日志参数结构体
     * @return 参考errno
     */
    int meg_set_log_param(MegConnLogParamStruct param);

    /**
     * SDK初始化接口
     *
     * @param params 初始化参数
     * @return 参考errno
     */
    int meg_conn_init(MegConnInitParamStruct params);

    /**
     * SDK反初始化接口
     *
     * @return 参考errno
     */
    int meg_conn_uninit();

    /**
     * 登录
     *
     * @param url 登录url，直连登录格式："协议://ip:port?user=用户名&password=密码"
     *            比如："tcp://127.0.0.1:34567?user=test&password=123"
     *            用户端主动登录格式："协议://ssid"
     *            比如："active://sn12345678"
     * @return errno_ok  成功  others  失败
     */
    int meg_conn_login(String url);

    /**
     * 登出
     * 1.此接口只给用户端登录使用。
     * 2.url为login时候使用的url，必须与login时的保持一致
     *
     * @param url 登出url，同 meg_conn_login() url 格式
     * @return errno_ok  成功  others  失败
     */
    int meg_conn_logout(String url);

    /**
     * 主动登录(异步)，设备端连接云端使用，实际连接状态由 svr_status_cb回调给出
     * 1.此接口只给用户端登录使用。
     * 2.url为login时候使用的url，必须与login时的保持一致
     *
     * @param url 登录url，设备端主动登录格式："协议://ip:port?sn=平台分配给设备的sn"
     *            比如："tcp://127.0.0.1:34567?sn=1223"
     * @return errno_ok  成功  others  失败
     */
    int meg_conn_active_login( String url );

    /**
     * 主动登出，给设备端从云端退出登录使用
     *
     * @param url 登出url，同 meg_conn_active_login() url 格式
     * @return errno_ok  成功  others  失败
     */
    int meg_conn_active_logout( String url );

    /**
     * 主动释放由 SDK申请的内存空间
     *
     * @param ptr
     */
    void meg_conn_free(Pointer ptr);

    /**
     * 查找工厂对象
     *
     * @param iid     MODULE_NAME
     * @param type    协议类型，如rpc通信组件则传入"rpc",如本地的组件则传入"local",对于用户端而言是rpc,设备端而言是local
     * @param factory 工厂对象
     * @return 参考errno
     */
    int meg_find_factory(String iid, String type, PointerByReference factory);
}
