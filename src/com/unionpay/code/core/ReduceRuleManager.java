/*
 * @author shuoshuofan
 * 
 */

package com.unionpay.code.core;
//java
import java.util.Date;
//hadoop
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//
//import com.unionpay.code.entity.LastLongDayVars;
//import com.unionpay.code.entity.LastNctRecordInfo;
//import com.unionpay.code.entity.LongDayVars;
//import com.unionpay.code.entity.LongDayVars3;
import com.unionpay.code.entity.ReduceItem;
//import com.unionpay.code.entity.ShortTimeVars;
import   com.unionpay.code.entity.*;
import com.unionpay.utils.TimeUtils;


public class ReduceRuleManager {

	private static Logger logger = LoggerFactory.getLogger(ReduceRuleManager.class);
	/**
	 * 当前交易二维码交易
	 */
	private ReduceItem currentRecord=null;
	
	/**
	 *  当日上笔二维码交易
	 */
	private ReduceItem curDayLastQRRecord=null;
	private CurDayLastQRVar curDayLastQRVar=null;
	/**
	 *  当日上笔非二维码交易
	 */
	private ReduceItem curDayLastNotQRRecord=null;
	private CurDayLastNotQRVar curDayLastNotQRVar=null;
	/**
	 *  非当日上笔二维码交易
	 */
	private ReduceItem notCurDayLastQRRecord=null;
	private NotCurDayLastQRVar notCurDayLastQRVar=null;
	/**
	 *  非当日上笔非二维码交易
	 */
	private ReduceItem notCurDayLastNotQRRecord=null;
	private NotCurDayLastNotQRVar notCurDayLastNotQRVar=null;
	/**
	 * 七日前上笔绑卡交易
	 */
//	private LastLongDayVars lsBefore7DaysActRecord;

//	private ShortTimeVars cardIn60MinVars;
	
	/**
	 * 当日上笔线上无卡交易衍生变量
	 */
//	private LastNctRecordInfo lastNctRecordInfo;
	
	/**
	 * 卡片当日频繁特征
	 */
	//private ShortTimeVars currentDayVars;
	
	/**
	 * 近六月卡片历史特征（排除当日）
	 */
	//private LongDayVars cardIn6MonthVars;
	
	public ReduceRuleManager() {
		//currentDayVars = new ShortTimeVars(true);
		//cardIn60MinVars =new ShortTimeVars(60,true);
		//cardIn6MonthVars=new LongDayVars(Constants.LONG_DAYS,false);
		//lsBefore7DaysActRecord=new LastLongDayVars(7);
	}
	
	/**
	 * 当笔交易日期：YYYYMMdd
	 */
	private Date currentDate;

	public void setCurrentTrans(String[] tokens) {
		
		currentRecord=new ReduceItem(tokens);
		/*
		ReduceItem tempRecord = new ReduceItem(tokens);
		String tempTm = tempRecord.getTfr_dt_tm().substring(0, 8);
		Date tempDate = TimeUtils.strToDate(tempTm);

		if(currentDate==null){
			
			curDayLastQRRecord=null;
			curDayLastNotQRRecord=null;
			notCurDayLastQRRecord=null;
			notCurDayLastNotQRRecord=null;
		}
		
		// 更新当日上笔二维码，当日上笔非二维码交易，非当日上笔二维码交易，非当日上笔非二维码交易
		
		// 1.更新排除当日交易：currentRecord 肯定非空
		if (currentDate != null && !tempDate.equals(currentDate)) {
			//上笔是二维码，且 日期改变
			if (Constants.QRGoodsTPSet.contains(currentRecord.getGoods_tp())) {
				notCurDayLastQRRecord       = currentRecord;
				notCurDayLastNotQRRecord    = curDayLastNotQRRecord;
				
				curDayLastQRRecord=null; 
				curDayLastNotQRRecord=null;

			} else {
				//上笔不是二维码交易，且 日期改变
				notCurDayLastNotQRRecord     = currentRecord;
				notCurDayLastQRRecord        = curDayLastQRRecord;
				
				curDayLastQRRecord=null; 
				curDayLastNotQRRecord=null;		
			}
		} else if (currentDate != null && tempDate.equals(currentDate)) {
			
			// 上笔是二维码，日期不变
			
			if (Constants.QRGoodsTPSet.contains(currentRecord.getGoods_tp())) {
				curDayLastQRRecord = currentRecord;
				
			} else {
		   // 上笔是无卡，日期不变
				curDayLastNotQRRecord = currentRecord;
			}

		}
		*/
		//上笔交易的变量
		//curDayLastQRVar=new CurDayLastQRVar(currentRecord,curDayLastQRRecord);
		//curDayLastNotQRVar=new CurDayLastNotQRVar(currentRecord,curDayLastNotQRRecord);
		//notCurDayLastQRVar =new NotCurDayLastQRVar(currentRecord,notCurDayLastQRRecord);
		//notCurDayLastNotQRVar =new NotCurDayLastNotQRVar(currentRecord,notCurDayLastNotQRRecord);
		/*	
		//cardIn60MinVars.addTimeRangeRecord(currentRecord);
		//currentDayVars.addCurrentDayRecord(currentRecord);
		//lsBefore7DaysActRecord.addRecord(currentRecord);
		//cardIn6MonthVars.addRecord(currentRecord);
		*/

	}

	// ===================getter and setter============================//
	
	/**
	 * @return the currentRecord
	 */
	public ReduceItem getCurrentRecord() {
		return currentRecord;
	}

	/**
	 * @param currentRecord the currentRecord to set
	 */
	public void setCurrentRecord(ReduceItem currentRecord) {
		this.currentRecord = currentRecord;
	}
	//返回当日上笔二维码交易
	public CurDayLastQRVar getCurDayLastQRVar() {
		return curDayLastQRVar;
	}
	//当日上笔非二维码
	public CurDayLastNotQRVar getCurDayLastNotQRVar() {
		return curDayLastNotQRVar;
	}
	//非当日上笔二维码
	public NotCurDayLastQRVar getNotCurDayLastQRVar() {
		return notCurDayLastQRVar;
	}
	//非当日上笔非二维码交易
	public NotCurDayLastNotQRVar getNotCurDayLastNotQRVar() {
		return notCurDayLastNotQRVar;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}

}
