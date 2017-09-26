
/**
 *     @author shuoshuofan
 *
 */
package com.unionpay.code.entity;

import org.apache.hadoop.hdfs.server.protocol.FencedException;
import org.apache.hadoop.hive.ql.parse.HiveParser_IdentifiersParser.orderByClause_return;
import org.apache.hadoop.hive.ql.parse.HiveParser_IdentifiersParser.stringLiteralSequence_return;

import com.unionpay.code.column.ReduceColumn;
//import java 
public class ReduceItem {
	
	
	
	
	
	//主键 
	private String ar_pri_acct_no;	
	private String sys_tra_no;
	//金额
	private String trans_at;
	//金额的衍生变量
	private String bigat_1000;//大额整额标识
	private String near_at_1000;
	private String near_at_500;
	private String at_89num_num;
	private String at_is_decimal;
	private String at_len;
	//时间
	private String tfr_dt_tm;
	//时间的衍生变量
	private String midnight_ind;
	private String time_interval;
	private String time_144p;
	private String time_24p;
	//地点
	private String client_ip;
	private String mobile;
	private String ip_prov_region_cd;
	private String tel_prov_region_cd;
	private String ip_city_region_cd;
	private String tel_city_region_cd;
	private String card_prov_region_cd;
	private String card_city_region_cd;
	private String longitude_latitude_prov_region_cd;
	private String longitude_latitude_city_region_cd;
	//地点衍生变量
	//卡片
	private String card_media;
	private String card_tp;
	//交易
	private String goods_tp;
	//用户
	private String user_id;
	//商户
	private String mchnt_cd;
	private String mchnt_nm;
	private String mchnt_tp;
	//设备
	private String imei;
	private String csn;
	private String meid;
	
	public ReduceItem( String[] recordArray){
		
	
		//主键
		this.ar_pri_acct_no=recordArray[ReduceColumn.ar_pri_acct_no.ordinal()];
		this.sys_tra_no=recordArray[ReduceColumn.sys_tra_no.ordinal()];
		//金额
		this.trans_at=recordArray[ReduceColumn.trans_at.ordinal()];
		//金额衍生变量
		this.bigat_1000=recordArray[ReduceColumn.bigat_1000.ordinal()];
		this.near_at_1000=recordArray[ReduceColumn.near_at_1000.ordinal()];
		this.near_at_500=recordArray[ReduceColumn.near_at_500.ordinal()];
		this.at_89num_num=recordArray[ReduceColumn.at_89num_num.ordinal()];
		this.at_is_decimal=recordArray[ReduceColumn.at_is_decimal.ordinal()];
		this.at_len=recordArray[ReduceColumn.at_len.ordinal()];
		//时间
		this.tfr_dt_tm=recordArray[ReduceColumn.tfr_dt_tm.ordinal()];
		//时间衍生变量
		this.midnight_ind=recordArray[ReduceColumn.midnight_ind.ordinal()];
		this.time_interval=recordArray[ReduceColumn.time_interval.ordinal()];
		this.time_144p=recordArray[ReduceColumn.time_144p.ordinal()];
		this.time_24p=recordArray[ReduceColumn.time_24p.ordinal()];	
		//地点
		/*
		this.mobile=recordArray[ReduceColumn.mobile.ordinal()];
		this.client_ip=recordArray[ReduceColumn.client_ip.ordinal()];
		this.ip_prov_region_cd=recordArray[ReduceColumn.ip_prov_region_cd.ordinal()];
		this.tel_prov_region_cd=recordArray[ReduceColumn.tel_prov_region_cd.ordinal()];		
		this.ip_city_region_cd=recordArray[ReduceColumn.ip_city_region_cd.ordinal()];
		this.tel_city_region_cd=recordArray[ReduceColumn.tel_city_region_cd.ordinal()];
		this.longitude_latitude_prov_region_cd=recordArray[ReduceColumn.longitude_latitude_prov_region_cd.ordinal()];
		this.longitude_latitude_city_region_cd=recordArray[ReduceColumn.longitude_latitude_city_region_cd.ordinal()];	
		//交易
		this.goods_tp=recordArray[ReduceColumn.goods_tp.ordinal()];	
		//卡片
		this.card_media=recordArray[ReduceColumn.card_media.ordinal()];
		this.card_tp=recordArray[ReduceColumn.card_tp.ordinal()];
		//商户
		this.mchnt_cd=recordArray[ReduceColumn.mchnt_cd.ordinal()];
		this.mchnt_nm=recordArray[ReduceColumn.mchnt_nm.ordinal()];
		this.mchnt_tp=recordArray[ReduceColumn.mchnt_tp.ordinal()];
		//用户
		this.user_id=recordArray[ReduceColumn.user_id.ordinal()];
		//设备
		this.imei=recordArray[ReduceColumn.imei.ordinal()];
		this.meid=recordArray[ReduceColumn.meid.ordinal()];
		this.csn=recordArray[ReduceColumn.csn.ordinal()];
			*/
	}
	public void computeDerivativeVar(){	
		//计算金额相关衍生变量
		compute_at_is_decimal();//首先计算是否是小数
		//compute_bigat_1000();
		compute_near_at_1000();
		compute_near_at_500() ;
		//compute_at_89num_num();
		compute_at_len();	
		//计算地点相关的衍生变量	
		//
	}
	private void compute_at_is_decimal() {
		
	}
	private void compute_bigat_1000(){
		
	}
	private void compute_near_at_1000(){
        
	}
	private void compute_near_at_500() {
	

	}
	private void compute_at_89num_num(){
	
	
	}
	
