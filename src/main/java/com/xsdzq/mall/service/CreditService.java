package com.xsdzq.mall.service;

import java.util.List;

import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.PresentResult;

public interface CreditService {

	List<CreditRecordEntity> getAllCreditRecordEntities();
	
	PresentResult getPresentResultEntities(MallUserEntity mallUserEntity);

}
