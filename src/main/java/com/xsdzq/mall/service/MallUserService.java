package com.xsdzq.mall.service;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.ActivityNumber;
import com.xsdzq.mall.model.User;

public interface MallUserService {

	public ActivityNumber login(User user);

	public MallUserEntity getUserByClientId(String clientId);

	public void addMallUser(MallUserEntity mallUserEntity);

	public void addCreditScore();

}
