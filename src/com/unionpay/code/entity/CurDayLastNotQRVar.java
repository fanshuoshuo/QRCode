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

public class CurDayLastNotQRVar {
	private ReduceItem curTrans;      //当笔交易
	private ReduceItem curDayLastNotQR; //上笔二维码交易
	
	//交易的衍生变量
	//存在属性
	int  curDayLastNotQR_exist;   //是否存在上笔二维码交易
	//如果不存在当日上笔二维码交易，一下变量全部设置为 -1 
	
	//金额衍生变量
	private String  curDayLastNotQR_trans_at;
	private String  trans_at_equal_curDayLastNotQR;
	
	//时间  间隔时间
	private String  tm_sub_curDayLastNotQR;

	//地点衍生变量
	private String  ip_equal_curDayLastNotQR;
	
	//交易
	private String   curDayLastNotQR_goods_tp;
	private String   goods_tp_equal_curDayLastNotQR;
	
	//商户
	private String  mchnt_cd_equal_curDayLastNotQR;
	
	//商户，交易类型，交易金额都相等 
	
	private String  all_equal_curDayLastNotQR;
	
	public CurDayLastNotQRVar(ReduceItem currentRecord,ReduceItem  curDayLastNotQRRecord) {
		
		curTrans=currentRecord;
		curDayLastNotQR=curDayLastNotQRRecord;
		ComputingVars();
	}
	public void  ComputingVars() {
		
		Computing_curDayLastNotQR_exist() ;
		Computing_curDayLastNotQR_trans_at();
		Computing_trans_at_equal_curDayLastNotQR();
		Computing_tm_sub_curDayLastNotQR() ;
		Computing_ip_equal_curDayLastNotQR();
		Computing_curDayLastNotQR_goods_tp() ;
		Computing_goods_tp_equal_curDayLastNotQR();
		Computing_mchnt_cd_equal_curDayLastNotQR() ;
		Computing_all_equal_curDayLastNotQR() ;
	}
	private void  Computing_curDayLastNotQR_exist() {
	
		curDayLastNotQR_exist=(curDayLastNotQR==null||curTrans==null)?0:1;
	}   
	private void  Computing_curDayLastNotQR_trans_at(){
		
		curDayLastNotQR_trans_at=(curDayLastNotQR_exist==1)?
							  curDayLastNotQR.getTrans_at():"-1";
	}
	private void  Computing_trans_at_equal_curDayLastNotQR() {
		
		trans_at_equal_curDayLastNotQR=(curDayLastNotQR_exist==1)?
							       curDayLastNotQR.getTrans_at():"-1";
		if (curDayLastNotQR_exist==1) {
			trans_at_equal_curDayLastNotQR=(curDayLastNotQR.getTrans_at().equals(curTrans.getTrans_at()))?
				       					"1":"0";
		} else {
			trans_at_equal_curDayLastNotQR="-1";
		}					       
	}
	//计算时间间隔
	private void  Computing_tm_sub_curDayLastNotQR() {
		
		if(curDayLastNotQR_exist==1) {	
			String tm=curTrans.getTfr_dt_tm();
			String curDayLastNotQR_tm=curDayLastNotQR.getTfr_dt_tm();
			//取时间差留待完善
			tm_sub_curDayLastNotQR="00";
		}else {
			
			tm_sub_curDayLastNotQR="-1";
		}
	}
	
	private void  Computing_ip_equal_curDayLastNotQR() {
		if (curDayLastNotQR_exist==1) {
			if (curTrans.getClient_ip()==null||curDayLastNotQR.getClient_ip()==null) {
				ip_equal_curDayLastNotQR="-1";
			} else {
				ip_equal_curDayLastNotQR=(curTrans.getClient_ip().equals(curDayLastNotQR.getClient_ip()))?"1":"0";
			}	
		} else {
			ip_equal_curDayLastNotQR="-1";
		}
	}
	private void  Computing_curDayLastNotQR_goods_tp() {
		curDayLastNotQR_goods_tp=(curDayLastNotQR_exist==1)?
							   curDayLastNotQR.getGoods_tp():"-1";
	}
	private void  Computing_goods_tp_equal_curDayLastNotQR() {
		if (curDayLastNotQR_exist==1) {
			goods_tp_equal_curDayLastNotQR=(curTrans.getGoods_tp().equals(curDayLastNotQR.getGoods_tp()))?
										"1":"0";
		} else {
			goods_tp_equal_curDayLastNotQR="-1";
		}
	}
	
