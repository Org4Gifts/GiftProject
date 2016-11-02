package tw.youth.project.gift2016.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	public DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException : " + e.getMessage());
		}
	}

	public Connection getConn(String url, String name, String password) {
		try {
			return DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			System.out.println("SQLException : " + e.getMessage());
			System.exit(0);
			return null;
		}
	}
}
