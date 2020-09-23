package com.xsdzq.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.constants.CreditRecordConst;
import com.xsdzq.mall.dao.CreditRecordRepository;
import com.xsdzq.mall.dao.MallUserInfoRepository;
import com.xsdzq.mall.dao.PresentResultRepository;
import com.xsdzq.mall.entity.CreditRecordCountEntity;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.MallUserInfoEntity;
import com.xsdzq.mall.entity.PresentResultEntity;
import com.xsdzq.mall.entity.ResultCountEntity;
import com.xsdzq.mall.model.CreditRecordMap;
import com.xsdzq.mall.model.CreditRecordMonth;
import com.xsdzq.mall.model.PresentResult;
import com.xsdzq.mall.model.ResultNumber;
import com.xsdzq.mall.service.CreditService;

@Service
@Transactional(readOnly = true)
public class CreditServiceImpl implements CreditService {

	private static final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);

	@Autowired
	private MallUserInfoRepository mallUserInfoRepository;

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
	public CreditRecordMap getUserCreditRecord(MallUserEntity mallUserEntity) {
		// TODO Auto-generated method stub

		List<CreditRecordMonth> recordMothList = new ArrayList<CreditRecordMonth>();
		List<CreditRecordCountEntity> countEntities = creditRecordRepository.getCreditRecordCountList(mallUserEntity);
		List<CreditRecordEntity> myCreditRecordEntities = creditRecordRepository
				.findByMallUserEntityOrderByRecordTimeDesc(mallUserEntity);
		int myUsedSumScore = 0;

		for (int i = 0; i < countEntities.size(); i++) {
			CreditRecordMonth creditRecordMonth = new CreditRecordMonth();
			String groupTime = countEntities.get(i).getGroupTime();
			int getSumScore = 0;
			int usedSumScore = 0;
			int invalidSumScore = 0;

			for (CreditRecordEntity creditRecordEntity : myCreditRecordEntities) {
				// 计算分数
				log.info("code: " + creditRecordEntity.getReasonCode());
				if (groupTime.equals(creditRecordEntity.getGroupTime())) {
					if (creditRecordEntity.isType()) {
						// 新增
						getSumScore += creditRecordEntity.getIntegralNumber();
					} else {
						// 兑换奖品
						if (creditRecordEntity.getReasonCode() != null
								&& creditRecordEntity.getReasonCode().equals(CreditRecordConst.EXCHANGECARD)) {
							usedSumScore += creditRecordEntity.getIntegralNumber();
						}
						// 失效
						if (creditRecordEntity.getReasonCode() != null
								&& creditRecordEntity.getReasonCode().equals(CreditRecordConst.EXPIREDCARD)) {
							invalidSumScore += creditRecordEntity.getIntegralNumber();
						}
					}
				}
			}
			myUsedSumScore += usedSumScore;
			creditRecordMonth.setGetSumScore(getSumScore);
			creditRecordMonth.setUsedSumScore(usedSumScore);
			creditRecordMonth.setInvalidSumScore(invalidSumScore);
			creditRecordMonth.setGroupTime(groupTime);
			recordMothList.add(creditRecordMonth);
		}

		MallUserInfoEntity myMallUserInfoEntity = mallUserInfoRepository.findByMallUserEntity(mallUserEntity);

		CreditRecordMap creditRecordMap = new CreditRecordMap();
		creditRecordMap.setSumScore(myUsedSumScore);
		creditRecordMap.setSumScore(myMallUserInfoEntity.getCreditScore());
		creditRecordMap.setCreditRecordMonths(recordMothList);

		return creditRecordMap;
	}

	@Override
	public List<CreditRecordEntity> getMallUserRecords(MallUserEntity mallUserEntity, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = PageRequest.of(pageNumber, pageSize);
		Page<CreditRecordEntity> presentRecordEntities = creditRecordRepository
				.findByMallUserEntityOrderByRecordTimeDesc(mallUserEntity, pageable);
		return presentRecordEntities.getContent();
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

		List<ResultCountEntity> resultCounts = presentResultRepository.findResultCountList(mallUserEntity);
		presentResult.setResultNumber(resultNumber);
		presentResult.setPresentResultList(presentResultEntities);
		presentResult.setResultCountList(resultCounts);

		return presentResult;
	}

}
