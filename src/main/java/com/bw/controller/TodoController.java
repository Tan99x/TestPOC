package com.bw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
import com.bw.model.AutheriseResponse;
import com.bw.model.CardDetails;
import com.bw.model.CardInfoRequest;
import com.bw.model.RefundRequestDTO;
import com.bw.model.Todo;
import com.bw.utils.CommonUtils;
import com.google.gson.Gson;

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

	@RequestMapping(value = "/refundPage", method = RequestMethod.GET)
	public String refundPage(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		return "refund-form";
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

	@RequestMapping(value = "/autherise", method = RequestMethod.GET)
	public String autherise(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		Gson gson = new Gson();
		int id = Integer.parseInt(request.getParameter("id"));
		Todo existingTodo = TodoDaoImpl.getInstance().selectTodo(id);
		CardInfoRequest cinfo = new CardInfoRequest();
		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardholderName(existingTodo.getCardHolderName());
		cardDetails.setCardNumber(existingTodo.getCardNumber());
		cardDetails.setSecurityCode(existingTodo.getCvv());
		cardDetails.setExpiryDate(existingTodo.getCardExpiry());
		cinfo.setCardDetails(cardDetails);
		cinfo.setAmount(existingTodo.getAmount());
		String reqBody = CommonUtils.dumpObject(cinfo);
		String resp = callApi(reqBody, "http://localhost:8080/process-card");
		AutheriseResponse autheriseResponse = gson.fromJson(resp, AutheriseResponse.class);
		if (autheriseResponse.getErrorCode().equals("200")) {
			request.setAttribute("confirmMsg", "Autherisation completed succesfully with transactionId"
					+ autheriseResponse.getData().getTransactionId());
		}
		return "confirmationPage";
	}

	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public String refund(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Gson gson = new Gson();
		RefundRequestDTO refundRequestDTO = new RefundRequestDTO();
		refundRequestDTO.setAmount(request.getParameter("amount"));
		refundRequestDTO.setTransactionId(request.getParameter("transactionId"));
		refundRequestDTO.setDesc(request.getParameter("desc"));
		String reqBody = CommonUtils.dumpObject(refundRequestDTO);
		String resp = callApi(reqBody, "http://localhost:8080/process-refund");
		AutheriseResponse autheriseResponse = gson.fromJson(resp, AutheriseResponse.class);
		if (autheriseResponse.getErrorCode().equals("200")) {
			request.setAttribute("confirmMsg",
					"Refund completed succesfully for transactionId " + refundRequestDTO.getTransactionId());
		}
		return "confirmationPage";
	}

	public String callApi(String reqBody, String url) {
		String resp = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");

			// For POST only - START
			httpURLConnection.setDoOutput(true);
			OutputStream os = httpURLConnection.getOutputStream();
			os.write(reqBody.getBytes());
			os.flush();
			os.close();

			int responseCode = httpURLConnection.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result
				resp = response.toString();
				System.out.println(response.toString());
			} else {
				System.out.println("POST request not worked");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
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
		String amount = request.getParameter("amount");
		Todo todo = new Todo(cardNumber, cardExpiry, cvv, cardHolderName, amount);
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
		String amount = request.getParameter("amount");

		Todo updateTodo = new Todo(id, cardNumber, cardExpiry, cvv, cardHolderName, amount);

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
