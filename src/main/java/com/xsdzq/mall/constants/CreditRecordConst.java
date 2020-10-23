package com.xsdzq.mall.constants;

public interface CreditRecordConst {

	// 增减积分
	boolean ADDSCORE = true;
	boolean REDUCESCORE = false;

	// 换算系数
	int EXCHANGECOEFFICIENT = 100;

	// item 系列
	String NEWACCOUNT = "10";
	String NEWACCOUNTITEM = "新开户";

	// reason系列
	String EXCHANGECARD = "10";
	String EXCHANGECARDREASON = "兑换奖品";

	String EXPIREDCARD = "11";
	String EXPIREDCARDREASON = "已失效";
	// changeType
	// 0. 未使用 1.为兑换完成，2.兑换完成 3.已过期
	int CHANGETYPE_UNUSED = 0;
	int CHANGETYPE_REMIND = 1;
	int CHANGETYPE_COMPLETE = 2;
	int CHANGETYPE_EXPRIE = 3;
	

}
