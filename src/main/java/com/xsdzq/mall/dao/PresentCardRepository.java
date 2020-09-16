package com.xsdzq.mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.PresentCardEntity;
import com.xsdzq.mall.entity.PresentEntity;

public interface PresentCardRepository extends JpaRepository<PresentCardEntity, Long> {

	List<PresentCardEntity> findByPresentEntityAndConvertStatus(PresentEntity presentEntity, int convertStatus);

}
