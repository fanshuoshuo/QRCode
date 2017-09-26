/*
 * @author  shuoshuofan
 * 
 * 记录当日上笔二维码变量
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
	private ReduceItem curTrans;      //当笔交易
	private ReduceItem notCurDayLastQR; //上笔二维码交易
	
	//交易的衍生变量
	//存在属性
	int  notCurDayLastQR_exist;   //是否存在上笔二维码交易
	//如果不存在当日上笔二维码交易，一下变量全部设置为 -1 
	
	//金额衍生变量
	private String  notCurDayLastQR_trans_at;
	private String  trans_at_equal_notCurDayLastQR;
	
	//时间  间隔时间
	private String  tm_sub_notCurDayLastQR;

	//地点衍生变量
	private String  ip_equal_notCurDayLastQR;
	
	//交易
	private String   notCurDayLastQR_goods_tp;
	private String   goods_tp_equal_notCurDayLastQR;
	
	//商户
	private String  mchnt_cd_equal_notCurDayLastQR;
	
	//商户，交易类型，交易金额都相等 
	
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
	//计算时间间隔
	private void  Computing_tm_sub_notCurDayLastQR() {
		
		if(notCurDayLastQR_exist==1) {	
			String tm=curTrans.getTfr_dt_tm();
			String notCurDayLastQR_tm=notCurDayLastQR.getTfr_dt_tm();
			//取时间差留待完善
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
		//存在属性
		.append(notCurDayLastQR_exist+",")
		
		//金额衍生变量
		.append(notCurDayLastQR_trans_at+",")
		.append(trans_at_equal_notCurDayLastQR+",")
		
		//时间  间隔时间
		.append(tm_sub_notCurDayLastQR+",")
		
		////地点衍生变量
		.append(ip_equal_notCurDayLastQR+",")
		
		//交易
		.append( notCurDayLastQR_goods_tp+",")
		.append(goods_tp_equal_notCurDayLastQR+",")
		
		//商户
		.append(mchnt_cd_equal_notCurDayLastQR+",")
		
		//商户交易类型交易金额都相等
		.append( all_equal_notCurDayLastQR+",");

		 return sb.toString();
	
	
	}
}
