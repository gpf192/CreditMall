package com.xsdzq.mall.service.client.chengquan;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DirectChargeServiceImpl implements DirectChargeService {
    private static final Logger logger = LoggerFactory.getLogger(DirectChargeServiceImpl.class);
    @Value("${chengquan.service.url}")
    private String serviceUrl;
    @Value("${chengquan.payTel.api}")
    private String payTelApi;
    @Value("${chengquan.getOrder.api}")
    private String getOrderApi;
    @Value("${chengquan.appId}")
    private String appId;
    @Value("${chengquan.key}")
    private String key;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public CommonRespEntity<PayTelRespEntity> payTel(PayTelReqEntity req) {
        assert req != null && req.getOrder_no() != null && req.getRecharge_number() != null && req.getPrice() != null && req.getTimestamp() != null;

        LinkedHashMap<String, String> signMap = new LinkedHashMap<>();
        signMap.put("app_id", "&");
        signMap.put("order_no", req.getOrder_no());
        signMap.put("price", req.getPrice().toString());
        signMap.put("recharge_number", req.getRecharge_number());
        signMap.put("timestamp", req.getTimestamp().toString());
        signMap.put("key", "&");
        paddingReq(req, signMap);

        ParameterizedTypeReference<CommonRespEntity<PayTelRespEntity>> respType = new ParameterizedTypeReference<CommonRespEntity<PayTelRespEntity>>() {
        };
        String urlTemplate = serviceUrl + "/" + payTelApi + "?app_id={0}&order_no={1}&price={2}&recharge_number={3}&timestamp={4}&sign={5}";
        String paramUrl = MessageFormat.format(urlTemplate, req.getApp_id(), req.getOrder_no(), req.getPrice().toString(), req.getRecharge_number(), req.getTimestamp().toString(), req.getSign());
        try {
            logger.info("话费下单接口请求开始req:{}", paramUrl);
            ResponseEntity<CommonRespEntity<PayTelRespEntity>> respEntity = restTemplate.exchange(paramUrl, HttpMethod.GET, null, respType);
            logger.info("话费下单接口请求完成resp:{}", JSON.toJSON(respEntity.getBody()));
            if (HttpStatus.OK == respEntity.getStatusCode()) {
                return respEntity.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("话费下单接口请求异常");
    }

    @Override
    public CommonRespEntity<GetOrderRespEntity> getOrder(GetOrderReqEntity req) {
        assert req != null && req.getOrder_no() != null;

        LinkedHashMap<String, String> signMap = new LinkedHashMap<>();
        signMap.put("app_id", "&");
        signMap.put("order_no", req.getOrder_no());
        signMap.put("timestamp", "&");
        signMap.put("key", "&");
        paddingReq(req, signMap);

        ParameterizedTypeReference<CommonRespEntity<GetOrderRespEntity>> respType = new ParameterizedTypeReference<CommonRespEntity<GetOrderRespEntity>>() {
        };
        String urlTemplate = serviceUrl + "/" + getOrderApi + "?app_id={0}&order_no={1}&timestamp={2}&sign={3}";
        String paramUrl = MessageFormat.format(urlTemplate, req.getApp_id(), req.getOrder_no(), req.getTimestamp().toString(), req.getSign());
        try {
            logger.info("订单查询接口请求开始req:{}", paramUrl);
            ResponseEntity<CommonRespEntity<GetOrderRespEntity>> respEntity = restTemplate.exchange(paramUrl, HttpMethod.GET, null, respType);
            logger.info("订单查询接口请求完成resp:{}", JSON.toJSON(respEntity.getBody()));
            if (HttpStatus.OK == respEntity.getStatusCode()) {
                return respEntity.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("订单查询接口请求异常");
    }

    private void paddingReq(AbstractHeadReqEntity req, LinkedHashMap<String, String> signMap) {
        if (req.getTimestamp() == null) {
            Long timestamp = new Date().getTime();
            req.setTimestamp(timestamp);
            signMap.replace("timestamp", "&", timestamp.toString());
        }

        req.setApp_id(appId);
        signMap.replace("app_id", "&", appId);
        signMap.replace("key", "&", key);
        req.setSign(getSign(signMap));
    }

    private String getSign(LinkedHashMap<String, String> map) {
        StringBuilder sb = new StringBuilder(200);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }

        return DigestUtils.md5DigestAsHex(sb.substring(1).getBytes()).toUpperCase();
    }
}
