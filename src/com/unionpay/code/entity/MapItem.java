
/**
 * @author shuoshuofan
 *
 */
package com.unionpay.code.entity;
import com.unionpay.code.column.MapColumn;
public class MapItem {
	
	private String event_id;
	private String svc_req_tp;
	private String event_tp;
	private String ar_pri_acct_no;	
	private String card_media;
	private String goods_tp;
	private String buss_chnl;
	private String iss_ins_id_cd;
	private String ar_card_bin;
	private String card_tp;
	private String client_ip;
	private String mac_addr;
	private String disk_seq;
	private String mobile;
	private String imei;
	private String csn;
	private String meid;
	private String client_id1;
	private String client_id2;
	private String fwd_sys_id;
	private String event_occu_dt;
	private String event_occu_tm;
	private String event_addr;
	private String trans_tp;
	private String user_id;
	private String open_crt_item_tp;
	private String iss_ins_id_md;
	private String certif_id;
	private String acq_ins_id_cd;
	private String trans_at;
	private String mchnt_cd;
	private String mchnt_nm;
	private String mchnt_tp;
	private String sub_mchnt_cd;
	private String sub_mchnt_name;
	private String goods_class;
	private String mchnt_order;
	private String rcv_province;
	private String rcv_city;
	private String open_chnl;
	private String mchnt_usr_id;
	private String mchnt_usr_rgstr_tm;
	private String mchnt_usr_rgstr_email;
	private String first_bus_open_dt;
	private String card_bin_region;
	private String rec_crt_ts;
	private String ip_prov_region_cd;
	private String tel_prov_region_cd;
	private String ip_city_region_cd;
	private String tel_city_region_cd;
	private String trans_dev_cd;
	private String tfr_dt_dm;
	private String sys_tra_no;
	private String longitude_latitude_prov_region_cd;
	private String longitude_latitude_city_region_cd;
	private String card_prov_region_cd;
	private String card_city_region_cd;
	private String data_pkg;
	private String mobile_region_no;
	private String mobile_region_nm;
	private String first_upmp_dt;
	private String first_upop_dt;
	private String pdate;

