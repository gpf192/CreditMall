package com.xsdzq.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsdzq.mall.dao.PresentResultRepository;
import com.xsdzq.mall.entity.PresentResultEntity;
import com.xsdzq.mall.service.PresentResultService;

public class PresentResultServiceImpl implements PresentResultService {

	@Autowired
	private PresentResultRepository presentResultRepository;

	@Override
	public List<PresentResultEntity> getResultEntities() {
		// TODO Auto-generated method stub
		return presentResultRepository.findAll();
	}

}
