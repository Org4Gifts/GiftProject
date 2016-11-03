package tw.youth.project.gift2016.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.youth.project.gift2016.sql.aodr.AODR;

public class DBManager {
	private static Connection conn;
	private String url;
	private String name;
	private String password;

	public DBManager(String url, String name, String password) {
		this.url = url;
		this.name = name;
		this.password = password;
	}

	public Connection getConn() {
		return conn;
	}

	public Boolean[] chkTableExist() {
		Boolean[] chk = new Boolean[SQLCmd.TABLES.length];
		for (int i = 0; i < chk.length; i++) {
			chk[i] = false;
		}
		try {
			// PreparedStatement ps = conn.prepareStatement("SELECT TABLE_NAME
			// FROM INFORMATION_SCHEMA.TABLES");
			PreparedStatement ps = conn.prepareStatement("show tables");
			ResultSet rs = ps.executeQuery();
			System.out.println("Read data");
			while (rs.next()) {
				for (int i = 0; i < SQLCmd.TABLES.length; i++) {
					if (rs.getString(1).equals(SQLCmd.TABLES[i])) {
						chk[i] = true;
					}
				}
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return chk;
	}

	public boolean starup() {
		conn = new DAO().getConn(url, name, password);
		return conn != null;
	}

	public String createTable(String tableName, String[] keys, String[] types, String[] uniques,String primary) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE").append(" ").append("TABLE").append(" ").append(tableName).append(" ").append("(");
		for (int i = 0; i < keys.length; i++) {
			sb.append(keys[i]).append(" ").append(SQLCmd.getSqlType(types[i])).append(" ").append("NOT NULL");
			for (String string : uniques) {
				if (keys[i].equals(string)) {
					sb.append(" ").append("UNIQUE");
				}
			}

			sb.append(",");
		}
		// created timestamp default 0,updated timestamp on update
		// current_timestamp
		sb.append("CREATED").append(" ").append("TIMESTAMP").append(" ").append("DEFAULT").append(" ").append("0")
				.append(",").append("UPDATED").append(" ").append("TIMESTAMP").append(" ").append("ON").append(" ")
				.append("UPDATE").append("CURRENT_TIMESTAMP").append(",");
		if (!primary.equals("")) {
			sb.append("PRIMARY KEY").append("(").append(primary).append("));");
		} else {
			sb.replace(sb.length() - 1, sb.length(), ");");
		}
		return sb.toString();
	}

	public boolean insert(String tableName, String[] keys, Object[] values, String[] types) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT").append(" ").append("INTO").append(" ").append(tableName).append(" ").append("(");
		for (String key : keys) {
			sb.append(key).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), ")").append(" ").append("VALUES").append("(");
		for (int i = 0; i < keys.length; i++) {
			sb.append("?").append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), ")");
		try {
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			for (int i = 0; i < values.length; i++) {
				ps.setObject(i, values[i]);
			}
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean update() {

		return true;
	}

	public boolean drop() {

		return true;
	}

	public <T> Object query(T tableName) {

		return null;
	}

	public boolean close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("closeSQL :" + e.getMessage());
		} finally {
			conn = null;
		}
		return conn == null;
	}

	public ArrayList<AODR> getPresentList() {
		return null;
	}
}
