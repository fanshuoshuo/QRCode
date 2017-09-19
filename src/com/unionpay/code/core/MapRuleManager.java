/**
 * @author shuoshuofan
 * 
 */

package com.unionpay.code.core;

//java
import java.util.Map;
import java.util.regex.Pattern;
//hadoop
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//myclass 
import com.unionpay.code.column.ArsvcTransTableColumn;
import com.unionpay.code.entity.DataPkgKey;
import com.unionpay.utils.StringUtils;



/**
 * @author zhaojintao
 * 
 */
public class MapRuleManager {

	private String tokens[];
	private Map<String, String> dataPkgMap;
	private static Logger logger = LoggerFactory.getLogger(MapRuleManager.class);
	private static IPSeeker ipSeeker=null;
	private String ip=null;

	public MapRuleManager(IPSeeker ipSeeker){
		this.ipSeeker=ipSeeker;
	}
	public MapRuleManager(){
		
	}
	public boolean setTransStr(String[] tokens) {
		this.tokens = tokens;
		this.dataPkgMap=null;
		try {
			String dataPkg=tokens[ArsvcTransTableColumn.DATA_PKG.ordinal()].trim();
			if(!"".equals(dataPkg)){
//				this.dataPkgMap = StringUtils.getJsonMapV2(dataPkg);//测试
				this.dataPkgMap = StringUtils.getJsonMap(dataPkg);//生产
			
				ip=StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.CLIENT_IP.ordinal());
				String[] regions=ipSeeker.getRegionInfo(ip).split(",");
				
				if(dataPkgMap.containsKey(DataPkgKey.ipProvRegion.name()))
					dataPkgMap.remove(DataPkgKey.ipProvRegion.name());
				
				if(dataPkgMap.containsKey(DataPkgKey.ipCityRegion.name()))
					dataPkgMap.remove(DataPkgKey.ipCityRegion.name());
				
				dataPkgMap.put(DataPkgKey.ipProvRegion.name(), regions[0]);
				dataPkgMap.put(DataPkgKey.ipCityRegion.name(), regions[1]);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(tokens[ArsvcTransTableColumn.DATA_PKG.ordinal()].trim());
//			logger.error(tokens[ArsvcTransTableColumn.EVENT_ID.ordinal()].trim());
			return  false;
		} 
		return true;
	}

	// **********************method**************************************//

