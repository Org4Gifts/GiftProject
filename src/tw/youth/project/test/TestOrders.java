package tw.youth.project.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tw.youth.project.gift2016.func.Orders;
import tw.youth.project.gift2016.sql.aodr.AODR;

public class TestOrders {

	@Test
	public void test() {
		// fail("Not yet implemented");
		AODR aodr = new AODR();
		Orders orders = new Orders();
		orders.createOrders(aodr);
	}

}
