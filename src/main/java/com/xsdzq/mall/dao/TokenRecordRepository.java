package com.xsdzq.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsdzq.mall.entity.TokenRecordEntity;

public interface TokenRecordRepository extends JpaRepository<TokenRecordEntity, Long> {

}
