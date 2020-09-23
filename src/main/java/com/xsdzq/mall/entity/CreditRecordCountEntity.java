package com.xsdzq.mall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mall_redit_record_count")
public class CreditRecordCountEntity {

	@Id
	@Column(name = "group_time")
	private String groupTime;

	@Column(name = "sum_score")
	private int sumScore;

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}

	public String getGroupTime() {
		return groupTime;
	}

	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
	}

	@Override
	public String toString() {
		return "CreditRecordCountEntity [groupTime=" + groupTime + ", sumScore=" + sumScore + "]";
	}

}
