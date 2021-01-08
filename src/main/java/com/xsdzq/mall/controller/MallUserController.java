package com.xsdzq.mall.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xsdzq.mall.annotation.UserLoginToken;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.MallUserInfoEntity;
import com.xsdzq.mall.entity.PresentCardEntity;
import com.xsdzq.mall.model.ActivityNumber;
import com.xsdzq.mall.model.PreExchangePresent;
import com.xsdzq.mall.model.PresentModel;
import com.xsdzq.mall.model.PresentModelNumber;
import com.xsdzq.mall.model.User;
import com.xsdzq.mall.model.UserData;
import com.xsdzq.mall.model.UserScoreNumber;
import com.xsdzq.mall.service.MallUserService;
import com.xsdzq.mall.service.TokenService;
import com.xsdzq.mall.util.AESUtil;
import com.xsdzq.mall.util.GsonUtil;
import com.xsdzq.mall.util.UserUtil;

@RestController
@RequestMapping(value = "/mall/user")
public class MallUserController {

	private static final Logger log = LoggerFactory.getLogger(MallUserController.class);

	@Autowired
	private MallUserService mallUserService;

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/login")
	public Map<String, Object> login(@RequestBody UserData userData) {
		String cryptUserString = userData.getEncryptData().trim();
		String userString;
		try {
			userString = AESUtil.decryptAES(cryptUserString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return GsonUtil.buildMap(1, "登录失败", null);
		}
		log.info(userString);
		User user = null;
		try {
			user = JSON.parseObject(userString, User.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return GsonUtil.buildMap(1, "登录失败", null);
		}
		log.info(user.toString());

		if (user.getClientId() == null || user.getClientId().equals("")) {
			return GsonUtil.buildMap(1, "登录信息为空，请重新登录", null);
		}

		if (user.getAccessToken() == null || user.getAccessToken().equals("")) {
			return GsonUtil.buildMap(1, "token不能为空", null);
		}
		// 二期兑换功能 --增加校验

		if (user.getLoginClientId() == null || user.getLoginClientId().equals("")) {
			return GsonUtil.buildMap(1, "登录标示不能为空", null);
		}

		ActivityNumber activityNumber = mallUserService.login(user);
		if (activityNumber == null) {
			return GsonUtil.buildMap(1, "登录失败，请重新登录", null);
		}
		MallUserEntity mallUserEntity = mallUserService.getUserByClientId(user.getClientId());
		String token = tokenService.getToken(UserUtil.convertUserByUserEntity(mallUserEntity));
		activityNumber.setToken(token);
		return GsonUtil.buildMap(0, "ok", activityNumber);
	}

	@PostMapping(value = "/preExchange")
	@UserLoginToken
	public Map<String, Object> preExchange(@RequestHeader("Authorization") String token,
			@RequestBody PresentModel presentModel) {

		MallUserEntity mallUserEntity = tokenService.getMallUserEntity(token);
		log.info("getPresentId: " + presentModel.getPresentId());
		PreExchangePresent preExchangePresent = mallUserService.preExchangePresent(mallUserEntity,
				presentModel.getPresentId());
		return GsonUtil.buildMap(0, "ok", preExchangePresent);
	}

	@PostMapping(value = "/exchange")
	@UserLoginToken
	public Map<String, Object> exchangePrize(@RequestHeader("Authorization") String token,
			@RequestBody PresentModelNumber presentModelNumber) {
		MallUserEntity mallUserEntity = tokenService.getMallUserEntity(token);
		if (mallUserEntity == null) {
			return GsonUtil.buildMap(1, "非法访问", null);
		}
		log.info("getPresentId: " + presentModelNumber.getPresentId());
		mallUserService.exchangePresent(mallUserEntity, presentModelNumber);
		MallUserInfoEntity mallUserInfoEntity = mallUserService.findByMallUserEntity(mallUserEntity);
		UserScoreNumber userScoreNumber = new UserScoreNumber();
		if (mallUserInfoEntity != null) {
			userScoreNumber.setScoreNumber(mallUserInfoEntity.getCreditScore());
		}
		return GsonUtil.buildMap(0, "ok", userScoreNumber);
	}

	@GetMapping(value = "/cards")
	@UserLoginToken
	public Map<String, Object> getPresentCards(@RequestHeader("Authorization") String token,
			@RequestParam long resultId) {
		// MallUserEntity mallUserEntity = tokenService.getMallUserEntity(token);
		List<PresentCardEntity> presentCardEntities = mallUserService.getPresentCardEntities(resultId);
		return GsonUtil.buildMap(0, "ok", presentCardEntities);

	}

}
