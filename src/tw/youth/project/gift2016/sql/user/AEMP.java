package tw.youth.project.gift2016.sql.user;

public class AEMP {
	// 員工基本資料檔

	private String EMPNO = "";
	// 工號
	private String ENAME = "";
	// 中文姓名
	private String EMAIL = "";
	// 電子郵件地址
	private String JOB = "";
	// 職稱
	private String AUTHORITY = ""; // auto
	// 簽核層級
	private String EXT = "";
	// 分機
	private String MGR = "";
	// 直屬主管工號
	private String DNO = "";
	// 部門代碼

	private String[] values = { "EMPNO", "ENAME", "EMAIL", "JOB", "AUTHORITY", "EXT", "MGR", "DNO" };
	private String[] types = { EMPNO.getClass().getSimpleName(), ENAME.getClass().getSimpleName(),
			EMAIL.getClass().getSimpleName(), JOB.getClass().getSimpleName(), AUTHORITY.getClass().getSimpleName(),
			EXT.getClass().getSimpleName(), MGR.getClass().getSimpleName(), DNO.getClass().getSimpleName() };
	private String primary = "EMPNO";
	private String[] unique = { "EMAIL" };

	public String getTableName() {
		return getClass().getSimpleName();
	}
	
	public String[] getValues() {
		return values;
	}

	public String[] getTypes() {
		return types;
	}

	public String getPrimary() {
		return primary;
	}

	public String[] getUnique() {
		return unique;
	}
	//以下是儲存的值

	public String getEMPNO() {
		return EMPNO;
	}

	public void setEMPNO(String eMPNO) {
		EMPNO = eMPNO;
	}

	public String getENAME() {
		return ENAME;
	}

	public void setENAME(String eNAME) {
		ENAME = eNAME;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getJOB() {
		return JOB;
	}

	public void setJOB(String jOB) {
		JOB = jOB;
	}

	public String getAUTHORITY() {
		return AUTHORITY;
	}

	public void setAUTHORITY(String aUTHORITY) {
		AUTHORITY = aUTHORITY;
	}

	public String getEXT() {
		return EXT;
	}

	public void setEXT(String eXT) {
		EXT = eXT;
	}

	public String getMGR() {
		return MGR;
	}

	public void setMGR(String mGR) {
		MGR = mGR;
	}

	public String getDNO() {
		return DNO;
	}

	public void setDNO(String dNO) {
		DNO = dNO;
	}

}
