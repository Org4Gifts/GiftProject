package tw.youth.project.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

import org.junit.Test;

public class Tester {

	// @Test
	public void test() {
		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();

		// USER
		System.out.println("USER");
		AUSER user = new AUSER();
		Object[] objs = { "K123457", "odise2", user.toMD5Pass("116025") };
		user.setValues(objs);
		System.out.println(dao.insert(user.getTableName(), user.getKeys(), user.getValues()));

	}

	@Test
	public void tests() {
		java.util.Date dates = new java.util.Date();
		java.sql.Date date = ToolBox.toSqlDate(dates);
		System.out.println(date.getTime() + " " + dates.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(dates));
		System.out.println(sdf.format(date));
	}

}
