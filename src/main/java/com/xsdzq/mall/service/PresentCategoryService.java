package com.xsdzq.mall.service;

import java.util.List;

import com.xsdzq.mall.entity.PresentCategoryEntity;
import com.xsdzq.mall.model.PresentCategory;

public interface PresentCategoryService {

	public void addPresentCategory(PresentCategory presentCategory);

	public List<PresentCategoryEntity> getCategoryEntities();

}
