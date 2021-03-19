package com.xsdzq.mall.service;

import com.xsdzq.mall.entity.UserBlackListEntity;

public interface UserBlackListService {

	UserBlackListEntity getBlackListEntityByClientId(String clientId);

}
