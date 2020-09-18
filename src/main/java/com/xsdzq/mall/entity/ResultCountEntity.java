package com.xsdzq.mall.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ResultCountEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	private String groupTime;

	private double sumValue;

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
