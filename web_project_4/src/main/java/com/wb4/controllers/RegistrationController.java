package com.wb4.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.entity.User;
import com.wb4.model.dao.jdbc.JdbcUserDao;
import com.wb4.services.ConstantValues;

@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegistrationController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		String path = ConstantValues.LOGIN_PAGE;
		
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		
		JdbcUserDao userDao = null;
		User user;
		
		if (repeatPassword.equals(password) && repeatPassword != null) {
			user = new User.Builder(login, password).setFirstName(firstName)
					.setMiddleName(middleName).setLastName(lastName).setEmail(email)
					.setTelephone(telephone).build();
			userDao = new JdbcUserDao(DataBaseConnection.getInstance().getConnection());
			userDao.create(user);
		} else {
			path = ConstantValues.ERROR_PAGE;
		}
		
		try {
			userDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
	}
}
