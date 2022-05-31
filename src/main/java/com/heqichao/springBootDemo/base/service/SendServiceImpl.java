package com.heqichao.springBootDemo.base.service;

import static com.heqichao.springBootDemo.megprotocol.Utils.CommonFunction.getContent;
import static com.heqichao.springBootDemo.megprotocol.Utils.CommonFunction.waitMs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
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
import com.heqichao.springBootDemo.megprotocol.SDKInitUnit;
import  com.heqichao.springBootDemo.megprotocol.MegCommon;
import com.heqichao.springBootDemo.megprotocol.MegDeviceStorage;
import com.heqichao.springBootDemo.megprotocol.MegDeviceAlarm;
import com.sun.jna.Memory;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

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
	public Map<String,Object> testGroup() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuilder outJsonStr = new StringBuilder();
		JSONObject inJson = new JSONObject();
		inJson.put("offset", 0);
        inJson.put("size", 10);
        inJson.put("get_feature", false);
        int ret = MegDeviceAlarm.queryAlarmHistory(SDKInitUnit.getDevice(), inJson.toString(), outJsonStr);
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
        int ret2 = MegDeviceStorage.getImage(SDKInitUnit.getDevice(), inJson.toString(), imgSize, imgData);
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
            //修改图片尺寸
            Thumbnails.of(pathFile).size(944,1024).toFile(pathFile590);
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
            data = getContent(pathFile590);
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

        int ret = MegFaceManager.addPerson(SDKInitUnit.getDevice(), new JSONObject(map).toString(), personFaces, outJsonStr);
        
		res.put("status", ret);
        res.put("type", "add_person");
        res.put("outPut", outJsonStr.toString());
        return res;
        
	}
    
    
    private static class MediaDeviceAlarmCb implements MegDeviceAlarm.MediaDeviceAlarmCb {
        @Override
        public void invoke(MegCommon.AlarmMsg.ByReference alarmMsg, Pointer userArg) {
        	UserStruct user = new UserStruct(userArg);
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
    public static class UserStruct extends Structure{
    	public UserStruct() {
    		
    	}
        public UserStruct(Pointer userArg) {
			// TODO Auto-generated constructor stub
		}
		public int id;
        public WString name;
        public int age;
		
    }
}
