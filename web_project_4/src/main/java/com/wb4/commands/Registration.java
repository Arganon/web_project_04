package com.wb4.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb4.entity.User;
import com.wb4.services.ConstantValues;
import com.wb4.services.UserService;
import com.wb4.utilites.ValidateInputData;

public class Registration implements Commands {
	protected static Registration instance = null;
	protected Registration() {}

	public static Registration getInstance() {
		if (instance == null) {
			instance = new Registration();
		}
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = makeUser(request, response);
		
		if (!isCorrectUser(user, request, response)) {
			return ConstantValues.REGISTRATION_PAGE;
		}
		UserService.getInstance().createUser(user);
		return ConstantValues.LOGIN_PAGE;
	}
	
	
	private User makeUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = new User.Builder(request.getParameter("login"), request.getParameter("password"))
				.setFirstName(request.getParameter("firstName"))
				.setMiddleName(request.getParameter("middleName"))
				.setLastName(request.getParameter("lastName"))
				.setEmail(request.getParameter("email"))
				.setTelephone(request.getParameter("telephone"))
				.build();

		return user;
	}
	
	private boolean isCorrectUser(User user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isCorrect = true;
		
		if (!ValidateInputData.checkData(user.getLogin(), ValidateInputData.TEXT)) {
			request.setAttribute("loginEr", "Incorrect login.");
			isCorrect = false;
		}
		
		if (isCorrect) {
			if (UserService.getInstance().findUser(user.getLogin()).isPresent()) {
				request.setAttribute("loginEr", "User with this login is already present.");
				isCorrect = false;
			}
		}
		
		if (!ValidateInputData.checkData(user.getFirstName(), ValidateInputData.TEXT)) {
			request.setAttribute("firstNameEr", "Incorrect type of text.");
			isCorrect = false;
		}
		
		if (!ValidateInputData.checkData(user.getMiddleName(), ValidateInputData.TEXT)) {
			request.setAttribute("middleNameEr", "Incorrect type of text.");
			isCorrect = false;
		}
		
		if (!ValidateInputData.checkData(user.getLastName(), ValidateInputData.TEXT)) {
			request.setAttribute("lastNameEr", "Incorrect type of text.");
			isCorrect = false;
		}
		
		if (!ValidateInputData.checkData(user.getEmail(), ValidateInputData.EMAIL)) {
			request.setAttribute("emailEr", "Incorrect email.");
			isCorrect = false;
		}
		if (!user.getPassword().equals(request.getParameter("repeatPassword"))) {
			request.setAttribute("repeatPasswordEr", "Incorrect password repeat.");
			isCorrect = false;
		}
		
		if (!ValidateInputData.checkData(user.getPassword(), ValidateInputData.PASSWORD)) {
			request.setAttribute("passwordEr", "Incorrect type of text.");
			isCorrect = false;
		}
		
		if (!ValidateInputData.checkData(user.getTelephone(), ValidateInputData.PHONE)) {
			request.setAttribute("telephoneEr", "Incorrect type of phone number.");
			isCorrect = false;
		}
		
		return isCorrect;
	}
}
