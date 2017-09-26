/*
 * @author  shuoshuofan
 * upmp table column 
 */

package com.unionpay.code.column;
public enum ReduceColumn {

	
	//主键
	ar_pri_acct_no,
	sys_tra_no,
	//金额
	trans_at,
	//金额衍生变量
	bigat_1000,
	near_at_1000,
	near_at_500,
	at_89num_num,
	at_is_decimal,
	at_len,
	//时间
	tfr_dt_tm,
	//时间衍生变量
	midnight_ind,
	 time_interval,
	time_144p,
	time_24p;
	/*
	//地点
	mobile,
	client_ip,
	ip_prov_region_cd,
	tel_prov_region_cd,		
	ip_city_region_cd,
	tel_city_region_cd,
	longitude_latitude_prov_region_cd,
	longitude_latitude_city_region_cd,	
	//交易
	goods_tp,	
	//卡片
	card_media,
	card_tp,
	//商户
	mchnt_cd,
	mchnt_nm,
	mchnt_tp,
	//用户
	user_id,
	//设备
	imei,
	meid,
	csn;
	*/
	public static void main(String args[]){
		for(ReduceColumn item :ReduceColumn.values())
	
			System.out.println("this."+item.name()+"=recordArray[ReduceColumn."+item.name()+".ordinal()];");
			
	}
}
