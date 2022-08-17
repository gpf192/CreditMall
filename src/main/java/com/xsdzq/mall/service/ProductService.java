package com.xsdzq.mall.service;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.DirectRechargeRespDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<DirectRechargeRespDTO> getHotDirectRecharge(MallUserEntity mallUserEntity);

    Integer getProductIntegral(BigDecimal productPrice);
}