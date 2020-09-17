package com.xsdzq.mall.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;

public interface CreditRecordRepository extends JpaRepository<CreditRecordEntity, Long> {

	Page<CreditRecordEntity> findByMallUserEntityOrderByRecordTimeDesc(MallUserEntity mallUserEntity,
			Pageable pageable);

}
