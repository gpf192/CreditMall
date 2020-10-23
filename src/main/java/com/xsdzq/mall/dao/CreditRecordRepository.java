package com.xsdzq.mall.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;

public interface CreditRecordRepository extends JpaRepository<CreditRecordEntity, Long>, CreditRecordSweeper {

	List<CreditRecordEntity> findByMallUserEntityOrderByRecordTimeDesc(MallUserEntity mallUserEntity);

	Page<CreditRecordEntity> findByMallUserEntityOrderByRecordTimeDesc(MallUserEntity mallUserEntity,
			Pageable pageable);

	List<CreditRecordEntity> findByMallUserEntityAndTypeAndChangeTypeLessThanEqualOrderByBeginDate(
			MallUserEntity mallUserEntity, Boolean type, int changeType);

}
