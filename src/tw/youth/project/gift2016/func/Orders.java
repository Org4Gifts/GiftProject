package tw.youth.project.gift2016.func;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import tw.youth.project.gift2016.consts.ConstValue;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.asignlog.ASIGNLOG;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

public class Orders {

	private AODR aodr;
	private AODRDT aodrdt;
	private AIO aio;
	private AIODT aiodt;
	private ArrayList<Object> orderList;
	private ArrayList<ADEP> adeps;
	private static Map<String, AEMP> signatures;
	private ASIGNLOG asignlog;
	private StringBuilder msg = new StringBuilder();

	// private static String orderNum = "";
	// private static String vhnoNum = "";
	private static Set<String> orderNum;
	private static Set<String> vhnoNum;

	public Orders(DBManager manager, AUSER user) {
		// TODO Auto-generated constructor stub
		// 先取得資料庫上所有訂單/調撥單的編號
		ArrayList<Object[]> arr = null;
		if (orderNum == null) {
			orderNum = new TreeSet<>();
			AODR aodr = new AODR();
			arr = manager.query(aodr.getTableName(), aodr.getKeys()[1], "", aodr.getLength());
			for (Object[] objects : arr) {
				orderNum.add((String) objects[1]);
			}
		}
		// orderNum = (String) arr.get(arr.size() - 1)[1];
		if (vhnoNum == null) {
			vhnoNum = new TreeSet<>();
			AIO aio = new AIO();
			arr = manager.query(aio.getTableName(), aio.getKeys()[1], "", aio.getLength());
			for (Object[] objects : arr) {
				vhnoNum.add((String) objects[1]);
			}
		}

		// vhnoNum = (String) arr.get(arr.size() - 1)[1];
		// queryADEP(dao);
		getSigners(manager, user);
	}

	private Map<String, AEMP> getSigners(DBManager manager, AUSER user) {
		if (signatures == null)
			querySigners(manager, user);
		return signatures;
	}

	// 查詢部門
	private void queryADEP(DBManager manager) {
		adeps = new ArrayList<>();
		ADEP adep = new ADEP();
		ArrayList<Object[]> arr = manager.query(adep.getTableName(), adep.getKeys()[1], "", adep.getLength());
		for (Object[] objects : arr) {
			adep.setValuesFull(objects);
			adeps.add(adep);
			adep = new ADEP();
		}
	}

	// 查詢訂/調撥單
	public <T> ArrayList<Object> queryOrders(DBManager manager, AUSER user, String key) {
		orderList = new ArrayList<>();
		ArrayList<Object[]> arr = null;
		if (key.equals("aodr")) {
			aodr = new AODR();
			arr = manager.query(aodr.getTableName(), aodr.getKeys()[2], user.getEmpno(), aodr.getLength());
		}
		if (key.equals("aio")) {
			aio = new AIO();
			arr = manager.query(aio.getTableName(), aio.getKeys()[2], user.getEmpno(), aio.getLength());
		}

		for (Object[] objects : arr) {
			if (key.equals("aodr")) {
				aodr = new AODR();
				aodr.setValuesFull(objects);
				orderList.add(aodr);
			} else {

				aio = new AIO();
				aio.setValuesFull(objects);
				orderList.add(aio);
			}
		}
		return orderList;
	}

	// 查詢所有簽核人員
	private void querySigners(DBManager manager, AUSER user) {
		AEMP aemp = new AEMP();
		ArrayList<Object[]> aemps = manager.query(aemp.getTableName(), aemp.getKeys()[1], "", aemp.getLength());
		signatures = new HashMap<>();
		for (Object[] objects : aemps) {
			aemp.setValuesFull(objects);
			if (user.getAuthority() > 0 ? user.getAuthority() <= aemp.getAuthority()
					: user.getAuthority() < aemp.getAuthority()) {
				signatures.put(aemp.getEmpno(), aemp);
			}
			aemp = new AEMP();
		}
	}

