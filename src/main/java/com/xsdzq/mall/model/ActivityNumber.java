package com.xsdzq.mall.model;

public class ActivityNumber {
	
	private int scoreNumber;
	private String token;

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

	@Override
	public String toString() {
		return "ActivityNumber [scoreNumber=" + scoreNumber + ", token=" + token + "]";
	}

}
