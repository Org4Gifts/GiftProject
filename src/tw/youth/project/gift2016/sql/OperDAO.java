package tw.youth.project.gift2016.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.youth.project.gift2016.sql.aodr.AODR;

public class OperDAO {
	private static Connection conn;
	private String url;
	private String name;
	private String password;

	public OperDAO(String url, String name, String password) {
		this.url = url;
		this.name = name;
		this.password = password;
	}

	public Connection getConn() {
		return conn;
	}

	public Boolean[] chkTableExist() {
		Boolean[] chk = new Boolean[MySqlCmd.TABLES.length];
		for (int i = 0; i < chk.length; i++) {
			chk[i] = false;
		}
		PreparedStatement ps;
		try {
			ps = OperDAO.conn.prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES");
			ResultSet rs = ps.executeQuery();
			System.out.println("Read data");
			while (rs.next()) {
				for (int i = 0; i < MySqlCmd.TABLES.length; i++) {
					if (rs.getString(1).equals(MySqlCmd.TABLES[i])) {
						chk[i] = true;
					}
				}
			}
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

	public boolean closeSQL() {
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
