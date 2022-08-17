package com.xsdzq.mall.dao;

import com.xsdzq.mall.entity.MallProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<MallProductEntity, Long> {

    List<MallProductEntity> findBySellStatusOrderByOfficialPriceAsc(Integer sellStatus);

    MallProductEntity findByGoodsNoAndGoodsTypeId(String goodsNo,String goodsTypeId);
}
