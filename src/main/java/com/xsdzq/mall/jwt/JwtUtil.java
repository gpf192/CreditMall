package com.xsdzq.mall.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xsdzq.mall.model.User;

public class JwtUtil {
	@Value("${jwt.expiretime}")
	private int expiretime;
	@Value("${jwt.secret.key}")
	private String key;

	public static String XSDZQSUBJECT = "xsdzq";

	public String getToken(User user) {

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

}
