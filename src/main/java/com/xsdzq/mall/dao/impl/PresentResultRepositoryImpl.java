package com.xsdzq.mall.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xsdzq.mall.dao.PresentResultSweeper;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.ResultCountEntity;

public class PresentResultRepositoryImpl implements PresentResultSweeper {

	private static final Logger log = LoggerFactory.getLogger(PresentResultRepositoryImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ResultCountEntity> findResultCountList(MallUserEntity mallUserEntity) {
		// TODO Auto-generated method stub
		String sql = "select result.group_time, sum(result.value) as sum_value, sum(result.integral_number) as sum_credit from mall_present_result result where result.client_id=?1 group by result.group_time order by result.group_time desc";

		/*TypedQuery<ResultCount> sqlQuery = em.createQuery(
				"select result.groupTime as groupTime, sum(result.value) as sumValue, sum(result.integralNumber) as sumCredit from PresentResultEntity result where result.mallUserEntity=?1 group by result.groupTime",
				ResultCount.class);
		sqlQuery.setParameter(1, mallUserEntity);
		sqlQuery.getResultList();*/
		
		
		//Query sqlQuery = em.createQuery(
		//		"select result.groupTime as groupTime, sum(result.value) as sumValue, sum(result.integralNumber) as sumCredit from PresentResultEntity result where result.mallUserEntity=?1 group by result.groupTime order by result.groupTime desc");
		//sqlQuery.setParameter(1, mallUserEntity);
		//List list = sqlQuery.getResultList();
		//for (Object object : list) {
			
		//}
		
		Query query = em.createNativeQuery(sql, ResultCountEntity.class);
		query.setParameter(1, mallUserEntity.getClientId()); 
		List<ResultCountEntity> resultList = query.getResultList();

		
		 /* Query query = em.createQuery(sql); 
		  query.setParameter(1,mallUserEntity.getClientId());
		  List<ResultCount> list =  query.getResultList(); 
		  em.close();*/
		 
		
		  //Query query = em.createNativeQuery(sql,ResultCount.class);
		  //query.setParameter(1, mallUserEntity.getClientId()); 
		  //em.clear();
		  //query.getResultList();
		  
		  //List<ResultCountInterface> list = query.getResultList(); for (ResultCount resultCount
		  //: list) { log.info(resultCount.toString()); }
		 
		
		
		return resultList;
	}

}
