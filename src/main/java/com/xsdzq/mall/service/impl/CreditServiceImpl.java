package com.xsdzq.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.dao.CreditRecordRepository;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.service.CreditService;

@Service
@Transactional(readOnly = true)
public class CreditServiceImpl implements CreditService {

	@Autowired
	private CreditRecordRepository creditRecordRepository;

	@Override
	public List<CreditRecordEntity> getAllCreditRecordEntities() {
		// TODO Auto-generated method stub
		return creditRecordRepository.findAll();
	}

}
