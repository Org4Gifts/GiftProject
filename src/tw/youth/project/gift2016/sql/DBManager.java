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
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

public class DBManager {
	private static Connection conn;
	private String url;
	private String db;
	private String name;
	private String password;

	public DBManager(String url, String db, String name, String password) {
		this.url = url;
		this.db = db;
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
		case "AUSER":
			table = new AUSER();
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
		conn = new DAO().getConn(url,db, name, password);
		return conn != null;
	}

	// 建立資料表
	public String createTable(String tableName, String[] keys, String[] types, String[] uniques) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE").append(" ").append("TABLE").append(" ").append(tableName).append(" ").append("(");
		for (int i = 0; i < keys.length; i++) {
			sb.append(keys[i]).append(" ").append(SQLCmd.getSqlType(types[i])).append(" ").append("NOT NULL");
			if (i == 0) {
				sb.append(" ").append("AUTO_INCREMENT");
			}
			if (types[i].equals("Date")) {
				sb.append(" ").append("DEFAULT").append(" ").append("0");
			}

			for (String string : uniques) {
				if (keys[i].equals(string)) {
					sb.append(" ").append("UNIQUE");
				}
			}

			sb.append(",");
		}
		sb.append("created").append(" ").append("DATETIME").append(" ").append("DEFAULT").append(" ")
				.append("CURRENT_TIMESTAMP").append(",").append("updated").append(" ").append("DATETIME").append(" ")
				.append("ON").append(" ").append("UPDATE").append(" ").append("CURRENT_TIMESTAMP").append(" ")
				.append("DEFAULT").append(" ").append("CURRENT_TIMESTAMP").append(",");
		sb.append("PRIMARY KEY").append("(").append("_id").append("));");
		return sb.toString();
	}

	// 這個專門用於phpMyAdmin
	public String createTable2(String tableName, String[] keys, String[] types, String[] uniques) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE").append(" ").append("TABLE").append(" ").append(tableName).append(" ").append("(");
		for (int i = 0; i < keys.length; i++) {
			sb.append(keys[i]).append(" ").append(SQLCmd.getSqlType(types[i])).append(" ").append("NOT NULL");
			if (i == 0) {
				sb.append(" ").append("AUTO_INCREMENT");
			}
			if (types[i].equals("Date")) {
				sb.append(" ").append("DEFAULT").append(" ").append("0");
			}
			for (String string : uniques) {
				if (keys[i].equals(string)) {
					sb.append(" ").append("UNIQUE");
				}
			}

			sb.append(",");
		}
		sb.append("created").append(" ").append("DATETIME").append(" ").append("NOT").append(" ").append("NULL")
				.append(" ").append("DEFAULT").append(" ").append("0").append(",").append("updated").append(" ")
				.append("DATETIME").append(" ").append("NOT").append(" ").append("NULL").append(" ").append("ON")
				.append(" ").append("UPDATE").append(" ").append("CURRENT_TIMESTAMP").append(" ").append("DEFAULT")
				.append(" ").append("0").append(",");
		sb.append("PRIMARY KEY").append("(").append("_id").append("));");
		return sb.toString();
	}

	// 插入資料
	// public synchronized String insert(String tableName, String[] keys,
	// Object[] values) {
	// StringBuilder sb = new StringBuilder();
	// sb.append("INSERT").append(" ").append("INTO").append("
	// ").append(tableName).append(" ").append("(");
	// for (String key : keys) {
	// if (key.equals("_id"))
	// continue;
	// sb.append(key).append(",");
	// }
	// sb.replace(sb.length() - 1, sb.length(), ")").append("
	// ").append("VALUES").append("(");
	// for (String key : keys) {
	// if (key.equals("_id"))
	// continue;
	// sb.append("?").append(",");
	// }
	// sb.replace(sb.length() - 1, sb.length(), ")");
	// try {
	// PreparedStatement ps = conn.prepareStatement(sb.toString());
	// for (int i = 0; i < values.length; i++) {
	// ps.setObject(i + 1, values[i]);
	// }
	// return "Insert " + (ps.executeUpdate() > 0);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// return "Insert error, " + e.getMessage();
	// }
	//
	// }

