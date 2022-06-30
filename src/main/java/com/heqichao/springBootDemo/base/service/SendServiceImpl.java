package com.heqichao.springBootDemo.base.service;

import static com.heqichao.springBootDemo.megprotocol.Utils.CommonFunction.getContent;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.heqichao.springBootDemo.base.util.SmsUtil;
import com.heqichao.springBootDemo.megprotocol.MegDevice;
import com.heqichao.springBootDemo.megprotocol.MegFaceManager;
import com.heqichao.springBootDemo.megprotocol.SDKInitUnit;
import com.heqichao.springBootDemo.megprotocol.MegError;
import com.heqichao.springBootDemo.megprotocol.MegCommon;
import com.heqichao.springBootDemo.megprotocol.MegCommon.AlarmSnCodeCbArg;
import com.heqichao.springBootDemo.megprotocol.MegDevRules;
import com.heqichao.springBootDemo.megprotocol.MegIntelliManager;
import com.heqichao.springBootDemo.megprotocol.MegDeviceAlarm;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

/**
 * Created by heqichao on 2018-2-12.
 */
@Slf4j
@Service
//@EnableConfigurationProperties(SmsUtil.class)
public class SendServiceImpl implements SendService {

    @Autowired
    private SmsUtil smsUtil;
    
    private final MediaDeviceAlarmCb deviceAlarmCb = new MediaDeviceAlarmCb();
    private final AlarmSnCodeCbArg arg = new MegCommon.AlarmSnCodeCbArg();
    
    //如果配置在类上 则整个类的方法均有事务
    @Transactional
    @Override
    public void sendSms(String sendUserId, String formUserId, String context, boolean useQueue) {
        System.out.println(smsUtil.getUserNo()+":"+smsUtil.getPassword());
    }
    
