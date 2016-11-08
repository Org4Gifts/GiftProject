package tw.youth.project.gift2016.sql.afab;

public class AFAB {
	// 廠別基本檔
	private Integer _id = 0;

	private String fno = "";
	// 廠別代碼
	private String fname = "";
	// 廠別名稱

	private String[] keys = { "_id", "fno", "fname" };
	private String[] types = { _id.getClass().getSimpleName(), fno.getClass().getSimpleName(),
			fname.getClass().getSimpleName() };
	private String[] uniques = { "fno" };
	
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
	// 以下是儲存的值

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

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
