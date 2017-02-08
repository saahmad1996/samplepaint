package hw5;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ShapeManager {

	// db
	static Connection conn;
	static Statement stmt;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/shape_user";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	
	private User user;

	public ShapeManager(User user) {
		this.user = new User(user);

		conn = null;
		stmt = null;

	}

	public Map<Point[], Color> getShape(String type) {
		conn = null;
		stmt = null;
		Map<Point[], Color> resultType = new HashMap<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = (Statement) conn.createStatement();

			String sql;
			sql = "SELECT * FROM `shape` INNER join user on shape.user_id=user.id WHERE shape.user_id="
					+ user.getId() + " and shape.type ='" + type + "';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Point p1 = new Point();
				p1.x = Integer.parseInt(rs.getString("x"));
				p1.y = Integer.parseInt(rs.getString("y"));
				Point p2 = new Point();
				p2.x = Integer.parseInt(rs.getString("xx"));
				p2.y = Integer.parseInt(rs.getString("yy"));

				Point[] points = { p1, p2 };
				String str = rs.getString("color");
				Color color = getColor(str);
				resultType.put(points, color);
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultType;
	}

	private Color getColor(String string) {
		switch (string) {
		case "red":
			return Color.RED;
		case "green":
			return Color.green;
		case "blue":
			return Color.BLUE;
		default:
			return Color.BLACK;
		}
	}

	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		conn = null;
		stmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = (Statement) conn.createStatement();

			String sql;

			sql = "INSERT INTO `shape` (`id`, `type`, `x`, `y`, `xx`, `yy`, `user_id`, `color`) VALUES (NULL, '"
					+ shape.getType()
					+ "', '"
					+ shape.getX1()
					+ "', '"
					+ shape.getY1()
					+ "', '"
					+ shape.getX2()
					+ "', '"
					+ shape.getY2()
					+ "', '"
					+ user.getId()
					+ "', '"
					+ shape.getStrColor() + "');";

			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
