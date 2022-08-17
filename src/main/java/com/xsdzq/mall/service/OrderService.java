package com.xsdzq.mall.service;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.ExchangePrizeReqDTO;
import com.xsdzq.mall.model.ExchangePrizeRespDTO;
import com.xsdzq.mall.model.ExchangeRecordRespDTO;
import com.xsdzq.mall.model.MyExchangeRecordRespDTO;

import java.util.List;

public interface OrderService {

    List<ExchangeRecordRespDTO> getLatestExchangeRecord();

    ExchangePrizeRespDTO exchangePrize(MallUserEntity mallUserEntity, ExchangePrizeReqDTO exchangePrizeReqDTO);

    List<MyExchangeRecordRespDTO> getUserExchangeRecord(MallUserEntity mallUserEntity);
}