	public MapItem( String[] recordArray){
		
		
		
		
		
		this.event_id=recordArray[MapColumn.event_id.ordinal()];
		this.svc_req_tp=recordArray[MapColumn.svc_req_tp.ordinal()];
		this.event_tp=recordArray[MapColumn.event_tp.ordinal()];
		this.ar_pri_acct_no=recordArray[MapColumn.ar_pri_acct_no.ordinal()];
		this.card_media=recordArray[MapColumn.card_media.ordinal()];
		this.goods_tp=recordArray[MapColumn.goods_tp.ordinal()];
		this.buss_chnl=recordArray[MapColumn.buss_chnl.ordinal()];
		this.iss_ins_id_cd=recordArray[MapColumn.iss_ins_id_cd.ordinal()];
		this.ar_card_bin=recordArray[MapColumn.ar_card_bin.ordinal()];
		this.card_tp=recordArray[MapColumn.card_tp.ordinal()];
		this.client_ip=recordArray[MapColumn.client_ip.ordinal()];
		this.mac_addr=recordArray[MapColumn.mac_addr.ordinal()];
		this.disk_seq=recordArray[MapColumn.disk_seq.ordinal()];
		this.mobile=recordArray[MapColumn.mobile.ordinal()];
		this.imei=recordArray[MapColumn.imei.ordinal()];
		this.meid=recordArray[MapColumn.meid.ordinal()];
		this.csn=recordArray[MapColumn.csn.ordinal()];
		this.client_id1=recordArray[MapColumn.client_id1.ordinal()];
		this.client_id2=recordArray[MapColumn.client_id2.ordinal()];
		this.fwd_sys_id=recordArray[MapColumn.fwd_sys_id.ordinal()];
		this.event_occu_dt=recordArray[MapColumn.event_occu_dt.ordinal()];
		this.event_occu_tm=recordArray[MapColumn.event_occu_tm.ordinal()];
		this.event_addr=recordArray[MapColumn.event_addr.ordinal()];
		this.trans_tp=recordArray[MapColumn.trans_tp.ordinal()];
		this.user_id=recordArray[MapColumn.user_id.ordinal()];
		this.open_crt_item_tp=recordArray[MapColumn.open_crt_item_tp.ordinal()];
		this.iss_ins_id_md=recordArray[MapColumn.iss_ins_id_md.ordinal()];
		this.certif_id=recordArray[MapColumn.certif_id.ordinal()];
		this.acq_ins_id_cd=recordArray[MapColumn.acq_ins_id_cd.ordinal()];
		this.trans_at=recordArray[MapColumn.trans_at.ordinal()];
		this.mchnt_cd=recordArray[MapColumn.mchnt_cd.ordinal()];
		this.mchnt_nm=recordArray[MapColumn.mchnt_nm.ordinal()];
		this.mchnt_tp=recordArray[MapColumn.mchnt_tp.ordinal()];
		this.sub_mchnt_cd=recordArray[MapColumn.sub_mchnt_cd.ordinal()];
		this.sub_mchnt_name=recordArray[MapColumn.sub_mchnt_name.ordinal()];
		this.goods_class=recordArray[MapColumn.goods_class.ordinal()];
		this.mchnt_order=recordArray[MapColumn.mchnt_order.ordinal()];
		this.rcv_province=recordArray[MapColumn.rcv_province.ordinal()];
		this.rcv_city=recordArray[MapColumn.rcv_city.ordinal()];
		this.open_chnl=recordArray[MapColumn.open_chnl.ordinal()];
		this.mchnt_usr_id=recordArray[MapColumn.mchnt_usr_id.ordinal()];
		this.mchnt_usr_rgstr_tm=recordArray[MapColumn.mchnt_usr_rgstr_tm.ordinal()];
		this.mchnt_usr_rgstr_email=recordArray[MapColumn.mchnt_usr_rgstr_email.ordinal()];
		this.first_bus_open_dt=recordArray[MapColumn.first_bus_open_dt.ordinal()];
		this.card_bin_region=recordArray[MapColumn.card_bin_region.ordinal()];
		this.rec_crt_ts=recordArray[MapColumn.rec_crt_ts.ordinal()].replace("END", "");
		this.ip_prov_region_cd=recordArray[MapColumn.ip_prov_region_cd.ordinal()];
		this.tel_prov_region_cd=recordArray[MapColumn.tel_prov_region_cd.ordinal()];
		this.ip_city_region_cd=recordArray[MapColumn.ip_city_region_cd.ordinal()];
		this.tel_city_region_cd=recordArray[MapColumn.tel_city_region_cd.ordinal()];
		this.trans_dev_cd=recordArray[MapColumn.trans_dev_cd.ordinal()];
		this.tfr_dt_dm=recordArray[MapColumn.tfr_dt_dm.ordinal()];
		this.sys_tra_no=recordArray[MapColumn.sys_tra_no.ordinal()];
		this.longitude_latitude_prov_region_cd=recordArray[MapColumn.longitude_latitude_prov_region_cd.ordinal()];
		this.longitude_latitude_city_region_cd=recordArray[MapColumn.longitude_latitude_city_region_cd.ordinal()];
		this.card_prov_region_cd=recordArray[MapColumn.card_prov_region_cd.ordinal()];
		this.card_city_region_cd=recordArray[MapColumn.card_city_region_cd.ordinal()];
		this.data_pkg=recordArray[MapColumn.data_pkg.ordinal()];
		this.mobile_region_no=recordArray[MapColumn.mobile_region_no.ordinal()];
		this.mobile_region_nm=recordArray[MapColumn.mobile_region_nm.ordinal()];
		this.first_upmp_dt=recordArray[MapColumn.first_upmp_dt.ordinal()];
		this.first_upop_dt=recordArray[MapColumn.first_upop_dt.ordinal()];
		this.pdate=recordArray[MapColumn.pdate.ordinal()];



	}

	public String getEvent_id() {
		return event_id;
	}


	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getSvc_req_tp() {
		return svc_req_tp;
	}




	public void setSvc_req_tp(String svc_req_tp) {
		this.svc_req_tp = svc_req_tp;
	}




	public String getEvent_tp() {
		return event_tp;
	}




	public void setEvent_tp(String event_tp) {
		this.event_tp = event_tp;
	}




	public String getAr_pri_acct_no() {
		return ar_pri_acct_no;
	}


	public void setAr_pri_acct_no(String ar_pri_acct_no) {
		this.ar_pri_acct_no = ar_pri_acct_no;
	}

	public String getCard_media() {
		return card_media;
	}



	public void setCard_media(String card_media) {
		this.card_media = card_media;
	}




	public String getGoods_tp() {
		return goods_tp;
	}




	public void setGoods_tp(String goods_tp) {
		this.goods_tp = goods_tp;
	}




	public String getBuss_chnl() {
		return buss_chnl;
	}




	public void setBuss_chnl(String buss_chnl) {
		this.buss_chnl = buss_chnl;
	}




	public String getIss_ins_id_cd() {
		return iss_ins_id_cd;
	}




	public void setIss_ins_id_cd(String iss_ins_id_cd) {
		this.iss_ins_id_cd = iss_ins_id_cd;
	}




