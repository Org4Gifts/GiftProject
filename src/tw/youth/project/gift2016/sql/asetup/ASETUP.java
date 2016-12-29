package tw.youth.project.gift2016.sql.asetup;

import java.sql.Timestamp;

public class ASETUP {
	//廠簽結者
	private Integer asetup_id = 0;
	private String fno = "";
	private String empno = "";
	private String dno = "";
	private Timestamp created;
	private Timestamp updated;
	
	private String[] keys = { "asetup_id", "fno", "empno", "dno"};
	private String[] types = { asetup_id.getClass().getSimpleName(), fno.getClass().getSimpleName(),
			empno.getClass().getSimpleName(),dno.getClass().getSimpleName() };
	private String[] uniques = { "empno" };

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
	
	public Integer getAsetup_id() {
		return asetup_id;
	}

	public void setAsetup_id(Integer asetup_id) {
		this.asetup_id = asetup_id;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
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
		setEmpno((String) values[i++]);
		setDno((String) values[i++]);
	}
	
	public void setValuesFull(Object[] values) {
		int i = 0;
		setAsetup_id((Integer) values[i++]);
		setFno((String) values[i++]);
		setEmpno((String) values[i++]);
		setDno((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getFno(), getEmpno(), getDno()};
	}

	public Object[] getValuesFull() {
		return new Object[] { getAsetup_id(), getFno(), getEmpno(), getDno(), getCreated(), getUpdated() };
	}
}
