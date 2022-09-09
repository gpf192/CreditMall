package com.xsdzq.mall.controller;


import com.xsdzq.mall.annotation.UserLoginToken;
import com.xsdzq.mall.constants.ExchangeStatusEnum;
import com.xsdzq.mall.constants.OrderStatusEnum;
import com.xsdzq.mall.entity.CRMCreditProductViewEntity;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.CreditRecordMap;
import com.xsdzq.mall.model.MyExchangeRecordRespDTO;
import com.xsdzq.mall.model.PresentResult;
import com.xsdzq.mall.model.ResultNumber;
import com.xsdzq.mall.service.CreditService;
import com.xsdzq.mall.service.OrderService;
import com.xsdzq.mall.service.TokenService;
import com.xsdzq.mall.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mall/credit")
public class CreditController {

	@Autowired
	private CreditService creditService;

	@Autowired
	private TokenService tokenService;

	@Resource
	private OrderService orderService;

	@GetMapping(value = "/record")
	@UserLoginToken
	public Map<String, Object> getMyRecordResult(@RequestHeader("Authorization") String token,
			@RequestParam int pageNumber, @RequestParam int pageSize) {
		MallUserEntity mallUserEntity = tokenService.getMallUserEntity(token);
		List<CreditRecordEntity> creditRecordEntities = creditService.getMallUserRecords(mallUserEntity, pageNumber,
				pageSize);
		return GsonUtil.buildMap(0, "success", creditRecordEntities);
	}

	@GetMapping(value = "/month")
	@UserLoginToken
	public Map<String, Object> getMyMothRecordReord(@RequestHeader("Authorization") String token) {
		MallUserEntity mallUserEntity = tokenService.getMallUserEntity(token);
		CreditRecordMap creditRecordMap = creditService.getUserCreditRecord(mallUserEntity);
		return GsonUtil.buildMap(0, "success", creditRecordMap);
	}

	@GetMapping(value = "/result")
	@UserLoginToken
	public Map<String, Object> getMyPresetResult(@RequestHeader("Authorization") String token) {
		MallUserEntity mallUserEntity = tokenService.getMallUserEntity(token);
		PresentResult presentResult = creditService.getPresentResultEntities(mallUserEntity);
		return GsonUtil.buildMap(0, "success", presentResult);
	}

	@GetMapping("/crm/getAllProducts")
	public Map<String, Object> getAllProducts() {

		List<CRMCreditProductViewEntity> entities = creditService.getAllCrmProducts();
		return GsonUtil.buildMap(0, "success", entities);
	}

	@GetMapping(value = "/composite-result")
	@UserLoginToken
	public Map<String, Object> getOrder(@RequestHeader("Authorization") String token) {
		MallUserEntity mallUserEntity = tokenService.getMallUserEntity(token);
		PresentResult presentResult = creditService.getPresentResultEntities(mallUserEntity);

		List<MyExchangeRecordRespDTO> userExchangeRecord = orderService.getUserExchangeRecord(mallUserEntity);
		if (!CollectionUtils.isEmpty(userExchangeRecord)) {
			ResultNumber resultNumber = presentResult.getResultNumber();
			for (MyExchangeRecordRespDTO mer : userExchangeRecord) {
				if (ExchangeStatusEnum.SUCCESS.getCode().equals(mer.getStatus())) {
					if (mer.getUseIntegral() != null) {
						resultNumber.setUsedScore(mer.getUseIntegral() + resultNumber.getUsedScore());
					}
					if (mer.getExchangePrice() != null) {
						resultNumber.setUsedValue(mer.getExchangePrice().add(resultNumber.getUsedValue()));
					}
				}
			}
		}
		presentResult.setDirectRechargeResultList(userExchangeRecord);
		return GsonUtil.buildMap(0, "success", presentResult);
	}
}