	public synchronized <T> String createOrders(DBManager manager, AUSER user, Object priObj, ArrayList<T> secObjs) {
		// 建立訂單/調撥單
		// 因為orderNum跟編號的關係，所以使用 synchronized 鎖定建立訂單的功能
		if (msg.length() > 0)
			msg.delete(0, msg.length());

		aodr = null;
		aodrdt = null;
		aio = null;
		aiodt = null;
		StringBuilder sb = new StringBuilder();
		String tempKey;
		String order = "A";
		int num = 0;
		int compare = 0;
		float total = 0.0f;

		if (priObj instanceof AODR) {
			aodr = (AODR) priObj;
			Iterator<String> it = orderNum.iterator();

			while (it.hasNext()) {
				String str = it.next();
				order = str.substring(0, 1);
				num = Integer.parseInt(str.substring(1));
			}
			compare = Integer
					.parseInt(ToolBox.getStrCurrentTimestamp().replace("-", "").split(" ")[0].substring(0, 6) + "001");
			if (compare > num) {
				aodr.setOrder1(order + compare);
			} else {
				aodr.setOrder1(order + ++num);
			}

			orderNum.add(aodr.getOrder1());

			tempKey = aodr.getSignerno();

			while (!tempKey.equals("P0001")) {
				// 設定查到董事長的工號就停止
				AEMP aemp = signatures.get(tempKey);
				sb.append(aemp.getEmpno()).append("_").append(aemp.getEname()).append("^");
				tempKey = aemp.getMgr();
			}

			if (sb.toString().substring(sb.length() - 1).equals("^"))
				sb.replace(sb.length() - 1, sb.length(), "");
			aodr.setSignerlist(sb.toString());
			aodr.setStatus(ConstValue.ORDERS_STATUS_PREPARING);

			for (T t : secObjs) {
				total += ((AODRDT) t).getPrc() * ((AODRDT) t).getQty();
			}
			aodr.setTamt(total);

			msg.append(manager.insert(aodr.getTableName(), aodr.getKeys(), aodr.getValues())).append(" , ");
			if (!msg.toString().contains("error")) {
				for (Object secObj : secObjs) {
					aodrdt = (AODRDT) secObj;
					aodrdt.setOrder1(aodr.getOrder1());
					msg.append(manager.insert(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValues()))
							.append(" , ");
				}
			}
		}

		if (priObj instanceof AIO) {
			aio = (AIO) priObj;
			Iterator<String> it = vhnoNum.iterator();
			order = "B";
			while (it.hasNext()) {
				String str = it.next();
				order = str.substring(0, 1);
				num = Integer.parseInt(str.substring(1));
			}
			compare = Integer
					.parseInt(ToolBox.getStrCurrentTimestamp().replace("-", "").split(" ")[0].substring(0, 6) + "001");
			if (compare > num) {
				aio.setVhno(order + compare);
			} else {
				aio.setVhno(order + ++num);
			}

			orderNum.add(aio.getVhno());

			tempKey = aio.getSignerno();

			while (!tempKey.equals("P0001")) {
				// 設定查到董事長的工號就停止
				AEMP aemp = signatures.get(tempKey);
				sb.append(aemp.getEmpno()).append("_").append(aemp.getEname()).append("^");
				tempKey = aemp.getMgr();
			}
			if (sb.toString().substring(sb.length() - 1).equals("^"))
				sb.replace(sb.length() - 1, sb.length(), "");

			aio.setSignerlist(sb.toString());

			for (T t : secObjs) {
				total += ((AIODT) t).getPrc() * ((AIODT) t).getQty();
			}
			aio.setTamt(total);

			msg.append(manager.insert(aio.getTableName(), aio.getKeys(), aio.getValues())).append(" , ");
			if (!msg.toString().contains("error")) {
				for (Object secObj : secObjs) {
					aiodt = (AIODT) secObj;
					aiodt.setVhno(aio.getVhno());
					msg.append(manager.insert(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValues())).append(" , ");
				}
			}
		}
		if (msg.toString().contains("error"))
			return ConstValue.ORDERS_ADD_FAILURE + "\n" + msg.toString();

		return aodrdt != null ? ConstValue.ORDERS_ADD_AODR_SUCCESS : ConstValue.ORDERS_ADD_AIO_SUCCESS;
	}

