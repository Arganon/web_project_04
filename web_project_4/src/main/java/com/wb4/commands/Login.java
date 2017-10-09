package com.wb4.commands;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.entity.User;
import com.wb4.services.ConstantValues;
import com.wb4.services.UserService;

public class Login implements Commands {
	protected static Login instance = null;
	
	protected Login() {
		super();
	}
	
	public static Login getInstance() {
		if (instance == null) {
			instance = new Login();
		}
		return instance;
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(ConstantValues.PARAM_LOGIN);
		String password = request.getParameter(ConstantValues.PARAM_PASSWORD);
		String pathToGo = ConstantValues.LOGIN_PAGE;

		if (login != null && password != null) {
			Optional<User> user = UserService.getInstance().userLogin(login, password);
			
			if(user.isPresent()) {
				request.getSession().setAttribute("USER", user.get());
				pathToGo = UsersPageRedirect.getInstance().getUserPage(user.get().getUserRole().toString().toUpperCase());
			}
		}
		return pathToGo;
	}
}



