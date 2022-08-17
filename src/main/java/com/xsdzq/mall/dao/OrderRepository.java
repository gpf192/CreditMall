package com.xsdzq.mall.dao;

import com.xsdzq.mall.entity.MallOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<MallOrderEntity, Long> {

    Page<MallOrderEntity> findByOrderStatusOrderByEndTimeDesc(Integer orderStatus, Pageable pageable);

    List<MallOrderEntity> findByClientIdAndTradeDate(String clientId, Integer tradeDate);

    MallOrderEntity findByOrderNo(String orderNo);

    Page<MallOrderEntity> findByClientIdOrderByCreateTimeDesc(String clientId, Pageable pageable);
}