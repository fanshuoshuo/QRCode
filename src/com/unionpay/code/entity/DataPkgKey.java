
/*
 * @author shuoshuofan
 * 
 */
package com.unionpay.code.entity;

public enum DataPkgKey {
	
	deviceType,
	mobileLastFour,
	traceNo,
	arPriAcctNo,
	devId,
	ipProvRegion,
	deviceNoProvRegion,
	captureMethod,
	safeCarrIss,
	mobile,
	eventId,
	seid,
	deviceNoProvCity,
	eventOccuTm,
	goodsTp,
	cardSecCityRegion,
	cardSecProvRegion,
	cardBinRegion,
	mobileProvRegion,
	deviceAccptLanguage,
	billingAddress,
	lbs,
	reasonCodes,
	deviceName,
	clientIp,
	eventTp,
	bussChnl,
	deviceNoEq,
	deviceNoLastFour,
	color,
	dpan,
	fpanSource,
	mchntUsrId,
	ipCityRegion,
	msqTypeFld,
	cardTp,
	deviceScore,
	fwdSysId,
	serviceType,
	mobileCityRegion,
	transTp,
	eventOccuDt,
	colorStandardsVersion,
	issInsIdCd,
	deviceNumber,
	accountScore,
	jwdCityRegion,
	jwdProvRegion,
	cgwInPort,
	recordTime,
	eventDtTm;
	
	public static void main(String args[]){
		for(DataPkgKey item :DataPkgKey.values())
			System.out.println(item.name()+":"+item.ordinal());
	}
}
