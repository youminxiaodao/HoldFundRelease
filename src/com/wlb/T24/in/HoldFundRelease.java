package com.wlb.T24.in;

import java.util.HashMap;

import com.wlb.T24.in.factory.HoldFundReleaseFactory;

public class HoldFundRelease {
	public static String inRoutineFormat(String inMsg) {
		String outMsg = "";
		if (!"".equals(inMsg)) {
			HoldFundReleaseFactory holdFundReleaseFactory = new HoldFundReleaseFactory();
			String type = inMsg.substring(0, 27);
			com.wlb.T24.in.factory.HoldFundRelease type2 = holdFundReleaseFactory
					.getType(type);
			outMsg = type2.retMsg(inMsg);
		}
		return outMsg;
	}

	public static String outRoutineFormat(String outMsg) {

		HashMap<String, String> map = new HashMap<String, String>();
		// HashMap<String, Entity> hashMap = new HashMap<String, Entity>();
		// map.put("", outMsg);
		String substring = outMsg.substring(34, 35);
		if ("1".equals(substring)) {
			outMsg = outMsg.substring(36, outMsg.length());
		} else {
			outMsg = outMsg.substring(40, outMsg.length());
		}
		String[] testS = outMsg.split(",");
		long testSLength = testS.length;
		for (int i = 0; i < testSLength; i++) {
			String[] data = testS[i].split("=", -1);
			map.put(data[0], data[1]);
		}
		String retoutMsg = "";
		/*
		 * String proCategory = outMsg.substring(
		 * outMsg.indexOf("ACCOUNT.CATEGORY:1:1=") +
		 * "ACCOUNT.CATEGORY:1:1=".length(),
		 * outMsg.indexOf(",ACCOUNT.PRODUCT.CODE:1:1="));
		 */
		String proCategory = map.get("ACCOUNT.CATEGORY:1:1");
		/*
		 * String proCode = outMsg.substring(
		 * outMsg.indexOf("ACCOUNT.PRODUCT.CODE:1:1=") + 25,
		 * outMsg.indexOf(",ACCOUNT.CURRENCY:1:1="));
		 */
		String proCode = map.get("ACCOUNT.PRODUCT.CODE:1:1");
		String proTpCode = proCode.substring(0, 3);
		// System.out.println("index of T.TYPE:"+outMsg.indexOf("T.TYPE:1:1="));
		/*
		 * String prodPlan = outMsg.substring(outMsg.indexOf("T.TYPE:1:1=") +
		 * 11, outMsg.indexOf(",EXT.ITF.CODE:1:1="));
		 */
		String prodPlan = "1";
		String shortName = "                  ";
		/*
		 * String accStatusString = outMsg.substring(
		 * outMsg.indexOf("ACCOUNT.STATUS:1:1=") + 19,
		 * outMsg.indexOf(",LEDGER.BALANCE:1:1="));
		 */
		String accStatusString = map.get("ACCOUNT.STATUS:1:1");
		String lastFinancialDat = "          ";
		String balEntryString = "  ";
		/*
		 * String ccyCode = outMsg.substring(
		 * outMsg.indexOf("ACCOUNT.CURRENCY:1:1=") + 21,
		 * outMsg.indexOf(",ACCOUNT.STATUS:1:1="));
		 */
		String ccyCode = map.get("ACCOUNT.CURRENCY:1:1");
		/*
		 * String ledgerBalance = outMsg.substring(
		 * outMsg.indexOf("LEDGER.BALANCE:1:1=") + 19,
		 * outMsg.indexOf(",AVAILABLE.BALANCE:1:1="));
		 */
		String ledgerBalance = map.get("LEDGER.BALANCE:1:1").replace(".", "");
//		String[] ledgerBalanceRef = ledgerBalance.split(".");
//		ledgerBalance=ledgerBalanceRef[0]+ledgerBalanceRef[1];
		while (ledgerBalance.length() < 15) {
			ledgerBalance = "0" + ledgerBalance;
		}
		String ledgerBalanceSign = "+";
		/*
		 * String availableBalance = outMsg.substring(
		 * outMsg.indexOf("AVAILABLE.BALANCE:1:1=") + 22,
		 * outMsg.indexOf(",HOLD.AMOUNT:1:1="));
		 */
		String availableBalance = map.get("AVAILABLE.BALANCE:1:1").replace(".", "");
		while (availableBalance.length() < 15) {
			availableBalance = "0" + availableBalance;
		}
		String availableBalanceSign = "+";
		/*
		 * String totalFloatAmount = outMsg.substring(
		 * outMsg.indexOf("FLOAT.AMOUNT:1:1=") + 17,
		 * outMsg.indexOf(",OD.LIMIT:1:1="));
		 */
		String totalFloatAmount = map.get("FLOAT.AMOUNT:1:1").replace(".", "");
		while (totalFloatAmount.length() < 15) {
			totalFloatAmount = "0" + totalFloatAmount;
		}
		String totalFloatAmountSign = "+";
		String totalHoldAmount;
		/*
		 * try { totalHoldAmount = outMsg.substring(
		 * outMsg.indexOf("ACC.T.HOLD.AMT:1:1=") + 19,
		 * outMsg.indexOf(",CURR.NO:1:1=")); } catch (Exception e) { // TODO
		 * Auto-generated catch block totalHoldAmount = "0"; }
		 */
		totalHoldAmount = map.get("ACC.T.HOLD.AMT");
		if (totalHoldAmount == null) {
			totalHoldAmount = "0";
		}
		totalHoldAmount = totalHoldAmount.replace(".", "");
		while (totalHoldAmount.length() < 15) {
			totalHoldAmount = "0" + totalHoldAmount;
		}
		String totalHoldAmountSign = "+";
		/*
		 * String CRLimit = outMsg.substring(outMsg.indexOf("OD.LIMIT:1:1=") +
		 * 13, outMsg.indexOf(",ITF.RETURN.CODE:1:1="));
		 */
		String CRLimit = map.get("OD.LIMIT:1:1".replace(".", ""));
		if (CRLimit == null) {
			CRLimit = "0";
		}
		while (CRLimit.length() < 15) {
			CRLimit = "0" + CRLimit;
		}
		String CRLimitSign = "+";
		/*
		 * String holdPlaceDate = outMsg.substring(
		 * outMsg.indexOf("EXT.REFERENCE:1:1=") + "EXT.TXN.TIME:1:1=".length(),
		 * outMsg.indexOf(",ACC.T.HOLD.AMT"));
		 */
		String holdPlaceDate = map.get("EXT.TXN.TIME:1:1");
		if (holdPlaceDate == null) {
			holdPlaceDate = "";
		}
		if ("".equals(holdPlaceDate)) {
			holdPlaceDate = "          ";
		} else {
			String hdPlDtSub = holdPlaceDate.substring(0, 7);
			holdPlaceDate = "20" + hdPlDtSub.substring(0, 2) + "-"
					+ hdPlDtSub.substring(2, 4) + "-"
					+ hdPlDtSub.substring(5, 7);
		}
		String Filler = "                 ";
		String retMsgString = "  ";
		String msgTyeString = " ";
		String msgCodeString = "";
		/*
		 * retoutMsg = "DD " + proTpCode + proCode + " " + prodPlan + shortName
		 * + accStatusString + lastFinancialDat + balEntryString + ccyCode +
		 * ledgerBalance + ledgerBalanceSign + availableBalance +
		 * availableBalanceSign + totalFloatAmount + totalFloatAmountSign +
		 * totalHoldAmount + totalHoldAmountSign + CRLimit + CRLimitSign +
		 * holdPlaceDate + Filler + retMsgString + msgTyeString + msgCodeString;
		 */

		retoutMsg = "DD " + proTpCode + proCode + "  " + shortName
				+ accStatusString + lastFinancialDat + balEntryString + "01";
		retoutMsg = retoutMsg + ccyCode + ledgerBalance + ledgerBalanceSign
				+ availableBalance + availableBalanceSign + totalFloatAmount
				+ totalFloatAmountSign + totalHoldAmount + totalHoldAmountSign
				+ CRLimit + CRLimitSign;
		for (int i = 0; i < 20; i++) {
			retoutMsg = retoutMsg + "   " + "             " 
					+ "             " 
					+ "             " + "             "
					+ "             ";
		}
		retoutMsg = retoutMsg + holdPlaceDate + Filler + retMsgString
				+ msgTyeString + msgCodeString;

		/*
		 * if (outMsg.indexOf("/-1/NO,") > 0) { retoutMsg =
		 * outRoutineFormatErr(outMsg); } else if (outMsg.indexOf("/1,") > 0) {
		 * retoutMsg = outRoutineFormatSuc(outMsg); }
		 */
		return retoutMsg;
	}

