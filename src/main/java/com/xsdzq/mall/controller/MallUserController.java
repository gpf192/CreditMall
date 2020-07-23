package com.xsdzq.mall.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsdzq.mall.service.MallUserService;
import com.xsdzq.mall.util.GsonUtil;

@RestController
@RequestMapping(value = "/mall/user")
public class MallUserController {

	@Autowired
	private MallUserService mallUserService;

	@PostMapping(value = "/add")
	public Map<String, Object> addMallUser() {

//mallUserService.addMallUser(mallUserEntity);
		return GsonUtil.buildMap(0, "success", null);

	}

}
