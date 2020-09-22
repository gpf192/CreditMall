package com.xsdzq.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.dao.PresentCategoryRepository;
import com.xsdzq.mall.dao.PresentRepository;
import com.xsdzq.mall.entity.PresentCategoryEntity;
import com.xsdzq.mall.entity.PresentEntity;
import com.xsdzq.mall.model.PresentCategorys;
import com.xsdzq.mall.service.PresentService;

@Service
@Transactional(readOnly = true)
public class PresentServiceImpl implements PresentService {

	@Autowired
	private PresentRepository presentRepository;

	@Autowired
	private PresentCategoryRepository presentCategoryRepository;

	@Override
	@Transactional
	public void addPresent(PresentEntity present) {
		// TODO Auto-generated method stub

		presentRepository.save(present);

	}

	@Override
	public List<PresentEntity> getPresentEntities() {
		// TODO Auto-generated method stub
		return presentRepository.findAll();
	}

	@Override
	public List<PresentCategorys> getPresentCategoryEntities() {
		// TODO Auto-generated method stub
		List<PresentCategoryEntity> presentCategoryEntities = presentCategoryRepository.findAll();
		List<PresentCategorys> presentCategorysList = new ArrayList<PresentCategorys>();
		for (PresentCategoryEntity presentCategoryEntity : presentCategoryEntities) {
			PresentCategorys presentCategorys = new PresentCategorys();
			presentCategorys.setId(presentCategoryEntity.getId());
			presentCategorys.setName(presentCategoryEntity.getName());
			//presentCategorys.setFlag(presentCategoryEntity.isFlag());
			presentCategorys.setSort(presentCategoryEntity.getSort());
			List<PresentEntity> presentEntities = presentRepository.findByPresentCategoryEntity(presentCategoryEntity);
			presentCategorys.setPresentEntities(presentEntities);
			presentCategorysList.add(presentCategorys);

		}
		return presentCategorysList;
	}

}
