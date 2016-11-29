package tw.youth.project.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tw.youth.project.gift2016.func.Orders;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.aodr.AODR;

public class TestOrders {

	@Test
	public void test() {
		// fail("Not yet implemented");
		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();
		AODR aodr = new AODR();
		Orders orders = new Orders(dao);
//		orders.createOrders(aodr);
	}

}
