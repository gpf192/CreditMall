package com.xsdzq.mall.model;

public class PresentModelNumber {

	private int presentId;
	private int prizeNumber;

	public int getPresentId() {
		return presentId;
	}

	public void setPresentId(int presentId) {
		this.presentId = presentId;
	}

	public int getPrizeNumber() {
		return prizeNumber;
	}

	public void setPrizeNumber(int prizeNumber) {
		this.prizeNumber = prizeNumber;
	}

	@Override
	public String toString() {
		return "PresentModelNumber [presentId=" + presentId + ", prizeNumber=" + prizeNumber + "]";
	}

}