	public String getAr_card_bin() {
		return ar_card_bin;
	}




	public void setAr_card_bin(String ar_card_bin) {
		this.ar_card_bin = ar_card_bin;
	}




	public String getCard_tp() {
		return card_tp;
	}




	public void setCard_tp(String card_tp) {
		this.card_tp = card_tp;
	}




	public String getClient_ip() {
		return client_ip;
	}




	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}




	public String getMac_addr() {
		return mac_addr;
	}




	public void setMac_addr(String mac_addr) {
		this.mac_addr = mac_addr;
	}




	public String getDisk_seq() {
		return disk_seq;
	}




	public void setDisk_seq(String disk_seq) {
		this.disk_seq = disk_seq;
	}




	public String getMobile() {
		return mobile;
	}




	public void setMobile(String mobile) {
		this.mobile = mobile;
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




	public String getClient_id1() {
		return client_id1;
	}




	public void setClient_id1(String client_id1) {
		this.client_id1 = client_id1;
	}




	public String getClient_id2() {
		return client_id2;
	}




	public void setClient_id2(String client_id2) {
		this.client_id2 = client_id2;
	}




	public String getFwd_sys_id() {
		return fwd_sys_id;
	}




	public void setFwd_sys_id(String fwd_sys_id) {
		this.fwd_sys_id = fwd_sys_id;
	}




	public String getEvent_occu_dt() {
		return event_occu_dt;
	}




	public void setEvent_occu_dt(String event_occu_dt) {
		this.event_occu_dt = event_occu_dt;
	}




	public String getEvent_occu_tm() {
		return event_occu_tm;
	}




	public void setEvent_occu_tm(String event_occu_tm) {
		this.event_occu_tm = event_occu_tm;
	}




	public String getEvent_addr() {
		return event_addr;
	}




	public void setEvent_addr(String event_addr) {
		this.event_addr = event_addr;
	}




	public String getTrans_tp() {
		return trans_tp;
	}




	public void setTrans_tp(String trans_tp) {
		this.trans_tp = trans_tp;
	}




	public String getUser_id() {
		return user_id;
	}




	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}




	public String getOpen_crt_item_tp() {
		return open_crt_item_tp;
	}




	public void setOpen_crt_item_tp(String open_crt_item_tp) {
		this.open_crt_item_tp = open_crt_item_tp;
	}




	public String getIss_ins_id_md() {
		return iss_ins_id_md;
	}




	public void setIss_ins_id_md(String iss_ins_id_md) {
		this.iss_ins_id_md = iss_ins_id_md;
	}




	public String getCertif_id() {
		return certif_id;
	}




	public void setCertif_id(String certif_id) {
		this.certif_id = certif_id;
	}




	public String getAcq_ins_id_cd() {
		return acq_ins_id_cd;
	}




	public void setAcq_ins_id_cd(String acq_ins_id_cd) {
		this.acq_ins_id_cd = acq_ins_id_cd;
	}




	public String getTrans_at() {
		return trans_at;
	}




	public void setTrans_at(String trans_at) {
		this.trans_at = trans_at;
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




	public String getSub_mchnt_cd() {
		return sub_mchnt_cd;
	}




	public void setSub_mchnt_cd(String sub_mchnt_cd) {
		this.sub_mchnt_cd = sub_mchnt_cd;
	}




	public String getSub_mchnt_name() {
		return sub_mchnt_name;
	}




	public void setSub_mchnt_name(String sub_mchnt_name) {
		this.sub_mchnt_name = sub_mchnt_name;
	}




	public String getGoods_class() {
		return goods_class;
	}




	public void setGoods_class(String goods_class) {
		this.goods_class = goods_class;
	}




	public String getMchnt_order() {
		return mchnt_order;
	}




	public void setMchnt_order(String mchnt_order) {
		this.mchnt_order = mchnt_order;
	}




	public String getRcv_province() {
		return rcv_province;
	}




	public void setRcv_province(String rcv_province) {
		this.rcv_province = rcv_province;
	}




	public String getRcv_city() {
		return rcv_city;
	}




	public void setRcv_city(String rcv_city) {
		this.rcv_city = rcv_city;
	}




	public String getOpen_chnl() {
		return open_chnl;
	}




	public void setOpen_chnl(String open_chnl) {
		this.open_chnl = open_chnl;
	}




	public String getMchnt_usr_id() {
		return mchnt_usr_id;
	}




	public void setMchnt_usr_id(String mchnt_usr_id) {
		this.mchnt_usr_id = mchnt_usr_id;
	}




	public String getMchnt_usr_rgstr_tm() {
		return mchnt_usr_rgstr_tm;
	}




	public void setMchnt_usr_rgstr_tm(String mchnt_usr_rgstr_tm) {
		this.mchnt_usr_rgstr_tm = mchnt_usr_rgstr_tm;
	}




	public String getMchnt_usr_rgstr_email() {
		return mchnt_usr_rgstr_email;
	}




	public void setMchnt_usr_rgstr_email(String mchnt_usr_rgstr_email) {
		this.mchnt_usr_rgstr_email = mchnt_usr_rgstr_email;
	}




	public String getFirst_bus_open_dt() {
		return first_bus_open_dt;
	}




	public void setFirst_bus_open_dt(String first_bus_open_dt) {
		this.first_bus_open_dt = first_bus_open_dt;
	}




	public String getCard_bin_region() {
		return card_bin_region;
	}




	public void setCard_bin_region(String card_bin_region) {
		this.card_bin_region = card_bin_region;
	}




	public String getRec_crt_ts() {
		return rec_crt_ts;
	}




	public void setRec_crt_ts(String rec_crt_ts) {
		this.rec_crt_ts = rec_crt_ts;
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




	public String getTrans_dev_cd() {
		return trans_dev_cd;
	}




	public void setTrans_dev_cd(String trans_dev_cd) {
		this.trans_dev_cd = trans_dev_cd;
	}




	public String getTfr_dt_dm() {
		return tfr_dt_dm;
	}




	public void setTfr_dt_dm(String tfr_dt_dm) {
		this.tfr_dt_dm = tfr_dt_dm;
	}




	public String getSys_tra_no() {
		return sys_tra_no;
	}




	public void setSys_tra_no(String sys_tra_no) {
		this.sys_tra_no = sys_tra_no;
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




	public String getData_pkg() {
		return data_pkg;
	}




	public void setData_pkg(String data_pkg) {
		this.data_pkg = data_pkg;
	}


	public String getMobile_region_no() {
		return mobile_region_no;
	}


	public void setMobile_region_no(String mobile_region_no) {
		this.mobile_region_no = mobile_region_no;
	}


	public String getMobile_region_nm() {
		return mobile_region_nm;
	}


	public void setMobile_region_nm(String mobile_region_nm) {
		this.mobile_region_nm = mobile_region_nm;
	}


	public String getFirst_upmp_dt() {
		return first_upmp_dt;
	}

	public void setFirst_upmp_dt(String first_upmp_dt) {
		this.first_upmp_dt = first_upmp_dt;
	}

	public String getFirst_upop_dt() {
		return first_upop_dt;
	}

	public void setFirst_upop_dt(String first_upop_dt) {
		this.first_upop_dt = first_upop_dt;
	}

	public String getPdate() {
		return pdate;
	}


	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb
		.append(event_id+",")
		.append(svc_req_tp+",")
		.append(event_tp+",")
		.append(ar_pri_acct_no+",")
		.append(card_media+",")
		.append(goods_tp+",")
		.append(buss_chnl+",")
		.append(iss_ins_id_cd+",")
		.append(ar_card_bin+",")
		.append(card_tp+",")
		.append(client_ip+",")
		.append(mac_addr+",")
		.append(disk_seq+",")
		.append(mobile+",")
		.append(imei+",")
		.append(meid+",")
		.append(csn+",")
		.append(client_id1+",")
		.append(client_id2+",")
		.append(fwd_sys_id+",")
		.append(event_occu_dt+",")
		.append(event_occu_tm+",")
		.append(event_addr+",")
		.append(trans_tp+",")
		.append(user_id+",")
		.append(open_crt_item_tp+",")
		.append(iss_ins_id_md+",")
		.append(certif_id+",")
		.append(acq_ins_id_cd+",")
		.append(trans_at+",")
		.append(mchnt_cd+",")
		.append(mchnt_nm+",")
		.append(mchnt_tp+",")
		.append(sub_mchnt_cd+",")
		.append(sub_mchnt_name+",")
		.append(goods_class+",")
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
		.append(ip_prov_region_cd+",")
		.append(tel_prov_region_cd+",")		
		.append(ip_city_region_cd+",")
		.append(tel_city_region_cd+",")
		.append(trans_dev_cd+",")
		.append(tfr_dt_dm+",")
		.append(sys_tra_no+",")
		.append(mobile+",")
		.append(longitude_latitude_prov_region_cd+",")
		.append(longitude_latitude_city_region_cd)
		.append(card_prov_region_cd)
		.append(data_pkg)
		.append(mobile_region_no)
		.append(mobile_region_nm)
		.append(first_upmp_dt)
		.append(first_upop_dt)
		.append(pdate);
		
		 return sb.toString();
       
	}
	
	
}
