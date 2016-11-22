package tw.youth.project.gift2016.func;

import java.util.ArrayList;

import tw.youth.project.gift2016.consts.ConstValue;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;

public class Login {
	private AUSER user;
	private boolean login = false;

	public Login(DBManager dao, String username, String passwd) {
		// 登入建構子 先查詢使用者帳號和密碼
		// TODO Auto-generated constructor stub
		if (!username.equals("")) {
			user = new AUSER();
			ArrayList<Object[]> chk = dao.query(user.getTableName(), user.getKeys()[2], username, user.getLength());
			for (Object[] objects : chk) {
				if (objects[2].equals(username)) {
					if (objects[3].equals(user.toMD5Pass(passwd))) {
						// 密碼也正確之後才將值儲存
						user.setValuesFull(objects);
						AEMP aemp = new AEMP();
						ArrayList<Object[]> arr = dao.query(aemp.getTableName(), aemp.getKeys()[1], user.getEmpno(),
								aemp.getLength());
						for (Object[] objects2 : arr) {
							aemp.setValuesFull(objects2);
							ADEP adep = new ADEP();
							ArrayList<Object[]> arr2 = dao.query(adep.getTableName(), adep.getKeys()[2], aemp.getDno(),
									aemp.getLength());
							for (Object[] objects3 : arr2) {
								adep.setValuesFull(objects3);
								user.setRole(adep.getRole());
								user.setAuthority(aemp.getAuthority());
							}
						}
						login = true;
					}
				}
			}
		}
		// 如果login不是true 則清空user類別
		if (!login)
			user = null;
	}

	public String checkLogin() {
		// 回傳是否登入成功
		return login ? ConstValue.LOGIN_SUCCESS : ConstValue.LOGIN_FAILURE;
	}

	public AUSER getUser() {
		// 取得使用者類別
		return user;
	}

	public String changPassword(DBManager dao, String oldPasswd, String newPasswd1, String newPasswd2) {
		// 修改密碼
		if (!login)
			return ConstValue.LOGIN_NOT_LOGIN;
		if (user.toMD5Pass(oldPasswd).equals(user.getPass())) {
			if (newPasswd1.equals(newPasswd2)) {
				user.setPass(user.toMD5Pass(newPasswd2));
				return dao.update(user.getTableName(), user.getKeys(), user.getValuesFull());
			} else {
				return ConstValue.LOGIN_NEW_PASS_ERROR;
			}
		} else {
			return ConstValue.LOGIN_OLD_PASS_ERROR;
		}
	}

	public String forgotPass(DBManager dao, String email) {
		// 忘記密碼的發送郵件功能 (未完成)
		AEMP aemp = new AEMP();
		ArrayList<Object[]> arr = dao.query(aemp.getTableName(), aemp.getKeys()[3], email, aemp.getLength());
		for (Object[] objects : arr) {
			if (objects[3].equals(email)) {
				AUSER user = new AUSER();
				ArrayList<Object[]> arr2 = dao.query(user.getTableName(), user.getKeys()[1], objects[1],
						user.getLength());
				for (Object[] objects2 : arr2) {
					if (objects[1].equals(objects2[1])) {
						user.setValuesFull(objects2);
					}
				}
				return ConstValue.LOGIN_SEND_EMAIL_SUCCESS;
			}
		}
		return ConstValue.LOGIN_CHECK_EMAIL_FAILURE;
	}

}
