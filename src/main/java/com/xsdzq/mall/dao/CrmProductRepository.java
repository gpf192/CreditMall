package com.xsdzq.mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.CRMCreditProductViewEntity;

public interface CrmProductRepository extends JpaRepository<CRMCreditProductViewEntity, Long>{
	List<CRMCreditProductViewEntity> findByOrderByProductCode();
}
