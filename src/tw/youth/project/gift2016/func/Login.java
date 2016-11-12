package tw.youth.project.gift2016.func;

import java.util.ArrayList;

import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.user.USER;

public class Login {
	USER user;

	public Login(DBManager dao, String username, String password) {
		// TODO Auto-generated constructor stub
		user = new USER();
		ArrayList<Object[]> chk = dao.query(user.getTableName(), user.getKeys()[2], username,
				user.getKeys().length + 2);
		for (Object[] objects : chk) {
			if (objects[3].equals(user.toMD5Pass(password))) {
				user.setValues(objects);
			}
		}
	}

	public String changPassword(DBManager dao, String oldPasswd, String newPasswd1, String newPasswd2) {
		if (newPasswd1.equals(newPasswd2)) {
			if (user.toMD5Pass(oldPasswd).equals(user.getPass())) {
				user.setPass(user.toMD5Pass(newPasswd2));
				return dao.update(user.getTableName(), user.getKeys(), user.getValues());
			} else {
				return "Old password isn't correct";
			}
		} else {
			return "New password isn't correct";
		}

	}

}
