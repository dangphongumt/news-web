package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewDAO extends IGenericDAO<NewModel>{
		NewModel findOne(Long id);
		List<NewModel> findByCategoryId(Long categoryId);
		Long save(NewModel newModel);
		void update(NewModel newModel);
		void delete(long id);
		List<NewModel> findAll(Pageble pageble);
		int getTotalItem();
}
