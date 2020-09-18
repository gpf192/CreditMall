package com.xsdzq.mall.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mall_result_count")
public class ResultCount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "group_time", unique = true)
	private String groupTime;

	@Column(name = "sum_value")
	private double sumValue;

	@Column(name = "sum_credit")
	private int sumCredit;

	public String getGroupTime() {
		return groupTime;
	}

	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
	}

	public double getSumValue() {
		return sumValue;
	}

	public void setSumValue(double sumValue) {
		this.sumValue = sumValue;
	}

	public int getSumCredit() {
		return sumCredit;
	}

	public void setSumCredit(int sumCredit) {
		this.sumCredit = sumCredit;
	}

	@Override
	public String toString() {
		return "ResultCount [groupTime=" + groupTime + ", sumValue=" + sumValue + ", sumCredit=" + sumCredit + "]";
	}

}
