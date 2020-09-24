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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mall_present")
@EntityListeners(AuditingEntityListener.class)
public class PresentEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_present")
	@SequenceGenerator(name = "sequence_present", sequenceName = "sequence_present", allocationSize = 1)
	@Column(name = "id")
	private long id;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "face_value", precision = 2)
	private float faceValue;

	@Column(name = "value", precision = 2)
	private float value;

	@Lob
	@Column(name = "image")
	private String image;

	@Column(name = "big_image")
	private String bigImage;

	@Column(name = "is_hot")
	private boolean isHot;

	@Column(name = "tip")
	private String tip;

	@Lob
	@Column(name = "description")
	private String description;

	@Lob
	@Column(name = "explain2", nullable = true)
	private String explain;

	@Column(name = "store_number")
	private int storeNumber;

	@Column(name = "convert_number")
	private int convertNumber;

	@Column(name = "store_unused")
	private int storeUnused;

	@Column(name = "sort")
	private int sort;

	@Column(name = "status")
	private String status;

	@Column(name = "categoryId", insertable = false, updatable = false)
	private long categoryId;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private PresentCategoryEntity presentCategoryEntity;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBigImage() {
		return bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public int getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}

	public int getConvertNumber() {
		return convertNumber;
	}

	public void setConvertNumber(int convertNumber) {
		this.convertNumber = convertNumber;
	}

	public int getStoreUnused() {
		return storeUnused;
	}

	public void setStoreUnused(int storeUnused) {
		this.storeUnused = storeUnused;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public PresentCategoryEntity getPresentCategoryEntity() {
		return presentCategoryEntity;
	}

	public void setPresentCategoryEntity(PresentCategoryEntity presentCategoryEntity) {
		this.presentCategoryEntity = presentCategoryEntity;
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

}
