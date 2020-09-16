package com.xsdzq.mall.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.constants.CreditRecordConst;
import com.xsdzq.mall.dao.CreditRecordRepository;
import com.xsdzq.mall.dao.DepartmentRepository;
import com.xsdzq.mall.dao.MallUserInfoRepository;
import com.xsdzq.mall.dao.MallUserRepository;
import com.xsdzq.mall.dao.PresentRepository;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.MallUserInfoEntity;
import com.xsdzq.mall.entity.PresentEntity;
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
	private DepartmentRepository departmentRepository;

	@Autowired
	private MallUserRepository mallUserRepository;

	@Autowired
	private MallUserInfoRepository mallUserInfoRepository;

	@Autowired
	private CreditRecordRepository creditRecordRepository;
	
	@Autowired
	private PresentRepository presentRepository;

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
		/*DepartmentEntity departmentEntity = departmentRepository.findByCode(department_code);
		if (departmentEntity == null) {
			departmentEntity = new DepartmentEntity();
			departmentEntity.setCode(department_code);
			departmentEntity.setName(department);
			departmentRepository.save(departmentEntity);
		}*/
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
		//creditRecordEntity.setItem(item);
		//creditRecordEntity.setItemCode(item_code);
		creditRecordEntity.setReason("兑换奖品");
		creditRecordEntity.setReasonCode("10");
		creditRecordEntity.setIntegralNumber(integral_number);
		creditRecordEntity.setDateFlag(nowFlag);
		creditRecordEntity.setGroupTime("202009");
		creditRecordEntity.setRecordTime(new Date());
		//creditRecordEntity.setCreateTime(date);
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
	public void exchangePrize(MallUserEntity mallUserEntity, int prizeId) {
		// TODO Auto-generated method stub
		PresentEntity presentEntity = presentRepository.findById((long)prizeId).get();

	}

}
