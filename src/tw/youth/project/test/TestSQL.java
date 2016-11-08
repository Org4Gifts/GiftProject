package tw.youth.project.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	@Test
	public void testTableExist() { // 測試OK
		boolean exists = false;

		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();

		try {
			PreparedStatement ps = dao.getConn().prepareStatement("show tables");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
				if (rs.getString(1) == USER.class.getSimpleName().toLowerCase()) {
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
	public void testCreateTable() { // 測試OK，尚待進一步檢查

		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");

		USER user = new USER();
		System.out.println(dao.createTable(user.getTableName(), user.getKeys(), user.getTypes(), user.getUniques()));

		AEMP aemp = new AEMP();
		System.out.println(dao.createTable(aemp.getTableName(), aemp.getKeys(), aemp.getTypes(), aemp.getUniques()));

		AVDR avdr = new AVDR();
		System.out.println(dao.createTable(avdr.getTableName(), avdr.getKeys(), avdr.getTypes(), avdr.getUniques()));

		AQTY aqty = new AQTY();
		System.out.println(dao.createTable(aqty.getTableName(), aqty.getKeys(), aqty.getTypes(), aqty.getUniques()));

		APRESENT apresent = new APRESENT();
		System.out.println(dao.createTable(apresent.getTableName(), apresent.getKeys(), apresent.getTypes(),
				apresent.getUniques()));

		AODR aodr = new AODR();
		System.out.println(dao.createTable(aodr.getTableName(), aodr.getKeys(), aodr.getTypes(), aodr.getUniques()));

		AODRDT aodrdt = new AODRDT();
		System.out.println(
				dao.createTable(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getTypes(), aodrdt.getUniques()));

		AIO aio = new AIO();
		System.out.println(dao.createTable(aio.getTableName(), aio.getKeys(), aio.getTypes(), aio.getUniques()));

		AIODT aiodt = new AIODT();
		System.out
				.println(dao.createTable(aiodt.getTableName(), aiodt.getKeys(), aiodt.getTypes(), aiodt.getUniques()));

		AINVENTORY ainventory = new AINVENTORY();
		System.out.println(dao.createTable(ainventory.getTableName(), ainventory.getKeys(), ainventory.getTypes(),
				ainventory.getUniques()));

		AFAB afab = new AFAB();
		System.out.println(dao.createTable(afab.getTableName(), afab.getKeys(), afab.getTypes(), afab.getUniques()));

		ADEP adep = new ADEP();
		System.out.println(dao.createTable(adep.getTableName(), adep.getKeys(), adep.getTypes(), adep.getUniques()));

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
