package tw.youth.project.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.DBManager;
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

public class TestSQL {

	// @Test
	public void testSQL() throws SQLException { // 測試OK
		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/", "odise", "116025");
		dao.starup();
		// mydb1?useUnicode=true&characterEncoding=utf-8
		PreparedStatement ps = dao.getConn().prepareStatement("select * from cmdev.dept where deptno LIKE ?");
		ps.setString(1, "%" + "" + "%"); // % 模糊搜尋
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getString(1) + " , ");
			System.out.print(rs.getString(2) + " , ");
			System.out.println(rs.getString(3));
		}
	}

	// @Test
	public void testTableExistAndTestTableFunc() throws InterruptedException { // 測試OK
		boolean exists = false;

		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();

		try {
			PreparedStatement ps = dao.getConn().prepareStatement("show tables");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("find table : " + rs.getString(1));
				if (rs.getString(1).equals(USER.class.getSimpleName().toLowerCase())) {
					exists = true;
					break;
				}
			}

			USER user = new USER();
			if (!exists) {
				String table = dao.createTable(user.getTableName(), user.getKeys(), user.getTypes(), user.getUniques());
				System.out.println(table);
				ps = dao.getConn().prepareStatement(table);
				System.out.println(ps.execute());
			}
			Object[] objs = { "K123456", "odise", "116025" };
			user.setValues(objs);
			System.out.println(dao.insert(user.getTableName(), user.getKeys(), objs));
			ArrayList<Object[]> arr = dao.query(user.getTableName(), "user", "o", 6);
			for (Object[] objects : arr) {
				for (Object object : objects) {
					System.out.println("arr1 : " + object);
				}
				user.setValuesFull(objects);
			}

			user.setPass("116022");
			System.out.println("Thread sleep 1.5Second");
			Thread.sleep(1500);
			System.out.println(dao.update(user.getTableName(), user.getKeys(), user.getValuesFull()));

			ArrayList<Object[]> arr2 = dao.query(user.getTableName(), "user", "o", 6);
			for (Object[] objects : arr2) {
				for (Object object : objects) {
					System.out.println("arr2 : " + object);
				}
			}

			System.out.println(dao.drop(user.getTableName(), "user", "odise"));

			ArrayList<Object[]> arr3 = dao.query(user.getTableName(), "user", "o", 6);
			for (Object[] objects : arr3) {
				for (Object object : objects) {
					System.out.println("arr3 : " + object);
				}
			}
			System.out.println("arr3 = " + arr3.size());

			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		// 無法使用
		// DatabaseMetaData meta = OperDAO.conn.getMetaData();
		// ResultSet set = meta.getSchemas();
		// while (set.next()) {
		// System.out.println(set.getString(1));
		// }
		// System.out.println("meta : " + meta.toString());
		// ResultSet rs1 = meta.getTables(null, null, null, new String[] {
		// "TABLE" });
		// ResultSet rs3 = meta.getTables(null, null, "%", null);
		// while (rs1.next()) {
		// System.out.println("rs1 : " + rs1.getString("TABLE_NAME"));
		// }
		// while (rs3.next()) {
		// System.out.println("rs2 : " + rs3.getString(3));
		// }

		// for (boolean chk : dao.chkTableExist()) {
		// System.out.println(chk);
		// }
	}

	// @Test
	public void testDropTables() { // 測試OK
		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();
		System.out.println("testDropTables : " + new Date(System.currentTimeMillis()).toString());
		try {
			System.out.println("drop user " + dao.getConn().prepareStatement("Drop table user;").execute());
			System.out.println("drop aemp " + dao.getConn().prepareStatement("Drop table aemp;").execute());
			System.out.println("drop avdr " + dao.getConn().prepareStatement("Drop table avdr;").execute());
			System.out.println("drop aqty " + dao.getConn().prepareStatement("Drop table aqty;").execute());
			System.out.println("drop apresent " + dao.getConn().prepareStatement("Drop table apresent;").execute());
			System.out.println("drop aodrdt " + dao.getConn().prepareStatement("Drop table aodrdt;").execute());
			System.out.println("drop aodr " + dao.getConn().prepareStatement("Drop table aodr;").execute());
			System.out.println("drop aiodt " + dao.getConn().prepareStatement("Drop table aiodt;").execute());
			System.out.println("drop aio " + dao.getConn().prepareStatement("Drop table aio;").execute());
			System.out.println("drop ainventory " + dao.getConn().prepareStatement("Drop table ainventory;").execute());
			System.out.println("drop afab " + dao.getConn().prepareStatement("Drop table afab;").execute());
			System.out.println("drop adep " + dao.getConn().prepareStatement("Drop table adep;").execute());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		dao.close();

	}

	// @Test
	public void testCreateTables() { // 測試OK
		testDropTables();

		System.out.println("testCreateTable : " + new Date(System.currentTimeMillis()).toString());
		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();

		String table;
		try {
			USER user = new USER();// 上面已測過 僅列出
			table = dao.createTable(user.getTableName(), user.getKeys(), user.getTypes(), user.getUniques());
			System.out.println(table);
			System.out.println(user.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			AEMP aemp = new AEMP();
			table = dao.createTable(aemp.getTableName(), aemp.getKeys(), aemp.getTypes(), aemp.getUniques());
			System.out.println(table);
			System.out.println(aemp.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			AVDR avdr = new AVDR();
			table = dao.createTable(avdr.getTableName(), avdr.getKeys(), avdr.getTypes(), avdr.getUniques());
			System.out.println(table);
			System.out.println(avdr.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			AQTY aqty = new AQTY();
			table = dao.createTable(aqty.getTableName(), aqty.getKeys(), aqty.getTypes(), aqty.getUniques());
			System.out.println(table);
			System.out.println(aqty.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			APRESENT apresent = new APRESENT();
			table = dao.createTable(apresent.getTableName(), apresent.getKeys(), apresent.getTypes(),
					apresent.getUniques());
			System.out.println(table);
			System.out.println(apresent.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			AODR aodr = new AODR();
			table = dao.createTable(aodr.getTableName(), aodr.getKeys(), aodr.getTypes(), aodr.getUniques());
			System.out.println(table);
			System.out.println(aodr.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			AODRDT aodrdt = new AODRDT();
			table = dao.createTable(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getTypes(), aodrdt.getUniques());
			System.out.println(table);
			System.out.println(aodrdt.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			AIO aio = new AIO();
			table = dao.createTable(aio.getTableName(), aio.getKeys(), aio.getTypes(), aio.getUniques());
			System.out.println(table);
			System.out.println(aio.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			AIODT aiodt = new AIODT();
			table = dao.createTable(aiodt.getTableName(), aiodt.getKeys(), aiodt.getTypes(), aiodt.getUniques());
			System.out.println(table);
			System.out.println(aiodt.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			AINVENTORY ainventory = new AINVENTORY();
			table = dao.createTable(ainventory.getTableName(), ainventory.getKeys(), ainventory.getTypes(),
					ainventory.getUniques());
			System.out.println(table);
			System.out.println(ainventory.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			AFAB afab = new AFAB();
			table = dao.createTable(afab.getTableName(), afab.getKeys(), afab.getTypes(), afab.getUniques());
			System.out.println(table);
			System.out.println(afab.getTableName() + " " + dao.getConn().prepareStatement(table).execute());

			ADEP adep = new ADEP();
			table = dao.createTable(adep.getTableName(), adep.getKeys(), adep.getTypes(), adep.getUniques());
			System.out.println(table);
			System.out.println(adep.getTableName() + " " + dao.getConn().prepareStatement(table).execute());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testInsertQueryUpdateDropTableColumns() { // 測試OK
		System.out.println("testInsertUpdateDropTables : " + new Date(System.currentTimeMillis()).toString());
		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();

		// USER
		System.out.println("USER");
		USER user = new USER();
		Object[] objs = { "K123456", "odise", "116025" };
		user.setValues(objs);
		System.out.println(dao.insert(user.getTableName(), user.getKeys(), user.getValues()));
		ArrayList<Object[]> arr = dao.query(user.getTableName(), "user", "o", user.getLength());
		for (Object[] objects : arr) {
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
			user.setValuesFull(objects);
		}

		user.setPass("116022");
		System.out.println(dao.update(user.getTableName(), user.getKeys(), user.getValuesFull()));
		ArrayList<Object[]> arr2 = dao.query(user.getTableName(), "user", "o", user.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(user.getTableName(), "user", "odise"));

		// AEMP
		System.out.println("AEMP");
		AEMP aemp = new AEMP();
		Object[] objs1 = { "K123456", "odise", "123@com", "eng", 0, "132-5979", "K123400", "DEP" };
		aemp.setValues(objs1);
		System.out.println(dao.insert(aemp.getTableName(), aemp.getKeys(), aemp.getValues()));
		arr = dao.query(aemp.getTableName(), "empno", "K", aemp.getLength());
		for (Object[] objects : arr) {
			aemp.setValuesFull(objects);
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		aemp.setEmail("456@com");
		System.out.println(dao.update(aemp.getTableName(), aemp.getKeys(), aemp.getValuesFull()));
		arr2 = dao.query(aemp.getTableName(), "empno", "K", aemp.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(aemp.getTableName(), "empno", "K123456"));

		// AVDR
		System.out.println("AVDR");
		AVDR avdr = new AVDR();
		Object[] objs2 = { "P001", "CG", "ComDim", "42610659", "李嘉明", "真會發", "Manager", "(087)433-5679",
				"(087)422-5998", "0998-477966", "(087)422-5778", "桃園市幼獅區幼獅路2段", "無" };
		avdr.setValues(objs2);
		System.out.println(dao.insert(avdr.getTableName(), avdr.getKeys(), avdr.getValues()));
		arr = dao.query(avdr.getTableName(), "id", "426", avdr.getLength());
		for (Object[] objects : arr) {
			avdr.setValuesFull(objects);
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		objs2[2] = "CardGame";
		avdr.setValues(objs2);
		System.out.println(dao.update(avdr.getTableName(), avdr.getKeys(), avdr.getValuesFull()));
		arr2 = dao.query(avdr.getTableName(), "id", "426", avdr.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(avdr.getTableName(), "id", "42610659"));

		// AQTY
		System.out.println("AQTY");
		AQTY aqty = new AQTY();
		Object[] objs3 = { 20161108, "P00", "P001", 100, 10, 5 };
		aqty.setValues(objs3);
		System.out.println(dao.insert(aqty.getTableName(), aqty.getKeys(), aqty.getValues()));
		arr = dao.query(aqty.getTableName(), "fgno", "P", aqty.getLength());
		for (Object[] objects : arr) {
			aqty.setValuesFull(objects);
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		objs3[3] = 200;
		aqty.setValues(objs3);
		System.out.println(dao.update(aqty.getTableName(), aqty.getKeys(), aqty.getValuesFull()));
		arr2 = dao.query(aqty.getTableName(), "fgno", "P", aqty.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(aqty.getTableName(), "fgno", "P001"));

		// APRESENT
		System.out.println("APRESENT");
		APRESENT apresent = new APRESENT();
		Object[] objs4 = { "P001", "筆記本", 10, 1, 1, "可申請", "無備註", 100, 150 };
		apresent.setValues(objs4);
		System.out.println(dao.insert(apresent.getTableName(), apresent.getKeys(), apresent.getValues()));
		arr = dao.query(apresent.getTableName(), "fgname", "筆", apresent.getLength());
		for (Object[] objects : arr) {
			apresent.setValuesFull(objects);
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		objs4[1] = "記事本";
		apresent.setValues(objs4);
		System.out.println(dao.update(apresent.getTableName(), apresent.getKeys(), apresent.getValuesFull()));
		arr2 = dao.query(apresent.getTableName(), "fgname", "筆", apresent.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(apresent.getTableName(), "fgno", "P001"));

		// AODRDT
		System.out.println("AODRDT");
		AODRDT aodrdt = new AODRDT();
		Object[] objs5 = { "A20161108", "P001", 5, 10, "無備註", 5 };
		aodrdt.setValues(objs5);
		System.out.println(dao.insert(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValues()));
		arr = dao.query(aodrdt.getTableName(), "order1", "1108", aodrdt.getLength());
		for (Object[] objects : arr) {
			aodrdt.setValuesFull(objects);
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		objs5[2] = 4;
		aodrdt.setValues(objs5);
		System.out.println(dao.update(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValuesFull()));
		arr2 = dao.query(aodrdt.getTableName(), "order1", "1108", aodrdt.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(aodrdt.getTableName(), "order1", "A20161108"));

		// AODR
		System.out.println("AODR");
		AODR aodr = new AODR();
		Object[] objs6 = { "A20161108", 20161108, "K123456", 100, "送禮" };
		aodr.setValues(objs6);
		System.out.println(dao.insert(aodr.getTableName(), aodr.getKeys(), aodr.getValues()));
		arr = dao.query(aodr.getTableName(), "empno", "234", aodr.getLength());
		for (Object[] objects : arr) {
			aodr.setValuesFull(objects);
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		objs6[4] = "自用";
		aodr.setValues(objs6);
		System.out.println(dao.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull()));
		arr2 = dao.query(aodr.getTableName(), "empno", "234", aodr.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(aodr.getTableName(), "empno", "K123456"));

		// AIODT
		System.out.println("AIODT");
		AIODT aiodt = new AIODT();
		Object[] objs7 = { "C60989", "P001", 5, 2, "A20161108", "送禮" };
		aiodt.setValues(objs7);
		System.out.println(dao.insert(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValues()));
		arr = dao.query(aiodt.getTableName(), "order1", "A20", aiodt.getLength());
		for (Object[] objects : arr) {
			aiodt.setValuesFull(objects);
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		objs7[2] = 6;
		aiodt.setValues(objs7);
		System.out.println(dao.update(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValuesFull()));
		arr2 = dao.query(aiodt.getTableName(), "order1", "A20", aiodt.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(aiodt.getTableName(), "order1", "A20161108"));

		// AIO
		System.out.println("AIO");
		AIO aio = new AIO();
		Object[] objs8 = { "C60989", "eng", 20161108, "P00", "D", 100, "eng廠區庫存不足" };
		aio.setValues(objs8);
		System.out.println(dao.insert(aio.getTableName(), aio.getKeys(), aio.getValues()));
		arr = dao.query(aio.getTableName(), "vhno", "C60", aio.getLength());
		for (Object[] objects : arr) {
			aio.setValuesFull(objects);
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		objs8[5] = 200;
		aio.setValues(objs8);
		System.out.println(dao.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull()));
		arr2 = dao.query(aio.getTableName(), "vhno", "C60", aio.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(aio.getTableName(), "vhno", "C60989"));

		// AINVENTORY
		System.out.println("AINVENTORY");
		AINVENTORY ainventory = new AINVENTORY();
		Object[] objs9 = { "Q123501", "eng", 20161108, "P001", 100, 99 };
		ainventory.setValues(objs9);
		System.out.println(dao.insert(ainventory.getTableName(), ainventory.getKeys(), ainventory.getValues()));
		arr = dao.query(ainventory.getTableName(), "invo", "235", ainventory.getLength());
		for (Object[] objects : arr) {
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		objs9[5] = 200;
		ainventory.setValues(objs9);
		System.out.println(dao.update(ainventory.getTableName(), ainventory.getKeys(), ainventory.getValuesFull()));
		arr2 = dao.query(ainventory.getTableName(), "invo", "235", ainventory.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(ainventory.getTableName(), "invo", "Q123501"));

		// AFAB
		System.out.println("AFAB");
		AFAB afab = new AFAB();
		Object[] objs10 = { "eng", "A廠區" };
		afab.setValues(objs10);
		System.out.println(dao.insert(afab.getTableName(), afab.getKeys(), afab.getValues()));
		arr = dao.query(afab.getTableName(), "fno", "en", afab.getLength());
		for (Object[] objects : arr) {
			for (Object object : objects) {
				System.out.println("arr1 : " + object);
			}
		}

		objs10[1] = "B廠區";
		afab.setValues(objs10);
		System.out.println(dao.update(afab.getTableName(), afab.getKeys(), afab.getValuesFull()));
		arr2 = dao.query(afab.getTableName(), "fno", "en", afab.getLength());
		for (Object[] objects : arr2) {
			for (Object object : objects) {
				System.out.println("arr2 : " + object);
			}
		}
		System.out.println(dao.drop(afab.getTableName(), "fno", "eng"));
	}

	// setAutoCommit(false) 自動更新取消
	// Statment addBatch() 加入batch
	// executeBatch 執行待寫入
	// conn.commit 執行批次上傳

	// ResultSetMetaData 可以取得欄位的細節

	// 交易 問題
	// conn.rollback(savepoint) 回朔到指定點
	// 使用在AutoCommit(false)的時候
	// Savepoint savepoint = conn.setSavepoint(); 設定save point
	// conn.commit();
	// conn.releaseSavepoint(savepoint); 釋放savepoint

	// @Test
	public void test() {
		Object[] obj = { "String", 1, 30.5 };
		for (Object object : obj) {
			System.out.println(object);
		}
	}

}
