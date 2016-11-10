package tw.youth.project.gift2016.sql.adep;

public class ADEP {
	// 部門基本資料檔

	private Integer _id = 0;

	private String dno = "";
	// 部門代碼
	private String dname = "";
	// 部門名稱
	private String fno = "";
	// 所在廠區

	private String[] keys = { "_id", "dno", "dname", "fno" };
	private String[] types = { _id.getClass().getSimpleName(), dno.getClass().getSimpleName(),
			dname.getClass().getSimpleName(), fno.getClass().getSimpleName() };
	private String[] uniques = { "dno" };

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

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

}
