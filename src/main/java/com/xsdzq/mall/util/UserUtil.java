package com.xsdzq.mall.util;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.User;

public class UserUtil {

	public static User convertUserByUserEntity(MallUserEntity userEntity) {
		User user = new User();
		user.setClientId(userEntity.getClientId());
		user.setLoginClientId(userEntity.getLoginClentId());
		user.setClientName(userEntity.getClientName());
		user.setFundAccount(userEntity.getFundAccount());
		user.setAccessToken(userEntity.getAccessToken());
		user.setMobile(userEntity.getMobile());
		user.setAppVersion(userEntity.getAppVersion());
		user.setLastOpIP(userEntity.getLastOpIP());
		user.setLastLoginTime(userEntity.getLastLoginTime());
		// user.setDepartmentCode(userEntity.getDepartmentCode());
		// user.setDepartmentName(userEntity.getDepartmentName());
		return user;
	}

	public static MallUserEntity convertUserByUserEntity(User user) {
		MallUserEntity userEntity = new MallUserEntity();
		userEntity.setClientId(user.getClientId());
		userEntity.setLoginClentId(user.getLoginClientId());
		userEntity.setClientName(user.getClientName());
		userEntity.setFundAccount(user.getFundAccount());
		userEntity.setAccessToken(user.getAccessToken());
		userEntity.setMobile(user.getMobile());
		userEntity.setAppVersion(user.getAppVersion());
		userEntity.setLastOpIP(user.getLastOpIP());
		userEntity.setLastLoginTime(user.getLastLoginTime());
		// userEntity.setDepartmentCode(user.getDepartmentCode());
		// userEntity.setDepartmentName(user.getDepartmentName());
		return userEntity;
	}

	public static void updateUserEntityByUser(MallUserEntity userEntity, User user) {
		// userEntity.setClientId(user.getClientId());
		userEntity.setLoginClentId(user.getLoginClientId());
		if (userEntity.getClientName() == null || userEntity.getClientName() == "") {
			userEntity.setClientName(user.getClientName());
		}
		userEntity.setFundAccount(user.getFundAccount());
		userEntity.setAccessToken(user.getAccessToken());
		userEntity.setMobile(user.getMobile());
		userEntity.setAppVersion(user.getAppVersion());
		userEntity.setLastOpIP(user.getLastOpIP());
		userEntity.setLastLoginTime(user.getLastLoginTime());
		// userEntity.setDepartmentCode(user.getDepartmentCode());
		// userEntity.setDepartmentName(user.getDepartmentName());
	}

}
