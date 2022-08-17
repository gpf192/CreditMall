package com.xsdzq.mall.dao;

import com.xsdzq.mall.entity.MallBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BrandRepository extends JpaRepository<MallBrandEntity, Long> {

    List<MallBrandEntity> findBySellStatusOrderByCreateTimeDesc(Integer sellStatus);

    MallBrandEntity findByGoodsTypeId(String goodsTypeId);
}