	@Override
    public Map<String,Object> initConnect() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int ret = SDKInitUnit.sdkInit();
        map.put("status", ret);
		return map;
		
    }
	
	@Override
	public Map<String,Object> initAlarm(Map map) throws Exception{
		String sn = map.remove("sn_code").toString();
		String callback = map.remove("callback").toString();
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("sn_code", sn);
		res.put("type", "init_alarm");
		StringBuilder outJsonStr = new StringBuilder();
		MegDevice megDevice = SDKInitUnit.getDevice(sn,MegDeviceAlarm.getModuleName());
		if(megDevice == null) {
			res.put("status", 8);
			res.put("outPut", "无设备连接或设备连接初始化失败");
			return res;
		}
		
		arg.callback = callback;
		arg.sn =sn; 
		arg.write();
        int ret = MegDeviceAlarm.subscribeStream(megDevice, outJsonStr, deviceAlarmCb, arg.getPointer(), null, null);
        res.put("open_alarm_status", ret);

        if (ret != MegError.ERROR_OK.getCode())
        {
            return res;
        }
        JSONObject outJson = new JSONObject(outJsonStr.toString());
        int handle = outJson.getInt("handle");
        res.put("handle", handle);
        
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
        
        res.put("alarm_status", retAlarm);
        return res;
	}
	
	@Override
	public Map<String,Object> testGroup(Map map) throws Exception{
		Map<String,Object> res = new HashMap<String,Object>();
		String sn = map.remove("sn_code").toString();
		StringBuilder outJsonStr = new StringBuilder();

		JSONObject inJson = new JSONObject();
        JSONObject param = new JSONObject();
        JSONArray algType = new JSONArray();
        param.put("offset", 0);
        param.put("size", 8);
        inJson.put("param", param);
        algType.put("access");
        inJson.put("alg_type", algType);

        MegDevice megDevice = SDKInitUnit.getDevice(sn,MegIntelliManager.getModuleName());
        if(megDevice == null) {
			res.put("status", 8);
			res.put("outPut", "无设备连接或设备连接初始化失败");
			return res;
		}
        int ret = MegIntelliManager.queryMonitor(megDevice, inJson.toString(), outJsonStr);
        res.put("status", ret);
        res.put("type", "query_person");
        res.put("outPut", outJsonStr.toString());
        
        MegDevice megDevice2 = SDKInitUnit.getDevice(sn,MegDevRules.getModuleName());
        if(megDevice2 == null) {
			res.put("status2", 8);
			res.put("outPut2", "无设备连接或设备连接初始化失败");
			return res;
		}
        
        JSONObject inJson3 = new JSONObject();

        JSONObject calendarSchedule = new JSONObject();
        JSONArray day = new JSONArray();
        day.put("2:00-4:00");
        day.put("10:00-12:00");
        day.put("14:00-16:00");
        calendarSchedule.put("1", day);
        calendarSchedule.put("2", day);
        calendarSchedule.put("3", day);
        calendarSchedule.put("4", day);
        inJson3.put("week_schedule", calendarSchedule);

        inJson3.put("schedule_plan_id", "1234567890");
        inJson3.put("schedule_plan_name", "schedule_plan_name1");
        inJson3.put("schedule_plan_type", 1);
        inJson3.put("app_type", 1);
        inJson3.put("start_time", "20190101");
        inJson3.put("end_time", "20190102");
        int ret3 = MegDevRules.addSchedulePlan(megDevice2, inJson3.toString());
        res.put("status3", ret3);
        
        StringBuilder outJsonStr2 = new StringBuilder();
        JSONObject inJson2 = new JSONObject();

        inJson2.put("offset", 0);
        inJson2.put("size", 10);
        inJson2.put("app_type", 0);
        int ret2 = MegDevRules.querySchedulePlan(megDevice, inJson2.toString(), outJsonStr2);
        res.put("status2", ret2);
        res.put("outPut2", outJsonStr2.toString());
		return res;
		
	}
	@Override
	public Map<String,Object> personQuery(Map map) throws Exception{
		Map<String,Object> res = new HashMap<String,Object>();
		try {
		String sn = map.remove("sn_code").toString();
		StringBuilder outJsonStr = new StringBuilder();
        JSONObject inJson = new JSONObject(map);
        inJson.put("get_feature", false);
        
        MegDevice megDevice = SDKInitUnit.getDevice(sn,MegFaceManager.getModuleName());
        if(megDevice == null) {
			res.put("status", 8);
			res.put("outPut", "无设备连接或设备连接初始化失败");
			return res;
		}
        int ret = MegFaceManager.queryPerson(megDevice, inJson.toString(), outJsonStr);
        JSONObject outJson = new JSONObject(outJsonStr.toString());
        outJson.put("sn_code", sn);
//        log.info("outJsonStr:"+outJson.toString());
        res.put("status", ret);
        res.put("type", "query_person");
        res.put("outPut", outJson.toString());
        return res;
        }catch (IOException e) {
            e.printStackTrace();
            res.put("status", 504);
            res.put("type", "query_person");
            res.put("outPut", "查询参数错误");
            return res;
        }
        
	}
	
	@Override
	public Map<String,Object> personAdd(Map map) throws Exception{
		Map<String,Object> res = new HashMap<String,Object>();
		Date date = new Date();
		String picName = date.getTime()+"";
		String sn = map.remove("sn_code").toString();
		String type = map.remove("image_type").toString();
		String pictureUrl = map.remove("image_url").toString();
		Map personInfo = (Map)map.get("person_info");
		String pictureCode = personInfo.get("code").toString();
		String rootPath = System.getProperty("user.dir");
		String pathFile = rootPath + "/data/"+picName+"_"+pictureCode+"."+type;
		String pathFile590 = rootPath + "/data/"+picName+"_"+pictureCode+"_590."+type;
		String usedPath = pathFile;
		MegDevice megDevice = SDKInitUnit.getDevice(sn,MegFaceManager.getModuleName());
		if(megDevice == null) {
			res.put("status", 8);
			res.put("outPut", "无设备连接或设备连接初始化失败");
			return res;
		}
        try {
        	//建立图片连接
        	URL url = new URL(pictureUrl);
        	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        	//设置referer白名单
        	connection.setRequestProperty("referer","http://39.103.132.160");
        	//设置请求方式
        	connection.setRequestMethod("GET");
        	//设置超时时间
        	connection.setConnectTimeout(10*1000);
        	//输入流
        	InputStream stream = connection.getInputStream();
        	//写文件
        	int len = 0;
        	byte[] test = new byte[1024];
            FileOutputStream fos;
            fos = new FileOutputStream(pathFile, true);
            //以流的方式上传
            while ((len =stream.read(test)) !=-1){
                fos.write(test,0,len);
            }
          //记得关闭流，不然消耗资源
            stream.close();
            fos.close();
          //获取分辨率
            File file = new File(pathFile);
            if(file.length()>0) {
            	FileInputStream fileInputStream = new FileInputStream(file);
            	BufferedImage sourceImg = ImageIO.read(fileInputStream);
            	int width = sourceImg.getWidth();
            	int height = sourceImg.getHeight();
            	log.info("img_width:"+width);
            	log.info("img_height:"+height);
            	fileInputStream.close();
            	//修改图片尺寸
            	if(width>1080 || height>1080) {
            		Thumbnails.of(pathFile).size(944,1024).toFile(pathFile590);
            		usedPath = pathFile590;
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
            res.put("status", 504);
            res.put("type", "add_person");
            res.put("outPut", "图片下载失败");
            return res;
        }
        
		StringBuilder outJsonStr = new StringBuilder();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		Map<String,Object> faceData = new HashMap<String,Object>();

        byte[] data = null;
        try
        {
            data = getContent(usedPath);
        }
        catch (IOException e)
        {
            log.error("Fail to read file!");
        }
        Pointer ptr = new Memory(data.length);
        ptr.write(0, data, 0, data.length);
        log.info("data.length:"+data.length);
        dataMap.put("data_size", data.length);
        dataMap.put("image_type", type);
        dataMap.put("img_name", pictureCode+"addperson");
        dataList.add(dataMap);
        faceData.put("data", dataList);
        faceData.put("data_type", 0);
        faceData.put("save_image", true);
        faceData.put("feature_version", "abc");
        map.put("face_data", faceData);
        
        MegFaceManager.BinDataStruct.ByValue binData = new MegFaceManager.BinDataStruct.ByValue();
        MegFaceManager.BinDataStruct.ByValue binData1 = new MegFaceManager.BinDataStruct.ByValue();
        MegFaceManager.BinDataStruct.ByValue binData2 = new MegFaceManager.BinDataStruct.ByValue();

        MegFaceManager.PersonFaceStruct personFaces = new MegFaceManager.PersonFaceStruct();
        personFaces.faces[0] = binData;
        personFaces.faces[0].data_size = data.length;
        personFaces.faces[0].data = ptr;
        personFaces.faces[1] = binData1;
        personFaces.faces[1].data_size = 0;
        personFaces.faces[1].data = null;
        personFaces.faces[2] = binData2;
        personFaces.faces[2].data_size = 0;
        personFaces.faces[2].data = null;

        int ret = MegFaceManager.addPerson(megDevice, new JSONObject(map).toString(), personFaces, outJsonStr);
        if(ret == 0) {
        	File fileDelete = new File(usedPath);
        	fileDelete.delete();
        }
		res.put("status", ret);
        res.put("type", "add_person");
        res.put("outPut", outJsonStr.toString());
        return res;
        
	}
	@Override
	public Map<String,Object> personUpdate(Map map) throws Exception{
		Map<String,Object> res = new HashMap<String,Object>();
		Date date = new Date();
		String picName = date.getTime()+"";
		String sn = map.remove("sn_code").toString();
		MegDevice megDevice = SDKInitUnit.getDevice(sn,MegFaceManager.getModuleName());
		if(megDevice == null) {
			res.put("status", 8);
			res.put("outPut", "无设备连接或设备连接初始化失败");
			return res;
		}
		StringBuilder outJsonStr = new StringBuilder();
		boolean hasFace = map.containsKey("image_url");
		if(hasFace) {
			
		}
		String type = map.remove("image_type").toString();
		String pictureUrl = map.remove("image_url").toString();
		String pictureCode = map.get("person_id").toString();
		String rootPath = System.getProperty("user.dir");
		String pathFile = rootPath + "/data/"+picName+"_"+pictureCode+"."+type;
		String pathFile590 = rootPath + "/data/"+picName+"_"+pictureCode+"_590."+type;
		String usedPath = pathFile;
		
        try {
        	//建立图片连接
        	URL url = new URL(pictureUrl);
        	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        	//设置referer白名单
        	connection.setRequestProperty("referer","http://39.103.132.160");
        	//设置请求方式
        	connection.setRequestMethod("GET");
        	//设置超时时间
        	connection.setConnectTimeout(10*1000);
        	//输入流
        	InputStream stream = connection.getInputStream();
        	//写文件
        	int len = 0;
        	byte[] test = new byte[1024];
            FileOutputStream fos;
            fos = new FileOutputStream(pathFile, true);
            //以流的方式上传
            while ((len =stream.read(test)) !=-1){
                fos.write(test,0,len);
            }
          //记得关闭流，不然消耗资源
            stream.close();
            fos.close();
          //获取分辨率
            File file = new File(pathFile);
            if(file.length()>0) {
            	FileInputStream fileInputStream = new FileInputStream(file);
            	BufferedImage sourceImg = ImageIO.read(fileInputStream);
            	int width = sourceImg.getWidth();
            	int height = sourceImg.getHeight();
            	log.info("img_width:"+width);
            	log.info("img_height:"+height);
            	fileInputStream.close();
            	//修改图片尺寸
            	if(width>1080 || height>1080) {
            		Thumbnails.of(pathFile).size(944,1024).toFile(pathFile590);
            		usedPath = pathFile590;
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
            res.put("status", 504);
            res.put("type", "update_person");
            res.put("outPut", "图片下载失败");
            return res;
        }
        
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		Map<String,Object> faceData = new HashMap<String,Object>();

        byte[] data = null;
        try
        {
            data = getContent(usedPath);
        }
        catch (IOException e)
        {
            log.error("Fail to read file!");
        }
        Pointer ptr = new Memory(data.length);
        ptr.write(0, data, 0, data.length);
        log.info("data.length:"+data.length);
        dataMap.put("data_size", data.length);
        dataMap.put("image_type", type);
        dataMap.put("img_name", pictureCode+"addperson");
        dataList.add(dataMap);
        faceData.put("data", dataList);
        faceData.put("data_type", 0);
        faceData.put("save_image", true);
        faceData.put("feature_version", "abc");
        map.put("face_data", faceData);
        
        MegFaceManager.BinDataStruct.ByValue binData = new MegFaceManager.BinDataStruct.ByValue();
        MegFaceManager.BinDataStruct.ByValue binData1 = new MegFaceManager.BinDataStruct.ByValue();
        MegFaceManager.BinDataStruct.ByValue binData2 = new MegFaceManager.BinDataStruct.ByValue();

        MegFaceManager.PersonFaceStruct personFaces = new MegFaceManager.PersonFaceStruct();
        personFaces.faces[0] = binData;
        personFaces.faces[0].data_size = data.length;
        personFaces.faces[0].data = ptr;
        personFaces.faces[1] = binData1;
        personFaces.faces[1].data_size = 0;
        personFaces.faces[1].data = null;
        personFaces.faces[2] = binData2;
        personFaces.faces[2].data_size = 0;
        personFaces.faces[2].data = null;

        int ret = MegFaceManager.addPerson(megDevice, new JSONObject(map).toString(), personFaces, outJsonStr);
        if(ret == 0) {
        	File fileDelete = new File(usedPath);
        	fileDelete.delete();
        }
		res.put("status", ret);
        res.put("type", "add_person");
        res.put("outPut", outJsonStr.toString());
        return res;
        
	}
    
    public static class MediaDeviceAlarmCb implements MegDeviceAlarm.MediaDeviceAlarmCb {
        @Override
        public void invoke(MegCommon.AlarmMsg.ByReference alarmMsg, Pointer userArg) {
        	AlarmSnCodeCbArg arg = new MegCommon.AlarmSnCodeCbArg(userArg);
        	arg.read();
            byte[] info = alarmMsg.jsonInfo.getByteArray(0, alarmMsg.infoLen);
            String infoStr = new String(info);
            log.info("callbackPointer: " + userArg);
            log.info("argCallback: " + arg.callback+",argSn:"+arg.sn);
//            log.info("binDataNumSize: " + alarmMsg.data.binDataNumSize);
//            log.info("type: " + alarmMsg.data.binDataInfos.type);
			try {
				JSONObject outJson = new JSONObject(infoStr);
				outJson.put("sn_code", arg.sn);
				log.info("info: " + outJson.toString());
				if(arg.callback.length()>1) {
					
//					URL url = new URL("http://ksjxisv.tfkuding.com/callback/240_handle");
					URL url = new URL(arg.callback);
					
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					// 设置请求方式
					connection.setRequestMethod("POST");
					// 设置是否向HttpURLConnection输出
					connection.setDoOutput(true);
					// 设置是否从httpUrlConnection读入
					connection.setDoInput(true);
					// 设置是否使用缓存
					connection.setUseCaches(false);
					connection.setConnectTimeout(10 * 1000);
					//设置参数类型是json格式
					connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
					connection.setRequestProperty("Connection", "Keep-Alive");
					connection.connect();
					
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
					writer.write(outJson.toString());
					writer.close();
					
					int responseCode = connection.getResponseCode();
					if(responseCode == HttpURLConnection.HTTP_OK) {
						String result="";
						//定义 BufferedReader输入流来读取URL的响应
						BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						String line;
						while ((line = in.readLine()) != null) {
							result += line;
						}
						log.info(result);
					}
					connection.disconnect();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("请求结果读取异常");
			}
        }
    }
}
