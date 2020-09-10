package com.xsdzq.mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.PresentCategoryEntity;
import com.xsdzq.mall.entity.PresentEntity;

public interface PresentRepository extends JpaRepository<PresentEntity, Long> {
	
	List<PresentEntity> findByPresentCategoryEntity(PresentCategoryEntity presentCategoryEntity);

}
