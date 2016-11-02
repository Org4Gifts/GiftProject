package tw.youth.project.gift2016.sql;

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

public class MySqlCmd {
	// 通用參數
	public static final String DB = "GIFT";
	public static final String[] TABLES = { USER.class.getSimpleName(), AEMP.class.getSimpleName(),
			AVDR.class.getSimpleName(), AQTY.class.getSimpleName(), APRESENT.class.getSimpleName(),
			AODRDT.class.getSimpleName(), AODR.class.getSimpleName(), AIODT.class.getSimpleName(),
			AIO.class.getSimpleName(), AINVENTORY.class.getSimpleName(), AFAB.class.getSimpleName(),
			ADEP.class.getSimpleName() };
	// 通用操作
	public static final String LOGIN = "";
	public static final String SHOW_TABLES = "show tables from %s";
	//
	public static final String CREATE_APPLICANT = "";
	public static final String UPDATE_APPLICANT = "";
	public static final String UPDATE_USER = "";

	public static final String APPROVE_APPLICANT = "";

	public static final String CREATE_USER = "";
	public static final String DROP = "";
	public static final String UPDATE_USER_MANAGER = "";

	private static String getSqlType(String type) {
		String sqlType = "";
		switch (type) {
		case "String":
			sqlType = "VARCHAR(20)";
			break;
		case "Integer":
			sqlType = "INT";
			break;
		case "Float":
			sqlType = "FLOAT";
			break;
		case "Character":
			sqlType = "CHAR(1)";
			break;
		case "Short":
			sqlType = "TINYINT";
			break;
		case "Long":
			sqlType = "BIGINT";
			break;
		case "Double":
			sqlType = "DOUBLE";
			break;
		}
		return sqlType;
	}

	public static String createTable(String tableName, String[] values, String[] types, String primary,
			String[] unique) {
		StringBuilder sb = new StringBuilder();
		sb.append("create table").append(" ").append(DB).append(".").append(tableName).append(" ").append("(");
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i]).append(" ").append(getSqlType(types[i])).append(" ").append("NOT NULL");
			for (String string : unique) {
				if (values[i].equals(string)) {
					sb.append(" ").append("UNIQUE");
				}
			}

			sb.append(",");
		}
		if (!primary.equals("")) {
			sb.append("PRIMARY KEY").append("(").append(primary).append("));");
		} else {
			sb.replace(sb.length() - 1, sb.length(), ");");
		}
		return sb.toString();
	}

}
