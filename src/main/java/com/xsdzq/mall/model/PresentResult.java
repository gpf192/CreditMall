package com.xsdzq.mall.model;

import java.util.List;

import com.xsdzq.mall.entity.PresentResultEntity;

public class PresentResult {

	private ResultNumber resultNumber;

	private List<PresentResultEntity> presentResultList;

	public ResultNumber getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(ResultNumber resultNumber) {
		this.resultNumber = resultNumber;
	}

	public List<PresentResultEntity> getPresentResultList() {
		return presentResultList;
	}

	public void setPresentResultList(List<PresentResultEntity> presentResultList) {
		this.presentResultList = presentResultList;
	}

}
