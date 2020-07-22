package com.xsdzq.mall.entity;

import java.io.Serializable;
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
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "mall_present")
public class Present implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	private long id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "face_value", precision = 2)
	private float faceValue;

	@Column(name = "value", precision = 2)
	private float value;

	@Column(name = "description")
	private String description;

	@Column(name = "store_number")
	private int storeNumber;

	@Column(name = "convert_number")
	private String convertNumber;

	@Column(name = "store_unserd")
	private String storeUnused;

	@Column(name = "status")
	private String status;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private PresentCategory presentCategory;

	// 创建时间
	@Column(name = "createtime")
	@CreatedDate
	private Date createtime;

	// 修改时间
	@Column(name = "modifytime", nullable = true)
	@LastModifiedDate
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

	public float getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(float faceValue) {
		this.faceValue = faceValue;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getConvertNumber() {
		return convertNumber;
	}

	public void setConvertNumber(String convertNumber) {
		this.convertNumber = convertNumber;
	}

	public String getStoreUnused() {
		return storeUnused;
	}

	public void setStoreUnused(String storeUnused) {
		this.storeUnused = storeUnused;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PresentCategory getPresentCategory() {
		return presentCategory;
	}

	@JsonBackReference
	public void setPresentCategory(PresentCategory presentCategory) {
		this.presentCategory = presentCategory;
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

	@Override
	public String toString() {
		return "Present [id=" + id + ", name=" + name + ", faceValue=" + faceValue + ", value=" + value
				+ ", description=" + description + ", storeNumber=" + storeNumber + ", convertNumber=" + convertNumber
				+ ", storeUnused=" + storeUnused + ", status=" + status + ", presentCategory=" + presentCategory
				+ ", createtime=" + createtime + ", modifytime=" + modifytime + "]";
	}

}
