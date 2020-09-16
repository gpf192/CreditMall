package com.xsdzq.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.constants.CreditRecordConst;
import com.xsdzq.mall.constants.PresentCardConst;
import com.xsdzq.mall.dao.CreditRecordRepository;
import com.xsdzq.mall.dao.MallUserInfoRepository;
import com.xsdzq.mall.dao.MallUserRepository;
import com.xsdzq.mall.dao.PresentCardRepository;
import com.xsdzq.mall.dao.PresentRepository;
import com.xsdzq.mall.dao.PresentResultRepository;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.MallUserInfoEntity;
import com.xsdzq.mall.entity.PresentCardEntity;
import com.xsdzq.mall.entity.PresentEntity;
import com.xsdzq.mall.entity.PresentResultEntity;
import com.xsdzq.mall.model.ActivityNumber;
import com.xsdzq.mall.model.User;
import com.xsdzq.mall.service.MallUserService;
import com.xsdzq.mall.util.DateUtil;
import com.xsdzq.mall.util.UserUtil;

@Service
@Transactional(readOnly = true)
public class MallUserServiceImpl implements MallUserService {

	private static final Logger log = LoggerFactory.getLogger(MallUserServiceImpl.class);

	@Autowired
	private MallUserRepository mallUserRepository;

	@Autowired
	private MallUserInfoRepository mallUserInfoRepository;

	@Autowired
	private CreditRecordRepository creditRecordRepository;

	@Autowired
	private PresentRepository presentRepository;

	@Autowired
	private PresentCardRepository presentCardRepository;

	@Autowired
	private PresentResultRepository presentResultRepository;

	@Override
	@Transactional
	public ActivityNumber login(User user) {
		// TODO Auto-generated method stub
		MallUserEntity requestUser = mallUserRepository.findByClientId(user.getClientId());
		MallUserInfoEntity mallUserInfoEntity = null;
		if (requestUser == null) {
			requestUser = UserUtil.convertUserByUserEntity(user);
			log.info(requestUser.toString());
			mallUserRepository.save(requestUser);
			mallUserInfoEntity = new MallUserInfoEntity();
			mallUserInfoEntity.setMallUserEntity(requestUser);
			mallUserInfoEntity.setCreditScore(0);
			mallUserInfoEntity.setSumScore(0);
			mallUserInfoEntity.setLevel(0);
			mallUserInfoRepository.save(mallUserInfoEntity);
		} else {
			mallUserInfoEntity = mallUserInfoRepository.findByMallUserEntity(requestUser);
			UserUtil.updateUserEntityByUser(requestUser, user);
			mallUserRepository.saveAndFlush(requestUser);
		}
		ActivityNumber activityNumber = new ActivityNumber();
		activityNumber.setScoreNumber(mallUserInfoEntity.getCreditScore());
		return activityNumber;

	}

