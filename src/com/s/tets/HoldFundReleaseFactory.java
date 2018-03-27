package com.s.tets;

public class HoldFundReleaseFactory {

	public HoldFundRelease getType(String type){
		if (type == null){
			return null;
		}
		if("FSHHSHFSH              DAHF".equalsIgnoreCase(type)){
			return new HoldFundReleaseReq();
			
		}
		else if("FSHHSHFSH              DARF".equalsIgnoreCase(type)){
			return new HoldFundReleaseReq();
		}
		return null;
	}
}
