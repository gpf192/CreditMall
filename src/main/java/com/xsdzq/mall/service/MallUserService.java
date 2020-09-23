package com.xsdzq.mall.service;

import java.util.List;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.PresentCardEntity;
import com.xsdzq.mall.model.ActivityNumber;
import com.xsdzq.mall.model.PreExchangePresent;
import com.xsdzq.mall.model.PresentModelNumber;
import com.xsdzq.mall.model.User;

public interface MallUserService {

	public ActivityNumber login(User user);

	public MallUserEntity getUserByClientId(String clientId);

	public void addMallUser(MallUserEntity mallUserEntity);

	public boolean isCanExchange(MallUserEntity mallUserEntity, String prizeId);

	public PreExchangePresent preExchangePresent(MallUserEntity mallUserEntity, int presentId);

	public void exchangePresent(MallUserEntity mallUserEntity, PresentModelNumber presentModelNumber);

	public List<PresentCardEntity> getPresentCardEntities(long resultId);

	public void addCreditScore();

}
