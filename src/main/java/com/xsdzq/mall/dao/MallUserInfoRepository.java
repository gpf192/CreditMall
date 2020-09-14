package com.xsdzq.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.MallUserInfoEntity;

public interface MallUserInfoRepository extends JpaRepository<MallUserInfoEntity, Long> {

	MallUserInfoEntity findByMallUserEntity(MallUserEntity mallUserEntity);

}
