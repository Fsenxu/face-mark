package com.heqichao.springBootDemo.base.control;

import com.heqichao.springBootDemo.base.exception.ResponeException;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.util.FileUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-14.
 */
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected   Integer getIntegerParam( String key){
        Integer integer =null;
        Map map = RequestContext.getContext().getParamMap();
        if(map!=null && StringUtil.isNotEmpty(key)){
            Object value =map.get(key);
            if(value!=null){
                if(value instanceof Integer){
                    integer= (Integer) value;
                }else if(value instanceof String){
                    if(StringUtil.isNotEmpty((String)value)){
                        try{
                            integer=  Integer.parseInt(((String) value).trim());
                        }catch (Exception e){
                            logger.error("getIntegerParam error ! "+key,e);
                            throw  new ResponeException("获取数值型参数有误："+key,e);
                        }
                    }

                }
            }
        }
        return integer;
    }

    protected  Map getParamMap(){
        Map map = RequestContext.getContext().getParamMap();
        if(map==null){
            map =new HashMap();
        }
        return  map;
    }


    /**
     * xlsx
     * @param map sheelName : data
     * @param fileName 文件名
     * @param titles 属性中文名称
     * @param attrCode data属性key
     */
    protected void exportX(Map<String,List> map, String fileName,String[] titles,String[] attrCode){
    	if(map!=null){
    		
    		FileOutputStream fos = null;
    		File file =null;
    		try{
    			Iterator entries = map.entrySet().iterator();
    			file = FileUtil.createTempDownloadFile(fileName+".xlsx");
    			fos= new FileOutputStream(file);
    			// 声明一个工作薄
    			SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
//    			while (entries.hasNext()) {
//    				Map.Entry entry = (Map.Entry) entries.next();
//    				String key = (String) entry.getKey();
//    				List<Map> attrList = (List<Map>) entry.getValue();
//    				String[] keys =key.split(ModelService.EXCEL_NAME_SPLIT);
//    				String modelName=key;
//    				if(keys.length >1){
//    					modelName = keys[1];
//    				}
//    				ExcelWriter.exportX(workbook,modelName,titles,attrList,attrCode);
//    			}
    			workbook.write(fos);
    		}catch (Exception e){
    			e.printStackTrace();
    		}finally {
    			if(fos!=null){
    				try {
    					fos.close();
    					download(file);
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    		}
    		
    		
    	}
    }

    
    protected void download(File file){
        if(file == null){
            return;
        }
        BufferedInputStream fis = null;
        BufferedOutputStream toClient = null;
        try {
            HttpServletResponse response=RequestContext.getContext().getResponse();
            fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // 清空response
            response.reset();
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("gb2312"), "ISO8859-1"));
            toClient.write(buffer);
            toClient.flush();
        }catch (Exception err){
            throw new ResponeException(err);
        } finally{
            try {
                if (toClient != null) {
                    toClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            FileUtil.deleteFile(file);
        }
    }
}
