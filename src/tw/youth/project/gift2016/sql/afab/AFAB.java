package tw.youth.project.gift2016.sql.afab;

import java.sql.Date;
import java.sql.Timestamp;

public class AFAB {
	// 廠別基本檔
	private Integer _id = 0;

	private String fno = "";
	// 廠別代碼 2碼
	private String fname = "";
	// 廠別名稱
	private Date created;
	private Date updated;

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = new Date(created.getTime());
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = new Date(updated.getTime());
	}

	public void setValues(Object[] values) {
		int i = 0;
		setFno((String) values[i++]);
		setFame((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setFno((String) values[i++]);
		setFame((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getFno(), getFname() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getFno(), getFname(), getCreated(), getUpdated() };
	}
}
