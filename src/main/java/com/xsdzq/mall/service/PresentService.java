package com.xsdzq.mall.service;

import java.util.List;

import com.xsdzq.mall.entity.PresentEntity;
import com.xsdzq.mall.model.PresentCategorys;
import com.xsdzq.mall.model.PresentLatestResult;

public interface PresentService {

	public void addPresent(PresentEntity present);

	public List<PresentEntity> getPresentEntities();

	public List<PresentEntity> getHotPresentList();

	public List<PresentLatestResult> getLatestPresentResultEntities();

	public List<PresentCategorys> getPresentCategoryEntities();
	

}
