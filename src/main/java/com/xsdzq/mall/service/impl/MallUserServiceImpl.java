package com.xsdzq.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xsdzq.mall.constants.CreditRecordConst;
import com.xsdzq.mall.constants.PresentCardConst;
import com.xsdzq.mall.constants.PresentConst;
import com.xsdzq.mall.dao.CreditRecordRepository;
import com.xsdzq.mall.dao.MallUserInfoRepository;
import com.xsdzq.mall.dao.MallUserRepository;
import com.xsdzq.mall.dao.PresentCardRepository;
import com.xsdzq.mall.dao.PresentRecordRepository;
import com.xsdzq.mall.dao.PresentRepository;
import com.xsdzq.mall.dao.PresentResultRepository;
import com.xsdzq.mall.dao.TokenRecordRepository;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.MallUserInfoEntity;
import com.xsdzq.mall.entity.PresentCardEntity;
import com.xsdzq.mall.entity.PresentEntity;
import com.xsdzq.mall.entity.PresentRecordEntity;
import com.xsdzq.mall.entity.PresentResultEntity;
import com.xsdzq.mall.entity.TokenRecordEntity;
import com.xsdzq.mall.exception.BusinessException;
import com.xsdzq.mall.model.ActivityNumber;
import com.xsdzq.mall.model.PreExchangePresent;
import com.xsdzq.mall.model.PresentModelNumber;
import com.xsdzq.mall.model.User;
import com.xsdzq.mall.properties.HSURLProperties;
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

	@Autowired
	private PresentRecordRepository presentRecordRepository;

	@Autowired
	private TokenRecordRepository tokenRecordRepository;

	@Autowired
	private HSURLProperties hSURLProperties;

	@Autowired
	private RestTemplate restTemplate;

	public boolean hsServiceCheck(String clientId, String loginClientId, String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("access_token", accessToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		String HS_GET_URL = hSURLProperties.getInfo();
		String url = HS_GET_URL + "?client_id=" + loginClientId;
		log.info(headers.toString());
		log.info("request url: " + url);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			if (HttpStatus.OK == responseEntity.getStatusCode()) {
				String response = responseEntity.getBody();
				log.info("hs loginClientId " + loginClientId + " response: " + response);
				TokenRecordEntity tokenRecordEntity = new TokenRecordEntity();
				tokenRecordEntity.setClientId(clientId);
				tokenRecordEntity.setLoginClientId(loginClientId);
				tokenRecordEntity.setAccessToken(accessToken);
				tokenRecordEntity.setResponse(response);
				tokenRecordRepository.save(tokenRecordEntity);

				JSONObject hsJsonObject = JSON.parseObject(response);
				String responseClientId = hsJsonObject.getString("client_id");
				if (responseClientId != null && loginClientId.equals(responseClientId)) {
					// 校验通过
					log.info(loginClientId + " 校验通过");
					return true;

				} else {
					return false;
					// throw new BusinessException("登录失败，请重新登录");
				}

			} else {
				log.error("#method# 远程调用失败 httpCode = [{}]", responseEntity.getStatusCode());
				return false;
				// throw new BusinessException("登录服务异常");
			}
		} catch (Exception e) {
			// TODO: handle exception
			TokenRecordEntity tokenRecordEntity = new TokenRecordEntity();
			tokenRecordEntity.setClientId(clientId);
			tokenRecordEntity.setLoginClientId(loginClientId);
			tokenRecordEntity.setAccessToken(accessToken);
			tokenRecordEntity.setResponse(e.getMessage());
			log.info("恒生调用 base/get 异常:" + tokenRecordEntity.toString());
			tokenRecordRepository.save(tokenRecordEntity);
			return false;
			// throw new BusinessException("登录失败，请重新登录");
		}

	}

	@Override
	@Transactional
	public ActivityNumber login(User user) {
		// TODO Auto-generated method stub
		// 0 前置 恒生校验 第一个版本需要注释
		if (user.getLoginClientId() != null && user.getLoginClientId().length() > 0) {
			boolean isCheck = hsServiceCheck(user.getClientId(), user.getLoginClientId(), user.getAccessToken());
			if (!isCheck) {
				return null;
			}
		} else {
			log.info(user.getClientId() + " 校验未通过");
		}
		// 1 产生token
		MallUserEntity requestUser = mallUserRepository.findByClientId(user.getClientId());
		MallUserInfoEntity mallUserInfoEntity = null;
		if (requestUser == null) {
			requestUser = UserUtil.convertUserByUserEntity(user);
			log.info("save user " + requestUser.toString());
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

	/*
	 * 
	 * @Override
	 * 
	 * @Transactional public ActivityNumber login(User user) { // TODO
	 * Auto-generated method stub MallUserEntity requestUser =
	 * mallUserRepository.findByClientId(user.getClientId()); MallUserInfoEntity
	 * mallUserInfoEntity = null; if (requestUser == null) { requestUser =
	 * UserUtil.convertUserByUserEntity(user); log.info(requestUser.toString());
	 * mallUserRepository.save(requestUser); mallUserInfoEntity = new
	 * MallUserInfoEntity(); mallUserInfoEntity.setMallUserEntity(requestUser);
	 * mallUserInfoEntity.setCreditScore(0); mallUserInfoEntity.setSumScore(0);
	 * mallUserInfoEntity.setLevel(0);
	 * mallUserInfoRepository.save(mallUserInfoEntity); } else { mallUserInfoEntity
	 * = mallUserInfoRepository.findByMallUserEntity(requestUser);
	 * UserUtil.updateUserEntityByUser(requestUser, user);
	 * mallUserRepository.saveAndFlush(requestUser); } ActivityNumber activityNumber
	 * = new ActivityNumber();
	 * activityNumber.setScoreNumber(mallUserInfoEntity.getCreditScore()); return
	 * activityNumber;
	 * 
	 * }
	 */

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
	public PreExchangePresent preExchangePresent(MallUserEntity mallUserEntity, int presentId) {
		// TODO Auto-generated method stub
		// 查询当日兑换情况
		Date date = new Date();
		String nowDate = DateUtil.getStandardDate(date);
		double canUsedValue = getCurrentDayValue(mallUserEntity, nowDate);
		MallUserInfoEntity mallUserInfoEntity = mallUserInfoRepository.findByMallUserEntity(mallUserEntity);
		PresentEntity presentEntity = presentRepository.findById((long) presentId).get();
		log.info("canUsedValue: " + canUsedValue);
		PreExchangePresent preExchangePresent = new PreExchangePresent();
		preExchangePresent.setMallUserInfoEntity(mallUserInfoEntity);
		preExchangePresent.setPresentEntity(presentEntity);
		preExchangePresent.setCanUsedValue(canUsedValue);
		return preExchangePresent;

	}

	@Override
	@Transactional
	public void exchangePresent(MallUserEntity mallUserEntity, PresentModelNumber presentModelNumber) {
		// TODO Auto-generated method stub

		int presentId = presentModelNumber.getPresentId();
		// 兑换奖品数
		int prizeNumber = presentModelNumber.getPrizeNumber();

		// 查询出奖品
		PresentEntity presentEntity = presentRepository.findById((long) presentId).get();
		if (presentEntity == null) {
			throw new BusinessException("非法兑换");
		}

		Date nowDate = new Date();
		String nowDateStr = DateUtil.getStandardDate(nowDate);

		// 0.兑换条件检测
		checkExchangePresent(mallUserEntity, presentEntity, nowDateStr, prizeNumber);

		// 1.查询出未兑换的cardList
		List<PresentCardEntity> presentCardList = presentCardRepository
				.findByPresentEntityAndConvertStatus(presentEntity, PresentCardConst.CARD_UNUSED);

		if (presentCardList.size() >= prizeNumber) {
			int sumScore = (int) (presentEntity.getValue() * 100 * prizeNumber);
			// a.用户减少积分
			MallUserInfoEntity mallUserInfoEntity = mallUserInfoRepository.findByMallUserEntity(mallUserEntity);
			if (mallUserInfoEntity.getCreditScore() - sumScore < 0) {
				throw new BusinessException("积分不足！");
			}
			mallUserInfoEntity.setCreditScore(mallUserInfoEntity.getCreditScore() - sumScore);
			mallUserInfoRepository.save(mallUserInfoEntity);

			// b.循环插记录
			int score = (int) (presentEntity.getValue() * 100);
			for (int i = 0; i < prizeNumber; i++) {
				PresentCardEntity presentCardEntity = presentCardList.get(i);
				// b1.添加creditRecord
				CreditRecordEntity creditRecordEntity = new CreditRecordEntity();
				creditRecordEntity.setType(CreditRecordConst.REDUCESCORE);
				creditRecordEntity.setReasonCode(CreditRecordConst.EXCHANGECARD);
				creditRecordEntity.setReason(CreditRecordConst.EXCHANGECARDREASON);
				creditRecordEntity.setItem(presentEntity.getName());
				creditRecordEntity.setIntegralNumber(score);
				creditRecordEntity.setDateFlag(nowDateStr);
				creditRecordEntity.setGroupTime(DateUtil.getStandardMonthDate(nowDate));
				creditRecordEntity.setChangeType(CreditRecordConst.CHANGETYPE_COMPLETE);
				creditRecordEntity.setRemindNumer(0);
				creditRecordEntity.setRecordTime(nowDate);
				creditRecordEntity.setMallUserEntity(mallUserEntity);
				creditRecordRepository.save(creditRecordEntity);

				presentCardEntity.setConvertStatus(PresentCardConst.CARD_USED);
				presentCardEntity.setConvertDate(nowDate);
				presentCardRepository.save(presentCardEntity);

				// b2. 奖品结果页面
				PresentRecordEntity presentRecordEntity = new PresentRecordEntity();
				presentRecordEntity.setIntegralNumber(score);
				presentRecordEntity.setPrice(presentEntity.getValue());
				presentRecordEntity.setNumber(1);
				presentRecordEntity.setDateFlag(nowDateStr);
				presentRecordEntity.setMallUserEntity(mallUserEntity);
				presentRecordEntity.setPresentCardEntity(presentCardEntity);
				presentRecordEntity.setRecordTime(nowDate);

				presentRecordRepository.save(presentRecordEntity);

			}

			// c.插入结果
			PresentResultEntity presentResultEntity = new PresentResultEntity();
			presentResultEntity.setIntegralNumber(sumScore);
			presentResultEntity.setValue(presentEntity.getValue() * prizeNumber);
			presentResultEntity.setChangeNumber(prizeNumber);
			presentResultEntity.setMallUserEntity(mallUserEntity);
			presentResultEntity.setPresentEntity(presentEntity);
			presentResultEntity.setDateFlag(DateUtil.getStandardDate(nowDate));
			presentResultEntity.setGroupTime(DateUtil.getStandardMonthDate(nowDate));
			presentResultEntity.setRecordTime(nowDate); // d.更新结果
			presentResultRepository.save(presentResultEntity);

			presentEntity.setStoreUnused(presentEntity.getStoreUnused() - prizeNumber);
			presentEntity.setConvertNumber(presentEntity.getConvertNumber() + prizeNumber);

			// 更新库存 和使用量
			presentRepository.save(presentEntity);
			// e.处理扣减积分问题 //
			handleRudeceCredit(mallUserEntity, sumScore);

		} else {
			throw new BusinessException("超过最大兑换数量！");
		}

	}

	public void checkExchangePresent(MallUserEntity mallUserEntity, PresentEntity presentEntity, String nowDate,
			int prizeNumber) {
		// 1.检查库存
		if (presentEntity.getStoreUnused() < prizeNumber) {
			throw new BusinessException("超过最大兑换数量！");
		}
		// 2.检查是否能够兑换
		double currentValue = getCurrentDayValue(mallUserEntity, nowDate);
		if (currentValue < presentEntity.getValue() * prizeNumber) {
			throw new BusinessException("积分不足，总金额超过最大积分价值！");
		}

	}

	public double getCurrentDayValue(MallUserEntity mallUserEntity, String nowDate) {
		// TODO Auto-generated method stub
		List<PresentResultEntity> myPresentResultEntities = presentResultRepository
				.findByMallUserEntityAndDateFlagOrderByRecordTimeDesc(mallUserEntity, nowDate);
		double usedValue = 0;
		for (PresentResultEntity presentResultEntity : myPresentResultEntities) {
			usedValue += presentResultEntity.getValue();
		}
		return PresentConst.QUOTANUMBER - usedValue;

	}

	void handleRudeceCredit(MallUserEntity mallUserEntity, int reduceScore) {
		List<CreditRecordEntity> creditRecordEntities = creditRecordRepository.findByUnusedCredit(mallUserEntity,
				CreditRecordConst.ADDSCORE, 1);
		log.info("creditRecordEntities: " + creditRecordEntities.size());
		for (CreditRecordEntity creditRecordEntity : creditRecordEntities) {
			log.info(creditRecordEntity.toString());
			// 做减法
			int score;
			if (creditRecordEntity.getChangeType() == CreditRecordConst.CHANGETYPE_UNUSED) {
				score = creditRecordEntity.getIntegralNumber();
			} else {
				score = creditRecordEntity.getRemindNumer();
			}
			if (reduceScore - score > 0) {
				creditRecordEntity.setChangeType(CreditRecordConst.CHANGETYPE_COMPLETE);
				creditRecordEntity.setRemindNumer(0);
				reduceScore = reduceScore - score;
				creditRecordRepository.save(creditRecordEntity);
			} else if (reduceScore - score == 0) {
				creditRecordEntity.setChangeType(CreditRecordConst.CHANGETYPE_COMPLETE);
				creditRecordEntity.setRemindNumer(0);
				reduceScore = reduceScore - score;
				creditRecordRepository.save(creditRecordEntity);
				break;
			} else {
				creditRecordEntity.setChangeType(CreditRecordConst.CHANGETYPE_REMIND);
				creditRecordEntity.setRemindNumer(score - reduceScore);
				reduceScore = 0;
				creditRecordRepository.save(creditRecordEntity);
				break;
			}
		}

	}

	@Override
	public List<PresentCardEntity> getPresentCardEntities(long resultId) {
		// TODO Auto-generated method stub
		PresentResultEntity presentResultEntity = presentResultRepository.findById(resultId).get();
		List<PresentCardEntity> presentCardEntities = presentCardRepository
				.findByConvertDate(presentResultEntity.getRecordTime());
		return presentCardEntities;

	}

	@Override
	public MallUserInfoEntity findByMallUserEntity(MallUserEntity mallUserEntity) {
		// TODO Auto-generated method stub
		MallUserInfoEntity mallUserInfoEntity = mallUserInfoRepository.findByMallUserEntity(mallUserEntity);
		return mallUserInfoEntity;
	}

}
