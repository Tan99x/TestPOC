package com.bw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bw.model.Todo;
import com.bw.utils.DBUtils;

/**
 * This DAO class provides CRUD database operations for the table todos in the
 * database.
 * 
 *
 */

public class TodoDaoImpl {

	private static TodoDaoImpl todoDaoImpl;

	private static final String INSERT_TODOS_SQL = "INSERT INTO card_details "
			+ "  (cardNumber, cardExpiry, cvv, cardHolderName) VALUES " + " (?, ?, ?, ?);";

	private static final String SELECT_TODO_BY_ID = "select id, cardNumber, cardExpiry, cvv, cardHolderName from card_details  where id =?";
	private static final String SELECT_ALL_TODOS = "select * from card_details ";
	private static final String DELETE_TODO_BY_ID = "delete from card_details  where id = ?;";
	private static final String UPDATE_TODO = "update card_details  set cardNumber = ?, cardExpiry= ?, cvv =?, cardHolderName =?, where id = ?;";

	private TodoDaoImpl() {
	}

	public static TodoDaoImpl getInstance() {
		if (todoDaoImpl == null) {
			return new TodoDaoImpl();
		}
		return todoDaoImpl;
	}

	public void insertTodo(Todo todo) throws SQLException {
		Connection connection = DBUtils.setupDB();
		System.out.println(INSERT_TODOS_SQL);
		// try-with-resource statement will auto close the connection.
		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
			preparedStatement.setString(1, todo.getCardNumber());
			preparedStatement.setString(2, todo.getCardExpiry());
			preparedStatement.setString(3, todo.getCvv());
			preparedStatement.setString(4, todo.getCardHolderName());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			DBUtils.printSQLException(exception);
		}
	}

	public Todo selectTodo(long todoId) {
		Connection connection = DBUtils.setupDB();
		Todo todo = null;
		// Step 1: Establishing a Connection
		try (
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);) {
			preparedStatement.setLong(1, todoId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String cardNumber = rs.getString("cardNumber");
				String cardExpiry = rs.getString("cardExpiry");
				String cvv = rs.getString("cvv");
				String cardHolderName = rs.getString("cardHolderName");
				todo = new Todo(id, cardNumber, cardExpiry, cvv, cardHolderName);
			}
		} catch (SQLException exception) {
			DBUtils.printSQLException(exception);
		}
		return todo;
	}

	public List<Todo> selectAllTodos() {
		Connection connection = DBUtils.setupDB();
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Todo> todos = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String cardNumber = rs.getString("cardNumber");
				String cardExpiry = rs.getString("cardExpiry");
				String cvv = rs.getString("cvv");
				String cardHolderName = rs.getString("cardHolderName");
				Todo todo = new Todo(id, cardNumber, cardExpiry, cvv, cardHolderName);
				todos.add(todo);
			}
		} catch (SQLException exception) {
			DBUtils.printSQLException(exception);
		}
		return todos;
	}

	public boolean deleteTodo(int id) throws SQLException {
		Connection connection = DBUtils.setupDB();

		boolean rowDeleted;
		try (PreparedStatement statement = connection.prepareStatement(DELETE_TODO_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateTodo(Todo todo) throws SQLException {
		Connection connection = DBUtils.setupDB();
		boolean rowUpdated;
		try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO);) {
			preparedStatement.setString(1, todo.getCardNumber());
			preparedStatement.setString(2, todo.getCardExpiry());
			preparedStatement.setString(3, todo.getCvv());
			preparedStatement.setString(4, todo.getCardHolderName());
			preparedStatement.setLong(5, todo.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
