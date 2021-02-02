package com.xsdzq.mall.model;

public class UserData {
	private String encryptData;
	private String encryptData2;
	private String lastOpIP;
	private String lastLoginTime;

	public UserData() {
		super();
	}

	public UserData(String encryptData) {
		super();
		this.encryptData = encryptData;
	}

	public String getEncryptData() {
		return encryptData;
	}

	public void setEncryptData(String encryptData) {
		this.encryptData = encryptData;
	}

	public String getEncryptData2() {
		return encryptData2;
	}

	public void setEncryptData2(String encryptData2) {
		this.encryptData2 = encryptData2;
	}

	public String getLastOpIP() {
		return lastOpIP;
	}

	public void setLastOpIP(String lastOpIP) {
		this.lastOpIP = lastOpIP;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		return "UserData [encryptData=" + encryptData + ", encryptData2=" + encryptData2 + ", lastOpIP=" + lastOpIP
				+ ", lastLoginTime=" + lastLoginTime + "]";
	}

}
