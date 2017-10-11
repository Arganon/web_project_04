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
	private Login() {}
	
	private static class Holder {
		private static Login INSTANCE = new Login();
	}
	
	public static Login getInstance() {
		return Login.Holder.INSTANCE;
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(ConstantValues.PARAM_LOGIN);
		String password = request.getParameter(ConstantValues.PARAM_PASSWORD);
		String pathToGo = ConstantValues.LOGIN_PAGE;
		Optional<User> user = Optional.empty();
		
		if (login != null && password != null) {
			user = UserService.getInstance().userLogin(login, password);
		}
		
		if(user.isPresent()) {
			request.getSession().setAttribute("USER", user.get());
			pathToGo = UsersPageRedirect.getInstance().getUserPage(user.get().getUserRole().toString().toUpperCase());
		}
		
		return pathToGo;
	}
}



