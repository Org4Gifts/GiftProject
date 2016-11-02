package tw.youth.project.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import tw.youth.project.gift2016.sql.MySqlCmd;
import tw.youth.project.gift2016.sql.OperDAO;
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

public class TestCase {

	// @Test
	public void testSQL() throws SQLException { // 測試OK
		OperDAO dao = new OperDAO("jdbc:mysql://localhost:3306/", "odise", "116025");
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
	public void testTableExist() { //測試OK
		OperDAO dao = new OperDAO("jdbc:mysql://localhost:3306/", "odise", "116025");
		dao.starup();
		Boolean[] chk = dao.chkTableExist();
		for (Boolean boolean1 : chk) {
			System.out.println(boolean1);
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

	@Test
	public void testCreateTable() { // 測試OK，尚待進一步檢查

		USER user = new USER();
		System.out.println(MySqlCmd.createTable(user.getTableName(), user.getValues(), user.getTypes(),
				user.getPrimary(), user.getUnique()));

		AEMP aemp = new AEMP();
		System.out.println(MySqlCmd.createTable(aemp.getTableName(), aemp.getValues(), aemp.getTypes(),
				aemp.getPrimary(), aemp.getUnique()));

		AVDR avdr = new AVDR();
		System.out.println(MySqlCmd.createTable(avdr.getTableName(), avdr.getValues(), avdr.getTypes(),
				avdr.getPrimary(), avdr.getUnique()));

		AQTY aqty = new AQTY();
		System.out.println(MySqlCmd.createTable(aqty.getTableName(), aqty.getValues(), aqty.getTypes(),
				aqty.getPrimary(), aqty.getUnique()));
		
		APRESENT apresent = new APRESENT();
		System.out.println(MySqlCmd.createTable(apresent.getTableName(), apresent.getValues(), apresent.getTypes(),
				apresent.getPrimary(), apresent.getUnique()));

		AODR aodr = new AODR();
		System.out.println(MySqlCmd.createTable(aodr.getTableName(), aodr.getValues(), aodr.getTypes(),
				aodr.getPrimary(), aodr.getUnique()));

		AODRDT aodrdt = new AODRDT();
		System.out.println(MySqlCmd.createTable(aodrdt.getTableName(), aodrdt.getValues(), aodrdt.getTypes(),
				aodrdt.getPrimary(), aodrdt.getUnique()));

		AIO aio = new AIO();
		System.out.println(MySqlCmd.createTable(aio.getTableName(), aio.getValues(), aio.getTypes(), aio.getPrimary(),
				aio.getUnique()));

		AIODT aiodt = new AIODT();
		System.out.println(MySqlCmd.createTable(aiodt.getTableName(), aiodt.getValues(), aiodt.getTypes(),
				aiodt.getPrimary(), aiodt.getUnique()));

		AINVENTORY ainventory = new AINVENTORY();
		System.out.println(MySqlCmd.createTable(ainventory.getTableName(), ainventory.getValues(),
				ainventory.getTypes(), ainventory.getPrimary(), ainventory.getUnique()));

		AFAB afab = new AFAB();
		System.out.println(MySqlCmd.createTable(afab.getTableName(), afab.getValues(), afab.getTypes(),
				afab.getPrimary(), afab.getUnique()));
		
		ADEP adep = new ADEP();
		System.out.println(MySqlCmd.createTable(adep.getTableName(), adep.getValues(), adep.getTypes(),
				adep.getPrimary(), adep.getUnique()));
		
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

}
