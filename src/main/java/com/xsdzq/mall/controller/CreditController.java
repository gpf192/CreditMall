package com.xsdzq.mall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsdzq.mall.entity.CRMCreditProductViewEntity;
import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.model.CreditRecordMap;
import com.xsdzq.mall.model.PresentResult;
import com.xsdzq.mall.service.CreditService;
import com.xsdzq.mall.service.TokenService;
import com.xsdzq.mall.util.GsonUtil;

@RestController
@RequestMapping(value = "/mall/credit")
public class CreditController {

	@Autowired
	private CreditService creditService;

	@Autowired
	private TokenService tokenService;

	//	@GetMapping(value = "/all")
	//	public Map<String, Object> addMallUser() {
	//
	//		// mallUserService.addMallUser(mallUserEntity);
	//		List<CreditRecordEntity> creditList = creditService.getAllCreditRecordEntities();
	//		return GsonUtil.buildMap(0, "success", creditList);
	//	}

	@GetMapping(value = "/record")
	public Map<String, Object> getMyRecordResult(@RequestHeader("Authorization") String token,
			@RequestParam int pageNumber, @RequestParam int pageSize) {
		MallUserEntity mallUserEntity = tokenService.getMallUserEntity(token);
		List<CreditRecordEntity> creditRecordEntities = creditService.getMallUserRecords(mallUserEntity, pageNumber,
				pageSize);
		return GsonUtil.buildMap(0, "success", creditRecordEntities);
	}

	@GetMapping(value = "/month")
	public Map<String, Object> getMyMothRecordReord(@RequestHeader("Authorization") String token) {
		MallUserEntity mallUserEntity = tokenService.getMallUserEntity(token);
		CreditRecordMap creditRecordMap = creditService.getUserCreditRecord(mallUserEntity);
		return GsonUtil.buildMap(0, "success", creditRecordMap);
	}

	@GetMapping(value = "/result")
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
	


}
