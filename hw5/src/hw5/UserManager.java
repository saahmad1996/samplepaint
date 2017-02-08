package hw5;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UserManager {

	// db
	static Connection conn;
	static Statement stmt;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/shape_user";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	public UserManager() {
		conn = null;
		stmt = null;

	}

	static boolean isValid(String userName, String password) {
		conn = null;
		stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = (Statement) conn.createStatement();

			String sql;
			sql = "SELECT * FROM `user` WHERE `userName` = '" + userName + "';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				if (rs.getString("password").equals(password)) {

					return true;
				}
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	static boolean isExist(String userName) {
		conn = null;
		stmt = null;
		boolean result = false;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = (Statement) conn.createStatement();

			String sql;
			sql = "SELECT * FROM `user` WHERE `userName` = '" + userName
					+ "' ;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				if (rs.getString("userName").equals(userName)) {
					result = true;

					return result;
				} else {
					result = false;
					return result;
				}

			}

			stmt.close();
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	static void addUser(User user) {
		conn = null;
		stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = (Statement) conn.createStatement();
			String sql;
			sql = "INSERT INTO `user` (`userName`, `password`) VALUES ('"
					+ user.getUserName() + "', '" + user.getPass() + "');";
			int rs = stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception s) {

		}

	}

	public static User getUser(String userName, String password) {

		conn = null;
		stmt = null;
		User resultUser = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = (Statement) conn.createStatement();
			String sql;
			sql = "SELECT * FROM `user` WHERE `userName` = '" + userName
					+ "' ;";
			ResultSet rs = stmt.executeQuery(sql);
			int d = Integer.parseInt(stmt.executeQuery(sql).getString(0));
			while (rs.next()) {
				resultUser.setId(d);
				resultUser.setUserName(userName);
				resultUser.setPass(password);

			}

			stmt.close();
			conn.close();

		} catch (Exception s) {

		}

		return resultUser;
	}

	public static int getUserId(String userName) {
		conn = null;
		stmt = null;
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = (Statement) conn.createStatement();
			String sql;
			sql = "SELECT * FROM `user` WHERE `userName` = '" + userName
					+ "' ;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result = Integer.parseInt(rs.getString("id"));
			}
			stmt.close();
			conn.close();

		} catch (Exception s) {

		}

		return result;
	}
}
