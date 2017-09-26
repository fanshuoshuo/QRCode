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

public class CurDayLastQRVar {
	private ReduceItem curTrans;      //���ʽ���
	private ReduceItem curDayLastQR; //�ϱʶ�ά�뽻��
	
	//���׵���������
	//��������
	int  curDayLastQR_exist;   //�Ƿ�����ϱʶ�ά�뽻��
	//��������ڵ����ϱʶ�ά�뽻�ף�һ�±���ȫ������Ϊ -1 
	
	//�����������
	private String  curDayLastQR_trans_at;
	private String  trans_at_equal_curDayLastQR;
	
	//ʱ��  ���ʱ��
	private String  tm_sub_curDayLastQR;

	//�ص���������
	private String  ip_equal_curDayLastQR;
	
	//����
	private String   curDayLastQR_goods_tp;
	private String   goods_tp_equal_curDayLastQR;
	
	//�̻�
	private String  mchnt_cd_equal_curDayLastQR;
	
	//�̻����������ͣ����׽���� 
	
	private String  all_equal_curDayLastQR;
	public  CurDayLastQRVar(ReduceItem currentRecord,ReduceItem  curDayLastQRRecord) {
		
		curTrans=currentRecord;
		curDayLastQR=curDayLastQRRecord;
		ComputingVars();
	}
	
	public void  ComputingVars() {
		
		Computing_curDayLastQR_exist() ;
		Computing_curDayLastQR_trans_at();
		Computing_trans_at_equal_curDayLastQR();
		Computing_tm_sub_curDayLastQR() ;
		Computing_ip_equal_curDayLastQR();
		Computing_curDayLastQR_goods_tp() ;
		Computing_goods_tp_equal_curDayLastQR();
		Computing_mchnt_cd_equal_curDayLastQR() ;
		Computing_all_equal_curDayLastQR() ;
	}
	private void  Computing_curDayLastQR_exist() {
	
		curDayLastQR_exist=(curDayLastQR==null||curTrans==null)?0:1;
	}   
	private void  Computing_curDayLastQR_trans_at(){
		
		curDayLastQR_trans_at=(curDayLastQR_exist==1)?
							  curDayLastQR.getTrans_at():"-1";
	}
	private void  Computing_trans_at_equal_curDayLastQR() {
		
		trans_at_equal_curDayLastQR=(curDayLastQR_exist==1)?
							       curDayLastQR.getTrans_at():"-1";
		if (curDayLastQR_exist==1) {
			trans_at_equal_curDayLastQR=(curDayLastQR.getTrans_at().equals(curTrans.getTrans_at()))?
				       					"1":"0";
		} else {
			trans_at_equal_curDayLastQR="-1";
		}					       
	}
	//����ʱ����
	private void  Computing_tm_sub_curDayLastQR() {
		
		if(curDayLastQR_exist==1) {	
			String tm=curTrans.getTfr_dt_tm();
			String curDayLastQR_tm=curDayLastQR.getTfr_dt_tm();
			//ȡʱ�����������
			tm_sub_curDayLastQR="00";
		}else {
			
			tm_sub_curDayLastQR="-1";
		}
	}
	
	private void  Computing_ip_equal_curDayLastQR() {
		if (curDayLastQR_exist==1) {
			if (curTrans.getClient_ip()==null||curDayLastQR.getClient_ip()==null) {
				ip_equal_curDayLastQR="-1";
			} else {
				ip_equal_curDayLastQR=(curTrans.getClient_ip().equals(curDayLastQR.getClient_ip()))?"1":"0";
			}	
		} else {
			ip_equal_curDayLastQR="-1";
		}
	}
	private void  Computing_curDayLastQR_goods_tp() {
		curDayLastQR_goods_tp=(curDayLastQR_exist==1)?
							   curDayLastQR.getGoods_tp():"-1";
	}
	private void  Computing_goods_tp_equal_curDayLastQR() {
		if (curDayLastQR_exist==1) {
			goods_tp_equal_curDayLastQR=(curTrans.getGoods_tp().equals(curDayLastQR.getGoods_tp()))?
										"1":"0";
		} else {
			goods_tp_equal_curDayLastQR="-1";
		}
	}
	
