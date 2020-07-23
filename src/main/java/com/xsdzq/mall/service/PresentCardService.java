package com.xsdzq.mall.service;

import java.util.List;

import com.xsdzq.mall.entity.PresentCardEntity;

public interface PresentCardService {

	public void addPresentCard(PresentCardEntity presentCard);

	public List<PresentCardEntity> getPresentCardEntities();

}
