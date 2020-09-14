package com.xsdzq.mall.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.ActivityNumber;
import com.xsdzq.mall.model.User;
import com.xsdzq.mall.model.UserData;
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
	public Map<String, Object> login(@RequestBody UserData userData) throws Exception {
		String cryptUserString = userData.getEncryptData();
		String userString = AESUtil.decryptAES(cryptUserString);
		log.info(userString);
		User user = JSON.parseObject(userString, User.class);
		ActivityNumber activityNumber = mallUserService.login(user);
		MallUserEntity mallUserEntity = mallUserService.getUserByClientId(user.getClientId());
		String token = tokenService.getToken(UserUtil.convertUserByUserEntity(mallUserEntity));
		activityNumber.setToken(token);
		return GsonUtil.buildMap(0, "ok", activityNumber);
	}

	@PostMapping(value = "/exchange")
	public Map<String, Object> exchangePrize(@RequestBody UserData userData) throws Exception {

		return GsonUtil.buildMap(0, "ok", null);
	}

	@GetMapping(value = "/credit/add")
	public Map<String, Object> addMallUser() {

		// mallUserService.addMallUser(mallUserEntity);
		mallUserService.addCreditScore();
		return GsonUtil.buildMap(0, "success", null);

	}

}