	// **********************当笔交易变量**************************************//


	
	/**
	 * 变量7：时间段
	 * 
	 * @return [06,12)=01 [12,18)=02; [18,23)=03; (23,06)=04;
	 */
	public String getTransTimeRange() {
//		String time=StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.eventDtTm.name());
		String time=StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.EVENT_OCCU_TM.ordinal());
		if(time.length()!=6){
			logger.error(time+"\t"+dataPkgMap.toString());
		}
		if (!time.equals("")) {
			int hour = Integer.parseInt(time.substring(0, 2));
			if (hour >=6 && hour < 12) {
				return "01";
			} else if (hour >=12 && hour < 18) {
				return "02";
			} else if (hour >=18 && hour < 23) {
				return "03";
			} else if (hour < 6||hour==23) {
				return "04";
			} else {
				return "";
			}
		} else
			return "";

	}
	
	/**
	 * 变量11：卡片发卡行+卡种
	 * 
	 * @return
	 */
	public String getIssInsIdCdCardTp() {
		String cardTp=StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.CARD_TP.ordinal());
		String issInsIdCd=this.getIssInsIdCd();
		
		return issInsIdCd+Constants.SEPARATOR_STRING+cardTp;
		
	}

	/**
	 * 变量12： 是否有经纬度
	 * 
	 * @return 有为1，无为0
	 */
	public int getLbsExistFlag() {		
		String item = StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.lbs.name()).replace("\\/", ",");
		if (Constants.NULL_STRING_FLAG.equals(item)) {
			return Constants.FALSE_FLAG;
		} else
			return Constants.TRUE_FLAG;

	}
	
	/**
	 * 变量13： 是否有sim卡信息
	 * 
	 * @return 有为1，无为0
	 */
	public int getDeviceNoExistFlag() {
		String item = this.getDeviceNumber();
		if (Constants.NULL_STRING_FLAG.equals(item)) {
			return Constants.FALSE_FLAG;
		} else
			return Constants.TRUE_FLAG;

	}

	/**
	 * 变量25： 设备SIM卡号码段，取前3位
	 * @return
	 */
	public String getDeviceNoRange(){
		String item=this.getDeviceNumber();
		if(item.length()>=6)
			return item.substring(3, 6);
		else 
			return Constants.NULL_STRING_FLAG;
	}

	
	/**
	 * 变量46：IP是否属于高危IP段
	 * 高危IP段：117.061.*.*
	 *           153.000.*.*
	 *           223.104.254.*
	 *           223.104.255.*
	 * @return
	 */
	public int getRiskIpFlag(){
		String ip=this.getIp();
		if(ip.startsWith("117.061") ||
		   ip.startsWith("153.000") ||
		   ip.startsWith("223.104.254") ||
		   ip.startsWith("223.104.255"))
			return Constants.TRUE_FLAG;
		else
			return Constants.FALSE_FLAG;
			
	}
	
	// ****************辅助变量（后续有用）*****************************//

	public String getTransDtTm(){
		String date=StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.EVENT_OCCU_DT.ordinal());
		String time=StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.EVENT_OCCU_TM.ordinal());
		return date+time;
	}
	
	/**
	 * 银行卡预留手机号
	 * 
	 * @return
	 */
	public String getMobile() {
		if (tokens != null
				&& !"".equals(tokens[ArsvcTransTableColumn.MOBILE.ordinal()]
						.trim())) {
			return tokens[ArsvcTransTableColumn.MOBILE.ordinal()].trim();
		} else
			return Constants.NULL_STRING_FLAG;
	}
	
	/**
	 * 设备手机号
	 * 
	 * @return
	 */
	public String getDeviceNumber() {
		if (dataPkgMap != null && dataPkgMap.containsKey("deviceNumber")) {
			String str = dataPkgMap.get("deviceNumber");
			if (str.length() == 15 && str.startsWith("1986")) {
				return "+86" + str.substring(4);
			} else if (str.length() == 16 && str.startsWith("+8686")) {
				return "+86" + str.substring(5);

			} else if (str.length() == 13 && str.startsWith("86")) {
				return "+" + str;
			} else if (str.length() == 11) {
				String reg = "^1[3|4|5|6|7|8|].*";
				if (Pattern.matches(reg, str)) {
					return "+86" + str;

				} else {
					return str;
				}
			} else if(str.length()==0){
				return Constants.NULL_STRING_FLAG;
			}else{
				
			}return dataPkgMap.get("deviceNumber");

		} else
			return Constants.NULL_STRING_FLAG;
	}
	
	/**
	 * 发卡机构取后四位
	 * @return
	 */
    public String getIssInsIdCd(){
    	String str=StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.ISS_INS_ID_CD.ordinal());
    	if(str.length()==8){
    		str=str.substring(0, 4);
    		if(Constants.issInsIdCdMap.containsKey(str))
    			str=Constants.issInsIdCdMap.get(str);
    	}
    	return str;
    }
	
	
	

	/**
	 * 交易ip
	 * @return
	 */
	public String getIp() {
//		String key=DataPkgKey.clientIp.name();
//		return StringUtils.getDataPkgValueByKey(dataPkgMap, key);
		return StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.CLIENT_IP.ordinal());

	}
	

	
	public String getArPriAcctNo() {
//		String key=DataPkgKey.arPriAcctNo.name();
//		return StringUtils.getDataPkgValueByKey(dataPkgMap, key);
		return StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.AR_PRI_ACCT_NO.ordinal());
	}
	
	public String getGoodsTp() {
//		String key=DataPkgKey.goodsTp.name();
//		return StringUtils.getDataPkgValueByKey(dataPkgMap, key);
		return StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.GOODS_TP.ordinal());

	}
	
	public String getSeid() {
		String key=DataPkgKey.seid.name();
		return StringUtils.getDataPkgValueByKey(dataPkgMap, key);
	}


	public String getPriAcctNo() {
		return StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.AR_PRI_ACCT_NO.ordinal());
	}

	/**
	 * 变量9：设备手机号与银行卡预留手机号后四位是否一致
	 * 
	 * @return 若设备手机号或银行卡预留手机号有一个为空，取-1； 若设备手机号或银行卡预留手机号后4位一致，取1，否则取0；
	 */
	public int getMobileSameFlag() {

		String str1 = this.getDeviceNoLastFour();
		String str2 = this.getMobileLastFour();

		if (Constants.NULL_STRING_FLAG.equals(str1) || Constants.NULL_STRING_FLAG.equals(str2)) {
			return Constants.NULL_INT_FLAG;
		} else if (str1.equals(str2)) {
			return Constants.TRUE_FLAG;
		} else {
			return Constants.FALSE_FLAG;
		}
	}
	


	/**
	 * 辅助变量V2：银行卡预留手机号后四位
	 * 
	 * @return
	 */
	public String getMobileLastFour() {
		if (dataPkgMap != null && dataPkgMap.containsKey("mobileLastFour")) {
			String mobileLastFour = dataPkgMap.get("mobileLastFour");
			if (mobileLastFour.length() == 4)
				return mobileLastFour;
			else
				return Constants.NULL_STRING_FLAG;
		} else {
			String str = this.getMobile();
			if (this.getMobileValidFlag() == 1) {
				return str.substring(str.length() - 4);

			} else {
				return Constants.NULL_STRING_FLAG;
			}

		}
	}

	/**
	 * 辅助变量V3：设备手机号后四位
	 * 
	 * @return
	 */
	public String getDeviceNoLastFour() {
		if (dataPkgMap != null && dataPkgMap.containsKey("deviceNoLastFour")) {
			return dataPkgMap.get("deviceNoLastFour");
		} else {
			String str = this.getDeviceNumber();
			if (this.getDeviceNoValidFlag() == 1) {
				return str.substring(str.length() - 4);

			} else {
				return Constants.NULL_STRING_FLAG;
			}
		}

	}

	/**
	 * 辅助变量V4： 当笔交易手机号是否有效
	 * 
	 * @return 若当笔交易手机号后4位为数字，则取值为1，否则为0
	 */
	public int getMobileValidFlag() {
		String item = this.getMobile();
		String reg = ".*[0-9]{4}$";
		if (Pattern.matches(reg, item)) {
			return Constants.TRUE_FLAG;
		} else {
			return Constants.FALSE_FLAG;
		}

	}

	/**
	 * 辅助变量V5：设备手机号是否为有效手机号
	 * 
	 * @return 若设备手机号长度<11或者长度>14, 取值0 若以“+”开头，返回1，否则返回0；
	 * 
	 */
	public int getDeviceNoValidFlag() {
		String item = this.getDeviceNumber();
		if (item.length() < 11 || item.length() > 14) {
			return Constants.FALSE_FLAG;
		} else if (item.startsWith("+")) {
			return Constants.TRUE_FLAG;
		} else
			return Constants.FALSE_FLAG;
	}

	/**
	 * 辅助变量V6：原因码
	 * 
	 * @return
	 */
	public String getResonCodes() {
		
		String key=DataPkgKey.reasonCodes.name();
		return StringUtils.getDataPkgValueByKey(dataPkgMap, key);
	}
	

	public int compareRegion(String name1,String name2) {
		String item1 = StringUtils.getDataPkgValueByKeyV1(dataPkgMap, name1) ;
		String item2 = StringUtils.getDataPkgValueByKeyV1(dataPkgMap, name2) ;
		return StringUtils.compareRegion(item1, item2);
	}



	// **************************华丽的分割线*******************************************//

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

