/*
 * @author  shuoshuofan
 * upmp table column 
 */

package com.unionpay.code.column;
public enum MapColumn {
	
	//参照表建立
	event_id,
	svc_req_tp,
	event_tp,
	ar_pri_acct_no,
	card_media,
	goods_tp,
	buss_chnl,
	iss_ins_id_cd,
	ar_card_bin,
	card_tp,
	client_ip,
	mac_addr,
	disk_seq,
	mobile,
	imei,
	meid,
	csn,
	client_id1,
	client_id2,
	fwd_sys_id,
	event_occu_dt,
	event_occu_tm,
	event_addr,
	trans_tp,
	user_id,
	open_crt_item_tp,
	iss_ins_id_md,
	certif_id,
	acq_ins_id_cd,
	trans_at,
	mchnt_cd,
	mchnt_nm,
	mchnt_tp,
	sub_mchnt_cd,
	sub_mchnt_name,
	goods_class,
	mchnt_order,
	rcv_province,
	rcv_city,
	open_chnl,
	mchnt_usr_id,
	mchnt_usr_rgstr_tm,
	mchnt_usr_rgstr_email,
	first_bus_open_dt,
	card_bin_region,
	rec_crt_ts,
	ip_prov_region_cd,
	tel_prov_region_cd,
	ip_city_region_cd,
	tel_city_region_cd,
	trans_dev_cd,
	tfr_dt_tm,
	sys_tra_no,
	longitude_latitude_prov_region_cd,
	longitude_latitude_city_region_cd,
	card_prov_region_cd,
	card_city_region_cd,
	data_pkg,
	mobile_region_no,
	mobile_region_nm,
	first_upmp_dt,
	first_upop_dt,
	pdate;
	
	public static void main(String args[]){
		for(MapColumn item :MapColumn.values())
			System.out.println(item.name()+":"+item.ordinal());
			//System.out.println("this."+item.name()+"=recordArray[MapColumn."+item.name()+".ordinal()];");
			
	}
}
