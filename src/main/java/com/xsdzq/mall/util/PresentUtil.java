package com.xsdzq.mall.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PresentUtil {

	private static final Logger log = LoggerFactory.getLogger(PresentUtil.class);

	public static String PRIZE_LOGIN_TYPE = "11";
	public static String PRIZE_SHARE_TYPE = "12";
	public static String PRIZE_REDUCE_TYPE = "01";

	private Random r = null;

	private PresentUtil() {
		r = new Random();
	}

	private volatile static PresentUtil singlePrizeUtil = null;

	public static PresentUtil getInstance() {
		if (singlePrizeUtil == null) {
			synchronized (PresentUtil.class) {
				if (singlePrizeUtil == null) {
					singlePrizeUtil = new PresentUtil();
				}
			}
		}
		return singlePrizeUtil;
	}

	/*
	 * params （int prizeAmount, int scope） prizeAmount 作为分子，scope作为分母。 随机数在分子中即为中奖
	 */

	public boolean testChoice(int prizeAmount, int scope) {
		// 得到随机数，在[1,scope+)
		int random = r.nextInt(scope) + 1;
		log.info("PrizeUtil: random " + random);
		if (random <= prizeAmount) {
			return true;
		} else {
			return false;
		}
	}


	// [0,n)
	public int getRandomTicket() {
		int numberArray[] = { 100, 200, 300, 500 };
		int random = r.nextInt(4);
		System.out.println("random: " + random);
		return numberArray[random];
	}

	/*
	 * public PrizeResultEntity getSecretPrizeResultEntity(PrizeResultEntity
	 * prizeResultEntity) { UserEntity userEntity =
	 * prizeResultEntity.getUserEntity();
	 * userEntity.setClientId(getSecretString(userEntity.getClientId()));
	 * userEntity.setFundAccount(getSecretString(userEntity.getFundAccount()));
	 * prizeResultEntity.setUserEntity(userEntity); return prizeResultEntity; }
	 */

	public String getSecretString(String no) {
		if (no.length() > 6) {
			return getStarString(no, 1, 5);
		}
		return no;
	}

	public String getSecretName(String name) {
		int len = name.length();
		if (len < 2) {
			return name;
		} else if (len == 2) {
			return name.substring(0, 1) + "*";
		} else if (len == 3) {
			return name.substring(0, 1) + "*" + name.substring(2);
		} else if (len > 3) {
			int half = (int) Math.floor(len / 2.0);
			int bagin = half - 2;
			int end = half + 2;
			return getStarString(name, bagin, end);
		}
		return name;
	}

	private static String getStarString(String content, int begin, int end) {

		if (begin >= content.length() || begin < 0) {
			return content;
		}
		if (end >= content.length() || end < 0) {
			return content;
		}
		if (begin >= end) {
			return content;
		}
		String starStr = "";
		for (int i = begin; i < end; i++) {
			starStr = starStr + "*";
		}
		return content.substring(0, begin) + starStr + content.substring(end, content.length());
	}

}
