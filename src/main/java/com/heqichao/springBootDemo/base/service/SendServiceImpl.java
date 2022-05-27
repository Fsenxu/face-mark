package com.heqichao.springBootDemo.base.service;

import static com.heqichao.springBootDemo.megprotocol.Utils.CommonFunction.getContent;
import static com.heqichao.springBootDemo.megprotocol.Utils.CommonFunction.waitMs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heqichao.springBootDemo.base.util.SmsUtil;
import  com.heqichao.springBootDemo.megprotocol.Clibrary;
import com.heqichao.springBootDemo.megprotocol.MegConnectLibrary;
import com.heqichao.springBootDemo.megprotocol.MegDevice;
import com.heqichao.springBootDemo.megprotocol.MegError;
import com.heqichao.springBootDemo.megprotocol.MegException;
import com.heqichao.springBootDemo.megprotocol.MegFaceManager;
import  com.heqichao.springBootDemo.megprotocol.MegCommon;
import com.heqichao.springBootDemo.megprotocol.MegDeviceStorage;
import com.heqichao.springBootDemo.megprotocol.MegDeviceAlarm;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by heqichao on 2018-2-12.
 */
@Slf4j
@Service
//@EnableConfigurationProperties(SmsUtil.class)
public class SendServiceImpl implements SendService {

    @Autowired
    private SmsUtil smsUtil;
    
    private static MegDevice megDevice;
    private MediaDeviceAlarmCb deviceAlarmCb = new MediaDeviceAlarmCb();

    //如果配置在类上 则整个类的方法均有事务
    @Transactional
    @Override
    public void sendSms(String sendUserId, String formUserId, String context, boolean useQueue) {
        System.out.println(smsUtil.getUserNo()+":"+smsUtil.getPassword());
    }
    
