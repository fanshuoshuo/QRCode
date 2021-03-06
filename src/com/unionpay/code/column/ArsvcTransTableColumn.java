/*
 * @author shuoshuofan
 * @date   20170911 
 * 
 */

package com.unionpay.code.column;

public enum ArsvcTransTableColumn {
	
	EVENT_ID,
	SVC_REQ_TP,
	EVENT_TP,
	AR_PRI_ACCT_NO,
	CARD_MEDIA,
	GOODS_TP,
	BUSS_CHNL,
	ISS_INS_ID_CD,
	AR_CARD_BIN,
	CARD_TP,
	CLIENT_IP,
	MAC_ADDR,
	DISK_SEQ,
	MOBILE,
	IMEI,
	MEID,
	CSN,
	CLIENT_ID1,
	CLIENT_ID2,
	FWD_SYS_ID,
	EVENT_OCCU_DT,
	EVENT_OCCU_TM,
	EVENT_ADDR,
	TRANS_TP,
	USER_ID,
	OPEN_CRT_ITEM_TP,
	ISS_INS_ID_MD,
	CERTIF_ID,
	ACQ_INS_ID_CD,
	TRANS_AT,
	MCHNT_CD,
	MCHNT_NM,
	MCHNT_TP,
	SUB_MCHNT_CD,
	SUB_MCHNT_NAME,
	GOODS_CLASS,
	MCHNT_ORDER,
	RCV_PROVINCE,
	RCV_CITY,
	OPEN_CHNL,
	MCHNT_USR_ID,
	MCHNT_USR_RGSTR_TM,
	MCHNT_USR_RGSTR_EMAIL,
	FIRST_BUS_OPEN_DT,
	CARD_BIN_REGION,
	REC_CRT_TS,
	IP_PROV_REGION_CD,
	TEL_PROV_REGION_CD,
	IP_CITY_REGION_CD,
	TEL_CITY_REGION_CD,
	TRANS_DEV_CD,
	TFR_DT_TM,
	SYS_TRA_NO,
	LONGITUDE_LATITUDE_PROV_REGION_CD,
	LONGITUDE_LATITUDE_CITY_REGION_CD,
	CARD_PROV_REGION_CD,
	CARD_CITY_REGION_CD,
	DATA_PKG,
	MOBLIE_REGION_NO,
	MOBLIE_REGION_NM,
	IP_REGION_NO,
	IP_REGION_NM,
	FIRST_UPMP_DT,
	FIRST_UPOP_DT,
	PDATE;
	
	
	public static void main(String args[]){
		System.out.println(ArsvcTransTableColumn.values().length+"");
		for(ArsvcTransTableColumn item :ArsvcTransTableColumn.values())
			System.out.println(item.name()+":"+item.ordinal());
	}
}
