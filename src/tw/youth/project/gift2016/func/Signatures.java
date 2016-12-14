package tw.youth.project.gift2016.func;

import java.util.ArrayList;

import tw.youth.project.gift2016.consts.ConstValue;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.asignlog.ASIGNLOG;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

public class Signatures {

	private StringBuilder sb = new StringBuilder();

	// 取得單筆訂單/調撥單
	public Object getOrder(DBManager manager, AUSER user, String key, String orderNum) {
		// 特殊查詢 查詢使用者
		if (user != null && !user.getEname().equals("")) {
			if (key.equals("aodr")) {
				AODR aodr = new AODR();
				for (Object[] objs : manager.query(aodr.getTableName(), aodr.getKeys()[1], orderNum,
						aodr.getLength())) {
					aodr.setValuesFull(objs);
					System.out.println(aodr.getOrder1() + " ; " + orderNum);
					if (aodr.getOrder1().equals(orderNum))
						return aodr;
				}
			}

			if (key.equals("aio")) {
				AIO aio = new AIO();
				for (Object[] objs : manager.query(aio.getTableName(), aio.getKeys()[1], orderNum, aio.getLength())) {
					System.out.println(aio.getVhno() + " ; " + orderNum);
					if (aio.getVhno().equals(orderNum))
						return aio;
				}

			}
		}
		return null;
	}

	// 取得多筆訂單/調撥單
	public ArrayList<Object> getOrders(DBManager manager, AUSER user, String key) {
		if (user != null && !user.getEname().equals("")) {
			// Querys query = new Querys(user);
			ArrayList<Object> arr = new ArrayList<>();
			if (key.equals("aodr")) {
				AODR aodr = new AODR();
				for (Object[] arr2 : manager.query(aodr.getTableName(), aodr.getKeys()[11], user.getEmpno(),
						aodr.getLength())) {
					aodr.setValuesFull(arr2);
					arr.add(aodr);
					aodr = new AODR();
				}
				return arr;
			}

			if (key.equals("aio")) {
				AIO aio = new AIO();
				for (Object[] arr2 : manager.query(aio.getTableName(), aio.getKeys()[14], user.getEmpno(),
						aio.getLength())) {
					aio.setValuesFull(arr2);
					arr.add(aio);
					aio = new AIO();
				}
				return arr;
			}
		}
		return null;
	}
	
	// 查詢廠區管理部門簽核者
	public String getLastSigner(DBManager manager, AUSER user,String key){
		ADEP adep = new ADEP();
		for(Object[] obj : manager.query(adep.getTableName(), adep.getKeys()[4], user.getFno(), adep.getLength())){
			adep.setValuesFull(obj);
			AEMP aemp = new AEMP();
			for (Object[] objs : manager.query(aemp.getTableName(), aemp.getKeys()[4], user.getFno(), adep.getLength())) {
				
			}
		}
		return "";
		
	}

	// 同意訂/調撥單
	public <T> String approveOrder(DBManager manager, AUSER user, T obj) {
		if (user != null && !user.getEname().equals(""))
			return orderFunc(manager, user, obj, ConstValue.SIGNATURE_STATUS_APPROVE);

		return ConstValue.LOGIN_NOT_LOGIN;
	}

	// 拒絕訂/調撥單
	public <T> String rejectOrder(DBManager manager, AUSER user, T obj) {
		if (user != null && !user.getEname().equals(""))
			return orderFunc(manager, user, obj, ConstValue.SIGNATURE_STATUS_REJECT);

		return ConstValue.LOGIN_NOT_LOGIN;
	}

	// 完成訂/調撥單 role=3的管理部門使用，還有更新庫存沒做
	public <T> String completeOrder(DBManager manager, AUSER user, T obj) {
		if (user != null && !user.getEname().equals("")) {
			if (user.getRole() == 3)
				return orderFunc(manager, user, obj, ConstValue.SIGNATURE_STATUS_COMPLETE);
			else
				return ConstValue.PERMISSION_NOT_ENOUGH;
		}
		return ConstValue.LOGIN_NOT_LOGIN;
	}

	// 作廢訂/調撥單 role=3的管理部門使用，還有更新庫存沒做
	public <T> String obsoleteOrder(DBManager manager, AUSER user, T obj) {
		if (user != null && !user.getEname().equals("")) {
			if (user.getRole() == 3) {
				if (sb.length() > 0)
					sb.delete(0, sb.length());
				if (obj instanceof AODR) {
					AODR aodr = (AODR) obj;
					aodr.setSignerno(user.getEmpno());
					aodr.setStatus(ConstValue.ORDERS_STATUS_OBSOLETED);
					return manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull());
				} else {
					AIO aio = (AIO) obj;
					aio.setSignerno(user.getEmpno());
					aio.setStatus(ConstValue.ORDERS_STATUS_OBSOLETED);
					return manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull());
				}
			} else
				return ConstValue.PERMISSION_NOT_ENOUGH;
		}
		return ConstValue.LOGIN_NOT_LOGIN;
	}

	// 訂/調撥單功能
	public <T> String orderFunc(DBManager manager, AUSER user, T obj, String cmd) {
		if (user != null && !user.getEname().equals("")) {
			if (sb.length() > 0)
				sb.delete(0, sb.length());
			ASIGNLOG signlog = new ASIGNLOG();
			Object[] objs;
			if (obj instanceof AODR) {
				AODR aodr = (AODR) obj;
				if(!user.getMgr().equals("P0001"))
					aodr.setSignerno(user.getMgr());
				else{
					aodr.setSignerno("P0016");
				}
				// 特殊查詢功能
				ArrayList<Object[]> arr = manager.query(signlog.getTableName(), signlog.getKeys()[1], aodr.getOrder1(),
						signlog.getLength());
				signlog.setValuesFull(arr.get(arr.size() - 1));

				objs = new Object[] { aodr.getOrder1(), user.getEmpno(), user.getEname(), user.getDno(),
						((float) (ToolBox.getCurrentTimestamp().getTime() - signlog.getCreated().getTime()))
								/ (60 * 60 * 1000),
						cmd };
				objs[objs.length - 1] = (float) objs[objs.length - 1] < 0.0f ? 0 : objs[objs.length - 1];
				signlog.setValues(objs);
				sb.append(manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull())).append("\n");
				sb.append(manager.insert(signlog.getTableName(), signlog.getKeys(), signlog.getValues())).append("\n");
				return sb.toString();
			} else {
				AIO aio = (AIO) obj;
				if(!user.getMgr().equals("P0001"))
					aio.setSignerno(user.getMgr());
				else{
					aio.setSignerno("P0016");
				}
				
				// 特殊查詢功能
				ArrayList<Object[]> arr = manager.query(signlog.getTableName(), signlog.getKeys()[1], aio.getVhno(),
						signlog.getLength());
				signlog.setValuesFull(arr.get(arr.size() - 1));
				objs = new Object[] { aio.getVhno(), user.getEmpno(), user.getEname(), user.getDno(),
						((float) (ToolBox.getCurrentTimestamp().getTime() - signlog.getCreated().getTime()))
								/ (60 * 60 * 1000),
						cmd };
				objs[objs.length - 1] = (float) objs[objs.length - 1] < 0.0f ? 0 : objs[objs.length - 1];
				signlog.setValues(objs);
				sb.append(manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull())).append("\n");
				sb.append(manager.insert(signlog.getTableName(), signlog.getKeys(), signlog.getValues())).append("\n");
				return sb.toString();
			}
		}
		return ConstValue.LOGIN_NOT_LOGIN;
	}

}
