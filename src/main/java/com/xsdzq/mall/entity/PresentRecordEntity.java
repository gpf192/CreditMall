package com.xsdzq.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mall_present_record")
@EntityListeners(AuditingEntityListener.class)
public class PresentRecordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "present_record_sequence")
	@SequenceGenerator(name = "present_record_sequence", sequenceName = "present_record_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "num", nullable = false)
	private Integer number = 0; //

	// 兑换时使用的积分
	@Column(name = "integral_number")
	private int integralNumber;

	@Column(name = "price")
	private double price;

	@Column(name = "data_flag", nullable = false)
	private String dateFlag; // 每日的判断标准

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private MallUserEntity mallUserEntity;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "card_id", referencedColumnName = "id")
	private PresentCardEntity presentCardEntity;

	@Column(name = "record_time", nullable = false)
	private Date recordTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public int getIntegralNumber() {
		return integralNumber;
	}

	public void setIntegralNumber(int integralNumber) {
		this.integralNumber = integralNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	@JsonIgnore
	public MallUserEntity getMallUserEntity() {
		return mallUserEntity;
	}

	public void setMallUserEntity(MallUserEntity mallUserEntity) {
		this.mallUserEntity = mallUserEntity;
	}

	public PresentCardEntity getPresentCardEntity() {
		return presentCardEntity;
	}

	public void setPresentCardEntity(PresentCardEntity presentCardEntity) {
		this.presentCardEntity = presentCardEntity;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

}
