package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.dao.impl.CategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewService;

public class NewService implements INewService {
	
	@Inject
	private INewDAO newDAO;
	
	@Inject
	private ICategoryDAO categoryDAO;

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return newDAO.findByCategoryId(categoryId);
	}

	@Override
	public NewModel save(NewModel newModel) {
		CategoryModel category = categoryDAO.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(category.getId());
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long newId = newDAO.save(newModel);
		return newDAO.findOne(newId);
		//System.out.println(newId);
		//return null;
	}

	@Override//modify by Phong
	public NewModel update(NewModel updateNew) {
		NewModel oldNew = newDAO.findOne(updateNew.getId());
		oldNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		oldNew.setModifiedBy(updateNew.getModifiedBy());
		oldNew.setTitle(updateNew.getTitle());
		oldNew.setThumbnail(updateNew.getThumbnail());
		oldNew.setShortDescription(updateNew.getShortDescription());
		oldNew.setContent(updateNew.getContent());
		CategoryModel category = categoryDAO.findOneByCode(updateNew.getCategoryCode());
		oldNew.setCategoryId(category.getId());
		newDAO.update(oldNew);
		return newDAO.findOne(oldNew.getId());
	}

	@Override
	public void delete(long[] ids) {
		for(long id: ids) {
			//1.delete comment (khoa ngoai new_id)
			//2.delete news
			newDAO.delete(id);
		}

	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

	@Override
	public NewModel findOne(long id) {
		NewModel newModel = newDAO.findOne(id);
		CategoryModel categoryModel = categoryDAO.findOne(newModel.getCategoryId());
		newModel.setCategoryCode(categoryModel.getCode());
		return newModel;
	}
	

}
