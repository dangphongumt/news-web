package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface IUserDAO extends IGenericDAO<UserModel>{
		UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
		List<UserModel> findAll(Pageble pageble);
		int getTotalItem();
}
