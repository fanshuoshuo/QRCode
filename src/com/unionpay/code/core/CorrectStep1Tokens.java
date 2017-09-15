/*
 * @author shuoshuofan
 */

package com.unionpay.code.core;

import com.unionpay.code.entity.MapItem;
import com.unionpay.utils.StringUtils;

public class CorrectStep1Tokens {

	private MapItem record;

	public CorrectStep1Tokens(MapItem record) {
		this.record=record;
		updateRecord();
	}
	
	public void updateRecord(){
		/*
		//1.捕捉方法映射：c=>1,m->2,manual->2,u->-1
		String item=record.getCaptureMethod();
		record.setCaptureMethod(correctCaptureMethod(item));
		
		//2.开户行机构代码，取前四位，并映射
		item=record.getIssInsIdCd();
		record.setIssInsIdCd(correctIssInsIdCd(item));
		
		item=record.getIssInsIdCdCardTp();
		record.setIssInsIdCdCardTp(record.getIssInsIdCd()+"_"+item.split("_")[1]);
		
		//3.设备手机号码range
		if(!record.getDeviceNumber().startsWith("+86")&&!record.getDeviceNumber().equals("-1")&&!record.getDeviceNoRange().startsWith("1")){
			record.setDeviceNoRange("-2");
		}
		
		//4.ip、jwd 所属省、地区、0000-0999（除0156）归为境外
		item=record.getIpProvRegionCd();
		record.setIpProvRegionCd(correctRegion(item));
		
		item=record.getIpCityRegionCd();
		record.setIpCityRegionCd(correctRegion(item));
		
		item=record.getJwdProvRegionCd();
		record.setJwdProvRegionCd(correctRegion(item));
		
		item=record.getJwdCityRegionCd();
		record.setJwdCityRegionCd(correctRegion(item));
		
		
		//5. same flag 变量校正

		item=compareRegion(record.getIpCityRegionCd(),record.getTelCityRegionCd());
		record.setIpTelCityRegionSameFlag(item);
		
		item=compareRegion(record.getIpProvRegionCd(),record.getTelProvRegionCd());
	    record.setIpTelProvRegionSameFlag(item);
		

		item=compareRegion(record.getIpCityRegionCd(),record.getDeviceNoCityRegionCd());
		record.setDeviceNoIpCityRegionSameFlag(item);
		
		item=compareRegion(record.getIpProvRegionCd(),record.getDeviceNoProvRegionCd());
	    record.setDeviceNoIpProvRegionSameFlag(item);
	    
	   
		item=compareRegion(record.getJwdCityRegionCd(),record.getIpCityRegionCd());
		record.setJwdIpCityRegionSameFlag(item);
		
		item=compareRegion(record.getJwdProvRegionCd(),record.getIpProvRegionCd());
	    record.setJwdIpProvRegionSameFlag(item);
	
		item=compareRegion(record.getJwdCityRegionCd(),record.getDeviceNoCityRegionCd());
		record.setJwdDeviceNoCityRegionSameFlag(item);
		
		item=compareRegion(record.getJwdProvRegionCd(),record.getDeviceNoProvRegionCd());
	    record.setJwdDeviceNoProvRegionSameFlag(item);
	    
		
		item=compareRegion(record.getJwdCityRegionCd(),record.getTelCityRegionCd());
		record.setJwdTelCityRegionSameFlag(item);
		
		item=compareRegion(record.getJwdProvRegionCd(),record.getTelProvRegionCd());
	    record.setJwdTelProvRegionSameFlag(item);
	    */
	    //7. 2147483647 
		
	}


	
	

	// ======================================================================================================//
	/**
	 * 捕捉方法映射：c=>1,m->2,manual->2,u->-1
	 * 
	 * @param method
	 * @return
	 */
	public String correctCaptureMethod(String method) {
		String result;
		if ("c".equals(method)) {
			result = "1";
		} else if ("m".equals(method) || "manual".equals(method)) {
			result = "2";

		} else if ("u".equals(method)) {
			result = "-1";
		} else {
			result = method;
		}
		return result;

	}

	/**
	 * ip/lbs所属省、市0001-0999（not 156），属于境外
	 * 
	 * @param region
	 * @return
	 */
	public String correctRegion(String region) {
		String result=Constants.NULL_STRING_FLAG;
		try{
			int regionInt = Integer.parseInt(region);
			if (regionInt >= 1 && regionInt <= 999 && regionInt != 156) {
				result = "0000";
			} else {
				result = region;
			}
		}catch (Exception e){
			
		}

		return result;
	}
	
	public String correctIssInsIdCd(String str ){
		
		if(str.length()==8){
    		str=str.substring(0, 4);
    		if(Constants.issInsIdCdMap.containsKey(str))
    			str=Constants.issInsIdCdMap.get(str);
    	}
    	return str;
	}

	public String compareRegion(String item1,String item2) {
		return String.valueOf(StringUtils.compareRegion(item1, item2));
	}

	public MapItem getRecord() {
		return record;
	}

	public void setRecord(MapItem record) {
		this.record = record;
	}

}
