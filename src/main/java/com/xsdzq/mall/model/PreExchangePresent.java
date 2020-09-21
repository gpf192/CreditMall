package com.xsdzq.mall.model;

import com.xsdzq.mall.entity.MallUserInfoEntity;
import com.xsdzq.mall.entity.PresentEntity;

public class PreExchangePresent {

	private double canUsedValue;
	private MallUserInfoEntity mallUserInfoEntity;
	private PresentEntity presentEntity;

	public double getCanUsedValue() {
		return canUsedValue;
	}

	public void setCanUsedValue(double canUsedValue) {
		this.canUsedValue = canUsedValue;
	}

	public MallUserInfoEntity getMallUserInfoEntity() {
		return mallUserInfoEntity;
	}

	public void setMallUserInfoEntity(MallUserInfoEntity mallUserInfoEntity) {
		this.mallUserInfoEntity = mallUserInfoEntity;
	}

	public PresentEntity getPresentEntity() {
		return presentEntity;
	}

	public void setPresentEntity(PresentEntity presentEntity) {
		this.presentEntity = presentEntity;
	}

}
