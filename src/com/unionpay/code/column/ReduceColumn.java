/*
 * @author  shuoshuofan
 * upmp table column 
 */

package com.unionpay.code.column;
public enum ReduceColumn {

	
	//����
	ar_pri_acct_no,
	sys_tra_no,
	//���
	trans_at,
	//�����������
	bigat_1000,
	near_at_1000,
	near_at_500,
	at_89num_num,
	at_is_decimal,
	at_len,
	//ʱ��
	tfr_dt_tm,
	//ʱ����������
	midnight_ind,
	 time_interval,
	time_144p,
	time_24p;
	/*
	//�ص�
	mobile,
	client_ip,
	ip_prov_region_cd,
	tel_prov_region_cd,		
	ip_city_region_cd,
	tel_city_region_cd,
	longitude_latitude_prov_region_cd,
	longitude_latitude_city_region_cd,	
	//����
	goods_tp,	
	//��Ƭ
	card_media,
	card_tp,
	//�̻�
	mchnt_cd,
	mchnt_nm,
	mchnt_tp,
	//�û�
	user_id,
	//�豸
	imei,
	meid,
	csn;
	*/
	public static void main(String args[]){
		for(ReduceColumn item :ReduceColumn.values())
	
			System.out.println("this."+item.name()+"=recordArray[ReduceColumn."+item.name()+".ordinal()];");
			
	}
}