	@Override
	public void addMallUser(MallUserEntity mallUserEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void addCreditScore() {
		// TODO Auto-generated method stub
		// 初始化用户
		String client_id = "100002";
		String client_name = "李二二";
		String mobile = "13812121315";
		String item = "新开户";
		String item_code = "10";
		int integral_number = 100;
		String date = "2020-09-20";
		// double value = 20;
		String department_code = "001";
		String department = "北三环营业部";

		// 1.部门的查找和新增
		/*
		 * DepartmentEntity departmentEntity =
		 * departmentRepository.findByCode(department_code); if (departmentEntity ==
		 * null) { departmentEntity = new DepartmentEntity();
		 * departmentEntity.setCode(department_code);
		 * departmentEntity.setName(department);
		 * departmentRepository.save(departmentEntity); }
		 */
		// 2.用户的查找和新增
		MallUserEntity owner = mallUserRepository.findByClientId(client_id);
		if (owner == null) {
			owner = new MallUserEntity();
			owner.setClientId(client_id);
			owner.setClientName(client_name);
			owner.setMobile(mobile);
			// owner.setDepartmentEntity(departmentEntity);
			mallUserRepository.save(owner);

			MallUserInfoEntity mallUserInfoEntity = new MallUserInfoEntity();
			mallUserInfoEntity.setMallUserEntity(owner);
			mallUserInfoEntity.setCreditScore(0);
			mallUserInfoEntity.setSumScore(0);
			mallUserInfoEntity.setLevel(0);
			mallUserInfoRepository.save(mallUserInfoEntity);
		} else {
			// update 逻辑 把部门信息同步过去
		}
		// 3.项目的定义是不是需要

		// 4.写记录
		String nowFlag = DateUtil.getStandardDate(new Date());
		CreditRecordEntity creditRecordEntity = new CreditRecordEntity();
		creditRecordEntity.setMallUserEntity(owner);
		creditRecordEntity.setType(CreditRecordConst.REDUCESCORE);
		// creditRecordEntity.setItem(item);
		// creditRecordEntity.setItemCode(item_code);
		creditRecordEntity.setReason("兑换奖品");
		creditRecordEntity.setReasonCode("10");
		creditRecordEntity.setIntegralNumber(integral_number);
		creditRecordEntity.setDateFlag(nowFlag);
		creditRecordEntity.setGroupTime("202009");
		creditRecordEntity.setRecordTime(new Date());
		// creditRecordEntity.setCreateTime(date);
		creditRecordRepository.save(creditRecordEntity);

		// 5.个人信息添加积分值

		MallUserInfoEntity myMallUserInfoEntity = mallUserInfoRepository.findByMallUserEntity(owner);
		myMallUserInfoEntity.setCreditScore(integral_number + myMallUserInfoEntity.getCreditScore());
		myMallUserInfoEntity.setSumScore(integral_number + myMallUserInfoEntity.getSumScore());
		mallUserInfoRepository.save(myMallUserInfoEntity);

	}

	@Override
	public MallUserEntity getUserByClientId(String clientId) {
		// TODO Auto-generated method stub
		MallUserEntity mallUserEntity = mallUserRepository.findByClientId(clientId);
		return mallUserEntity;
	}

	@Override
	public boolean isCanExchange(MallUserEntity mallUserEntity, String prizeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public void exchangePresent(MallUserEntity mallUserEntity, int presentId) {
		// TODO Auto-generated method stub
		// 1.查询出奖品
		Date nowDate = new Date();
		PresentEntity presentEntity = presentRepository.findById((long) presentId).get();
		// 2.查询出未兑换的cardList
		List<PresentCardEntity> presentCardList = presentCardRepository
				.findByPresentEntityAndConvertStatus(presentEntity, PresentCardConst.CARD_UNUSED);
		if (presentCardList.size() > 0) {
			// 可以兑换 暂定一个
			PresentCardEntity presentCardEntity = presentCardList.get(0);
			int score = (int) (presentEntity.getValue() * 100);

			// 兑换
			// a.用户减少积分
			MallUserInfoEntity mallUserInfoEntity = mallUserInfoRepository.findByMallUserEntity(mallUserEntity);
			mallUserInfoEntity.setCreditScore(mallUserInfoEntity.getCreditScore() - score);
			// b.添加creditRecord
			CreditRecordEntity creditRecordEntity = new CreditRecordEntity();
			creditRecordEntity.setType(CreditRecordConst.REDUCESCORE);
			creditRecordEntity.setReasonCode(CreditRecordConst.EXCHANGECARD);
			creditRecordEntity.setReason(CreditRecordConst.EXCHANGECARDREASON);
			creditRecordEntity.setItem(presentEntity.getName());
			creditRecordEntity.setIntegralNumber(score);
			creditRecordEntity.setDateFlag(DateUtil.getStandardDate(nowDate));
			creditRecordEntity.setGroupTime(DateUtil.getStandardMonthDate(nowDate));
			creditRecordEntity.setRecordTime(nowDate);
			creditRecordEntity.setMallUserEntity(mallUserEntity);
			// c.插入结果
			PresentResultEntity presentResultEntity = new PresentResultEntity();
			presentResultEntity.setIntegralNumber(score);
			presentResultEntity.setValue(presentEntity.getValue());
			presentResultEntity.setMallUserEntity(mallUserEntity);
			presentResultEntity.setPresentCardEntity(presentCardEntity);
			presentResultEntity.setDateFlag(DateUtil.getStandardDate(nowDate));
			presentResultEntity.setGroupTime(DateUtil.getStandardMonthDate(nowDate));
			presentResultEntity.setRecordTime(nowDate);
			// d.更新兑换状态
			presentCardEntity.setConvertStatus(PresentCardConst.CARD_USED);

			// e.全部更新数据库
			presentCardRepository.save(presentCardEntity);
			mallUserInfoRepository.save(mallUserInfoEntity);
			creditRecordRepository.save(creditRecordEntity);
			presentResultRepository.save(presentResultEntity);

		}

	}

}
