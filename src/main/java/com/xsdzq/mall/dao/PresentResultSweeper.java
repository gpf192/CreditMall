package com.xsdzq.mall.dao;

import java.util.List;

import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.ResultCountEntity;

public interface PresentResultSweeper {

	List<ResultCountEntity> findResultCountList(MallUserEntity mallUserEntity);

}