	private void  Computing_mchnt_cd_equal_curDayLastQR() {
		if (curDayLastQR_exist==1) {
			mchnt_cd_equal_curDayLastQR=(curTrans.getMchnt_cd().equals(curDayLastQR.getMchnt_cd()))?
										"1":"0";
		} else {
			mchnt_cd_equal_curDayLastQR="-1";
		}	
	}
	private void  Computing_all_equal_curDayLastQR() {
		if (curDayLastQR_exist==1) {
		if (mchnt_cd_equal_curDayLastQR.equals("1")&&
			goods_tp_equal_curDayLastQR.equals("1")&&
			trans_at_equal_curDayLastQR.equals("1")) {
			all_equal_curDayLastQR="1";	
		} else {
			all_equal_curDayLastQR="0";	
		}
		}else {all_equal_curDayLastQR="-1";	}
		
	}
	public ReduceItem getCurTrans() {
		return curTrans;
	}
	public void setCurTrans(ReduceItem curTrans) {
		this.curTrans = curTrans;
	}
	public ReduceItem getCurDayLastQR() {
		return curDayLastQR;
	}
	public void setCurDayLastQR(ReduceItem curDayLastQR) {
		this.curDayLastQR = curDayLastQR;
	}
	public int isCurDayLastQR_exist() {
		return curDayLastQR_exist;
	}
	public void setCurDayLastQR_exist(int curDayLastQR_exist) {
		this.curDayLastQR_exist = curDayLastQR_exist;
	}
	public String getCurDayLastQR_trans_at() {
		return curDayLastQR_trans_at;
	}
	public void setCurDayLastQR_trans_at(String curDayLastQR_trans_at) {
		this.curDayLastQR_trans_at = curDayLastQR_trans_at;
	}
	public String getTrans_at_equal_curDayLastQR() {
		return trans_at_equal_curDayLastQR;
	}
	public void setTrans_at_equal_curDayLastQR(String trans_at_equal_curDayLastQR) {
		this.trans_at_equal_curDayLastQR = trans_at_equal_curDayLastQR;
	}
	public String getTm_sub_curDayLastQR() {
		return tm_sub_curDayLastQR;
	}
	public void setTm_sub_curDayLastQR(String tm_sub_curDayLastQR) {
		this.tm_sub_curDayLastQR = tm_sub_curDayLastQR;
	}
	public String getIp_equal_curDayLastQR() {
		return ip_equal_curDayLastQR;
	}
	public void setIp_equal_curDayLastQR(String ip_equal_curDayLastQR) {
		this.ip_equal_curDayLastQR = ip_equal_curDayLastQR;
	}
	public String getCurDayLastQR_goods_tp() {
		return curDayLastQR_goods_tp;
	}
	public void setCurDayLastQR_goods_tp(String curDayLastQR_goods_tp) {
		this.curDayLastQR_goods_tp = curDayLastQR_goods_tp;
	}
	public String getGoods_tp_equal_curDayLastQR() {
		return goods_tp_equal_curDayLastQR;
	}

	public void setGoods_tp_equal_curDayLastQR(String goods_tp_equal_curDayLastQR) {
		this.goods_tp_equal_curDayLastQR = goods_tp_equal_curDayLastQR;
	}
	public String getMchnt_cd_equal_curDayLastQR() {
		return mchnt_cd_equal_curDayLastQR;
	}
	public void setMchnt_cd_equal_curDayLastQR(String mchnt_cd_equal_curDayLastQR) {
		this.mchnt_cd_equal_curDayLastQR = mchnt_cd_equal_curDayLastQR;
	}
	public String getAll_equal_curDayLastQR() {
		return all_equal_curDayLastQR;
	}
	public void setAll_equal_curDayLastQR(String all_equal_curDayLastQR) {
		this.all_equal_curDayLastQR = all_equal_curDayLastQR;
	}

public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb
		//��������
		.append(curDayLastQR_exist+",")
		
		//�����������
		.append(curDayLastQR_trans_at+",")
		.append(trans_at_equal_curDayLastQR+",")
		
		//ʱ��  ���ʱ��
		.append(tm_sub_curDayLastQR+",")
		
		////�ص���������
		.append(ip_equal_curDayLastQR+",")
		
		//����
		.append( curDayLastQR_goods_tp+",")
		.append(goods_tp_equal_curDayLastQR+",")
		
		//�̻�
		.append(mchnt_cd_equal_curDayLastQR+",")
		
		//�̻��������ͽ��׽����
		.append( all_equal_curDayLastQR+",");

		 return sb.toString();
	
	
	}
}
