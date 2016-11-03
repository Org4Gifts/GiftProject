package tw.youth.project.gift2016.sql.user;

public class AEMP {
	// 員工基本資料檔

	private String empno = "";
	// 工號
	private String ename = "";
	// 中文姓名
	private String email = "";
	// 電子郵件地址
	private String job = "";
	// 職稱
	private String authority = ""; // auto
	// 簽核層級
	private String ext = "";
	// 分機
	private String mgr = "";
	// 直屬主管工號
	private String dno = "";
	// 部門代碼

	private String[] keys = { "empno", "ename", "email", "job", "authority", "ext", "mgr", "dno" };
	private String[] types = { empno.getClass().getSimpleName(), ename.getClass().getSimpleName(),
			email.getClass().getSimpleName(), job.getClass().getSimpleName(), authority.getClass().getSimpleName(),
			ext.getClass().getSimpleName(), mgr.getClass().getSimpleName(), dno.getClass().getSimpleName() };
	private String[] uniques = { "email" };
	private String primary = "empno";


	public String getTableName() {
		return getClass().getSimpleName();
	}
	
	public String[] getKeys() {
		return keys;
	}

	public String[] getTypes() {
		return types;
	}

	public String getPrimary() {
		return primary;
	}

	public String[] getUniques() {
		return uniques;
	}
	//以下是儲存的值

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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
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

}
