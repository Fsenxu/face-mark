package com.heqichao.springBootDemo.base.util;


import com.heqichao.springBootDemo.base.exception.ResponeException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * DateUtil工具类
 * @author HUSQ
 *
 */
public class DateUtil {
	
	private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat longDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static SimpleDateFormat dateShortFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 在日期上增加天数
	 * @param sDate 日期
	 * @param day 天数，负数则减去相应的天数
	 * @return Date
	 */
	public static Date addDay(Date sDate, int day){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sDate); 
		
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 在日期上增加月数
	 * @param sDate 日期
	 * @param month 月数，负数则减去相应的月数
	 * @return Date
	 */
	public static Date addMonth(Date sDate, int month){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sDate); 
		
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}
	
	/**
	 * 在日企上增加分钟数
	 * @param sDate 日期
	 * @param minute 分钟数，负数则减去相应的分钟数
	 * @return
	 * @author liangguojian
	 */
	public static Date addMinute(Date sDate, int minute){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sDate); 
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
	/**
	 * 在日期上增加秒数
	 * @param sDate 日期
	 * @param second 秒数，负数则减去相应的秒数
	 * @return
	 * @author zhoulumin
	 */
	public static Date addSecond(Date sDate, int second){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sDate); 
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}


	

	/**
	 * 判断时间格式是否正确
	 * @param date
	 * @return
	 */
	public static boolean checkDateValid(String date) {
		if (StringUtil.isEmpty(date)){
			return false;
		}
		try {
			Date date1 =parasDate(date);
			return dateToString(date1).equals(date);
		}catch (Exception e){
			return false;
		}

	}


	/**
	 * 将日期转换为Str
	 * @param date 日期
	 * @return
	 */
	public static String dateToString(Date date){
		if(date==null){
			return "";
		}
		synchronized (dateFormat) {
			return dateFormat.format(date);
		}
	}
	/**
	 * 将日期转换为Str(只转换日期)
	 * @param date 日期
	 * @return
	 */
	public static String dateToShortString(Date date){
		synchronized (dateShortFormat) {
			return dateShortFormat.format(date);
		}
	}
	
	/**
	 * 将日期转换为毫秒的格式
	 * @param date 日期
	 * @return
	 */
	public static String dateToLongString(Date date){
		synchronized (longDateFormat) {
			return longDateFormat.format(date);
		}
	}
	
	/**
	 * 将日期转换为指定格式的Str
	 * @param date 日期
	 * @param format 格式
	 * @return
	 */
	public static String dateToString(Date date, String format){
		if(date==null){
			return "";
		}
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 将str转换为时间
	 * @param datestr 字符串
	 * @return
	 */
	public static Date parasDate(String datestr){
		if(StringUtil.isEmpty(datestr)){
			return null;
		}
		try {
				if(datestr.length()==10){
					synchronized (dateShortFormat) {
						return dateShortFormat.parse(datestr);
					}
				}
				synchronized (dateFormat) {
					return dateFormat.parse(datestr);
				}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ResponeException("datestr:"+datestr +"is error");
		}
	}
	
	/**
	 * 将str转换为开始时间
	 * @param datestr 字符串
	 * @return
	 */
	public static Date parasStartDate(String datestr){
		if(StringUtil.isEmpty(datestr)){
			return null;
		}
		if(datestr.length()==10){
			datestr=datestr+" 00:00:00";
		}
		return parasDate(datestr);
	}
	/**
	 * 将str转换为结束时间
	 * @param datestr 字符串
	 * @return
	 */
	public static Date parasEndDate(String datestr){
		if(StringUtil.isEmpty(datestr)){
			return null;
		}
		if(datestr.length()==10){
			datestr=datestr+" 23:59:59";
		}
		return parasDate(datestr);
	}
	
	/**
	 * 将str转换为时间
	 * @param datestr 字符串
	 * @param format 字符串格式
	 * @return
	 */
	public static Date parasDate(String datestr,String format){
		if(StringUtil.isEmpty(datestr)){
			return null;	
		}
		try {
			return new SimpleDateFormat(format).parse(datestr);
		} catch (ParseException e) {
	//		e.printStackTrace();
			throw new ResponeException("datestr:"+datestr +"is error");
		}
	}
	
	/**
	 * 获取  参数日期  所在年份的 最小时间
	 * @param date 需要获取年份开始时间的日期
	 * @return 所在年份最小时间
	 */
	public static Date getYearMINDateTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取 参数日期 所在年份的最大时间
	 * @param date  需要获取年份最大时间的日期
	 * @return date 所在年份最大时间
	 */
	public static Date getYearMAXDateTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取  参数日期  所在月份的 最小时间
	 * @param date 需要获取月份开始时间的日期
	 * @return 所在月的最小时间
	 */
	public static Date getMonthMINDateTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取  参数日期 所在月份的最大时间
	 * @param date 需要获取月份最大时间的日期
	 * @return 所在月的最大时间
	 */
	public static Date getMonthMAXDateTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取  参数日期  所在天的 最小时间
	 * @author liangyuxiang
	 * @param date 需要获取天开始时间的日期
	 * @return 所在天最小时间
	 */
	public static Date getDayMINDateTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取 参数日期 所在天的最大时间
	 * @author liangyuxiang
	 * @param date  需要获取天最大时间的日期
	 * @return date 所在天最大时间
	 */
	public static Date getDayMAXDateTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		date = calendar.getTime();
		return date;
	}

	private static int day = 1000 * 3600 * 24;

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 相差天数
	 * @author liangyuxiang
	 */
	public static int daysBetween(Date date1, Date date2) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		Long betweenDays = Math.abs((time2 - time1)) / day;
		return betweenDays.intValue();

	}
	/**
	 * 计算两个日期之间相差的分钟数
	 *
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 相差天数
	 * @author liangyuxiang
	 */
	public static int minBetween(Date date1, Date date2) {
		if(date1==null || date2==null){
			throw new ResponeException("要比较的时间不能为空");
		}
		
		long diff = 0l;
		if(date1.getTime()>date2.getTime()){
			diff = date1.getTime() - date2.getTime();   
		}else{
			diff = date2.getTime() - date1.getTime();   
		}
		Long days = diff / (1000 * 60);     
		return days.intValue();

	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param dateStr1 时间1，格式为yyyy-MM-dd 或者yyyy-MM-dd HH:mm:ss的字符串
	 * @param dateStr2 时间2，格式为yyyy-MM-dd 或者yyyy-MM-dd HH:mm:ss的字符串
	 * @return 相差天数
	 * @author liangyuxiang
	 */
	public static int daysBetween(String dateStr1, String dateStr2) {
		try {
			Date d1 = dateShortFormat.parse(dateStr1);
			Date d2 = dateShortFormat.parse(dateStr2);
			return daysBetween(d1, d2);
		} catch (ParseException e) {
			throw new ResponeException("dateStr:" + dateStr1 + " or " + dateStr2 + " is error");
		}
	}
	
	/**
	 * 在日期上增加月数并且设置成该月的第一天的0时0分0秒
	 * @param sDate 日期
	 * @param month 月数，负数则减去相应的月数
	 * @return Date
	 */
	public static Date addMonthFirstDay(Date sDate, int month){
		
		Date date = DateUtil.getMonthMINDateTime(sDate);
		date = DateUtil.addMonth(date, month);
		return date;
	}
	
	/**
	 * 获取本周内的开始时间
	 * @param date 
	 * @return
	 */
	public static Date getOneWeekBefore(Date date){
		Calendar cal =Calendar.getInstance();
		date = DateUtil.getDayMINDateTime(date);
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); 
		return cal.getTime();
	}
	
	/**
	 * 获取本月内的开始时间
	 * @param date 
	 * @return
	 */
	public static Date getOneMonthBefore(Date date){
		return DateUtil.addMonthFirstDay(date, 0);
	}
	
	/**
	 * 获取三个月内的开始时间
	 * @param date 
	 * @return
	 */
	public static Date getThreeMonthBefore(Date date){
		return DateUtil.addMonthFirstDay(date, -2);
	}
	
	/**
	 * 获取六个月内（半年内）的开始时间
	 * @param date 
	 * @return
	 */
	public static Date getSixMonthBefore(Date date){
		return DateUtil.addMonthFirstDay(date, -5);
	}
	
	/**
	 * 获取本年内 的开始时间
	 * @param date 
	 * @return
	 */
	public static Date getOneYearBefore(Date date){
		return DateUtil.addMonthFirstDay(date, -11);
	}
	
	/**
	 * 对分钟数向下取整，"10:58:45"->> "10:55:00", "12:04:19" ->> "12:00:00"
	 * @param date 
	 * @return
	 */
	public static Date getLastMinutes(Date date){
		Date rDate = new Date();
		rDate.setTime(date.getTime());
		int min = rDate.getMinutes();
		//对个位向下取整处理
		if(((min%10)>=0) && ((min%10)<=4)){
			min = ((min/10)*10) + 0;
		}else{
			min = ((min/10)*10) + 5;
		}
		rDate.setMinutes(min);
		rDate.setSeconds(0);
		return rDate;
	}
	
	
	/**
	 * 时间转换成字符串
	 * @param timestamp  时间
	 * @param dateFormat  时间格式
	 * @return  时间的字符串格式
	 */
	public static String timestamp2String(Timestamp timestamp,
			String dateFormat) {
		SimpleDateFormat format=new SimpleDateFormat(dateFormat);
		return format.format(timestamp);
	}
	
	/**
	 * 某天的结束时间
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getEndTime(Date date) {
		long resultMis = 0;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String str = format.format(date);
			Date d2 = format.parse(str);
			int dayMis = 1000 * 60 * 60 * 24;
			long curMillisecond = d2.getTime();
			resultMis = curMillisecond + (dayMis - 1);
		} catch (ParseException e) {
			throw new ResponeException("时间转换失败" );
		}
		return new Date(resultMis);
	}

	public static String getStartTime(String dateStr){
		if(StringUtil.isNotEmpty(dateStr)){
			dateStr=dateStr+" 00:00:00";
		}
		return dateStr;
	}

	public static String getEndTime(String dateStr){
		if(StringUtil.isNotEmpty(dateStr)){
			dateStr=dateStr+" 23:59:59";
		}
		return dateStr;
	}

	/**
	 * 获取当前时间所属季度
	 * @param sDate
	 * @return
	 */
	public static String getQuarter(Date sDate) {
		 Calendar cal = Calendar.getInstance();
		 if(sDate != null) {
			 cal.setTime(sDate); 
		 }
         int m = cal.get(Calendar.MONTH) + 1;
         String quarter = " ";
         if (m >= 1 && m <= 3) {
         quarter = "01";
         }
         if (m >= 4 && m <= 6) {
         quarter = "02";
         	}
         	if (m >= 7 && m <= 9) {
         	 quarter = "03";
         	}
         	if (m >= 10 && m <= 12) {
         	quarter = "04";
         }
         	return quarter;
	}


	public static String getYearWeek(String dateStr){
		//日历
		Calendar c =  new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//每周从周一开始
		c.setMinimalDaysInFirstWeek(7);
		Date dateTime=parasDate(dateStr);
		c.setTime(dateTime);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		int year = c.get(Calendar.YEAR);
		c.add(Calendar.DAY_OF_MONTH, +7);
		//如果当前周数大于7天后的周数，说明这周是今年的最后一周
		//且年数与7天后的年数相等，说明是跨年
		if(week > c.get(Calendar.WEEK_OF_YEAR) && year == c.get(Calendar.YEAR) ){
			year=year-1;
		}

		return week>9?year+"-"+week:year+"-0"+week;
	}
	
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	/* 
     * 将时间戳转换为时间
     */
    public static String stampToDateString(String s){
        String res;
        long lt = new Long(s);
        Date date = new Date(lt);
        res = dateFormat.format(date);
        return res;
    }
    /**
     * 将时间戳转换为时间
     * @param s 时间戳字符串
     * @return 日期
     */
    public static Date stampToDate(String s){
    	long lt = new Long(s);
    	Date date = new Date(lt);
    	return date;
    }
    /* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) throws ParseException {
        String res;
        Date date = dateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    
    /**
     * 时间比较时间戳
     * @param date 当前时间
     * @param s 时间戳
     * @return true就是时间戳已过期
     * @throws Exception
     */
    public static boolean dateCompareStamp(Date date,String s) throws Exception {
    	long lt = new Long(s);
    	long ts = date.getTime();
    	return lt < ts;
    }
}
