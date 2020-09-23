package com.xsdzq.mall.model;

public class CreditRecordMonth {

	private int getSumScore;

	private int usedSumScore;

	private int invalidSumScore;

	private String groupTime;

	public int getGetSumScore() {
		return getSumScore;
	}

	public void setGetSumScore(int getSumScore) {
		this.getSumScore = getSumScore;
	}

	public int getUsedSumScore() {
		return usedSumScore;
	}

	public void setUsedSumScore(int usedSumScore) {
		this.usedSumScore = usedSumScore;
	}

	public int getInvalidSumScore() {
		return invalidSumScore;
	}

	public void setInvalidSumScore(int invalidSumScore) {
		this.invalidSumScore = invalidSumScore;
	}

	public String getGroupTime() {
		return groupTime;
	}

	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
	}

	@Override
	public String toString() {
		return "CreditRecordMonth [getSumScore=" + getSumScore + ", usedSumScore=" + usedSumScore + ", invalidSumScore="
				+ invalidSumScore + ", groupTime=" + groupTime + "]";
	}

}