	@Override
    public Map<String,Object> testMeg(){
		Map<String,Object> map = new HashMap<String,Object>();
		int conn = sdkInit();
		try {
			megDevice = getDevice();
		} catch (MegException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("conn", conn);
		StringBuilder outJsonStr = new StringBuilder();

        int ret = MegDeviceAlarm.subscribeStream(megDevice, outJsonStr, deviceAlarmCb, null, null, null);

        JSONObject outJson = new JSONObject(outJsonStr.toString());
        int handle = outJson.getInt("handle");
	    map.put("ret", ret);
	    map.put("outJson", outJsonStr.toString());
	    map.put("handle", handle);
	    
	    JSONObject inJson = new JSONObject();
        JSONArray alarmType = new JSONArray();
        JSONObject alarmTypeItem = new JSONObject();
        JSONArray minorType = new JSONArray();
        minorType.put("normal_access_of_staff");

        alarmTypeItem.put("major_type", "access_control");
        alarmTypeItem.put("minor_type", minorType);
        alarmType.put(alarmTypeItem);

        inJson.put("alarm_type", alarmType);
        inJson.put("handle", handle);
        int ret2 = MegDeviceAlarm.subscribeAlarmType(megDevice, inJson.toString());
        map.put("ret2", ret2);
        
//		
		return map;
		
    }
	
	@Override
	public Map<String,Object> testGroup(){
		Map<String,Object> map = new HashMap<String,Object>();
//		StringBuilder outJsonStr = new StringBuilder();
//		String path = System.getProperty("user.dir");
//
//        byte[] data = null;
//        try
//        {
//            data = getContent(path + "/data/staff.jpg");
//        }
//        catch (IOException e)
//        {
//            log.error("Fail to read file!");
//        }
//        Pointer ptr = new Memory(data.length);
//        ptr.write(0, data, 0, data.length);
//        log.info("data.length:"+data.length);
//
//
//        String inStr = "{"
//        + "\"person_id\" : \"333454566\","
//        + "\"person_info\"       : { \"name\": \"test_name\", \"code\": \"4543667787\", \"type\": \"staff\"},"
//        + "\"enable_face_filter\": false,"
//        + "\"face_data\": {\"data_type\":0, " +
//        "\"save_image\":true, " +
//        "\"feature_version\": \"abc\", " +
//        "\"data\":[{\"data_size\":" + data.length + ",\"image_type\": \"jpg\",\"img_name\":\"img_name_addperson\"}]},"
//        + "\"groups\"   : [{\"group_id\": \"627b7f877490411aad1f31a94e82c205\"}]"
//        + "}";
//
//        MegFaceManager.BinDataStruct.ByValue binData = new MegFaceManager.BinDataStruct.ByValue();
//        MegFaceManager.BinDataStruct.ByValue binData1 = new MegFaceManager.BinDataStruct.ByValue();
//        MegFaceManager.BinDataStruct.ByValue binData2 = new MegFaceManager.BinDataStruct.ByValue();
//
//        MegFaceManager.PersonFaceStruct personFaces = new MegFaceManager.PersonFaceStruct();
//        personFaces.faces[0] = binData;
//        personFaces.faces[0].data_size = data.length;
//        personFaces.faces[0].data = ptr;
//        personFaces.faces[1] = binData1;
//        personFaces.faces[1].data_size = 0;
//        personFaces.faces[1].data = null;
//        personFaces.faces[2] = binData2;
//        personFaces.faces[2].data_size = 0;
//        personFaces.faces[2].data = null;
//
//        int ret = MegFaceManager.addPerson(megDevice, new JSONObject(inStr).toString(), personFaces, outJsonStr);
//		map.put("testGroup", ret);
//		map.put("outJsonStr", outJsonStr.toString());
		StringBuilder outJsonStr = new StringBuilder();
		JSONObject inJson = new JSONObject();
		inJson.put("offset", 0);
        inJson.put("size", 10);
        inJson.put("get_feature", false);
        int ret = MegDeviceAlarm.queryAlarmHistory(megDevice, inJson.toString(), outJsonStr);
        map.put("ret", ret);
        map.put("outJsonStr", outJsonStr.toString());
        
        
        JSONObject outJson = new JSONObject(outJsonStr.toString());

        JSONArray alarmList = outJson.getJSONArray("list");
        if (alarmList.length() != 10)
        {
            log.error("alarm num is wrong!");
        }

        JSONArray fullImages = alarmList.getJSONObject(0).getJSONArray("full_images");
        JSONObject fullImage = fullImages.getJSONObject(0);
        JSONObject image_data = fullImage.getJSONObject("image_data");
        
        String imagePath = image_data.getString("value");
         inJson = new JSONObject();
        inJson.put("image_uri", imagePath);
        IntByReference imgSize = new IntByReference();
        PointerByReference imgData = new PointerByReference();
        int ret2 = MegDeviceStorage.getImage(megDevice, inJson.toString(), imgSize, imgData);
        log.debug("getImage img_size: " + imgSize.getValue());
        map.put("ret2", ret2);
        map.put("img_size", imgSize.getValue());

        byte[] data = imgData.getValue().getByteArray(0, imgSize.getValue());
        try {
        	String path = System.getProperty("user.dir");
        	Date date = new Date();
            FileOutputStream fos;
            fos = new FileOutputStream(path + "/data/"+date.getTime()+".jpg", true);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MegConnectLibrary.INSTANTCE.meg_conn_free(imgData.getValue());
		
		return map;
		
	}
    
    private static String g_url = "tcp://10.235.102.29:9090?user=admin&password=admin123";
    private final static String g_active_svr_url = "tcp://0.0.0.0:8199";
    private final static int g_need_active_server = 1;
    private static Boolean sdkInited = false;
    private static Boolean connected = false;

    public static int sdkInit() {
        if (sdkInited) {
            return MegError.ERROR_MULTIPLIE.getCode();
        }

        log.debug("sdk_version: " + MegConnectLibrary.INSTANTCE.meg_conn_get_sdk_version());

        MegConnectLibrary.MegConnLogParamStruct logparm = new MegConnectLibrary.MegConnLogParamStruct();
        logparm.enable = 1;
        logparm.lv = MegConnectLibrary.MEG_LOG_LEVEL_DEBUG;
        logparm.file_num = 2;
        logparm.file_size = 12 * 1024 * 1024;

        byte[] log_path = "./".getBytes();
        byte[] log_prefix = "meg_client".getBytes();
        System.arraycopy(log_path, 0, logparm.log_path, 0, log_path.length);
        System.arraycopy(log_prefix, 0, logparm.log_prefix, 0, log_prefix.length);
        MegConnectLibrary.INSTANTCE.meg_set_log_param(logparm);

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
        {
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
    private static class MediaDeviceAlarmCb implements MegDeviceAlarm.MediaDeviceAlarmCb {
        @Override
        public void invoke(MegCommon.AlarmMsg.ByReference alarmMsg, Pointer userArg) {
            byte[] info = alarmMsg.jsonInfo.getByteArray(0, alarmMsg.infoLen);
            String infoStr = new String(info);
            log.info("info: " + infoStr);
            log.info("binDataNumSize: " + alarmMsg.data.binDataNumSize);
            log.info("type: " + alarmMsg.data.binDataInfos.type);

            MegCommon.BinDataInfo[] binDataInfos = (MegCommon.BinDataInfo[])alarmMsg.data.binDataInfos.toArray(alarmMsg.data.binDataNumSize);

            for (MegCommon.BinDataInfo binDataInfo : binDataInfos)
            {
                log.info("binDataInfos: " + binDataInfo.size);

                byte[] data = binDataInfo.binData.getByteArray(0, binDataInfo.size);
                try {
                	String path = System.getProperty("user.dir");
                	Date date = new Date();
                    FileOutputStream fos;
                    fos = new FileOutputStream(path + "/data/"+date.getTime()+".jpg", false);
                    fos.write(data);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