	private void  Computing_mchnt_cd_equal_curDayLastNotQR() {
		if (curDayLastNotQR_exist==1) {
			mchnt_cd_equal_curDayLastNotQR=(curTrans.getMchnt_cd().equals(curDayLastNotQR.getMchnt_cd()))?
										"1":"0";
		} else {
			mchnt_cd_equal_curDayLastNotQR="-1";
		}	
	}
	private void  Computing_all_equal_curDayLastNotQR() {
		if (curDayLastNotQR_exist==1) {
		if (mchnt_cd_equal_curDayLastNotQR.equals("1")&&
			goods_tp_equal_curDayLastNotQR.equals("1")&&
			trans_at_equal_curDayLastNotQR.equals("1")) {
			all_equal_curDayLastNotQR="1";	
		} else {
			all_equal_curDayLastNotQR="0";	
		}
		}else {all_equal_curDayLastNotQR="-1";	}
		
	}
	public ReduceItem getCurTrans() {
		return curTrans;
	}
	public void setCurTrans(ReduceItem curTrans) {
		this.curTrans = curTrans;
	}
	public ReduceItem getcurDayLastNotQR() {
		return curDayLastNotQR;
	}
	public void setcurDayLastNotQR(ReduceItem curDayLastNotQR) {
		this.curDayLastNotQR = curDayLastNotQR;
	}
	public int iscurDayLastNotQR_exist() {
		return curDayLastNotQR_exist;
	}
	public void setcurDayLastNotQR_exist(int curDayLastNotQR_exist) {
		this.curDayLastNotQR_exist = curDayLastNotQR_exist;
	}
	public String getcurDayLastNotQR_trans_at() {
		return curDayLastNotQR_trans_at;
	}
	public void setcurDayLastNotQR_trans_at(String curDayLastNotQR_trans_at) {
		this.curDayLastNotQR_trans_at = curDayLastNotQR_trans_at;
	}
	public String getTrans_at_equal_curDayLastNotQR() {
		return trans_at_equal_curDayLastNotQR;
	}
	public void setTrans_at_equal_curDayLastNotQR(String trans_at_equal_curDayLastNotQR) {
		this.trans_at_equal_curDayLastNotQR = trans_at_equal_curDayLastNotQR;
	}
	public String getTm_sub_curDayLastNotQR() {
		return tm_sub_curDayLastNotQR;
	}
	public void setTm_sub_curDayLastNotQR(String tm_sub_curDayLastNotQR) {
		this.tm_sub_curDayLastNotQR = tm_sub_curDayLastNotQR;
	}
	public String getIp_equal_curDayLastNotQR() {
		return ip_equal_curDayLastNotQR;
	}
	public void setIp_equal_curDayLastNotQR(String ip_equal_curDayLastNotQR) {
		this.ip_equal_curDayLastNotQR = ip_equal_curDayLastNotQR;
	}
	public String getcurDayLastNotQR_goods_tp() {
		return curDayLastNotQR_goods_tp;
	}
	public void setcurDayLastNotQR_goods_tp(String curDayLastNotQR_goods_tp) {
		this.curDayLastNotQR_goods_tp = curDayLastNotQR_goods_tp;
	}
	public String getGoods_tp_equal_curDayLastNotQR() {
		return goods_tp_equal_curDayLastNotQR;
	}

	public void setGoods_tp_equal_curDayLastNotQR(String goods_tp_equal_curDayLastNotQR) {
		this.goods_tp_equal_curDayLastNotQR = goods_tp_equal_curDayLastNotQR;
	}
	public String getMchnt_cd_equal_curDayLastNotQR() {
		return mchnt_cd_equal_curDayLastNotQR;
	}
	public void setMchnt_cd_equal_curDayLastNotQR(String mchnt_cd_equal_curDayLastNotQR) {
		this.mchnt_cd_equal_curDayLastNotQR = mchnt_cd_equal_curDayLastNotQR;
	}
	public String getAll_equal_curDayLastNotQR() {
		return all_equal_curDayLastNotQR;
	}
	public void setAll_equal_curDayLastNotQR(String all_equal_curDayLastNotQR) {
		this.all_equal_curDayLastNotQR = all_equal_curDayLastNotQR;
	}

public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb
		//存在属性
		.append(curDayLastNotQR_exist+",")
		
		//金额衍生变量
		.append(curDayLastNotQR_trans_at+",")
		.append(trans_at_equal_curDayLastNotQR+",")
		
		//时间  间隔时间
		.append(tm_sub_curDayLastNotQR+",")
		
		////地点衍生变量
		.append(ip_equal_curDayLastNotQR+",")
		
		//交易
		.append( curDayLastNotQR_goods_tp+",")
		.append(goods_tp_equal_curDayLastNotQR+",")
		
		//商户
		.append(mchnt_cd_equal_curDayLastNotQR+",")
		
		//商户交易类型交易金额都相等
		.append( all_equal_curDayLastNotQR+",");

		 return sb.toString();
	
	
	}
}
