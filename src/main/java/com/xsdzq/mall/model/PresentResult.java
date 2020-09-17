package com.xsdzq.mall.model;

import java.util.List;

import com.xsdzq.mall.entity.PresentResultEntity;
import com.xsdzq.mall.entity.ResultCountEntity;

public class PresentResult {

	private ResultNumber resultNumber;

	private List<PresentResultEntity> presentResultList;

	private List<ResultCountEntity> resultCountList;

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

	public List<ResultCountEntity> getResultCountList() {
		return resultCountList;
	}

	public void setResultCountList(List<ResultCountEntity> resultCountList) {
		this.resultCountList = resultCountList;
	}

}
