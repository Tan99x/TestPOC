package com.bw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bw.model.Todo;
import com.bw.utils.DBUtils;

/**
 * This DAO class provides CRUD database operations for the table todos in the
 * database.
 * 
 * @author Ramesh Fadatare
 *
 */

public class TodoDaoImpl {

	private static TodoDaoImpl todoDaoImpl;

	private static final String INSERT_TODOS_SQL = "INSERT INTO todos"
			+ "  (title, username, description, target_date,  is_done) VALUES " + " (?, ?, ?, ?, ?);";

	private static final String SELECT_TODO_BY_ID = "select id,title,username,description,target_date,is_done from todos where id =?";
	private static final String SELECT_ALL_TODOS = "select * from todos";
	private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
	private static final String UPDATE_TODO = "update todos set title = ?, username= ?, description =?, target_date =?, is_done = ? where id = ?;";

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
			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getUsername());
			preparedStatement.setString(3, todo.getDescription());
			preparedStatement.setDate(4, DBUtils.getSQLDate(todo.getTargetDate()));
			preparedStatement.setBoolean(5, todo.getStatus());
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
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				todo = new Todo(id, title, username, description, targetDate, isDone);
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
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				todos.add(new Todo(id, title, username, description, targetDate, isDone));
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
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_TODO);) {
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getUsername());
			statement.setString(3, todo.getDescription());
			statement.setDate(4, DBUtils.getSQLDate(todo.getTargetDate()));
			statement.setBoolean(5, todo.getStatus());
			statement.setLong(6, todo.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
