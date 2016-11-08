package tw.youth.project.gift2016.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.afab.AFAB;
import tw.youth.project.gift2016.sql.ainventory.AINVENTORY;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.apresent.APRESENT;
import tw.youth.project.gift2016.sql.aqty.AQTY;
import tw.youth.project.gift2016.sql.avdr.AVDR;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.USER;

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

	public Object getTableObject(String tableName) {
		Object table = null;
		switch (tableName) {
		case "USER":
			table = new USER();
			break;
		case "AEMP":
			table = new AEMP();
			break;
		case "AVDR":
			table = new AVDR();
			break;
		case "AQTY":
			table = new AQTY();
			break;
		case "APRESENT":
			table = new APRESENT();
			break;
		case "AODRDT":
			table = new AODRDT();
			break;
		case "AODR":
			table = new AODR();
			break;
		case "AIODT":
			table = new AIODT();
			break;
		case "AIO":
			table = new AIO();
			break;
		case "AINVENTORY":
			table = new AINVENTORY();
			break;
		case "AFAB":
			table = new AFAB();
			break;
		case "ADEP":
			table = new ADEP();
			break;
		}
		return table;
	}

	public boolean starup() {
		conn = new DAO().getConn(url, name, password);
		return conn != null;
	}

	public String createTable(String tableName, String[] keys, String[] types, String[] uniques, String primary) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE").append(" ").append("TABLE").append(" ").append(tableName).append(" ").append("(");
		for (int i = 0; i < keys.length; i++) {
			sb.append(keys[i]).append(" ").append(SQLCmd.getSqlType(types[i])).append(" ").append("NOT NULL");
			if (i == 0) {
				sb.append(" ").append("AUTO_INCREMENT");
			}
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

	public synchronized String insert(String tableName, String[] keys, Object[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT").append(" ").append("INTO").append(" ").append(tableName).append(" ").append("(");
		for (String key : keys) {
			sb.append(key).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), ")").append(" ").append("VALUES").append("(");
		for (String key : keys) {
			sb.append("?").append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), ")");
		try {
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			for (int i = 0; i < values.length; i++) {
				ps.setObject(i + 1, values[i]);
			}
			return "Insert " + (ps.executeUpdate() > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Insert error, " + e.getMessage();
		}

	}

	public synchronized String update(String tableName, String[] keys, Object[] values) {
		// "UPDATE library_db.books SET
		// TITLE=?,ORIGINALTITLE=?,ISBN=?,AUTHOR=?,"
		// +
		// "EDITION=?,BINDING=?,PUBLISHER=?,PUBLISHED=?,LISTPRICE=?,COLUMNS=?,SUBJECT=?,WEBSITE=?,BORROWER=?,"
		// + "REMARK=?,REFERENCE=?,COMMENT=? WHERE NUMBER=?";
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE").append(" ").append(tableName).append(" ").append("SET").append(" ");
		for (String key : keys) {
			sb.append(key).append("=?,");
		}
		sb.replace(sb.length() - 1, sb.length(), " ");
		sb.append("WHERE").append(" ").append(keys[0]).append("=?");

		try {
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			for (int i = 0; i < values.length; i++) {
				ps.setObject(i + 1, values[i]);
			}
			return "update " + (ps.executeUpdate() > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "update error, " + e.getMessage();
		}
	}

	public String drop(String tableName, String key, Object value) {
		String dropTable = "DELETE from %s WHERE %s=?";
		try {
			PreparedStatement ps = conn.prepareStatement(String.format(dropTable, tableName, key));
			ps.setObject(1, value);
			return "drop " + (ps.executeUpdate() > 0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "drop error, " + e.getMessage();
		}
	}

	public ArrayList<Object[]> query(String tableName, String key, Object value, int length) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT").append(" ").append("*").append("FROM").append(tableName).append(" ").append("WHERE")
				.append(" ").append(key).append(" ").append("LIKE").append(" ").append("?");

		try {
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setObject(1, "%" + value + "%");
			ResultSet rs = ps.executeQuery();
			ArrayList<Object[]> arr = new ArrayList<>();
			while (rs.next()) {
				Object[] objs = new Object[length];
				for (int i = 0; i < length; i++) {
					objs[i] = rs.getObject(i + 1);
				}
				arr.add(objs);
			}
			return arr;
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}

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
