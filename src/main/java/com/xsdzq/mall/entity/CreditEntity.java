package com.xsdzq.mall.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_credit")
@EntityListeners(AuditingEntityListener.class)
public class CreditEntity implements Serializable {
	
	//积分表 分散在record表中。
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mall_credit_sequence")
	@SequenceGenerator(name = "mall_credit_sequence", sequenceName = "sequence_mall_credit", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@Column(name = "category_name")
	private String categoryName;//项目名称
	
	@Column(name = "category_code", unique = true)
	private String categoryCode;//项目编码，唯一
	
	@Column(name = "front_name")
	private String frontName;//前端显示名称
	
	@Column(name = "integral_value")
	private String integralValue;//积分值
	
	@Column(name = "flag")
	private String flag;//是否启用,0未启用，1启用

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createtime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getFrontName() {
		return frontName;
	}

	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}

	public String getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(String integralValue) {
		this.integralValue = integralValue;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "CreditEntity [id=" + id + ", categoryName=" + categoryName + ", categoryCode=" + categoryCode
				+ ", frontName=" + frontName + ", integralValue=" + integralValue + ", flag=" + flag + ", createtime="
				+ createtime + "]";
	}


}
