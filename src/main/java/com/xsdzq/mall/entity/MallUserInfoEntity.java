package com.xsdzq.mall.entity;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mall_user_info")
@EntityListeners(AuditingEntityListener.class)
public class MallUserInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mall_user_info_sequence")
	@SequenceGenerator(name = "mall_user_info_sequence", sequenceName = "mall_user_info_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	// 总积分
	@Column(name = "credit_score")
	private int creditScore = 0;

	// 会员等级使用 历史总积分
	@Column(name = "sum_score")
	private int sumScore = 0;

	// 会员等级使用
	@Column(name = "user_level", columnDefinition = "tinyint default 0")
	private int level = 0;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private MallUserEntity mallUserEntity;

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
	private Date modifytime;

	@Column(name = "client_id", insertable = false, updatable = false)
	private String clientId;

	// 冻结积分
	@Column(name = "frozen_integral",columnDefinition = "int default 0")
	private Integer frozenIntegral = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCreditScore() {
		return creditScore - frozenIntegral;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@JsonIgnore
	public MallUserEntity getMallUserEntity() {
		return mallUserEntity;
	}

	public void setMallUserEntity(MallUserEntity mallUserEntity) {
		this.mallUserEntity = mallUserEntity;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Integer getFrozenIntegral() {
		return frozenIntegral;
	}

	public void setFrozenIntegral(Integer frozenIntegral) {
		this.frozenIntegral = frozenIntegral;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
