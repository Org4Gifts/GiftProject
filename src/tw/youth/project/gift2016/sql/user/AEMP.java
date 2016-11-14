package tw.youth.project.gift2016.sql.user;

import java.sql.Timestamp;

public class AEMP {
	// 員工基本資料檔
	private Integer _id = 0;

	private String empno = "";
	// 工號
	private String ename = "";
	// 中文姓名
	private String email = "";
	// 電子郵件地址
	private String job = "";
	// 職稱
	private Short authority = 0; // auto
	// 簽核層級 0職員、1課長、2經理級、3處長級、4副總經理級、5總經理、6董事長
	private String ext = "";
	// 分機
	private String mgr = "";
	// 直屬主管工號
	private String dno = "";
	// 部門代碼
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "_id", "empno", "ename", "email", "job", "authority", "ext", "mgr", "dno" };
	private String[] types = { _id.getClass().getSimpleName(), empno.getClass().getSimpleName(),
			ename.getClass().getSimpleName(), email.getClass().getSimpleName(), job.getClass().getSimpleName(),
			authority.getClass().getSimpleName(), ext.getClass().getSimpleName(), mgr.getClass().getSimpleName(),
			dno.getClass().getSimpleName() };
	private String[] uniques = { "empno", "email" };

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

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Short getAuthority() {
		return authority;
	}

	public void setAuthority(Short authority) {
		this.authority = authority;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
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
		setEname((String) values[i++]);
		setEmail((String) values[i++]);
		setJob((String) values[i++]);
		System.out.println(values[i].getClass().getSimpleName());
		setAuthority(Short.parseShort(Integer.toString((Integer) values[i++])));
		setExt((String) values[i++]);
		setMgr((String) values[i++]);
		setDno((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setEmpno((String) values[i++]);
		setEname((String) values[i++]);
		setEmail((String) values[i++]);
		setJob((String) values[i++]);
		System.out.println(values[i].getClass().getSimpleName());
		setAuthority(Short.parseShort(Integer.toString((Integer) values[i++])));
		setExt((String) values[i++]);
		setMgr((String) values[i++]);
		setDno((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getEmpno(), getEname(), getEmail(), getJob(), getAuthority(), getExt(), getMgr(),
				getDno() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getEmpno(), getEname(), getEmail(), getJob(), getAuthority(), getExt(),
				getMgr(), getDno(), getCreated(), getUpdated() };
	}
}
