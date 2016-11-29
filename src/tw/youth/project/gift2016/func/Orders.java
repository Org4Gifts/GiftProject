package tw.youth.project.gift2016.func;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import tw.youth.project.gift2016.consts.ConstValue;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

public class Orders {

	private AODR aodr;
	private AODRDT aodrdt;
	private AIO aio;
	private AIODT aiodt;
	private List<Object> list = new ArrayList<>();
	private StringBuilder msg = new StringBuilder();

	private static Set<String> orderNum = new TreeSet<>();
	private static Set<String> vhnoNum = new TreeSet<>();

	public Orders(DBManager dao) {
		// TODO Auto-generated constructor stub
		AODR aodr = new AODR();
		ArrayList<Object[]> arr = dao.query(aodr.getTableName(), aodr.getKeys()[1], "", aodr.getLength());
		for (Object[] objects : arr) {
			orderNum.add((String) objects[1]);
		}
		AIO aio = new AIO();
		arr = dao.query(aio.getTableName(), aio.getKeys()[1], "", aio.getLength());
		for (Object[] objects : arr) {
			vhnoNum.add((String) objects[1]);
		}
	}

//	public Object getOrderValue(AUSER user) {
//		return null;
//	}

	public String createOrders(AUSER user, String key, Object[][] objs) {

		if (key.equals("aodrdt")) {
			aodr = new AODR();
			Iterator<String> it = orderNum.iterator();
			String order = "";
			int num = 0;
			int compare = 0;
			if (it.hasNext()) {
				order = it.next().substring(0, 1);
				num = Integer.parseInt(it.next().substring(1));
			}
			compare = Integer.parseInt(ToolBox.getCurrentTimeStamp().replace("-", "").split(" ")[0] + "001");
			if (compare > num) {
				aodr.setOrder1(order + compare);
			} else {
				aodr.setOrder1(order + ++num);
			}
			for (Object[] obj : objs) {
				aodrdt = new AODRDT();
				aodrdt.setValues(obj);
				list.add(aodrdt);
			}

		} else if (key.equals("aiodt")) {
			aio = new AIO();
			Iterator<String> it = vhnoNum.iterator();
			String order = "";
			int num = 0;
			int compare = 0;
			if (it.hasNext()) {
				order = it.next().substring(0, 1);
				num = Integer.parseInt(it.next().substring(1));
			}
			compare = Integer.parseInt(ToolBox.getCurrentTimeStamp().replace("-", "").split(" ")[0] + "001");
			if (compare > num) {
				aio.setVhno(order + compare);
			} else {
				aio.setVhno(order + ++num);
			}
			aiodt = new AIODT();
			aiodt.setValues(objs);
		}

		return (aodrdt == null) && (aiodt == null) ? ConstValue.ORDERS_FAILURE
				: (aodrdt != null ? ConstValue.ORDERS_AODRDT_SUCCESS : ConstValue.ORDERS_AIODT_SUCCESS);
	}

	public String updateOrders(AUSER user, String key, Object obj) {
		
		return null;
	}

	public String submitOrders(DBManager dao, AUSER user, Object priObj, Object secObj) {
		if (msg.length() > 0)
			msg.delete(0, msg.length());

		if (priObj instanceof AODR) {
			aodr = (AODR) priObj;
			aodrdt = (AODRDT) secObj;
			msg.append(dao.insert(aodr.getTableName(), aodr.getKeys(), aodr.getValues())).append(" , ");
			msg.append(dao.insert(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValues())).append(".");

		}
		if (priObj instanceof AIODT) {
			aio = (AIO) priObj;
			aiodt = (AIODT) secObj;
			msg.append(dao.insert(aio.getTableName(), aio.getKeys(), aio.getValues())).append(" , ");
			msg.append(dao.insert(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValues())).append(".");
		}

		return msg.toString();
	}

	public String cancelOrders(AUSER user, String key) {
		return "";
	}

}
