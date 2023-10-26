package com.bw.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bw.dao.TodoDaoImpl;
import com.bw.model.Todo;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the todo.
 * 
 * @email
 */

@Controller
public class TodoController {

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newTodo(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		return "todo-form";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			return insertTodo(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String listTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Todo> listTodo = TodoDaoImpl.getInstance().selectAllTodos();
		request.setAttribute("listTodo", listTodo);
		return "todo-list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			return deleteTodo(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			return showEditForm(request, response);

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			return updateTodo(request, response);

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private String showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Todo existingTodo = TodoDaoImpl.getInstance().selectTodo(id);
		request.setAttribute("todo", existingTodo);
		return "todo-form";
	}

	private String insertTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String cardNumber = request.getParameter("cardNumber");
		String cardExpiry = request.getParameter("cardExpiry");
		String cvv = request.getParameter("cvv");
		String cardHolderName = request.getParameter("cardHolderName");
		Todo todo = new Todo(cardNumber, cardExpiry, cvv, cardHolderName);
		TodoDaoImpl.getInstance().insertTodo(todo);
		List<Todo> listTodo = TodoDaoImpl.getInstance().selectAllTodos();
		request.setAttribute("listTodo", listTodo);
		return "todo-list";
	}

	private String updateTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String cardNumber = request.getParameter("cardNumber");
		String cardExpiry = request.getParameter("cardExpiry");
		String cvv = request.getParameter("cvv");
		String cardHolderName = request.getParameter("cardHolderName");
		Todo updateTodo = new Todo(id, cardNumber, cardExpiry, cvv, cardHolderName);

		TodoDaoImpl.getInstance().updateTodo(updateTodo);

		List<Todo> listTodo = TodoDaoImpl.getInstance().selectAllTodos();
		request.setAttribute("listTodo", listTodo);
		return "todo-list";
	}

	private String deleteTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		TodoDaoImpl.getInstance().deleteTodo(id);

		List<Todo> listTodo = TodoDaoImpl.getInstance().selectAllTodos();
		request.setAttribute("listTodo", listTodo);
		return "todo-list";
	}
}
