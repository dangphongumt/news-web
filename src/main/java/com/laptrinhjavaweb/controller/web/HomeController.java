 package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constrant.SystemConstrant;
import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.dao.impl.NewDAO;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.CategoryService;
import com.laptrinhjavaweb.service.impl.NewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns =  { "/trang-chu","/dang-nhap","/thoat"} )
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 2686801510274002166L;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");//message.properties
	
	@Inject
	private IUserService userService;
	
	@Inject
	private ICategoryService categoryService;

	@Inject
	private INewService newService;
//	@Inject
//	private INewDAO newDAO;

//	public HomeController() {
//		newDAO = new NewDAO();
//	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewModel model = FormUtil.toModel(NewModel.class, request);
		String action = request.getParameter("action");
		if(action != null && action.equals("login")) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if(message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));//for send data from controller to JSP view, we use request.setAttribute
				request.setAttribute("alert", alert);//?????https://www.youtube.com/watch?v=eA3P71eS97E&list=PLW1k06REn7HsHn6D6e27gtFm-HXrZDLPX&index=39
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if(action != null && action.equals("logout")) {
				SessionUtil.getInstance().removeValue(request,"USERMODEL");
				response.sendRedirect(request.getContextPath()+"/trang-chu");//we are standing on /thoat controller, and we can switch to /trang-chu controller
		} else {
			request.setAttribute("categories", categoryService.findAll());
			Pageble pageble = new PageRequest(1, 6, new Sorter("id", "desc"));	
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem()); 
			model.setTotalPage(1);//total pages
			request.setAttribute(SystemConstrant.MODEL,model);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action = request.getParameter("action");
			if(action != null && action.equals("login")) {
				UserModel model = FormUtil.toModel(UserModel.class, request);
				model = userService.findByUserNameAndPasswordAndStatus(model.getUsername(), model.getPassword(), 1);
				if(model!=null) {
					SessionUtil.getInstance().putValue(request,"USERMODEL", model);
					if(model.getRole().getCode().equals("USER")) {
						response.sendRedirect(request.getContextPath()+"/trang-chu");
					}else if(model.getRole().getCode().equals("ADMIN")) {
						response.sendRedirect(request.getContextPath()+"/admin-home");
					}
				}else {
					response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");//redirect login form when can not find UserModel
				}
			}
	}
}
