package com.xsdzq.mall.service.client.chengquan;

public interface DirectChargeService {

    CommonRespEntity<PayTelRespEntity> payTel(PayTelReqEntity telPayReqEntity);

    CommonRespEntity<GetOrderRespEntity> getOrder(GetOrderReqEntity orderGetReqEntity);
}
