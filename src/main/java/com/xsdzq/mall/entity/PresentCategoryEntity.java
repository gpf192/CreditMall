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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_present_category")
@EntityListeners(AuditingEntityListener.class)
public class PresentCategoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_present_Category")
	@SequenceGenerator(name = "sequence_present_Category", sequenceName = "sequence_present_Category", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "code", unique = true)
	private String code;
	
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "flag")
	private boolean flag;

	@Column(name = "sort")
	private int sort;

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifytime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "PresentCategoryEntity [id=" + id + ", code=" + code + ", name=" + name + ", flag=" + flag + ", sort="
				+ sort + ", createtime=" + createtime + ", modifytime=" + modifytime + "]";
	}



}
