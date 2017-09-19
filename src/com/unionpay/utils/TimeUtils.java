package com.unionpay.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unionpay.code.core.Constants;
import com.unionpay.code.entity.MapItem;

public class TimeUtils {

	static private Logger logger = LoggerFactory.getLogger(TimeUtils.class);

	static private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	static private SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
	static private SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss:SSS");
	static private SimpleDateFormat fullFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	static public List<String> getDateRangeList(Date start, Date stop) {
		if (start.getTime() - stop.getTime() >= 0) {
			return null;
		}

		List<String> list = new ArrayList<String>();

		String startString = toDateString(start);
		String stopString = toDateString(stop);
		// logger.info(startString);
		// logger.info(stopString);

		while (true) {
			list.add(startString);
			// logger.info("haha" + startString);

			if (startString.equals(stopString)) {
				break;
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(toDate(startString));
			cal.add(Calendar.DATE, 1);
			startString = toDateString(cal.getTime());
		}

		return list;
	}

	static public Date toDate(String dateString) {
		Date date = null;
		try {
			if (dateString.length() != 8) {

				throw new ParseException(dateString, 0);
			}
			date = dateFormat.parse(dateString);
		} catch (Exception e) {
			logger.error("Exception",e);
		}
		return date;
	}

	// ////////////////////////////////////////////////////////////////////////
	static public String toDateTimeString(long ts) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(ts);
		return timestampFormat.format(cal.getTime());
	}

	static public String toDateTimeString(Date date) {
		return timestampFormat.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////
	static public String toDateString(Date date) {
		return dateFormat.format(date);
	}

	static public Date toTime(String TimeString) {
		Date date = null;
		try {
			if (TimeString.length() != 8) {

				throw new ParseException(TimeString, 0);
			}
			date = timeFormat.parse(TimeString);
		} catch (Exception e) {
			logger.error("Exception",e);
		}
		return date;
	}

	static public String toTimeString(Date date) {
		return timeFormat.format(date);
	}

	public static Date addDate(Date stopTime, int amount) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(stopTime);
		cal.add(Calendar.DATE, amount);

		return cal.getTime();
	}

