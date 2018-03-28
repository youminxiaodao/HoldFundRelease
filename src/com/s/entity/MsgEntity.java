package com.s.entity;

public class MsgEntity {
	int begPos;
	int endPos;
	String msgBody;
	
	public int getBegPos() {
		return begPos;
	}
	public void setBegPos(int begPos) {
		this.begPos = begPos;
	}
	public int getEndPos() {
		return endPos;
	}
	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	@Override
	public String toString() {
		return "MsgEntity [begPos=" + begPos + ", endPos=" + endPos + ", msgBody=" + msgBody + "]";
	}
	
}
