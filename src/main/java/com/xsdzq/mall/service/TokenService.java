package com.xsdzq.mall.service;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.ParamEntity;
import com.xsdzq.mall.model.User;

public interface TokenService {

	public static String XSDZQSUBJECT = "xsdzq";

	String getToken(User user);

	MallUserEntity getMallUserEntity(String token);

	ParamEntity getValueByCode(String code);

	void modifyParam(ParamEntity entity);
}
