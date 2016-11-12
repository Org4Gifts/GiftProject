package tw.youth.project.gift2016.sql.apresent;

import java.sql.Timestamp;

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
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "_id", "fgno", "fgname", "prc", "grade", "authority", "status", "note1", "fqty", "iqty" };
	private String[] types = { _id.getClass().getSimpleName(), fgno.getClass().getSimpleName(),
			fgname.getClass().getSimpleName(), prc.getClass().getSimpleName(), grade.getClass().getSimpleName(),
			authority.getClass().getSimpleName(), status.getClass().getSimpleName(), note1.getClass().getSimpleName(),
			fqty.getClass().getSimpleName(), iqty.getClass().getSimpleName() };
	private String[] uniques = { "fgno", "fgname" };

	public String getTableName() {
		return getClass().getSimpleName();
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
		setFgno((String) values[i++]);
		setFgname((String) values[i++]);
		setPrc((Float) values[i++]);
		setGrade((Short) values[i++]);
		setAuthority((Short) values[i++]);
		setStatus((String) values[i++]);
		setNote1((String) values[i++]);
		setFqty((Integer) values[i++]);
		setIqty((Integer) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setFgno((String) values[i++]);
		setFgname((String) values[i++]);
		setPrc((Float) values[i++]);
		setGrade((Short) values[i++]);
		setAuthority((Short) values[i++]);
		setStatus((String) values[i++]);
		setNote1((String) values[i++]);
		setFqty((Integer) values[i++]);
		setIqty((Integer) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getFgno(), getFgname(), getPrc(), getGrade(), getAuthority(), getStatus(), getNote1(),
				getFqty(), getIqty() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getFgno(), getFgname(), getPrc(), getGrade(), getAuthority(), getStatus(),
				getNote1(), getFqty(), getIqty(), getCreated(), getUpdated() };
	}
}
