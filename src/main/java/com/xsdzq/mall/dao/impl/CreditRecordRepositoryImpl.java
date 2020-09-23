package com.xsdzq.mall.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.xsdzq.mall.dao.CreditRecordSweeper;
import com.xsdzq.mall.entity.CreditRecordCountEntity;
import com.xsdzq.mall.entity.MallUserEntity;

public class CreditRecordRepositoryImpl implements CreditRecordSweeper {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CreditRecordCountEntity> getCreditRecordCountList(MallUserEntity mallUserEntity) {
		// TODO Auto-generated method stub
		String sql = "select sum(record.integral_number) AS sum_score, record.group_time AS group_time from mall_credit_record record where record.client_id =?1 group by record.group_time order by record.group_time desc";
		Query query = entityManager.createNativeQuery(sql, CreditRecordCountEntity.class);
		query.setParameter(1, mallUserEntity.getClientId());
		return query.getResultList();
	}

}