	public static String addDate(String stopTime, int amount) {
		Date date=null;
		try {
			date = dateFormat.parse(stopTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, amount);

		return dateFormat.format(cal.getTime());
	}
	
	/**
	 * 根据起始时间和结束时间，生成每天0点的时间序列
	 * 
	 * @param startTime
	 * @param stopTime
	 * @return
	 */
	public static List<Date> getDateSequence(Date startTime, Date stopTime) {

		List<Date> list = new ArrayList<Date>();

		if (startTime.getTime() > stopTime.getTime()) {
			return list;
		}

		Calendar cal = Calendar.getInstance();

		cal.setTime(stopTime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);

		Date lastDate = cal.getTime();

		cal.setTime(startTime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date currentDate = cal.getTime();

		while (currentDate.before(lastDate)) {
			list.add(currentDate);
			cal.add(Calendar.DATE, 1);
			currentDate = cal.getTime();
		}

		list.add(lastDate);

		return list;
	}
	
    /**
	 * 功能：将输入时间进行N日内计算（前几日+N，后几日-N）
	 * @param basicDate ：输入日期
	 * @param ：N日
	 * @return ：第N日日期
	 * @throws ParseException 
	 */
    public static Date nDate(Date basicDate,int n)  {
		basicDate = new Date(basicDate.getTime() + (long)n * 24 * 60 * 60 * 1000);
        return basicDate;
   	}


    /**
     * 日期间隔天数
     * @param beginDateStr，yyyyMMdd
     * @param endDateStr，yyyyMMdd
     * @return
     */
    public static long getDayDiff(String beginDateStr,String endDateStr){
    	long timediff=0;
    	if(beginDateStr==null||
    			endDateStr==null||
    			Constants.NULL_STRING_FLAG2.equals(beginDateStr)||
    			Constants.NULL_STRING_FLAG2.equals(endDateStr))
    		return Constants.NULL_INT_FLAG2;
    	else if("".equals(beginDateStr)||
    			"".equals(endDateStr)||
    			Constants.NULL_STRING_FLAG.equals(beginDateStr)||
    			Constants.NULL_STRING_FLAG.equals(endDateStr)){
    		return Constants.NULL_INT_FLAG;
    	}else{
        	try {
    			Date   beginDate=beginDateStr.length()==8?dateFormat.parse(beginDateStr):fullFormat.parse(beginDateStr);
    			Date   endDate=beginDateStr.length()==8?dateFormat.parse(endDateStr):fullFormat.parse(endDateStr);
    			timediff=(endDate.getTime()-beginDate.getTime())/(24*3600*1000);
    			
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    		

    	
    	return timediff;
    	
    }
    
    /**
     * 日期间隔分钟
     * @param beginDateStr，yyyyMMdd
     * @param endDateStr，yyyyMMdd
     * @return
     */
    public static long getMinuteDiff(String beginDateStr,String endDateStr){
    	long timediff=0;
    	try {
			Date   beginDate=fullFormat.parse(beginDateStr);
			Date   endDate=fullFormat.parse(endDateStr);
			timediff=(endDate.getTime()-beginDate.getTime())/(60*1000);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(timediff==0){
    		timediff=1;
    	}
    	return timediff;
    	
    }
    
    
    public static Date strToDate(String str){
    	Date date = null;
    	try {
    		date= dateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return date;
    	
    }
    
    
	/**
	 * 比较两个时间之差
	 * @param item1
	 * @param item2
	 * @param flag,true，返回分钟，false 返回天数
	 * @return
	 */
	public static long compareTimeDiff(MapItem item1,MapItem item2,boolean flag) {
		
		if(item1!=null &&item2!=null){
			String beginDateStr = item1.getTfr_dt_dm();
			String endDateStr = item2.getTfr_dt_dm();
			
			if(Constants.NULL_STRING_FLAG.equals(beginDateStr) 
					|| Constants.NULL_STRING_FLAG.equals(endDateStr) ){
				return Constants.NULL_INT_FLAG;
			}else if(flag){
				return TimeUtils.getMinuteDiff(beginDateStr, endDateStr);
			}else{
				return TimeUtils.getDayDiff(beginDateStr, endDateStr);
			}
			
		}else{
			return Constants.NULL_INT_FLAG2;
		}
	}
	
    
	public static void main(String[] args) {
		
		
		System.out.println(getMinuteDiff("20150602120114","20150602121212"));
		System.out.println(getDayDiff("20150301","20150430"));
		System.out.println(getDayDiff("20150601121212","20150430121212"));

//		Calendar cal = Calendar.getInstance();
//		//
//		// cal.set(2012, 7, 1, 0, 0, 0);
//		// cal.set(Calendar.MILLISECOND, 0);
//
//		String s1 = "2012-08-01-00:00:00:000";
//		String s2 = "2012-08-03-00:00:00:000";
//
//		Calendar tmp = Calendar.getInstance();
//		tmp.set(2012, 7, 1, 0, 0, 0);
//		tmp.set(Calendar.MILLISECOND, 0);
//		// System.out.println(tmp.getTimeZone());
//		// System.out.println(dateTimeFormat.format(tmp.getTime()));
//
//		try {
//
//			Date d1 = timestampFormat.parse(s1);
//
//			cal.setTime(d1);
//			System.out.println(d1);
//
//			System.out.println(timestampFormat.format(d1));
//			System.out.println(cal.get(Calendar.HOUR_OF_DAY));
//
//			Date d2 = timestampFormat.parse(s2);
//			System.out.println(d2);
//			System.out.println(timestampFormat.format(d2));
//
//			List<Date> list = getDateSequence(d1, d2);
//
//			for (Date d : list) {
//				System.out.println(d);
//			}
//
//		} catch (Exception e) {
//			logger.error("Exception",e);
//		}

	}
}
