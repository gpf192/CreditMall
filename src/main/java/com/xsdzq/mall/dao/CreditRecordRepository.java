package com.xsdzq.mall.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;

public interface CreditRecordRepository extends JpaRepository<CreditRecordEntity, Long>, CreditRecordSweeper {

	List<CreditRecordEntity> findByMallUserEntityOrderByRecordTimeDesc(MallUserEntity mallUserEntity);

	Page<CreditRecordEntity> findByMallUserEntityOrderByGroupTimeDescRecordTimeDesc(MallUserEntity mallUserEntity,
			Pageable pageable);

	List<CreditRecordEntity> findByMallUserEntityAndTypeAndChangeTypeLessThanEqualOrderByEndDate(
			MallUserEntity mallUserEntity, Boolean type, int changeType);
	
	@Query(value = "select r from CreditRecordEntity r where r.mallUserEntity = ?1 and r.type = 1 and r.changeType <=1 order by r.endDate , r.recordTime")
	List<CreditRecordEntity> findByUnusedCredit(MallUserEntity mallUserEntity, Boolean type, int changeType);

}
