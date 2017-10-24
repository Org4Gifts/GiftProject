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
import tw.youth.project.gift2016.sql.asetup.ASETUP;
import tw.youth.project.gift2016.sql.asignlog.ASIGNLOG;
import tw.youth.project.gift2016.sql.avdr.AVDR;
import tw.youth.project.gift2016.sql.normal.Bulletin;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;

public class SQLCmd {
	// 通用參數
	public static final String DB_URL = "jdbc:mysql://localhost:3306/";
	public static final String DB_NAME = "GIFT";
	public static final String DB_USER = "odise";
	public static final String DB_PASS = "116025";

	public static final String[] TABLES = { AUSER.class.getSimpleName().toLowerCase(),
			AEMP.class.getSimpleName().toLowerCase(), AVDR.class.getSimpleName().toLowerCase(),
			ASIGNLOG.class.getSimpleName().toLowerCase(), ASETUP.class.getSimpleName().toLowerCase(),
			AQTY.class.getSimpleName().toLowerCase(), APRESENT.class.getSimpleName().toLowerCase(),
			AODRDT.class.getSimpleName().toLowerCase(), AODR.class.getSimpleName().toLowerCase(),
			AIODT.class.getSimpleName().toLowerCase(), AIO.class.getSimpleName().toLowerCase(),
			AINVENTORY.class.getSimpleName().toLowerCase(), AFAB.class.getSimpleName().toLowerCase(),
			ADEP.class.getSimpleName().toLowerCase(),Bulletin.class.getSimpleName().toLowerCase()};
	// // 通用操作
	// public static final String LOGIN = "";
	// public static final String SHOW_TABLES = "show tables from %s";
	// //
	// public static final String CREATE_APPLICANT = "";
	// public static final String UPDATE_APPLICANT = "";
	// public static final String UPDATE_USER = "";
	//
	// public static final String APPROVE_APPLICANT = "";
	//
	// public static final String CREATE_USER = "";
	// public static final String DROP = "";
	// public static final String UPDATE_USER_MANAGER = "";

	public static String getSqlType(String type) {
		String sqlType = "";
		switch (type) {
		case "String":
			sqlType = "VARCHAR(32)";
			break;
		case "Integer":
			sqlType = "INT";
			// 長度11
			break;
		case "Float":
			sqlType = "FLOAT";
			break;
		case "Character":
			sqlType = "CHAR(1)";
			break;
		case "Short":
			sqlType = "TINYINT";
			// 0~255
			break;
		case "Long":
			sqlType = "BIGINT";
			break;
		case "Double":
			sqlType = "DOUBLE";
			break;
		case "Date":
			sqlType = "DATETIME";
			break;
		case "Timestamp":
			sqlType = "DATETIME";
			break;
		}
		return sqlType;
	}

}
