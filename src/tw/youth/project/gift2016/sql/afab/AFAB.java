package tw.youth.project.gift2016.sql.afab;

import java.sql.Timestamp;

public class AFAB {
	// 廠別基本檔
	private Integer afab_id = 0;

	private String fno = "";
	// 廠別代碼 2碼
	private String fname = "";
	// 廠別名稱
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "afab_id", "fno", "fname" };
	private String[] types = { afab_id.getClass().getSimpleName(), fno.getClass().getSimpleName(),
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

	public Integer getAfab_id() {
		return afab_id;
	}

	public void setAfab_id(Integer afab_id) {
		this.afab_id = afab_id;
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
		setFno((String) values[i++]);
		setFame((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		setAfab_id((Integer) values[i++]);
		setFno((String) values[i++]);
		setFame((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getFno(), getFname() };
	}

	public Object[] getValuesFull() {
		return new Object[] { getAfab_id(), getFno(), getFname(), getCreated(), getUpdated() };
	}
}
