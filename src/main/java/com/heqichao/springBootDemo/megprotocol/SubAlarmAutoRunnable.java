package com.heqichao.springBootDemo.megprotocol;

import org.json.JSONArray;
import org.json.JSONObject;

import com.heqichao.springBootDemo.base.service.SendServiceImpl.MediaDeviceAlarmCb;
import com.heqichao.springBootDemo.megprotocol.MegCommon.AlarmSnCodeCbArg;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubAlarmAutoRunnable implements Runnable{

	private static final AlarmSnCodeCbArg arg = new MegCommon.AlarmSnCodeCbArg();
    private static final MediaDeviceAlarmCb deviceAlarmCb = new MediaDeviceAlarmCb();
    
	private String url;
    public void setUrl(String url){
        this.url = url;
    }
    @Override
    public void run() {  
    	StringBuilder outJsonStr = new StringBuilder();
    	MegDevice megDevice = null;
		try {
			megDevice = SDKInitUnit.getDeviceByUrl(url,MegDeviceAlarm.getModuleName());
			if(megDevice == null) {
				log.info("无设备连接或设备连接初始化失败");
				return;
			}
		} catch (MegException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
	    
		arg.callback = "http://ksjxisv.tfkuding.com/callback/240_handle";
		arg.sn =url.replace("active://", ""); 
		arg.write();
		
        int ret = MegDeviceAlarm.subscribeStream(megDevice, outJsonStr, deviceAlarmCb, arg.getPointer(), null, null);
        log.info("auto_open_alarm_status:"+ret);

        if (ret != MegError.ERROR_OK.getCode())
        {
            return;
        }
        JSONObject outJson = new JSONObject(outJsonStr.toString());
        int handle = outJson.getInt("handle");
//        res.put("handle", handle);
        
        JSONObject inJson = new JSONObject();
        JSONArray alarmType = new JSONArray();
        JSONObject alarmTypeItem = new JSONObject();
        JSONArray minorType = new JSONArray();
        minorType.put("normal_access_of_staff");//员工正常通行时间
//        minorType.put("network_disconnect");
//        minorType.put("ip_conflict");//IP冲突
//        minorType.put("mac_conflict");//MAC冲突
//        minorType.put("sd_card_abnormal");//SD卡异常
//        minorType.put("network_alarm");//网络报警
//        minorType.put("network_recovery");//网络恢复
//        minorType.put("io_input");//io输入报警
//        minorType.put("stranger");//陌生人
        minorType.put("non_living_attack");//非活体攻击
        minorType.put("no_wear_respirator");//未戴口罩
        minorType.put("import_person_try_to_pass");//重点人员尝试通行
        minorType.put("stranger_try_to_pass");//陌生人尝试通行
        minorType.put("abnormal_access_of_staff");//员工非通行时间尝试访问
        minorType.put("normal_access_of_visitor");//访客正常通行时间
        minorType.put("abnormal_access_of_visitor");//访客非通行时间尝试访问
        minorType.put("fire_control");//消防报警
        minorType.put("door_always_open");//门常开未关
        minorType.put("doorbell_rings");//门铃响
        minorType.put("suspicious_target");//可疑目标

        alarmTypeItem.put("major_type", "access_control");
        alarmTypeItem.put("minor_type", minorType);
        alarmType.put(alarmTypeItem);

        inJson.put("alarm_type", alarmType);
        inJson.put("handle", handle);
        int retAlarm = MegDeviceAlarm.subscribeAlarmType(megDevice, inJson.toString());
        
        log.info("auto_alarm_status:"+retAlarm);
    }  
}
