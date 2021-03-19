package com.xsdzq.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.dao.UserBlackListRepository;
import com.xsdzq.mall.entity.UserBlackListEntity;
import com.xsdzq.mall.service.UserBlackListService;

@Service
@Transactional(readOnly = true)
public class UserBlackListServiceImpl implements UserBlackListService {

	@Autowired
	private UserBlackListRepository userBlackListRepository;

	@Override
	public UserBlackListEntity getBlackListEntityByClientId(String clientId) {
		// TODO Auto-generated method stub
		return userBlackListRepository.findByClientId(clientId);

	}

}
