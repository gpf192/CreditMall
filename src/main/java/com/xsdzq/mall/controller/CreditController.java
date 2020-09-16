package com.xsdzq.mall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsdzq.mall.entity.CreditRecordEntity;
import com.xsdzq.mall.service.CreditService;
import com.xsdzq.mall.util.GsonUtil;

@RestController
@RequestMapping(value = "/mall/credit")
public class CreditController {

	@Autowired
	private CreditService creditService;

	@GetMapping(value = "/all")
	public Map<String, Object> addMallUser() {

		// mallUserService.addMallUser(mallUserEntity);
		List<CreditRecordEntity> creditList = creditService.getAllCreditRecordEntities();
		return GsonUtil.buildMap(0, "success", creditList);

	}

}
