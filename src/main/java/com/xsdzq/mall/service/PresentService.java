package com.xsdzq.mall.service;

import java.util.List;

import com.xsdzq.mall.entity.PresentEntity;

public interface PresentService {

	public void addPresent(PresentEntity present);

	public List<PresentEntity> getPresentEntities();

}
