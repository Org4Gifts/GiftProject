package tw.youth.project.gift2016.func;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import tw.youth.project.gift2016.consts.ConstValue;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

public class Orders {

	private AODR aodr;
	private AODRDT aodrdt;
	private AIO aio;
	private AIODT aiodt;
	private ArrayList<ADEP> adeps;
	private ArrayList<AEMP> signatures;
	private StringBuilder msg = new StringBuilder();

	private static Set<String> orderNum = new TreeSet<>();
	private static Set<String> vhnoNum = new TreeSet<>();

	public Orders(DBManager dao) {
		// TODO Auto-generated constructor stub
		// 先取得資料庫上所有訂單/調撥單的編號
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
		queryADEP(dao);
	}

	public ArrayList<AEMP> getSignatures(DBManager dao, AUSER user) {
		if (signatures == null)
			querySignatures(dao, user);
		return signatures;
	}

	public void queryADEP(DBManager dao) {
		adeps = new ArrayList<>();
		ADEP adep = new ADEP();
		ArrayList<Object[]> arr = dao.query(adep.getTableName(), adep.getKeys()[1], "", adep.getLength());
		for (Object[] objects : arr) {
			adep.setValuesFull(objects);
			adeps.add(adep);
			adep = new ADEP();
		}
	}

	public void querySignatures(DBManager dao, AUSER user) {
		AEMP aemp = new AEMP();
		ArrayList<Object[]> aemps = dao.query(aemp.getTableName(), aemp.getKeys()[1], "", aemp.getLength());
		signatures = new ArrayList<>();
		for (Object[] objects : aemps) {
			aemp.setValuesFull(objects);
			if (user.getAuthority() > 0 ? user.getAuthority() <= aemp.getAuthority()
					: user.getAuthority() < aemp.getAuthority()) {
				signatures.add(aemp);
			}
			aemp = new AEMP();
		}
	}

	public <T> String createOrders(DBManager dao, AUSER user, Object priObj, ArrayList<T> secObjs) {
		// 建立訂單/調撥單
		if (msg.length() > 0)
			msg.delete(0, msg.length());

		aodr = null;
		aodrdt = null;
		aio = null;
		aiodt = null;

		if (priObj instanceof AODR) {
			aodr = (AODR) priObj;
			Iterator<String> it = orderNum.iterator();
			String order = "A";
			int num = 0;
			int compare = 0;
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
			msg.append(dao.insert(aodr.getTableName(), aodr.getKeys(), aodr.getValues())).append(" , ");
			if (!msg.toString().contains("error")) {
				for (Object secObj : secObjs) {
					aodrdt = (AODRDT) secObj;
					aodrdt.setOrder1(aodr.getOrder1());
					msg.append(dao.insert(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValues())).append(" , ");
				}
			}
		}

		if (priObj instanceof AIO) {
			aio = (AIO) priObj;
			Iterator<String> it = vhnoNum.iterator();
			String order = "B";
			int num = 0;
			int compare = 0;
			if (it.hasNext()) {
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
			msg.append(dao.insert(aio.getTableName(), aio.getKeys(), aio.getValues())).append(" , ");
			if (!msg.toString().contains("error")) {
				for (Object secObj : secObjs) {
					aiodt = (AIODT) secObj;
					aiodt.setVhno(aio.getVhno());
					msg.append(dao.insert(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValues())).append(" , ");
				}
			}
		}
		if (msg.toString().contains("error"))
			return ConstValue.ORDERS_ADD_FAILURE + "\n" + msg.toString();

		return aodrdt != null ? ConstValue.ORDERS_ADD_AODR_SUCCESS : ConstValue.ORDERS_ADD_AIO_SUCCESS;
	}

	public <T> String updateOrders(DBManager dao, AUSER user, Object priObj, ArrayList<T> secObjs) {
		// 更新訂單/調撥單
		if (msg.length() > 0)
			msg.delete(0, msg.length());

		if (priObj instanceof AODR) {
			aodr = (AODR) priObj;
			msg.append(dao.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull())).append(" , ");
			for (Object secObj : secObjs) {
				aodrdt = (AODRDT) secObj;
				if (aodrdt.get_id() == 0)
					msg.append(dao.insert(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValues())).append(" , ");
				else
					msg.append(dao.update(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValuesFull()))
							.append(" , ");
			}
		}
		if (priObj instanceof AIO) {
			aio = (AIO) priObj;
			msg.append(dao.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull())).append(" , ");
			for (Object secObj : secObjs) {
				aiodt = (AIODT) secObj;
				if (aiodt.get_id() == 0)
					msg.append(dao.insert(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValues())).append(" , ");
				else
					msg.append(dao.update(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValuesFull())).append(" , ");
			}
		}
		if (msg.toString().contains("error"))
			return ConstValue.ORDERS_UPDATE_FAILURE + "\n" + msg.toString();

