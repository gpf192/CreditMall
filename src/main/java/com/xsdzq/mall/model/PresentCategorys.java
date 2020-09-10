package com.xsdzq.mall.model;

import java.util.List;

import com.xsdzq.mall.entity.PresentEntity;

public class PresentCategorys {
	private Long id;
	private String name;
	private Boolean flag;
	private int sort;

	private List<PresentEntity> presentEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<PresentEntity> getPresentEntities() {
		return presentEntities;
	}

	public void setPresentEntities(List<PresentEntity> presentEntities) {
		this.presentEntities = presentEntities;
	}

}
