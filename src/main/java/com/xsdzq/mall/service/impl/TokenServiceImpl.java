package com.xsdzq.mall.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xsdzq.mall.dao.MallUserRepository;
import com.xsdzq.mall.dao.ParamRepository;
import com.xsdzq.mall.entity.MallUserEntity;
import com.xsdzq.mall.entity.ParamEntity;
import com.xsdzq.mall.model.User;
import com.xsdzq.mall.service.TokenService;

@Service("tokenServiceImpl")
public class TokenServiceImpl implements TokenService {

	@Value("${jwt.expiretime}")
	private int expiretime;
	@Value("${jwt.secret.key}")
	private String key;

	@Autowired
	MallUserRepository mallUserRepository;

	@Autowired
	ParamRepository paramRepository;

	@Override
	public String getToken(User user) {
		// TODO Auto-generated method stub
		Algorithm algorithm = Algorithm.HMAC256(key);
		long nowMillis = System.currentTimeMillis();
		long exprieMillis = nowMillis + expiretime;
		Date now = new Date(nowMillis);
		Date exprieDate = new Date(exprieMillis);

		String token = "";
		token = JWT.create().withSubject(XSDZQSUBJECT).withAudience(user.getClientId()).withIssuedAt(now)
				.withExpiresAt(exprieDate).sign(algorithm);
		return token;
	}

	@Override
	public MallUserEntity getMallUserEntity(String token) {
		// TODO Auto-generated method stub
		String clientId = JWT.decode(token).getAudience().get(0);
		MallUserEntity mallUserEntity = mallUserRepository.findByClientId(clientId);
		return mallUserEntity;
	}

	@Override
	public ParamEntity getValueByCode(String code) {
		// TODO Auto-generated method stub
		// ParamEntity p = paramRepository.getValueByCode(code);
		ParamEntity paramEntity = new ParamEntity();
		return paramEntity;
	}

	@Override
	@Transactional
	public void modifyParam(ParamEntity entity) {
		// TODO Auto-generated method stub
		// paramRepository.modifyParam(entity);
	}

}
