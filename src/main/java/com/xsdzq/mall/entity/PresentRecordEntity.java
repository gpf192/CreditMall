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

@Entity
@Table(name = "mall_present_record")
@EntityListeners(AuditingEntityListener.class)
public class PresentRecordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "present_record_sequence")
	@SequenceGenerator(name = "present_record_sequence", sequenceName = "present_record_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "type", nullable = false)
	private boolean type; // type 为0 表示减少 reason 以0开头 0X， 为1表示增加 reason 以1开头 1X

	@Column(name = "reason", nullable = false)
	private String reason;

	@Column(name = "num", nullable = false)
	private Integer number = 0; // 增加或者减少的数量,默认为0

	@Column(name = "price")
	private float price;

	@Column(name = "data_flag", nullable = false)
	private String dateFlag; // 每日的判断标准

	@Column(name = "record_time", nullable = false)
	private Date recordTime;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private MallUserEntity mallUserEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public MallUserEntity getMallUserEntity() {
		return mallUserEntity;
	}

	public void setMallUserEntity(MallUserEntity mallUserEntity) {
		this.mallUserEntity = mallUserEntity;
	}

	@Override
	public String toString() {
		return "PresentRecord [id=" + id + ", type=" + type + ", reason=" + reason + ", number=" + number
				+ ", dateFlag=" + dateFlag + ", recordTime=" + recordTime + ", mallUserEntity=" + mallUserEntity + "]";
	}

}
