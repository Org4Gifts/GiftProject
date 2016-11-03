package tw.youth.project.gift2016.sql.afab;

public class AFAB {
	// 廠別基本檔

	private String fno = "";
	// 廠別代碼
	private String fname = "";
	// 廠別名稱

	private String[] keys = { "fno", "fname" };
	private String[] types = { fno.getClass().getSimpleName(), fname.getClass().getSimpleName() };
	private String[] uniques = { "" };
	private String primary = "fno";

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
	// 以下是儲存的值

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getFname() {
		return fname;
	}

	public void setFame(String fname) {
		this.fname = fname;
	}
}
