package com.laptrinhjavaweb.controller.web;

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
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = {"/tin-chi-tiet"} )
public class NewController extends HttpServlet{
	
	@Inject
	private INewService newService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		NewModel model = FormUtil.toModel(NewModel.class, request);
		if(model.getId() != null) {
			model = newService.findOne(model.getId());
		}
		request.setAttribute(SystemConstrant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/detail.jsp");
		rd.forward(request, response);
	}
}
