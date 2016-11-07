package tw.youth.project.gift2016.sql.apresent;

public class APRESENT {
	// 禮品基本檔
	private Integer _id = 0;

	private String fgno = "";
	// 禮品編號
	private String fgname = "";
	// 禮品名稱
	private Float prc = 0.0f;
	// 禮品單價
	private Short grade = 0;
	// 禮品等級
	private Short authority = 0;
	// 申請層級
	private String status = "";
	// 申請狀態
	private String note1 = "";
	// 備註
	private Integer fqty = 0;
	// 安全庫存量
	private Integer iqty = 0; // auto
	// 即時庫存量

	private String[] keys = { "_id", "fgno", "fgname", "prc", "grade", "authority", "status", "note1", "fqty", "iqty" };
	private String[] types = { _id.getClass().getSimpleName(), fgno.getClass().getSimpleName(),
			fgname.getClass().getSimpleName(), prc.getClass().getSimpleName(), grade.getClass().getSimpleName(),
			authority.getClass().getSimpleName(), status.getClass().getSimpleName(), note1.getClass().getSimpleName(),
			fqty.getClass().getSimpleName(), iqty.getClass().getSimpleName() };
	private String[] uniques = { "fgname" };
	private String primary = "fgno";

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
	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getFgno() {
		return fgno;
	}

	public void setFgno(String fgno) {
		this.fgno = fgno;
	}

	public String getFgname() {
		return fgname;
	}

	public void setFgname(String fgname) {
		this.fgname = fgname;
	}

	public Float getPrc() {
		return prc;
	}

	public void setPrc(Float prc) {
		this.prc = prc;
	}

	public Short getGrade() {
		return grade;
	}

	public void setGrade(Short grade) {
		this.grade = grade;
	}

	public Short getAuthority() {
		return authority;
	}

	public void setAuthority(Short authority) {
		this.authority = authority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public Integer getFqty() {
		return fqty;
	}

	public void setFqty(Integer fqty) {
		this.fqty = fqty;
	}

	public Integer getIqty() {
		return iqty;
	}

	public void setIqty(Integer iqty) {
		this.iqty = iqty;
	}

}