	public <T> String updateOrders(DBManager manager, AUSER user, Object priObj, ArrayList<T> secObjs) {
		// 更新訂單/調撥單
		if (msg.length() > 0)
			msg.delete(0, msg.length());

		float total = 0.0f;

		if (priObj instanceof AODR) {
			for (Object secObj : secObjs) {
				aodrdt = (AODRDT) secObj;
				if (aodrdt.getAodrdt_id() == 0)
					msg.append(manager.insert(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValues()))
							.append(" , ");
				else
					msg.append(manager.update(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValuesFull()))
							.append(" , ");
				total += aodrdt.getPrc() * aodrdt.getQty();
			}
			aodr = (AODR) priObj;
			aodr.setTamt(total);
			msg.append(manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull())).append(" , ");
		}
		if (priObj instanceof AIO) {
			for (Object secObj : secObjs) {
				aiodt = (AIODT) secObj;
				if (aiodt.getAiodt_id() == 0)
					msg.append(manager.insert(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValues())).append(" , ");
				else
					msg.append(manager.update(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValuesFull()))
							.append(" , ");
				total += aiodt.getPrc() * aiodt.getQty();
			}
			aio = (AIO) priObj;
			aio.setTamt(total);
			msg.append(manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull())).append(" , ");
		}
		if (msg.toString().contains("error"))
			return ConstValue.ORDERS_UPDATE_FAILURE + "\n" + msg.toString();

		return priObj instanceof AODR ? ConstValue.ORDERS_UPDATE_AODR_SUCCESS : ConstValue.ORDERS_UPDATE_AIO_SUCCESS;
	}

	public String delOrderdt(DBManager manager, AUSER user, Object priObj) {
		// 刪除訂/調撥單副檔
		float tempCount = 0.0f;
		if (msg.length() > 0)
			msg.delete(0, msg.length());

		if (priObj instanceof AODRDT) {
			aodrdt = (AODRDT) priObj;
			msg.append(manager.drop(aodrdt.getTableName(), aodrdt.getKeys()[0], aodrdt.getAodrdt_id())).append("\n");
			aodr = new AODR();
			for (Object[] dts : manager.query(aodrdt.getTableName(), aodrdt.getKeys()[1], aodrdt.getOrder1(),
					aodrdt.getLength())) {
				aodrdt.setValuesFull(dts);
				tempCount += aodrdt.getQty() * aodrdt.getPrc();
			}
			aodr.setValuesFull(
					manager.query(aodr.getTableName(), aodr.getKeys()[1], aodrdt.getOrder1(), aodr.getLength()).get(0));
			System.out.println(aodr.getOrder1());
			aodr.setTamt(tempCount);
			msg.append(manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull()));
			
		}

		if (priObj instanceof AIODT) {
			aiodt = (AIODT) priObj;
			msg.append(manager.drop(aiodt.getTableName(), aiodt.getKeys()[0], aiodt.getAiodt_id())).append("\n");
			aio = new AIO();
			for (Object[] dts : manager.query(aiodt.getTableName(), aiodt.getKeys()[1], aiodt.getVhno(),
					aiodt.getLength())) {
				aiodt.setValuesFull(dts);
				tempCount += aiodt.getQty() * aiodt.getPrc();
			}
			aio.setValuesFull(
					manager.query(aio.getTableName(), aio.getKeys()[1], aio.getVhno(), aio.getLength()).get(0));
			System.out.println(aio.getVhno());
			aio.setTamt(tempCount);
			msg.append(manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull()));
		}

		if (msg.toString().contains("error"))
			return ConstValue.ORDERS_DROP_FAILURE + "\n" + msg.toString();

		return priObj instanceof AODR ? ConstValue.ORDERS_DROP_AODRDT_SUCCESS : ConstValue.ORDERS_DROP_AIODT_SUCCESS;
	}

	// 取消訂單/調撥單 使用更新方式替換Status訊息
	public String cancelOrders(DBManager manager, AUSER user, Object priObj) {
		if (msg.length() > 0)
			msg.delete(0, msg.length());

		if (priObj instanceof AODR) {
			aodr = (AODR) priObj;
			aodr.setStatus(ConstValue.ORDERS_STATUS_CANCEL);
			msg.append(manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull()));
			// msg.append(manager.drop(aodr.getTableName(), aodr.getKeys()[0],
			// aodr.getAodr_id())).append(" , ");
			// if (aodrdt != null)
			// msg.append(manager.drop(aodrdt.getTableName(), aodr.getKeys()[1],
			// aodr.getOrder1())).append(" , ");
			// else
			// msg.append(manager.drop(new AODRDT().getTableName(),
			// aodr.getKeys()[1],
			// aodr.getOrder1())).append(" , ");
		}

		if (priObj instanceof AIO) {
			aio = (AIO) priObj;
			aio.setStatus(ConstValue.ORDERS_STATUS_CANCEL);
			msg.append(manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull()));
			// msg.append(manager.drop(aio.getTableName(), aio.getKeys()[0],
			// aio.getAio_id())).append(" , ");

			// if (aiodt != null)
			// msg.append(manager.drop(aiodt.getTableName(), aio.getKeys()[1],
			// aio.getVhno())).append(" , ");
			// else
			// msg.append(manager.drop(new AIODT().getTableName(),
			// aio.getKeys()[1],
			// aio.getVhno())).append(" , ");

		}

		if (msg.toString().contains("error"))
			return ConstValue.ORDERS_CANCEL_FAILURE + "\n" + msg.toString();

		return priObj instanceof AODR ? ConstValue.ORDERS_CANCEL_AODR_SUCCESS : ConstValue.ORDERS_CANCEL_AIO_SUCCESS;
	}

	public <T> String submitOrders(DBManager manager, AUSER user, T priObj) {
		// 送出訂單/調撥單
		if (msg.length() > 0)
			msg.delete(0, msg.length());
		asignlog = new ASIGNLOG();

		if (priObj instanceof AODR) {
			aodr = (AODR) priObj;
			aodr.setStatus(ConstValue.ORDERS_STATUS_PROCESSING);
			msg.append(manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull())).append(" , ");
			asignlog.setValues(
					new Object[] { aodr.getOrder1(), user.getEmpno(), user.getEname(), user.getDno(), 0.0f, "Send" });
			msg.append(manager.insert(asignlog.getTableName(), asignlog.getKeys(), asignlog.getValues()));
		}
		if (priObj instanceof AIO) {
			aio = (AIO) priObj;
			aio.setStatus(ConstValue.ORDERS_STATUS_PROCESSING);
			msg.append(manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull())).append(" , ");
			asignlog.setValues(
					new Object[] { aio.getVhno(), user.getEmpno(), user.getEname(), user.getDno(), 0.0f, "Send" });
			msg.append(manager.insert(asignlog.getTableName(), asignlog.getKeys(), asignlog.getValues()));
		}
		if (msg.toString().contains("error"))
			return ConstValue.ORDERS_SUBMIT_FAILURE + "\n" + msg.toString();

		return priObj instanceof AODR ? ConstValue.ORDERS_SUBMIT_AODR_SUCCESS : ConstValue.ORDERS_SUBMIT_AIO_SUCCESS;
	}

}
