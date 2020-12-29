package com.xsdzq.mall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsdzq.mall.entity.PresentCardEntity;
import com.xsdzq.mall.service.PresentCardService;
import com.xsdzq.mall.util.GsonUtil;

@RestController
@RequestMapping(value = "/mall/card")
public class PresentCardController {
	@Autowired
	private PresentCardService presentCardService;

	//	@PostMapping(value = "/add")
	//	public Map<String, Object> addCard(@RequestBody PresentCardEntity presentCardEntity) {
	//
	//		presentCardService.addPresentCard(presentCardEntity);
	//		return GsonUtil.buildMap(0, "success", null);
	//	}

	@GetMapping(value = "/all")
	public Map<String, Object> getPresentCategorys() {

		List<PresentCardEntity> presentCardEntities = presentCardService.getPresentCardEntities();
		return GsonUtil.buildMap(0, "success", presentCardEntities);
	}

}
