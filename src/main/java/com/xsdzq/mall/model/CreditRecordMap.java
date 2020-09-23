package com.xsdzq.mall.model;

import java.util.List;

public class CreditRecordMap {

	private int sumScore;

	private int sumUsedScore;

	private List<CreditRecordMonth> creditRecordMonths;

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}

	public int getSumUsedScore() {
		return sumUsedScore;
	}

	public void setSumUsedScore(int sumUsedScore) {
		this.sumUsedScore = sumUsedScore;
	}

	public List<CreditRecordMonth> getCreditRecordMonths() {
		return creditRecordMonths;
	}

	public void setCreditRecordMonths(List<CreditRecordMonth> creditRecordMonths) {
		this.creditRecordMonths = creditRecordMonths;
	}

}
