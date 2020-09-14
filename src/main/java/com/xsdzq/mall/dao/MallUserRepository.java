package com.xsdzq.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.MallUserEntity;

public interface MallUserRepository extends JpaRepository<MallUserEntity, Long> {

	MallUserEntity findByClientId(String clientId);

}
