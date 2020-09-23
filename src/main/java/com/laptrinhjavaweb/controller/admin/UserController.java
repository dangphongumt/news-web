package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constrant.SystemConstrant;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-user"})
public class UserController extends HttpServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private IUserService userService;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		UserModel userModel = FormUtil.toModel(UserModel.class, request);
		Pageble pageble = new PageRequest(userModel.getPage(), userModel.getMaxPageItem(), new Sorter(userModel.getSortName(), userModel.getSortBy()));
		userModel.setListResult(userService.findAll(pageble));
		userModel.setTotalItem(userService.getTotalItem());
		userModel.setTotalPage((int)Math.ceil((double) userModel.getTotalItem() / userModel.getMaxPageItem()));
		request.setAttribute(SystemConstrant.MODEL, userModel);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/list.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
	}
}
