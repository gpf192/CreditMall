package com.xsdzq.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.dao.CreditRecordRepository;
import com.xsdzq.mall.dao.PresentResultRepository;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.PresentResultEntity;
import com.xsdzq.mall.model.PresentResult;
import com.xsdzq.mall.model.ResultNumber;
import com.xsdzq.mall.service.CreditService;

@Service
@Transactional(readOnly = true)
public class CreditServiceImpl implements CreditService {

	@Autowired
	private CreditRecordRepository creditRecordRepository;

	@Autowired
	private PresentResultRepository presentResultRepository;

	@Override
	public List<CreditRecordEntity> getAllCreditRecordEntities() {
		// TODO Auto-generated method stub
		return creditRecordRepository.findAll();
	}

	@Override
	public PresentResult getPresentResultEntities(MallUserEntity mallUserEntity) {
		// TODO Auto-generated method stub

		PresentResult presentResult = new PresentResult();
		List<PresentResultEntity> presentResultEntities = presentResultRepository
				.findByMallUserEntityOrderByRecordTimeDesc(mallUserEntity);

		int usedScore = 0;
		double usedValue = 0;
		for (PresentResultEntity presentResultEntity : presentResultEntities) {
			usedScore += presentResultEntity.getIntegralNumber();
			usedValue += presentResultEntity.getValue();
		}

		ResultNumber resultNumber = new ResultNumber();
		resultNumber.setUsedScore(usedScore);
		resultNumber.setUsedValue(usedValue);

		presentResult.setResultNumber(resultNumber);
		presentResult.setPresentResultList(presentResultEntities);
		return presentResult;
	}

}
