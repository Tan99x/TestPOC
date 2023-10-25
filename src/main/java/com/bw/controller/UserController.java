package com.bw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bw.dao.UserDao;
import com.bw.model.User;

/**
 * @email Ramesh Fadatare
 */

@Controller
public class UserController {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return register(request, response);
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "register";
	}

	private String register(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User employee = new User();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUsername(username);
		employee.setPassword(password);

		try {
			int result = UserDao.registerEmployee(employee);
			if (result == 1) {
				request.setAttribute("NOTIFICATION", "User Registered Successfully!");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "register";
	}
}
