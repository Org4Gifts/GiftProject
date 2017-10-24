package tw.youth.project.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.Test;

import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.aio.AIO;

class Tests {

	@Test
	void test() {
//		Timestamp odate = new Timestamp(100000L);
//		System.out.println(odate.getClass().getSimpleName());
//		
		System.out.println("testCreateTable : " + new Date(System.currentTimeMillis()).toString());
		DBManager dao = new DBManager(SQLCmd.DB_URL, SQLCmd.DB_NAME, SQLCmd.DB_USER, SQLCmd.DB_PASS);
		dao.starup();

		String table;
		try {
		AIO aio = new AIO();
		System.out.println("Start "+aio.getClass().getSimpleName());
		table = dao.createTable(aio.getTableName(), aio.getKeys(), aio.getTypes(), aio.getUniques());
		System.out.println(table);
		System.out.println(aio.getTableName() + " " + dao.getConn().prepareStatement(table).execute());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in "+e.getMessage());
		}
		dao.close();
		System.out.println("Test done.");
	}

}
