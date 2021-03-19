package com.xsdzq.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.UserBlackListEntity;

public interface UserBlackListRepository extends JpaRepository<UserBlackListEntity, Long> {
	
	UserBlackListEntity findByClientId(String clientId);

}