	public static String HoldFundReleaseTest(String inMsg) {
		String outMsg = "";
		if (!"".equals(inMsg)) {
			HoldFundReleaseFactory holdFundReleaseFactory = new HoldFundReleaseFactory();
			String type = inMsg.substring(0, 27);
			com.wlb.T24.in.factory.HoldFundRelease type2 = holdFundReleaseFactory
					.getType(type);
			outMsg = type2.retMsg(inMsg);
		}
		return outMsg;
	}

	public static void main(String[] args) {

		String string = "FSHHSHFSH              DAHFQAN2018-05-2511:50:34000000000000300220Y        N079                                                                       0630306010800000000000060220525776                                                                                                                   HKD000000000058304+          0013482955                                ";
		/*
		 * String inRoutineFormat = inRoutineFormat(string);
		 * System.out.println(inRoutineFormat);
		 * 
		 * System.out.println(string.length());
		 * System.out.println(string.indexOf("08")); System.out.println("tell:"
		 * + string.substring(159, 167)); System.out.println("term:" +
		 * string.substring(167, 171)); //
		 * System.out.println(string.indexOf("60220525776"));
		 * System.out.println("reference:" + string.substring(171, 173));
		 * System.out.println("acc.no:" + string.substring(173, 184));
		 * System.out.println("filter:" + string.substring(184, 231));
		 * System.out.println("remark:" + string.substring(231, 281));
		 * System.out.println("filter:" + string.substring(281, 299));
		 * System.out.println("ccy:" + string.substring(299, 302));
		 * System.out.println("hold amt:" + string.substring(302, 317));
		 * System.out.println("hold amt sign:" + string.substring(317, 318));
		 * System.out.println("hold fund release date:" + string.substring(318,
		 * 328)); System.out.println("hold fund reason:" + string.substring(328,
		 * 331));
		 */
		String outMsg = "ACLK1733936205/I18032307190002198/1,ACCOUNT.NUMBER:1:1=60220525776,FROM.DATE:1:1=20171205,LOCKED.AMOUNT:1:1=58304,T.HLD.FND.CODE:1:1=001,T.ACC.NO:1:1=60220525776,T.CURRENCY:1:1=HKD,T.HLD.REASON:1:1=HOLD BY BILLS DEPT,T.TYPE:1:1=1,EXT.ITF.CODE:1:1=ITF002.0021.A,EXT.SYSTEM.NAME:1:1=SH,EXT.CHANNEL.ID:1:1=KB,EXT.TELLER.ID:1:1=08000000,EXT.TERM.ID:1:1=0000,EXT.REFERENCE:1:1=1803230719000,EXT.TXN.TIME:1:1=1803230719000,ACC.T.HOLD.AMT:1:1=000000000058304,CURR.NO:1:1=1,INPUTTER:1:1=10563_INPUTTER__WS___OFS_ITF.ONLINE.OFS,DATE.TIME:1:1=1803231923,AUTHORISER:1:1=10563_INPUTTER_WS____OFS_ITF.ONLINE.OFS,CO.CODE:1:1=HK0010108,DEPT.CODE:1:1=1,INACTIV.MARKER:1:1=,ACCOUNT.CATEGORY:1:1=6001,ACCOUNT.PRODUCT.CODE:1:1=SAV01,ACCOUNT.CURRENCY:1:1=HKD,ACCOUNT.STATUS:1:1=O,LEDGER.BALANCE:1:1=240943.33,AVAILABLE.BALANCE:1:1=124335.33,HOLD.AMOUNT:1:1=116608,FLOAT.AMOUNT:1:1=0,OD.LIMIT:1:1=0,ITF.RETURN.CODE:1:1=E-000000,ITF.RETURN.MSG:1:1=SUCCESS,ITF.REFERENCE:1:1=DFE183451056369792.00";
		// String outMsg =
		// "ACLK1733921187/I18032710520124851/-1/NO,ACCOUNT.NUMBER:1:1=EB-MISSING.RECORD,LOCKED.AMOUNT:1:1=Bucket Error E-139218,T.TYPE:1:1=Bucket Error E-139218,ACCOUNT.NUMBER:1:1=EB.RTN.INP.MISS.3,ACCOUNT.NUMBER:1:1=EB-MISSING.RECORD,T.TYPE:1:1=EB-MISSING.RECORD,INACTIV.MARKER:1:1=,ACCOUNT.CATEGORY:1:1=6001,ACCOUNT.PRODUCT.CODE:1:1=SAV01,ACCOUNT.CURRENCY:1:1=HKD,ACCOUNT.STATUS:1:1=O,LEDGER.BALANCE:1:1=240943.33,AVAILABLE.BALANCE:1:1=124335.33,HOLD.AMOUNT:1:1=116608,FLOAT.AMOUNT:1:1=0,OD.LIMIT:1:1=0,ITF.RETURN.CODE:1:1=E-139636,ITF.RETURN.MSG:1:1=MISSING & - RECORD,ITF.REFERENCE:1:1=DFE183495008539133.00,EXT.REFERENCE:1:1=1803271052012";
		// outRoutineFormat(outMsg);
		// inRoutineFormat(string);
		// inRoutineFormat(string);
		String testString = "ACLK1733936205/I18032307190002198/1,ACCOUNT.NUMBER:1:1=60220525776,FROM.DATE:1:1=20171205,LOCKED.AMOUNT:1:1=58304,T.HLD.FND.CODE:1:1=001,T.ACC.NO:1:1=60220525776,T.CURRENCY:1:1=HKD,T.HLD.REASON:1:1=HOLD BY BILLS DEPT,T.TYPE:1:1=1,EXT.ITF.CODE:1:1=ITF002.0021.A,EXT.SYSTEM.NAME:1:1=SH,EXT.CHANNEL.ID:1:1=KB,EXT.TELLER.ID:1:1=08000000,EXT.TERM.ID:1:1=0000,EXT.REFERENCE:1:1=1803230719000,EXT.TXN.TIME:1:1=1803230719000,ACC.T.HOLD.AMT:1:1=000000000058304,CURR.NO:1:1=1,INPUTTER:1:1=10563_INPUTTER__WS___OFS_ITF.ONLINE.OFS,DATE.TIME:1:1=1803231923,AUTHORISER:1:1=10563_INPUTTER_WS____OFS_ITF.ONLINE.OFS,CO.CODE:1:1=HK0010108,DEPT.CODE:1:1=1,INACTIV.MARKER:1:1=,ACCOUNT.CATEGORY:1:1=6001,ACCOUNT.PRODUCT.CODE:1:1=SAV01,ACCOUNT.CURRENCY:1:1=HKD,ACCOUNT.STATUS:1:1=O,LEDGER.BALANCE:1:1=240943.33,AVAILABLE.BALANCE:1:1=124335.33,HOLD.AMOUNT:1:1=116608,FLOAT.AMOUNT:1:1=0,OD.LIMIT:1:1=0,ITF.RETURN.CODE:1:1=E-000000,ITF.RETURN.MSG:1:1=SUCCESS,ITF.REFERENCE:1:1=DFE183451056369792.00";
		/*
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * HashMap<String, Entity> hashMap = new HashMap<String, Entity>(); //
		 * map.put("",testString); String substring =
		 * testString.substring(34,35); if("1".equals(substring)){ testString
		 * =testString.substring(36,testString.length());
		 * 
		 * } else{ testString =testString.substring(40,testString.length()); }
		 * String[] testS = testString.split(","); long testSLength =
		 * testS.length; for (int i = 0; i < testSLength; i++) { String[] data =
		 * testS[i].split("=",-1); map.put(data[0], data[1]);
		 * System.out.println(data[1]); } System.out.println(map);
		 */
		// inRoutineFormat(string);
		outMsg = "ACLK1733921187/I18032710520124851/-1/NO,ACCOUNT.NUMBER:1:1=EB-MISSING.RECORD,LOCKED.AMOUNT:1:1=Bucket Error E-139218,T.TYPE:1:1=Bucket Error E-139218,ACCOUNT.NUMBER:1:1=EB.RTN.INP.MISS.3,ACCOUNT.NUMBER:1:1=EB-MISSING.RECORD,T.TYPE:1:1=EB-MISSING.RECORD,INACTIV.MARKER:1:1=,ACCOUNT.CATEGORY:1:1=6001,ACCOUNT.PRODUCT.CODE:1:1=SAV01,ACCOUNT.CURRENCY:1:1=HKD,ACCOUNT.STATUS:1:1=O,LEDGER.BALANCE:1:1=240943.33,AVAILABLE.BALANCE:1:1=124335.33,HOLD.AMOUNT:1:1=116608,FLOAT.AMOUNT:1:1=0,OD.LIMIT:1:1=0,ITF.RETURN.CODE:1:1=E-139636,ITF.RETURN.MSG:1:1=MISSING & - RECORD,ITF.REFERENCE:1:1=DFE183495008539133.00,EXT.REFERENCE:1:1=1803271052012";
		 String outRoutineFormat = outRoutineFormat(outMsg);
		 System.out.println("outRoutineFormat:"+outRoutineFormat);
//		String inRoutineFormatString = inRoutineFormat("FSHHSHFSH              DARFQAN2018-04-3018:47:17000000000000300235Y        N088                                                                       0630326010800000000000060118022477                                                                                                                   HKD000000075194775+          0082018-04-30601083479662                  ACLK1812080854");
//		System.out.println("inRoutineFormatString:" + inRoutineFormatString);
	}
}