//		System.out.println(getIpProvRegionCd());
		StringBuilder builder = new StringBuilder();
//		builder.append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.eventId.name()) + ",")
//		       .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.arPriAcctNo.name())  + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.dpan.name())  + ",")
//			   .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.SYS_TRA_NO.ordinal()) + ",")			   
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.eventOccuDt.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.eventOccuTm.name()) + ",")
//			   
//			   .append(this.getTransTimeRange() + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.captureMethod.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.deviceType.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.goodsTp.name()) + ",")
//			   
//			   .append(this.getIssInsIdCdCardTp() + ",")
//			   .append(this.getLbsExistFlag() + ",")
//			   .append(this.getDeviceNoExistFlag() + ",")
//			   
//			   .append(StringUtils.strContain(this.getResonCodes(), "04") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "05") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "06") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "07") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "08") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "09") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "0A") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "0C") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "0D") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "0G") + ",")
//			   .append(StringUtils.strContain(this.getResonCodes(), "0I") + ",")
//			   
//			   .append(this.getDeviceNoRange() + ",")
//			   
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.mobileProvRegion.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.mobileCityRegion.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.jwdProvRegion.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.jwdCityRegion.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.deviceNoProvRegion.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.deviceNoProvCity.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.ipProvRegion.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.ipCityRegion.name()) + ",")
//
//			   .append(this.compareRegion(DataPkgKey.jwdProvRegion.name() ,     DataPkgKey.mobileProvRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.jwdCityRegion.name() ,     DataPkgKey.mobileCityRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.deviceNoProvRegion.name() ,DataPkgKey.mobileProvRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.deviceNoProvCity.name() ,  DataPkgKey.mobileCityRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.ipProvRegion.name() ,      DataPkgKey.mobileProvRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.ipCityRegion.name() ,      DataPkgKey.mobileCityRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.jwdProvRegion.name() ,     DataPkgKey.deviceNoProvRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.jwdCityRegion.name() ,     DataPkgKey.deviceNoProvCity.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.jwdProvRegion.name() ,     DataPkgKey.ipProvRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.jwdCityRegion.name() ,     DataPkgKey.ipCityRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.deviceNoProvRegion.name(), DataPkgKey.ipProvRegion.name() )+ ",")
//			   .append(this.compareRegion(DataPkgKey.deviceNoProvCity.name() ,  DataPkgKey.ipCityRegion.name() )+ ",")
//					
//			   .append(this.getRiskIpFlag()+ ",")
//			
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.mchntUsrId.name()) + ",")	   
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.eventDtTm.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.seid.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.clientIp.name()) + ",")
//			   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.issInsIdCd.name()) + ",")	
//			   
//			   .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.MCHNT_CD.ordinal()) + ",")
//			   .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.TRANS_AT.ordinal()) + ",")	
//		
//			   .append(this.getMobile()+ ",")
//			   .append(this.getDeviceNumber())	;
//		
		builder.append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.EVENT_ID.ordinal()) + ",")
	       .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.AR_PRI_ACCT_NO.ordinal()) + ",")
		   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.dpan.name())  + ",")
		   .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.SYS_TRA_NO.ordinal()) + ",")			   
		   .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.EVENT_OCCU_DT.ordinal()) + ",")
		   .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.EVENT_OCCU_TM.ordinal())+ ",")
		   
		   .append(this.getTransTimeRange() + ",")
		   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.captureMethod.name()) + ",")
		   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.deviceType.name()) + ",")
		   .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.GOODS_TP.ordinal()) + ",")
		   
		   .append(this.getIssInsIdCdCardTp() + ",")
		   .append(this.getLbsExistFlag() + ",")
		   .append(this.getDeviceNoExistFlag() + ",")
		   
		   .append(StringUtils.strContain(this.getResonCodes(), "04") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "05") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "06") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "07") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "08") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "09") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "0A") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "0C") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "0D") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "0G") + ",")
		   .append(StringUtils.strContain(this.getResonCodes(), "0I") + ",")
		   
		   .append(this.getDeviceNoRange() + ",")
		   
		   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.mobileProvRegion.name()) + ",")
		   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.mobileCityRegion.name()) + ",")
		   .append(StringUtils.getDataPkgValueByKeyV1(dataPkgMap, DataPkgKey.jwdProvRegion.name()) + ",")
		   .append(StringUtils.getDataPkgValueByKeyV1(dataPkgMap, DataPkgKey.jwdCityRegion.name()) + ",")
		   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.deviceNoProvRegion.name()) + ",")
		   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.deviceNoProvCity.name()) + ",")
		   .append(StringUtils.getDataPkgValueByKeyV1(dataPkgMap, DataPkgKey.ipProvRegion.name()) + ",")
		   .append(StringUtils.getDataPkgValueByKeyV1(dataPkgMap, DataPkgKey.ipCityRegion.name()) + ",")

		   .append(this.compareRegion(DataPkgKey.jwdProvRegion.name() ,     DataPkgKey.mobileProvRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.jwdCityRegion.name() ,     DataPkgKey.mobileCityRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.deviceNoProvRegion.name() ,DataPkgKey.mobileProvRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.deviceNoProvCity.name() ,  DataPkgKey.mobileCityRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.ipProvRegion.name() ,      DataPkgKey.mobileProvRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.ipCityRegion.name() ,      DataPkgKey.mobileCityRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.jwdProvRegion.name() ,     DataPkgKey.deviceNoProvRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.jwdCityRegion.name() ,     DataPkgKey.deviceNoProvCity.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.jwdProvRegion.name() ,     DataPkgKey.ipProvRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.jwdCityRegion.name() ,     DataPkgKey.ipCityRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.deviceNoProvRegion.name(), DataPkgKey.ipProvRegion.name() )+ ",")
		   .append(this.compareRegion(DataPkgKey.deviceNoProvCity.name() ,  DataPkgKey.ipCityRegion.name() )+ ",")
				
		   .append(this.getRiskIpFlag()+ ",")
		
		   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.mchntUsrId.name()) + ",")	   
		   .append(this.getTransDtTm() + ",")
		   .append(StringUtils.getDataPkgValueByKey(dataPkgMap, DataPkgKey.seid.name()) + ",")
		   .append(this.getIp() + ",")
		   
		   .append(this.getIssInsIdCd() + ",")			   
		   .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.MCHNT_CD.ordinal()) + ",")
		   .append(StringUtils.getTokensValueByKey(tokens, ArsvcTransTableColumn.TRANS_AT.ordinal()) + ",")	
	
		   .append(this.getMobile()+ ",")
		   .append(this.getDeviceNumber()+ ",")
		   .append(this.getMobileSameFlag())	;
		


	   
		
		return builder.toString();
	}

	public static void main(String args[]) {
		String ip1 = "1000";
		String ip2 = "1001";
		String zxsCityCode = "1000,1100,2900,6900";
		System.out.println(zxsCityCode.contains(ip1));
		System.out.println(zxsCityCode.contains(ip2));
		System.out.println(zxsCityCode.contains(ip2));

	}

}
