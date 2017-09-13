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
	 * ��ǰ���ף��󿨻����޿�����
	 */
	private MapItem currentRecord=null;
	
	/**
	 *  �����ϱ��޿�����
	 */
	private MapItem lastNoCardTransRecord=null;
	
	/**
	 *  �����ϱʰ󿨽���
	 */
	private MapItem lastActiveCardTransRecord=null;
	
	
	/**
	 *  �ǵ����ϱ��޿�����
	 */
	private MapItem lastNotCurrentDayNctRecord=null;
	
	/**
	 *  �ǵ����ϱʰ󿨽���
	 */
	private MapItem lastNotCurrentDayActRecord=null;
	
	

	/**
	 * ����ǰ�ϱʰ󿨽���
	 */
	private LastLongDayVars lsBefore7DaysActRecord;

	private ShortTimeVars cardIn60MinVars;
	
	/**
	 * �����ϱ������޿�������������
	 */
	private LastNctRecordInfo lastNctRecordInfo;
	
	/**
	 * ��Ƭ����Ƶ������
	 */
	private ShortTimeVars currentDayVars;
	
	/**
	 * �����¿�Ƭ��ʷ�������ų����գ�
	 */
	private LongDayVars cardIn6MonthVars;
	


	public ReduceRuleManager() {

		currentDayVars = new ShortTimeVars(true);
		cardIn60MinVars =new ShortTimeVars(60,true);
		cardIn6MonthVars=new LongDayVars(Constants.LONG_DAYS,false);
		lsBefore7DaysActRecord=new LastLongDayVars(7);
	}

	/**
	 * ���ʽ������ڣ�YYYYMMdd
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
		
		// �����ϱʽ���:����,�ǵ��գ��󿨡��ǰ�
		
		// 1.�����ų����ս��ף�currentRecord �϶��ǿ�
		if (currentDate != null && !tempDate.equals(currentDate)) {
			//�ϱ��ǰ󿨣��� ���ڸı�
			if (Constants.actGoodsTpSet.contains(currentRecord.getGoodsTp())) {
				lastNotCurrentDayActRecord = currentRecord;
				lastNotCurrentDayNctRecord = lastNoCardTransRecord;
				lastNoCardTransRecord = null;
				lastActiveCardTransRecord = null;

			} else {
				//�ϱ����޿����ף��� ���ڸı�
				lastNotCurrentDayNctRecord = currentRecord;
				lastNotCurrentDayActRecord = lastActiveCardTransRecord;
				lastNoCardTransRecord = null;
				lastActiveCardTransRecord = null;
			}

		} else if (currentDate != null && tempDate.equals(currentDate)) {
			// �ϱ��ǰ󿨣����ڲ���
			if (Constants.actGoodsTpSet.contains(currentRecord.getGoodsTp())) {
				lastActiveCardTransRecord = currentRecord;

			} else {
		   // �ϱ����޿������ڲ���
				lastNoCardTransRecord = currentRecord;
			}

		}
	

		// ���µ��ʽ���
		this.currentRecord = tempRecord;
		currentDate = tempDate;
	
		lastNctRecordInfo=new LastNctRecordInfo(currentRecord,lastNoCardTransRecord);
		cardIn60MinVars.addTimeRangeRecord(currentRecord);
		currentDayVars.addCurrentDayRecord(currentRecord);
		lsBefore7DaysActRecord.addRecord(currentRecord);
		cardIn6MonthVars.addRecord(currentRecord);



	}


	//**************�ϱʽ�����Ϣ**************************//
	

	
	//**************��������**************************//
	



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
