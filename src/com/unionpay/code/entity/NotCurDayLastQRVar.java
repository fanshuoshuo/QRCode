/*
 * @author  shuoshuofan
 * 
 * ��¼�����ϱʶ�ά�����
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

public class NotCurDayLastQRVar {
	private ReduceItem curTrans;      //���ʽ���
	private ReduceItem notCurDayLastQR; //�ϱʶ�ά�뽻��
	
	//���׵���������
	//��������
	int  notCurDayLastQR_exist;   //�Ƿ�����ϱʶ�ά�뽻��
	//��������ڵ����ϱʶ�ά�뽻�ף�һ�±���ȫ������Ϊ -1 
	
	//�����������
	private String  notCurDayLastQR_trans_at;
	private String  trans_at_equal_notCurDayLastQR;
	
	//ʱ��  ���ʱ��
	private String  tm_sub_notCurDayLastQR;

	//�ص���������
	private String  ip_equal_notCurDayLastQR;
	
	//����
	private String   notCurDayLastQR_goods_tp;
	private String   goods_tp_equal_notCurDayLastQR;
	
	//�̻�
	private String  mchnt_cd_equal_notCurDayLastQR;
	
	//�̻����������ͣ����׽���� 
	
	private String  all_equal_notCurDayLastQR;
	
	public NotCurDayLastQRVar(ReduceItem currentRecord,ReduceItem  notCurDayLastQRRecord) {
		
		curTrans=currentRecord;
		notCurDayLastQR=notCurDayLastQRRecord;
		ComputingVars();
	}

	public void  ComputingVars() {
		
		Computing_notCurDayLastQR_exist() ;
		Computing_notCurDayLastQR_trans_at();
		Computing_trans_at_equal_notCurDayLastQR();
		Computing_tm_sub_notCurDayLastQR() ;
		Computing_ip_equal_notCurDayLastQR();
		Computing_notCurDayLastQR_goods_tp() ;
		Computing_goods_tp_equal_notCurDayLastQR();
		Computing_mchnt_cd_equal_notCurDayLastQR() ;
		Computing_all_equal_notCurDayLastQR() ;
	}
	private void  Computing_notCurDayLastQR_exist() {
	
		notCurDayLastQR_exist=(notCurDayLastQR==null||curTrans==null)?0:1;
	}   
	private void  Computing_notCurDayLastQR_trans_at(){
		
		notCurDayLastQR_trans_at=(notCurDayLastQR_exist==1)?
							  notCurDayLastQR.getTrans_at():"-1";
	}
	private void  Computing_trans_at_equal_notCurDayLastQR() {
		
		trans_at_equal_notCurDayLastQR=(notCurDayLastQR_exist==1)?
							       notCurDayLastQR.getTrans_at():"-1";
		if (notCurDayLastQR_exist==1) {
			trans_at_equal_notCurDayLastQR=(notCurDayLastQR.getTrans_at().equals(curTrans.getTrans_at()))?
				       					"1":"0";
		} else {
			trans_at_equal_notCurDayLastQR="-1";
		}					       
	}
	//����ʱ����
	private void  Computing_tm_sub_notCurDayLastQR() {
		
		if(notCurDayLastQR_exist==1) {	
			String tm=curTrans.getTfr_dt_tm();
			String notCurDayLastQR_tm=notCurDayLastQR.getTfr_dt_tm();
			//ȡʱ�����������
			tm_sub_notCurDayLastQR="00";
		}else {
			
			tm_sub_notCurDayLastQR="-1";
		}
	}
	
	private void  Computing_ip_equal_notCurDayLastQR() {
		if (notCurDayLastQR_exist==1) {
			if (curTrans.getClient_ip()==null||notCurDayLastQR.getClient_ip()==null) {
				ip_equal_notCurDayLastQR="-1";
			} else {
				ip_equal_notCurDayLastQR=(curTrans.getClient_ip().equals(notCurDayLastQR.getClient_ip()))?"1":"0";
			}	
		} else {
			ip_equal_notCurDayLastQR="-1";
		}
	}
	private void  Computing_notCurDayLastQR_goods_tp() {
		notCurDayLastQR_goods_tp=(notCurDayLastQR_exist==1)?
							   notCurDayLastQR.getGoods_tp():"-1";
	}
	private void  Computing_goods_tp_equal_notCurDayLastQR() {
		if (notCurDayLastQR_exist==1) {
			goods_tp_equal_notCurDayLastQR=(curTrans.getGoods_tp().equals(notCurDayLastQR.getGoods_tp()))?
										"1":"0";
		} else {
			goods_tp_equal_notCurDayLastQR="-1";
		}
	}
	
	private void  Computing_mchnt_cd_equal_notCurDayLastQR() {
		if (notCurDayLastQR_exist==1) {
			mchnt_cd_equal_notCurDayLastQR=(curTrans.getMchnt_cd().equals(notCurDayLastQR.getMchnt_cd()))?
										"1":"0";
		} else {
			mchnt_cd_equal_notCurDayLastQR="-1";
		}	
	}
	private void  Computing_all_equal_notCurDayLastQR() {
		if (notCurDayLastQR_exist==1) {
		if (mchnt_cd_equal_notCurDayLastQR.equals("1")&&
			goods_tp_equal_notCurDayLastQR.equals("1")&&
			trans_at_equal_notCurDayLastQR.equals("1")) {
			all_equal_notCurDayLastQR="1";	
		} else {
			all_equal_notCurDayLastQR="0";	
		}
		}else {all_equal_notCurDayLastQR="-1";	}
		
	}
	public ReduceItem getCurTrans() {
		return curTrans;
	}
	public void setCurTrans(ReduceItem curTrans) {
		this.curTrans = curTrans;
	}
	public ReduceItem getnotCurDayLastQR() {
		return notCurDayLastQR;
	}
	public void setnotCurDayLastQR(ReduceItem notCurDayLastQR) {
		this.notCurDayLastQR = notCurDayLastQR;
	}
	public int isnotCurDayLastQR_exist() {
		return notCurDayLastQR_exist;
	}
	public void setnotCurDayLastQR_exist(int notCurDayLastQR_exist) {
		this.notCurDayLastQR_exist = notCurDayLastQR_exist;
	}
	public String getnotCurDayLastQR_trans_at() {
		return notCurDayLastQR_trans_at;
	}
	public void setnotCurDayLastQR_trans_at(String notCurDayLastQR_trans_at) {
		this.notCurDayLastQR_trans_at = notCurDayLastQR_trans_at;
	}
	public String getTrans_at_equal_notCurDayLastQR() {
		return trans_at_equal_notCurDayLastQR;
	}
	public void setTrans_at_equal_notCurDayLastQR(String trans_at_equal_notCurDayLastQR) {
		this.trans_at_equal_notCurDayLastQR = trans_at_equal_notCurDayLastQR;
	}
	public String getTm_sub_notCurDayLastQR() {
		return tm_sub_notCurDayLastQR;
	}
	public void setTm_sub_notCurDayLastQR(String tm_sub_notCurDayLastQR) {
		this.tm_sub_notCurDayLastQR = tm_sub_notCurDayLastQR;
	}
	public String getIp_equal_notCurDayLastQR() {
		return ip_equal_notCurDayLastQR;
	}
	public void setIp_equal_notCurDayLastQR(String ip_equal_notCurDayLastQR) {
		this.ip_equal_notCurDayLastQR = ip_equal_notCurDayLastQR;
	}
	public String getnotCurDayLastQR_goods_tp() {
		return notCurDayLastQR_goods_tp;
	}
	public void setnotCurDayLastQR_goods_tp(String notCurDayLastQR_goods_tp) {
		this.notCurDayLastQR_goods_tp = notCurDayLastQR_goods_tp;
	}
	public String getGoods_tp_equal_notCurDayLastQR() {
		return goods_tp_equal_notCurDayLastQR;
	}

	public void setGoods_tp_equal_notCurDayLastQR(String goods_tp_equal_notCurDayLastQR) {
		this.goods_tp_equal_notCurDayLastQR = goods_tp_equal_notCurDayLastQR;
	}
	public String getMchnt_cd_equal_notCurDayLastQR() {
		return mchnt_cd_equal_notCurDayLastQR;
	}
	public void setMchnt_cd_equal_notCurDayLastQR(String mchnt_cd_equal_notCurDayLastQR) {
		this.mchnt_cd_equal_notCurDayLastQR = mchnt_cd_equal_notCurDayLastQR;
	}
	public String getAll_equal_notCurDayLastQR() {
		return all_equal_notCurDayLastQR;
	}
	public void setAll_equal_notCurDayLastQR(String all_equal_notCurDayLastQR) {
		this.all_equal_notCurDayLastQR = all_equal_notCurDayLastQR;
	}

public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb
		//��������
		.append(notCurDayLastQR_exist+",")
		
		//�����������
		.append(notCurDayLastQR_trans_at+",")
		.append(trans_at_equal_notCurDayLastQR+",")
		
		//ʱ��  ���ʱ��
		.append(tm_sub_notCurDayLastQR+",")
		
		////�ص���������
		.append(ip_equal_notCurDayLastQR+",")
		
		//����
		.append( notCurDayLastQR_goods_tp+",")
		.append(goods_tp_equal_notCurDayLastQR+",")
		
		//�̻�
		.append(mchnt_cd_equal_notCurDayLastQR+",")
		
		//�̻��������ͽ��׽����
		.append( all_equal_notCurDayLastQR+",");

		 return sb.toString();
	
	
	}
}
