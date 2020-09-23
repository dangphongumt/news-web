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
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {

	private static final long serialVersionUID = 2686801510274002166L;
	
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private INewService newService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		NewModel model = FormUtil.toModel(NewModel.class, request);
		String view = "";
		if(model.getType().equals(SystemConstrant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));	
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem()); 
			model.setTotalPage((int)Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));//total pages
			
			view = "/views/admin/new/list.jsp";
			
		}else if(model.getType().equals(SystemConstrant.EDIT)) {
			if(model.getId() != null) {
				model = newService.findOne(model.getId());
			}
			
			request.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/new/edit.jsp";
			
		}
		MessageUtil.showMessage(request);
		request.setAttribute(SystemConstrant.MODEL, model);//gói của HttpSẻvletRequest, view - model -controller, return date from controller to view ->setAttribute
		RequestDispatcher rd = request.getRequestDispatcher(view);//return edit page
		rd.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
