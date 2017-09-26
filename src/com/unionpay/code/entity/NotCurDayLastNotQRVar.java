/*
 * @author  shuoshuofan
 * 
 * ��¼�ǵ����ϱʷǶ�ά�뽻�ױ���
 * 
 */
package com.unionpay.code.entity;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.hadoop.hbase.protobuf.generated.ComparatorProtos.NullComparatorOrBuilder;
import org.apache.hadoop.hive.ql.parse.HiveParser.ifExists_return;
import org.apache.hadoop.hive.ql.parse.HiveParser_IdentifiersParser.stringLiteralSequence_return;

import com.google.common.collect.ComputationException;
import com.unionpay.code.entity.ReduceItem;

public class NotCurDayLastNotQRVar {
	private ReduceItem curTrans;      //���ʽ���
	private ReduceItem NotCurDayLastNotQR; //�ϱʶ�ά�뽻��
	
	//���׵���������
	//��������
	int  NotCurDayLastNotQR_exist;   //�Ƿ�����ϱʶ�ά�뽻��
	//��������ڵ����ϱʶ�ά�뽻�ף�һ�±���ȫ������Ϊ -1 
	
	//�����������
	private String  NotCurDayLastNotQR_trans_at;
	private String  trans_at_equal_NotCurDayLastNotQR;
	
	//ʱ��  ���ʱ��
	private String  tm_sub_NotCurDayLastNotQR;

	//�ص���������
	private String  ip_equal_NotCurDayLastNotQR;
	
	//����
	private String   NotCurDayLastNotQR_goods_tp;
	private String   goods_tp_equal_NotCurDayLastNotQR;
	
	//�̻�
	private String  mchnt_cd_equal_NotCurDayLastNotQR;
	
	//�̻����������ͣ����׽���� 
	
	private String  all_equal_NotCurDayLastNotQR;
	
	public  NotCurDayLastNotQRVar(ReduceItem currentRecord,ReduceItem notCurDayLastNotQRRecord) {
		
		curTrans=currentRecord;
		NotCurDayLastNotQR=notCurDayLastNotQRRecord;
		ComputingVars();
	}
	public void  ComputingVars() {
		
		Computing_NotCurDayLastNotQR_exist() ;
		Computing_NotCurDayLastNotQR_trans_at();
		Computing_trans_at_equal_NotCurDayLastNotQR();
		Computing_tm_sub_NotCurDayLastNotQR() ;
		Computing_ip_equal_NotCurDayLastNotQR();
		Computing_NotCurDayLastNotQR_goods_tp() ;
		Computing_goods_tp_equal_NotCurDayLastNotQR();
		Computing_mchnt_cd_equal_NotCurDayLastNotQR() ;
		Computing_all_equal_NotCurDayLastNotQR() ;
	}
	private void  Computing_NotCurDayLastNotQR_exist() {
	
		NotCurDayLastNotQR_exist=(NotCurDayLastNotQR==null||curTrans==null)?0:1;
	}   
	private void  Computing_NotCurDayLastNotQR_trans_at(){
		
		NotCurDayLastNotQR_trans_at=(NotCurDayLastNotQR_exist==1)?
							  NotCurDayLastNotQR.getTrans_at():"-1";
	}
	private void  Computing_trans_at_equal_NotCurDayLastNotQR() {
		
		trans_at_equal_NotCurDayLastNotQR=(NotCurDayLastNotQR_exist==1)?
							       NotCurDayLastNotQR.getTrans_at():"-1";
		if (NotCurDayLastNotQR_exist==1) {
			trans_at_equal_NotCurDayLastNotQR=(NotCurDayLastNotQR.getTrans_at().equals(curTrans.getTrans_at()))?
				       					"1":"0";
		} else {
			trans_at_equal_NotCurDayLastNotQR="-1";
		}					       
	}
	//����ʱ����
	private void  Computing_tm_sub_NotCurDayLastNotQR() {
		
		if(NotCurDayLastNotQR_exist==1) {	
			String tm=curTrans.getTfr_dt_tm();
			String NotCurDayLastNotQR_tm=NotCurDayLastNotQR.getTfr_dt_tm();
			//ȡʱ�����������
			tm_sub_NotCurDayLastNotQR="00";
		}else {
			
			tm_sub_NotCurDayLastNotQR="-1";
		}
	}
	
	private void  Computing_ip_equal_NotCurDayLastNotQR() {
		if (NotCurDayLastNotQR_exist==1) {
			if (curTrans.getClient_ip()==null||NotCurDayLastNotQR.getClient_ip()==null) {
				ip_equal_NotCurDayLastNotQR="-1";
			} else {
				ip_equal_NotCurDayLastNotQR=(curTrans.getClient_ip().equals(NotCurDayLastNotQR.getClient_ip()))?"1":"0";
			}	
		} else {
			ip_equal_NotCurDayLastNotQR="-1";
		}
	}
	private void  Computing_NotCurDayLastNotQR_goods_tp() {
		NotCurDayLastNotQR_goods_tp=(NotCurDayLastNotQR_exist==1)?
							   NotCurDayLastNotQR.getGoods_tp():"-1";
	}
	private void  Computing_goods_tp_equal_NotCurDayLastNotQR() {
		if (NotCurDayLastNotQR_exist==1) {
			goods_tp_equal_NotCurDayLastNotQR=(curTrans.getGoods_tp().equals(NotCurDayLastNotQR.getGoods_tp()))?
										"1":"0";
		} else {
			goods_tp_equal_NotCurDayLastNotQR="-1";
		}
	}
	
