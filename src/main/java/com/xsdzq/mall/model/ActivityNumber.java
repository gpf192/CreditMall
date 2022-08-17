package com.xsdzq.mall.model;

public class ActivityNumber {
	
	private int scoreNumber;
	private String token;
	private Integer priceDayQuota;

	public int getScoreNumber() {
		return scoreNumber;
	}

	public void setScoreNumber(int scoreNumber) {
		this.scoreNumber = scoreNumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getPriceDayQuota() {
		return priceDayQuota;
	}

	public void setPriceDayQuota(Integer priceDayQuota) {
		this.priceDayQuota = priceDayQuota;
	}

	@Override
	public String toString() {
		return "ActivityNumber{" +
				"scoreNumber=" + scoreNumber +
				", token='" + token + '\'' +
				", priceDayQuota=" + priceDayQuota +
				'}';
	}
}
