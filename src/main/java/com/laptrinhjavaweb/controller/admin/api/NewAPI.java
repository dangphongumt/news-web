package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet{
	
	@Inject 
	private INewService newService;
	
	private static final long serialVersionUID = 4335547647062801919L;

	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		request.getReader();//HttpServletRequest 
		NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);//convert from JSON to NewModel
		newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUsername());
		newModel = newService.save(newModel);
		mapper.writeValue(response.getOutputStream(), newModel);//return value of JSON for client
		
	}
protected void doPut(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		request.getReader();//HttpServletRequest 
	NewModel updateNew = HttpUtil.of(request.getReader()).toModel(NewModel.class);//convert from JSON to NewModel
		updateNew.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(request,"USERMODEL")).getUsername());
		updateNew = newService.update(updateNew);
		mapper.writeValue(response.getOutputStream(), updateNew);//return value of JSON for client
	//System.out.println(newModel.getId());
		
	}


protected void doDelete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	
	ObjectMapper mapper = new ObjectMapper();
	request.setCharacterEncoding("UTF-8");
	response.setContentType("application/json");
	request.getReader();//HttpServletRequest 
NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);//convert from JSON to NewModel
newService.delete(newModel.getIds());
	mapper.writeValue(response.getOutputStream(), "delete succesful");//return value of JSON for client
//System.out.println(newModel.getId());
	
}
}
