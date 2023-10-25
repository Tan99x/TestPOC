package com.bw.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bw.dao.LoginDao;
import com.bw.model.LoginBean;

/**
 * @email Ramesh Fadatare
 */

@Controller
public class LoginController {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authenticate(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return authenticate(request, response);
	}

	private String authenticate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		try {
			if (LoginDao.validate(loginBean)) {
				return "todo-list";
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("loginError",
						"Either the User ID or Password that you're using to sign in, is invalid");
				return "login";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "login";
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}
}
