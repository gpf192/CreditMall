package com.xsdzq.mall.dao;

import java.util.List;

import com.xsdzq.mall.entity.CreditRecordCountEntity;
import com.xsdzq.mall.entity.MallUserEntity;

public interface CreditRecordSweeper {

	List<CreditRecordCountEntity> getCreditRecordCountList(MallUserEntity mallUserEntity);

}
