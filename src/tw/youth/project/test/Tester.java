package tw.youth.project.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tw.youth.project.gift2016.func.Login;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

import org.junit.Test;

public class Tester {

	@Test
	public void testAodrdt() {
		DBManager manager = new DBManager(SQLCmd.DB_URL,SQLCmd.DB_NAME, SQLCmd.DB_USER, SQLCmd.DB_PASS);
		manager.starup();
		// AUSER user = new Login(manager, "P0006", "P0006").getUser();
		AODRDT aodrdt = new AODRDT();
		AODR aodr = new AODR();
		float tempCount = 0.0f;
		aodrdt.setOrder1("A201612015");
		for (Object[] dts : manager.query(aodrdt.getTableName(), aodrdt.getKeys()[1], aodrdt.getOrder1(),
				aodrdt.getLength())) {
			aodrdt.setValuesFull(dts);
			tempCount += aodrdt.getQty() * aodrdt.getPrc();
		}
		aodr.setValuesFull(
				manager.query(aodr.getTableName(), aodr.getKeys()[1], aodrdt.getOrder1(), aodr.getLength()).get(0));
		aodr.setTamt(tempCount);
		manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull());
	}

	// @Test
	public void test() {
		DBManager dao = new DBManager(SQLCmd.DB_URL , SQLCmd.DB_NAME, SQLCmd.DB_USER, SQLCmd.DB_PASS);
		dao.starup();

		// USER
		System.out.println("USER");
		AUSER user = new AUSER();
		Object[] objs = { "K123457", "odise2", user.toMD5Pass("116025") };
		user.setValues(objs);
		System.out.println(dao.insert(user.getTableName(), user.getKeys(), user.getValues()));

	}

	// @Test
	public void tests() {
		java.util.Date dates = new java.util.Date();
		java.sql.Date date = ToolBox.toSqlDate(dates);
		System.out.println(date.getTime() + " " + dates.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(dates));
		System.out.println(sdf.format(date));

		String str = sdf.format(dates);
		System.out.println(str.substring(0, str.length() - 2));
	}

	// @Test
	public void testAodr() {
		ArrayList<Object[]> arr = null;
		ArrayList<Object[]> arr2 = null;
		DBManager dao = new DBManager(SQLCmd.DB_URL , SQLCmd.DB_NAME, SQLCmd.DB_USER, SQLCmd.DB_PASS);
		dao.starup();
		// AODR
		System.out.println("AODR");
		AODR aodr = null;
		// AODRSS aodrss = null;
		try {
			System.out.println(ToolBox.toSqlDate(new Date()).getClass().getName());
			// aodrss = new AODRSS();
			aodr = new AODR();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception " + e.getMessage());
		}

		// System.out.println(aodrss);
		//// System.out.println(aodr);
		//// System.out.println("");
		//// System.out.println("SetObj");
		Object[] objs6 = { "A20161108", ToolBox.toSqlDate(new Date()), "K123456", "F1", "0800", 100L, "Preparing", 2,
				"測試申請單1" };
		System.out.println("SetSuccess");
		aodr.setValues(objs6);
		System.out.println("SetValue");
		System.out.println(dao.insert(aodr.getTableName(), aodr.getKeys(), aodr.getValues()));
		arr = dao.query(aodr.getTableName(), "empno", "234", aodr.getLength());
		for (Object[] objects : arr) {
			String str = "arr1 ";
			for (Object object : objects) {
				str += object + ", ";
			}
			System.out.println(str);
			aodr.setValuesFull(objects);
		}

		objs6[6] = "Rejected";
		aodr.setValues(objs6);
		System.out.println(dao.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull()));
		arr2 = dao.query(aodr.getTableName(), "empno", "234", aodr.getLength());
		for (Object[] objects : arr2) {
			String str = "arr2 ";
			for (Object object : objects) {
				str += object + ", ";
			}
			System.out.println(str);
		}
		System.out.println(dao.drop(aodr.getTableName(), "empno", "K123456") + "\n");
	}

	// @Test
	public void testMd5() {
		AUSER user = new AUSER();
		System.out.println(user.toMD5Pass("P0001"));
	}

}
