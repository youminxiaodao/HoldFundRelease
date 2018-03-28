package com.s.entity;

import java.util.HashMap;

public class FacMa {
	public static void main(String[] args) {
		MsgEntity msgEntity = new MsgEntity();
		msgEntity.setBegPos(0);
		msgEntity.setEndPos(10);
		msgEntity.setMsgBody("String");
		HashMap<String, MsgEntity> hashMap = new HashMap<String,MsgEntity>();
		System.out.println("msgEntity:"+msgEntity.toString());
		hashMap.put("1", msgEntity);
		System.out.println("hashMap:"+hashMap);
		System.out.println(hashMap.get("1"));
	}
}
