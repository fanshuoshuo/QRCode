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
import com.unionpay.nfc.entity.LastLongDayVars;
import com.unionpay.nfc.entity.LastNctRecordInfo;
import com.unionpay.nfc.entity.LongDayVars;
import com.unionpay.nfc.entity.LongDayVars3;
import com.unionpay.nfc.entity.MapItem;
import com.unionpay.nfc.entity.ShortTimeVars;
import com.unionpay.utils.TimeUtils;


public class ReduceRuleManager {

	private static Logger logger = LoggerFactory.getLogger(ReduceRuleManager.class);
	
	/**
	 * 当前交易：绑卡或者无卡交易
	 */
	private MapItem currentRecord=null;
	
	/**
	 *  当日上笔无卡交易
	 */
	private MapItem lastNoCardTransRecord=null;
	
	/**
	 *  当日上笔绑卡交易
	 */
	private MapItem lastActiveCardTransRecord=null;
	
	
	/**
	 *  非当日上笔无卡交易
	 */
	private MapItem lastNotCurrentDayNctRecord=null;
	
	/**
	 *  非当日上笔绑卡交易
	 */
	private MapItem lastNotCurrentDayActRecord=null;
	
	

	/**
	 * 七日前上笔绑卡交易
	 */
	private LastLongDayVars lsBefore7DaysActRecord;

	private ShortTimeVars cardIn60MinVars;
	
	/**
	 * 当日上笔线上无卡交易衍生变量
	 */
	private LastNctRecordInfo lastNctRecordInfo;
	
	/**
	 * 卡片当日频繁特征
	 */
	private ShortTimeVars currentDayVars;
	
	/**
	 * 近六月卡片历史特征（排除当日）
	 */
	private LongDayVars cardIn6MonthVars;
	


	public ReduceRuleManager() {

		currentDayVars = new ShortTimeVars(true);
		cardIn60MinVars =new ShortTimeVars(60,true);
		cardIn6MonthVars=new LongDayVars(Constants.LONG_DAYS,false);
		lsBefore7DaysActRecord=new LastLongDayVars(7);
	}

	/**
	 * 当笔交易日期：YYYYMMdd
	 */
	private Date currentDate;



	public void setCurrentTrans(String[] tokens) {

		MapItem tempRecord = new MapItem(tokens);
		String tempStr = tempRecord.getTransDtTm().substring(0, 8);
		Date tempDate = TimeUtils.strToDate(tempStr);


		if(currentDate==null){
			lastNoCardTransRecord=null;
			lastActiveCardTransRecord=null;
			lastNotCurrentDayNctRecord=null;
			lastNotCurrentDayActRecord=null;
		}
		
		// 更新上笔交易:当日,非当日，绑卡、非绑卡
		
		// 1.更新排除当日交易：currentRecord 肯定非空
		if (currentDate != null && !tempDate.equals(currentDate)) {
			//上笔是绑卡，且 日期改变
			if (Constants.actGoodsTpSet.contains(currentRecord.getGoodsTp())) {
				lastNotCurrentDayActRecord = currentRecord;
				lastNotCurrentDayNctRecord = lastNoCardTransRecord;
				lastNoCardTransRecord = null;
				lastActiveCardTransRecord = null;

			} else {
				//上笔是无卡交易，且 日期改变
				lastNotCurrentDayNctRecord = currentRecord;
				lastNotCurrentDayActRecord = lastActiveCardTransRecord;
				lastNoCardTransRecord = null;
				lastActiveCardTransRecord = null;
			}

		} else if (currentDate != null && tempDate.equals(currentDate)) {
			// 上笔是绑卡，日期不变
			if (Constants.actGoodsTpSet.contains(currentRecord.getGoodsTp())) {
				lastActiveCardTransRecord = currentRecord;

			} else {
		   // 上笔是无卡，日期不变
				lastNoCardTransRecord = currentRecord;
			}

		}
	

		// 更新当笔交易
		this.currentRecord = tempRecord;
		currentDate = tempDate;
	
		lastNctRecordInfo=new LastNctRecordInfo(currentRecord,lastNoCardTransRecord);
		cardIn60MinVars.addTimeRangeRecord(currentRecord);
		currentDayVars.addCurrentDayRecord(currentRecord);
		lsBefore7DaysActRecord.addRecord(currentRecord);
		cardIn6MonthVars.addRecord(currentRecord);



	}


	//**************上笔交易信息**************************//
	

	
	//**************辅助函数**************************//
	



	// ===================getter and setter============================//

	/**
	 * @return the currentRecord
	 */
	public MapItem getCurrentRecord() {
		return currentRecord;
	}

	/**
	 * @param currentRecord the currentRecord to set
	 */
	public void setCurrentRecord(MapItem currentRecord) {
		this.currentRecord = currentRecord;
	}


	/**
	 * @return the currentDayVars
	 */
	public ShortTimeVars getCurrentDayVars() {
		return currentDayVars;
	}


	/**
	 * @param currentDayVars the currentDayVars to set
	 */
	public void setCurrentDayVars(ShortTimeVars currentDayVars) {
		this.currentDayVars = currentDayVars;
	}




	public LastNctRecordInfo getLastNctRecordInfo() {
		return lastNctRecordInfo;
	}


	public void setLastNctRecordInfo(LastNctRecordInfo lastNctRecordInfo) {
		this.lastNctRecordInfo = lastNctRecordInfo;
	}





	public LastLongDayVars getLsBefore7DaysActRecord() {
		return lsBefore7DaysActRecord;
	}


	public void setLsBefore7DaysActRecord(LastLongDayVars lsBefore7DaysActRecord) {
		this.lsBefore7DaysActRecord = lsBefore7DaysActRecord;
	}


	public ShortTimeVars getCardIn60MinVars() {
		return cardIn60MinVars;
	}


	public void setCardIn60MinVars(ShortTimeVars cardIn60MinVars) {
		this.cardIn60MinVars = cardIn60MinVars;
	}


	public LongDayVars getCardIn6MonthVars() {
		return cardIn6MonthVars;
	}


	public void setCardIn6MonthVars(LongDayVars cardIn6MonthVars) {
		this.cardIn6MonthVars = cardIn6MonthVars;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		

		return sb.toString();
	}

}