	private void compute_at_len() {
		
		
	}
	//计算相关的衍生变量
	private void compute_midnight_ind() {
		
	}
	private void compute_time_interval(){
		
	}
	private void compute_time_144p() {
		
	}
	private void compute_time_24p() {
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb
		//主键
		.append(ar_pri_acct_no+",")
		.append(sys_tra_no+",")
		//金额
		.append(trans_at+",")
		//金额衍生变量
		.append(bigat_1000+",")
		.append(near_at_1000+",")
		.append(near_at_500+",")
		.append(at_89num_num+",")
		.append(at_is_decimal+",")
		.append(at_len+",")
		//时间
		.append(tfr_dt_tm+",")
		.append(midnight_ind+",")
		.append( time_interval+",")
		.append(time_144p+",")
		.append(time_24p+",")
		//交易
		.append(goods_tp+",")
		//商户
		.append(mchnt_cd+",")
		.append(mchnt_nm+",")
		.append(mchnt_tp+",")
		//用户
		.append(user_id+",")
		//地点
		.append(mobile+",")
		.append(client_ip+",")
		.append(ip_prov_region_cd+",")
		.append(tel_prov_region_cd+",")		
		.append(ip_city_region_cd+",")
		.append(tel_city_region_cd+",")
		.append(longitude_latitude_prov_region_cd+",")
		.append(longitude_latitude_city_region_cd)
		//卡片
		.append(card_media+",")
		.append(card_tp+",")
		//设备
		.append(mobile+",")
		.append(imei+",")
		.append(meid+",")
		.append(csn);
		
		 return sb.toString();
		/*
		.append(sub_mchnt_cd+",")
		.append(sub_mchnt_name+",")
		.append(buss_chnl+",")
		.append(iss_ins_id_cd+",")
		.append(ar_card_bin+",")
		.append(data_pkg)
		.append(mobile_region_no)
		.append(mobile_region_nm)
		.append(first_upmp_dt)
		.append(first_upop_dt)
		.append(pdate);
		.append(trans_dev_cd+",")
		.append(card_prov_region_cd)
		.append(client_id1+",")
		.append(client_id2+",")
		.append(fwd_sys_id+",")
		.append(mchnt_order+",")
		.append(rcv_province+",")
		.append(rcv_city+",")
		.append(open_chnl+",")
		.append(mchnt_usr_id+",")
		.append(mchnt_usr_rgstr_tm+",")
		.append(mchnt_usr_rgstr_email+",")
		.append(first_bus_open_dt+",")
		.append(card_bin_region+",")
		.append(rec_crt_ts+",")
		.append(mac_addr+",")
		.append(disk_seq+",")
		.append(event_addr+",")
		.append(trans_tp+",")	
		.append(open_crt_item_tp+",")
		.append(iss_ins_id_md+",")
		.append(certif_id+",")
		.append(acq_ins_id_cd+",")
		 */
		//.append(event_id+",")
		//.append(svc_req_tp+",")
		//.append(event_tp+",")
		//.append(event_occu_dt+",")
		//.append(event_occu_tm+",")
		
       
	}
	public String getAr_pri_acct_no() {
		return ar_pri_acct_no;
	}
	public void setAr_pri_acct_no(String ar_pri_acct_no) {
		this.ar_pri_acct_no = ar_pri_acct_no;
	}
	public String getSys_tra_no() {
		return sys_tra_no;
	}
	public void setSys_tra_no(String sys_tra_no) {
		this.sys_tra_no = sys_tra_no;
	}
	public String getTrans_at() {
		return trans_at;
	}
	public void setTrans_at(String trans_at) {
		this.trans_at = trans_at;
	}
	public String getBigat_1000() {
		return bigat_1000;
	}
	public void setBigat_1000(String bigat_1000) {
		this.bigat_1000 = bigat_1000;
	}
	public String getNear_at_1000() {
		return near_at_1000;
	}
	public void setNear_at_1000(String near_at_1000) {
		this.near_at_1000 = near_at_1000;
	}
	public String getNear_at_500() {
		return near_at_500;
	}
	public void setNear_at_500(String near_at_500) {
		this.near_at_500 = near_at_500;
	}
	public String getAt_89num_num() {
		return at_89num_num;
	}
	public void setAt_89num_num(String at_89num_num) {
		this.at_89num_num = at_89num_num;
	}
	public String getAt_is_decimal() {
		return at_is_decimal;
	}
	public void setAt_is_decimal(String at_is_decimal) {
		this.at_is_decimal = at_is_decimal;
	}
	public String getAt_len() {
		return at_len;
	}
	public void setAt_len(String at_len) {
		this.at_len = at_len;
	}
	public String getTfr_dt_tm() {
		return tfr_dt_tm;
	}
	public void setTfr_dt_tm(String tfr_dt_tm) {
		this.tfr_dt_tm = tfr_dt_tm;
	}
	public String getMidnight_ind() {
		return midnight_ind;
	}
	public void setMidnight_ind(String midnight_ind) {
		this.midnight_ind = midnight_ind;
	}
	public String getTime_interval() {
		return time_interval;
	}
	public void setTime_interval(String time_interval) {
		this.time_interval = time_interval;
	}
	public String getTime_144p() {
		return time_144p;
	}
	public void setTime_144p(String time_144p) {
		this.time_144p = time_144p;
	}
	public String getTime_24p() {
		return time_24p;
	}
	public void setTime_24p(String time_24p) {
		this.time_24p = time_24p;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIp_prov_region_cd() {
		return ip_prov_region_cd;
	}
	public void setIp_prov_region_cd(String ip_prov_region_cd) {
		this.ip_prov_region_cd = ip_prov_region_cd;
	}
	public String getTel_prov_region_cd() {
		return tel_prov_region_cd;
	}
	public void setTel_prov_region_cd(String tel_prov_region_cd) {
		this.tel_prov_region_cd = tel_prov_region_cd;
	}
	public String getIp_city_region_cd() {
		return ip_city_region_cd;
	}
	public void setIp_city_region_cd(String ip_city_region_cd) {
		this.ip_city_region_cd = ip_city_region_cd;
	}
	public String getTel_city_region_cd() {
		return tel_city_region_cd;
	}
	public void setTel_city_region_cd(String tel_city_region_cd) {
		this.tel_city_region_cd = tel_city_region_cd;
	}
	public String getCard_prov_region_cd() {
		return card_prov_region_cd;
	}
	public void setCard_prov_region_cd(String card_prov_region_cd) {
		this.card_prov_region_cd = card_prov_region_cd;
	}
	public String getCard_city_region_cd() {
		return card_city_region_cd;
	}
	public void setCard_city_region_cd(String card_city_region_cd) {
		this.card_city_region_cd = card_city_region_cd;
	}
	public String getLongitude_latitude_prov_region_cd() {
		return longitude_latitude_prov_region_cd;
	}
	public void setLongitude_latitude_prov_region_cd(String longitude_latitude_prov_region_cd) {
		this.longitude_latitude_prov_region_cd = longitude_latitude_prov_region_cd;
	}
	public String getLongitude_latitude_city_region_cd() {
		return longitude_latitude_city_region_cd;
	}
	public void setLongitude_latitude_city_region_cd(String longitude_latitude_city_region_cd) {
		this.longitude_latitude_city_region_cd = longitude_latitude_city_region_cd;
	}
	public String getCard_media() {
		return card_media;
	}
	public void setCard_media(String card_media) {
		this.card_media = card_media;
	}
	public String getCard_tp() {
		return card_tp;
	}
	public void setCard_tp(String card_tp) {
		this.card_tp = card_tp;
	}
	public String getGoods_tp() {
		return goods_tp;
	}
	public void setGoods_tp(String goods_tp) {
		this.goods_tp = goods_tp;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMchnt_cd() {
		return mchnt_cd;
	}
	public void setMchnt_cd(String mchnt_cd) {
		this.mchnt_cd = mchnt_cd;
	}
	public String getMchnt_nm() {
		return mchnt_nm;
	}
	public void setMchnt_nm(String mchnt_nm) {
		this.mchnt_nm = mchnt_nm;
	}
	public String getMchnt_tp() {
		return mchnt_tp;
	}
	public void setMchnt_tp(String mchnt_tp) {
		this.mchnt_tp = mchnt_tp;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getCsn() {
		return csn;
	}
	public void setCsn(String csn) {
		this.csn = csn;
	}
	public String getMeid() {
		return meid;
	}
	public void setMeid(String meid) {
		this.meid = meid;
	}
	
	
}
