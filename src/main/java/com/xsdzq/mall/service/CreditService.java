package com.xsdzq.mall.service;

import java.util.List;

import com.xsdzq.mall.entity.CRMCreditProductViewEntity;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.CreditRecordMap;
import com.xsdzq.mall.model.PresentResult;

public interface CreditService {

	List<CreditRecordEntity> getAllCreditRecordEntities();

	List<CreditRecordEntity> getMallUserRecords(MallUserEntity mallUserEntity, int pageNumber, int pageSize);

	PresentResult getPresentResultEntities(MallUserEntity mallUserEntity);

	CreditRecordMap getUserCreditRecord(MallUserEntity mallUserEntity);
	//crm
	public List<CRMCreditProductViewEntity> getAllCrmProducts();
	
	public List<CRMCreditProductViewEntity> getTopCrmProducts();

}
