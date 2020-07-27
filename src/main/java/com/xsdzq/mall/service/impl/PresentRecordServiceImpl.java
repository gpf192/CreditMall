package com.xsdzq.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsdzq.mall.dao.PresentRecordRepository;
import com.xsdzq.mall.entity.PresentRecordEntity;
import com.xsdzq.mall.service.PresentRecordService;

public class PresentRecordServiceImpl implements PresentRecordService {

	@Autowired
	private PresentRecordRepository presentRecordRepository;

	@Override
	public List<PresentRecordEntity> getRecordEntities() {
		// TODO Auto-generated method stub
		return presentRecordRepository.findAll();
	}

}
