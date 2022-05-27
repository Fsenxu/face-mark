package com.heqichao.springBootDemo.base.util;

import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by heqichao on 2018-2-12.
 */
public class StringUtil {

    public static boolean isEmpty(String context){
        if(context==null || "".equals(context)){
            return true;
        }
        return  false;
    }

    public static boolean isNotEmpty(String context){
        return !isEmpty(context);
    }
    
    // Add by Muzzy Xu.
    public static Integer objectToInteger(Object obj) {
    	if(obj == null) {
    		return null;
    	}
    	try {
    		return Integer.valueOf(obj.toString());
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
    }
    /**
     * 字符串转小数
     * @param str
     * @return
     */
    public static Double string2Double(String str) {
    	if(isEmpty(str)) {
    		return null;
    	}
    	try {
    		return Double.parseDouble(str);
    	} catch (Exception e) {
//			e.printStackTrace();
    		return null;
    	}
    }
    
    public static Integer getIntegerByMap(Map map,String key) {
    	if(map == null || map.size() == 0) {
    		return null;
    	}
    	try {
    		return Integer.valueOf(map.get(key).toString().trim());
    	} catch (Exception e) {
//			e.printStackTrace();
    		return null;
    	}
    }


	public static Float getFloatByMap(Map map,String key) {
		if(map == null || map.size() == 0) {
			return null;
		}
		try {
			return Float.valueOf(map.get(key).toString().trim());
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}

    public static BigDecimal getBigDecimalByMap(Map map,String key) {
    	if(map.size() == 0) {
    		return null;
    	}
    	try {
    		return new BigDecimal(map.get(key).toString().trim());
    	} catch (Exception e) {
//			e.printStackTrace();
    		return null;
    	}
    }

    
    public static String getStringByMap(Map map,String key) {
    	if(map.size() == 0) {
    		return null;
    	}
    	try {
    		return map.get(key).toString();
    	} catch (Exception e) {
//    		e.printStackTrace();
    		return null;
    	}
    }
    public static Boolean getBooleanByMap(Map map,String key) {
    	if(map.size() == 0) {
    		return null;
    	}
    	try {
    		return Boolean.parseBoolean(map.get(key).toString());
    	} catch (Exception e) {
//    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * 字符串转字符串数组（每两位字符）
     * @param str
     * @return String[]
     */
    public static String[] string2StringArray(String str) {
	    int m=str.length()/2;
	    if(m*2<str.length()){
	    	m++;
	    }
	    String[] strs=new String[m];
	    int j=0;
	    for(int i=0;i<str.length();i++){
		    if(i%2==0){
		    	strs[j]=""+str.charAt(i);
		    }else{
		    	strs[j]=strs[j]+str.charAt(i);
		    	j++;
		    }
	    }
	    return strs;
	}

	/**
	 * 合并字符串数组
	 * @param str
	 * @param frex 分割符
	 * @return
	 */
	public static String getString(String[] str,String frex){
		String returnStr ="";
		if(str!=null && str.length >0){
			for(String s :str){
				returnStr=returnStr+s+frex;
			}
			if(StringUtil.isNotEmpty(frex)){
				returnStr=returnStr.substring(0,returnStr.length()-frex.length());
			}
		}
		return  returnStr;
	}



	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HELLO_WORLD->HelloWorld
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name, boolean isUpperCase) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			return toCase(name.substring(0, 1), isUpperCase) + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel :  camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (StringUtil.isEmpty(camel)) {
				continue;
			}
			// 处理真正的驼峰片段
			if (result.length() == 0) {
				// 第一个驼峰片段，全部字母都小写
				result.append(toCase(camel.substring(0, 1), isUpperCase));
				if(camel.length()>1){
					result.append(camel.substring(1).toLowerCase());
				}
			} else {
				// 其他的驼峰片段，首字母大写
				result.append(camel.substring(0, 1).toUpperCase());
				if(camel.length()>1){
					result.append(camel.substring(1).toLowerCase());
				}
			}
		}
		return result.toString();
	}

	private static String toCase(String s, boolean isUpperCase){
		if(isUpperCase){
			return s.toUpperCase();
		}else{
			return s.toLowerCase();
		}
	}
//	public static void main(String[] args) throws Exception {
//		String str="/%E9%A3%8E%E5%8F%B6%E7%94%B5%E6%B5%81";//这个utf-8编码
//		String ss=URLDecoder.decode(str,"UTF-8"); 
//		System.out.println(ss);//
//	}


	public static String[] getStrArr(Object obj) throws Exception {
		if(obj ==null){
			return new String[]{};
		}
		if (obj instanceof String){
			return new String[]{(String) obj};
		}else if(obj instanceof String[]){
			return (String[]) obj;
		}
		throw new Exception("数据异常");
	}
	
	/**
	 * 只含有英文和数字
	 * @param str
	 * @return
	 */
	public static boolean isLetterDigit(String str) {
    	String regex = "^[a-z0-9A-Z]+$";
    	return str.matches(regex);
    }
	
	/**
	 * 字符串去除所有特殊字符
	 * @param str
	 * @return
	 */
	public static String  stringFilter(String str) {
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
		Pattern p = Pattern.compile(regEx); 
        Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	/**
	 * json对象字符串转map
	 * @param obj
	 * @return
	 */
	public static Map  jsonString2Map(Object obj) {
		String body = JSONObject.toJSONString(obj);
        Object parse = JSON.parse(body);
		return JSONObject.parseObject(parse.toString(), Map.class);
	}
}
