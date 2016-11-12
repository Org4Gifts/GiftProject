package tw.youth.project.gift2016.sql.user;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class USER {

	private Integer _id = 0;
	private String empno = "";
	private String user = "";
	private String pass = "";

	private Timestamp  created;
	private Timestamp updated;

	private String[] keys = { "_id", "empno", "user", "pass" };
	private String[] types = { _id.getClass().getSimpleName(), empno.getClass().getSimpleName(),
			user.getClass().getSimpleName(), pass.getClass().getSimpleName() };
	private String[] uniques = { "empno", "user" };

	public String getTableName() {
		return getClass().getSimpleName().toLowerCase();
	}

	public String[] getKeys() {
		return keys;
	}

	public String[] getTypes() {
		return types;
	}

	public String[] getUniques() {
		return uniques;
	}

	public int getLength() {
		return getKeys().length + 2;
	}

	// 轉換成md5

	public String toMD5Pass(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(source.getBytes("UTF-8"));
			return getString(bytes);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private String getString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			String hex = Integer.toHexString((int) 0x00FF & b);
			if (hex.length() == 1) {
				sb.append("0");
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	// 以下是儲存的值

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public void setValues(Object[] values) {
		int i = 0;
		setEmpno((String) values[i++]);
		setUser((String) values[i++]);
		setPass((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setEmpno((String) values[i++]);
		setUser((String) values[i++]);
		setPass((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getEmpno(), getUser(), getPass() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getEmpno(), getUser(), getPass(), getCreated(), getUpdated() };
	}

}
