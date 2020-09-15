package com.xsdzq.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mall_present_result")
public class PresentResultEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_present_result_sequence")
	@SequenceGenerator(name = "credit_present_result_sequence", sequenceName = "credit_present_result_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "record_time", nullable = false)
	private Date recordTime;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private MallUserEntity mallUserEntity;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "card_id", referencedColumnName = "id")
	private PresentCardEntity presentCardEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PresentCardEntity getPresentCardEntity() {
		return presentCardEntity;
	}

	public void setPresentCardEntity(PresentCardEntity presentCardEntity) {
		this.presentCardEntity = presentCardEntity;
	}

}
