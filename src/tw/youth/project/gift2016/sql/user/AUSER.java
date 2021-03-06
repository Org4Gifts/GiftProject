package tw.youth.project.gift2016.sql.user;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class AUSER {

	private Integer auser_id = 0;
	private String empno = "";
	// 員工編號
	private String user = "";
	// 使用者名稱
	private String pass = "";
	// 使用者密碼
	private Timestamp created;
	private Timestamp updated;

	// 額外需求部分

	private String ename = "";
	// 中文姓名
	private Short authority = 0;
	// 權限
	private String mgr = "";
	// 直屬主管工號
	private String dno = "";
	// 部門代碼 4碼
	private String fno = "";
	// 廠區代碼 2碼
	private Short role;
	// 部門權限

	private String[] keys = { "auser_id", "empno", "user", "pass" };
	private String[] types = { auser_id.getClass().getSimpleName(), empno.getClass().getSimpleName(),
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

	public Integer getAuser_id() {
		return auser_id;
	}

	public void setAuser_id(Integer auser_id) {
		this.auser_id = auser_id;
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

	// 以下是額外需求的資料
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Short getAuthority() {
		return authority;
	}

	public void setAuthority(Short authority) {
		this.authority = authority;
	}

	public String getMgr() {
		return mgr;
	}

	public void setMgr(String mgr) {
		this.mgr = mgr;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public Short getRole() {
		return role;
	}

	public void setRole(Short role) {
		this.role = role;
	}

	public void setValues(Object[] values) {
		int i = 0;
		setEmpno((String) values[i++]);
		setUser((String) values[i++]);
		setPass((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		setAuser_id((Integer) values[i++]);
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
		return new Object[] { getAuser_id(), getEmpno(), getUser(), getPass(), getCreated(), getUpdated() };
	}

}
