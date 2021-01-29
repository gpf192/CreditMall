package com.xsdzq.mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.MallUserEntity;

public interface MallUserRepository extends JpaRepository<MallUserEntity, Long> {

	MallUserEntity findByClientId(String clientId);
	
	List<MallUserEntity> findByLoginClentId(String loginClentId);


}
