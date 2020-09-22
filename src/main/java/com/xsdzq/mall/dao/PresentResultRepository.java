package com.xsdzq.mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.PresentResultEntity;

public interface PresentResultRepository extends JpaRepository<PresentResultEntity, Long>, PresentResultSweeper {

	List<PresentResultEntity> findByMallUserEntityOrderByRecordTimeDesc(MallUserEntity mallUserEntity);

	List<PresentResultEntity> findByMallUserEntityAndDateFlagOrderByRecordTimeDesc(MallUserEntity mallUserEntity,
			String dateFlag);

}