	private void  Computing_mchnt_cd_equal_NotCurDayLastNotQR() {
		if (NotCurDayLastNotQR_exist==1) {
			mchnt_cd_equal_NotCurDayLastNotQR=(curTrans.getMchnt_cd().equals(NotCurDayLastNotQR.getMchnt_cd()))?
										"1":"0";
		} else {
			mchnt_cd_equal_NotCurDayLastNotQR="-1";
		}	
	}
	private void  Computing_all_equal_NotCurDayLastNotQR() {
		if (NotCurDayLastNotQR_exist==1) {
		if (mchnt_cd_equal_NotCurDayLastNotQR.equals("1")&&
			goods_tp_equal_NotCurDayLastNotQR.equals("1")&&
			trans_at_equal_NotCurDayLastNotQR.equals("1")) {
			all_equal_NotCurDayLastNotQR="1";	
		} else {
			all_equal_NotCurDayLastNotQR="0";	
		}
		}else {all_equal_NotCurDayLastNotQR="-1";	}
		
	}
	public ReduceItem getCurTrans() {
		return curTrans;
	}
	public void setCurTrans(ReduceItem curTrans) {
		this.curTrans = curTrans;
	}
	public ReduceItem getNotCurDayLastNotQR() {
		return NotCurDayLastNotQR;
	}
	public void setNotCurDayLastNotQR(ReduceItem NotCurDayLastNotQR) {
		this.NotCurDayLastNotQR = NotCurDayLastNotQR;
	}
	public int isNotCurDayLastNotQR_exist() {
		return NotCurDayLastNotQR_exist;
	}
	public void setNotCurDayLastNotQR_exist(int NotCurDayLastNotQR_exist) {
		this.NotCurDayLastNotQR_exist = NotCurDayLastNotQR_exist;
	}
	public String getNotCurDayLastNotQR_trans_at() {
		return NotCurDayLastNotQR_trans_at;
	}
	public void setNotCurDayLastNotQR_trans_at(String NotCurDayLastNotQR_trans_at) {
		this.NotCurDayLastNotQR_trans_at = NotCurDayLastNotQR_trans_at;
	}
	public String getTrans_at_equal_NotCurDayLastNotQR() {
		return trans_at_equal_NotCurDayLastNotQR;
	}
	public void setTrans_at_equal_NotCurDayLastNotQR(String trans_at_equal_NotCurDayLastNotQR) {
		this.trans_at_equal_NotCurDayLastNotQR = trans_at_equal_NotCurDayLastNotQR;
	}
	public String getTm_sub_NotCurDayLastNotQR() {
		return tm_sub_NotCurDayLastNotQR;
	}
	public void setTm_sub_NotCurDayLastNotQR(String tm_sub_NotCurDayLastNotQR) {
		this.tm_sub_NotCurDayLastNotQR = tm_sub_NotCurDayLastNotQR;
	}
	public String getIp_equal_NotCurDayLastNotQR() {
		return ip_equal_NotCurDayLastNotQR;
	}
	public void setIp_equal_NotCurDayLastNotQR(String ip_equal_NotCurDayLastNotQR) {
		this.ip_equal_NotCurDayLastNotQR = ip_equal_NotCurDayLastNotQR;
	}
	public String getNotCurDayLastNotQR_goods_tp() {
		return NotCurDayLastNotQR_goods_tp;
	}
	public void setNotCurDayLastNotQR_goods_tp(String NotCurDayLastNotQR_goods_tp) {
		this.NotCurDayLastNotQR_goods_tp = NotCurDayLastNotQR_goods_tp;
	}
	public String getGoods_tp_equal_NotCurDayLastNotQR() {
		return goods_tp_equal_NotCurDayLastNotQR;
	}

	public void setGoods_tp_equal_NotCurDayLastNotQR(String goods_tp_equal_NotCurDayLastNotQR) {
		this.goods_tp_equal_NotCurDayLastNotQR = goods_tp_equal_NotCurDayLastNotQR;
	}
	public String getMchnt_cd_equal_NotCurDayLastNotQR() {
		return mchnt_cd_equal_NotCurDayLastNotQR;
	}
	public void setMchnt_cd_equal_NotCurDayLastNotQR(String mchnt_cd_equal_NotCurDayLastNotQR) {
		this.mchnt_cd_equal_NotCurDayLastNotQR = mchnt_cd_equal_NotCurDayLastNotQR;
	}
	public String getAll_equal_NotCurDayLastNotQR() {
		return all_equal_NotCurDayLastNotQR;
	}
	public void setAll_equal_NotCurDayLastNotQR(String all_equal_NotCurDayLastNotQR) {
		this.all_equal_NotCurDayLastNotQR = all_equal_NotCurDayLastNotQR;
	}

public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb
		//��������
		.append(NotCurDayLastNotQR_exist+",")
		
		//�����������
		.append(NotCurDayLastNotQR_trans_at+",")
		.append(trans_at_equal_NotCurDayLastNotQR+",")
		
		//ʱ��  ���ʱ��
		.append(tm_sub_NotCurDayLastNotQR+",")
		
		////�ص���������
		.append(ip_equal_NotCurDayLastNotQR+",")
		
		//����
		.append( NotCurDayLastNotQR_goods_tp+",")
		.append(goods_tp_equal_NotCurDayLastNotQR+",")
		
		//�̻�
		.append(mchnt_cd_equal_NotCurDayLastNotQR+",")
		
		//�̻��������ͽ��׽����
		.append( all_equal_NotCurDayLastNotQR+",");

		 return sb.toString();
	
	
	}
}
