package com.xsdzq.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsdzq.mall.dao.PresentCategoryRepository;
import com.xsdzq.mall.dao.PresentRepository;
import com.xsdzq.mall.dao.PresentResultRepository;
import com.xsdzq.mall.entity.PresentCategoryEntity;
import com.xsdzq.mall.entity.PresentEntity;
import com.xsdzq.mall.entity.PresentResultEntity;
import com.xsdzq.mall.model.PresentCategorys;
import com.xsdzq.mall.service.PresentService;

@Service
@Transactional(readOnly = true)
public class PresentServiceImpl implements PresentService {

	@Autowired
	private PresentRepository presentRepository;

	@Autowired
	private PresentCategoryRepository presentCategoryRepository;
	
	@Autowired
	private PresentResultRepository presentResultRepository;
	
	@Override
	public List<PresentResultEntity> getLatestPresentResultEntities() {
		// TODO Auto-generated method stub
		PageRequest pageable = PageRequest.of(0, 3);
		Page<PresentResultEntity> resultPage = presentResultRepository.findByOrderByRecordTimeDesc(pageable);
		return resultPage.getContent();
	}

	@Override
	@Transactional
	public void addPresent(PresentEntity present) {
		// TODO Auto-generated method stub
		presentRepository.save(present);

	}

	@Override
	public List<PresentEntity> getHotPresentList() {
		// TODO Auto-generated method stub
		return presentRepository.findByIsHotOrderBySortDesc(true);

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
			// presentCategorys.setFlag(presentCategoryEntity.isFlag());
			presentCategorys.setSort(presentCategoryEntity.getSort());
			List<PresentEntity> presentEntities = presentRepository
					.findByPresentCategoryEntityOrderBySortDesc(presentCategoryEntity);
			presentCategorys.setPresentEntities(presentEntities);
			presentCategorysList.add(presentCategorys);

		}
		return presentCategorysList;
	}

	

}