	// 這個用於phpMyAdmin
	public synchronized String insert(String tableName, String[] keys, Object[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT").append(" ").append("INTO").append(" ").append(tableName).append(" ").append("(");
		for (String key : keys) {
			if (key.contains("_id"))
				continue;
			sb.append(key).append(",");
		}
		sb.append("created").append(","); // 增加的地方
		sb.append("updated").append(","); // 增加的地方
		sb.replace(sb.length() - 1, sb.length(), ")").append(" ").append("VALUES").append("(");
		for (String key : keys) {
			if (key.contains("_id"))
				continue;
			sb.append("?").append(",");
		}
		sb.append("?").append(",");// 增加的地方
		sb.append("?").append(")");// 增加的地方
		try {
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			for (int i = 0; i < values.length; i++) {
				ps.setObject(i + 1, values[i]);
			}
			ps.setObject(values.length + 1, ToolBox.getCurrentTimestamp()); // 增加的地方
			ps.setObject(values.length + 2, ToolBox.getCurrentTimestamp()); // 增加的地方
			return "Insert " + (ps.executeUpdate() > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Insert error, " + e.getMessage();
		}

	}

	// 更新資料
	// public synchronized String update(String tableName, String[] keys,
	// Object[] values) {
	// StringBuilder sb = new StringBuilder();
	// sb.append("UPDATE").append(" ").append(tableName).append("
	// ").append("SET").append(" ");
	// for (String key : keys) {
	// if (key.equals("_id"))
	// continue;
	// sb.append(key).append("=?,");
	// }
	// sb.replace(sb.length() - 1, sb.length(), " ");
	// sb.append("WHERE").append(" ").append(keys[0]).append("=?");
	// try {
	// PreparedStatement ps = conn.prepareStatement(sb.toString());
	// int i;
	// for (i = 1; i < values.length; i++) {
	// if (values.length - i <= 2)
	// continue;
	// ps.setObject(i, values[i]);
	// }
	// ps.setObject(i - 2, values[0]);
	//
	// return "update " + (ps.executeUpdate() > 0);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// return "update error, " + e.getMessage();
	// }
	// }

	// 這個用於phpMyAdmin
	public synchronized String update(String tableName, String[] keys, Object[] values) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE").append(" ").append(tableName).append(" ").append("SET").append(" ");
		for (String key : keys) {
			if (key.contains("_id"))
				continue;
			sb.append(key).append("=?,");
		}
		sb.append("created").append("=?,");
		sb.append("updated").append("=?").append(" ");
		sb.append("WHERE").append(" ").append(keys[0]).append("=?");
		try {
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			int i;
			for (i = 1; i < values.length; i++) {
				if (values.length - i <= 2)
					continue;
				ps.setObject(i, values[i]);
				System.out.println(i + " ; " + values[i]);
			}
			ps.setObject(i - 2, ToolBox.getCurrentTimestamp());
			ps.setObject(i - 1, ToolBox.getCurrentTimestamp());
			ps.setObject(i, values[0]);

			return "update " + (ps.executeUpdate() > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "update error, " + e.getMessage();
		}
	}

	// 刪除資料
	public synchronized String drop(String tableName, String key, Object value) {
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

	// 查詢資料
	public synchronized ArrayList<Object[]> query(String tableName, String key, Object value, int length) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT").append(" ").append("*").append("FROM").append(" ").append(tableName).append(" ")
				.append("WHERE").append(" ").append(key).append(" ").append("LIKE").append(" ").append("?");

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
			System.out.println("Query SQLException : " + e.getMessage());
			return null;
		}

	}

	// public <T> Object query(T tableName) {
	//
	// return null;
	// }

	public synchronized boolean close() {
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
