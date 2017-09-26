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
	 * ��ǰ���׶�ά�뽻��
	 */
	private ReduceItem currentRecord=null;
	
	/**
	 *  �����ϱʶ�ά�뽻��
	 */
	private ReduceItem curDayLastQRRecord=null;
	private CurDayLastQRVar curDayLastQRVar=null;
	/**
	 *  �����ϱʷǶ�ά�뽻��
	 */
	private ReduceItem curDayLastNotQRRecord=null;
	private CurDayLastNotQRVar curDayLastNotQRVar=null;
	/**
	 *  �ǵ����ϱʶ�ά�뽻��
	 */
	private ReduceItem notCurDayLastQRRecord=null;
	private NotCurDayLastQRVar notCurDayLastQRVar=null;
	/**
	 *  �ǵ����ϱʷǶ�ά�뽻��
	 */
	private ReduceItem notCurDayLastNotQRRecord=null;
	private NotCurDayLastNotQRVar notCurDayLastNotQRVar=null;
	/**
	 * ����ǰ�ϱʰ󿨽���
	 */
//	private LastLongDayVars lsBefore7DaysActRecord;

//	private ShortTimeVars cardIn60MinVars;
	
	/**
	 * �����ϱ������޿�������������
	 */
//	private LastNctRecordInfo lastNctRecordInfo;
	
	/**
	 * ��Ƭ����Ƶ������
	 */
	//private ShortTimeVars currentDayVars;
	
	/**
	 * �����¿�Ƭ��ʷ�������ų����գ�
	 */
	//private LongDayVars cardIn6MonthVars;
	
	public ReduceRuleManager() {
		//currentDayVars = new ShortTimeVars(true);
		//cardIn60MinVars =new ShortTimeVars(60,true);
		//cardIn6MonthVars=new LongDayVars(Constants.LONG_DAYS,false);
		//lsBefore7DaysActRecord=new LastLongDayVars(7);
	}
	
	/**
	 * ���ʽ������ڣ�YYYYMMdd
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
		
		// ���µ����ϱʶ�ά�룬�����ϱʷǶ�ά�뽻�ף��ǵ����ϱʶ�ά�뽻�ף��ǵ����ϱʷǶ�ά�뽻��
		
		// 1.�����ų����ս��ף�currentRecord �϶��ǿ�
		if (currentDate != null && !tempDate.equals(currentDate)) {
			//�ϱ��Ƕ�ά�룬�� ���ڸı�
			if (Constants.QRGoodsTPSet.contains(currentRecord.getGoods_tp())) {
				notCurDayLastQRRecord       = currentRecord;
				notCurDayLastNotQRRecord    = curDayLastNotQRRecord;
				
				curDayLastQRRecord=null; 
				curDayLastNotQRRecord=null;

			} else {
				//�ϱʲ��Ƕ�ά�뽻�ף��� ���ڸı�
				notCurDayLastNotQRRecord     = currentRecord;
				notCurDayLastQRRecord        = curDayLastQRRecord;
				
				curDayLastQRRecord=null; 
				curDayLastNotQRRecord=null;		
			}
		} else if (currentDate != null && tempDate.equals(currentDate)) {
			
			// �ϱ��Ƕ�ά�룬���ڲ���
			
			if (Constants.QRGoodsTPSet.contains(currentRecord.getGoods_tp())) {
				curDayLastQRRecord = currentRecord;
				
			} else {
		   // �ϱ����޿������ڲ���
				curDayLastNotQRRecord = currentRecord;
			}

		}
		*/
		//�ϱʽ��׵ı���
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
	//���ص����ϱʶ�ά�뽻��
	public CurDayLastQRVar getCurDayLastQRVar() {
		return curDayLastQRVar;
	}
	//�����ϱʷǶ�ά��
	public CurDayLastNotQRVar getCurDayLastNotQRVar() {
		return curDayLastNotQRVar;
	}
	//�ǵ����ϱʶ�ά��
	public NotCurDayLastQRVar getNotCurDayLastQRVar() {
		return notCurDayLastQRVar;
	}
	//�ǵ����ϱʷǶ�ά�뽻��
	public NotCurDayLastNotQRVar getNotCurDayLastNotQRVar() {
		return notCurDayLastNotQRVar;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}

}
