package com.bw.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bw.model.LoginBean;
import com.bw.utils.DBUtils;

public class LoginDao {

	public static boolean validate(LoginBean loginBean)
			throws ClassNotFoundException, FileNotFoundException, SQLException {
		boolean status = false;
		Connection connection = DBUtils.setupDB();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("select * from users where username = ? and password = ? ")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			// process sql exception
			DBUtils.printSQLException(e);
		}
		return status;
	}
}
