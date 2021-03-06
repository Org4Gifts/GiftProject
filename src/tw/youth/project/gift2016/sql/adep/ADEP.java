package tw.youth.project.gift2016.sql.adep;

import java.sql.Timestamp;

public class ADEP {
	// 部門基本資料檔

	private Integer adep_id = 0;

	private String dno = "";
	// 部門代碼 4碼
	private Short role = 0;
	// 部門權限 : 一般部門0、核銷部門1、庫存部門2、管理部門3、總經理室4、董事長室5
	private String dname = "";
	// 部門名稱
	private String fno = "";
	// 所在廠區
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "adep_id", "dno", "role", "dname", "fno" };
	private String[] types = { adep_id.getClass().getSimpleName(), dno.getClass().getSimpleName(),
			role.getClass().getSimpleName(), dname.getClass().getSimpleName(), fno.getClass().getSimpleName() };
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

	public int getLength() {
		return getKeys().length + 2;
	}
	// 以下是儲存的值

	public Integer getAdep_id() {
		return adep_id;
	}

	public void setAdep_id(Integer adep_id) {
		this.adep_id = adep_id;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public Short getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = Short.parseShort(Integer.toString(role));
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
		setDno((String) values[i++]);
		setRole((Integer) values[i++]);
		setDname((String) values[i++]);
		setFno((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		setAdep_id((Integer) values[i++]);
		setDno((String) values[i++]);
		setRole((Integer) values[i++]);
		setDname((String) values[i++]);
		setFno((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getDno(), getRole(), getDname(), getFno() };
	}

	public Object[] getValuesFull() {
		return new Object[] { getAdep_id(), getDno(), getRole(), getDname(), getFno(), getCreated(), getUpdated() };
	}

}