		return priObj instanceof AODR ? ConstValue.ORDERS_UPDATE_AODR_SUCCESS : ConstValue.ORDERS_UPDATE_AIO_SUCCESS;
	}

	public <T> String submitOrders(DBManager dao, AUSER user, Object priObj, ArrayList<T> secObj) {
		// 送出訂單/調撥單
		if (msg.length() > 0)
			msg.delete(0, msg.length());

		if (priObj instanceof AODR) {
			aodr = (AODR) priObj;
			msg.append(dao.insert(aodr.getTableName(), aodr.getKeys(), aodr.getValues())).append(" , ");
			for (Object object : secObj) {
				aodrdt = (AODRDT) object;
				msg.append(dao.insert(aodrdt.getTableName(), aodrdt.getKeys(), aodrdt.getValues())).append(" , ");
			}

		}
		if (priObj instanceof AIO) {
			aio = (AIO) priObj;
			msg.append(dao.insert(aio.getTableName(), aio.getKeys(), aio.getValues())).append(" , ");
			for (Object object : secObj) {
				aiodt = (AIODT) object;
				msg.append(dao.insert(aiodt.getTableName(), aiodt.getKeys(), aiodt.getValues())).append(" , ");
			}
		}
		if (msg.toString().contains("error"))
			return ConstValue.ORDERS_SUBMIT_FAILURE + "\n" + msg.toString();

		return priObj instanceof AODR ? ConstValue.ORDERS_SUBMIT_AODR_SUCCESS : ConstValue.ORDERS_SUBMIT_AIO_SUCCESS;
	}

	public String cancelOrders(DBManager dao, AUSER user, Object priObj) {
		if (msg.length() > 0)
			msg.delete(0, msg.length());

		if (priObj instanceof AODR) {
			aodr = (AODR) priObj;
			msg.append(dao.drop(aodr.getTableName(), aodr.getKeys()[0], aodr.get_id())).append(" , ");
			if (aodrdt != null)
				msg.append(dao.drop(aodrdt.getTableName(), aodr.getKeys()[1], aodr.getOrder1())).append(" , ");
			else
				msg.append(dao.drop(new AODRDT().getTableName(), aodr.getKeys()[1], aodr.getOrder1())).append(" , ");
		}
		if (priObj instanceof AIO) {
			aio = (AIO) priObj;
			msg.append(dao.drop(aio.getTableName(), aio.getKeys()[0], aio.get_id())).append(" , ");
			if (aiodt != null)
				msg.append(dao.drop(aiodt.getTableName(), aio.getKeys()[1], aio.getVhno())).append(" , ");
			else
				msg.append(dao.drop(new AIODT().getTableName(), aio.getKeys()[1], aio.getVhno())).append(" , ");
		}

		if (msg.toString().contains("error"))
			return ConstValue.ORDERS_CANCEL_FAILURE + "\n" + msg.toString();

		return priObj instanceof AODR ? ConstValue.ORDERS_CANCEL_AODR_SUCCESS : ConstValue.ORDERS_CANCEL_AIO_SUCCESS;
	}

}
