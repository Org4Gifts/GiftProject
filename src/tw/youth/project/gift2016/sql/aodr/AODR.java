package tw.youth.project.gift2016.sql.aodr;

import java.sql.Timestamp;

public class AODR {
	// 訂單主檔
	private Integer aodr_id = 0;

	private String order1 = "";
	// 訂單編號
	private Timestamp odate = new Timestamp(100000L);
	// 訂定日期
	private String empno = "";
	// 員工編號
	private String fno = "";
	// 廠區編號 2碼
	private String dno = "";
	// 部門編號 4碼
	private Float tamt = 0.0f;
	// 訂單金額
	private String status = "";
	// 訂單狀態
	// Status0 : Rejected (單據被退件)
	// Status1 : Preparing (單據準備中，尚未送出)
	// STatus2 : Processing (單據被送出，待簽核中)
	// Status8 : Approved (簽核流程結束)
	// Status9 : Completed (行政部門結案，禮品被領走，系統自動扣除 【及時庫存量】)
	// Status10 : Obsoleted (行政部門作廢，系統自動加回 【及時庫存量】)
	private Short authority = 0;
	// 訂單最高簽核層級
	private String signerno = "";
	// 訂單目前簽核人員
	private String purpose = "";
	// 需求目的
	private String signerlist = "";
	// 簽核人員名單 員工編號_員工姓名^員工編號_員工姓名
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "aodr_id", "order1", "odate", "empno", "fno", "dno", "tamt", "status", "authority",
			"signerno", "purpose", "signerlist" };
	private String[] types = { aodr_id.getClass().getSimpleName(), order1.getClass().getSimpleName(),
			odate.getClass().getSimpleName(), empno.getClass().getSimpleName(), fno.getClass().getSimpleName(),
			dno.getClass().getSimpleName(), tamt.getClass().getSimpleName(), status.getClass().getSimpleName(),
			authority.getClass().getSimpleName(), signerno.getClass().getSimpleName(),
			purpose.getClass().getSimpleName(), signerlist.getClass().getSimpleName() };
	private String[] uniques = { "order1" };

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
	public Integer getAodr_id() {
		return aodr_id;
	}

	public void setAodr_id(Integer aodr_id) {
		this.aodr_id = aodr_id;
	}

	public String getOrder1() {
		return order1;
	}

	public void setOrder1(String order1) {
		this.order1 = order1;
	}

	public Timestamp getOdate() {
		return odate;
	}

	public void setOdate(Timestamp odate) {
		this.odate = odate;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public Float getTamt() {
		return tamt;
	}

	public void setTamt(Float tamt) {
		this.tamt = tamt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Short getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = Short.parseShort(Integer.toString(authority));
	}

	public String getSignerno() {
		return signerno;
	}

	public void setSignerno(String signerno) {
		this.signerno = signerno;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getSignerlist() {
		return signerlist;
	}

	public void setSignerlist(String signerlist) {
		this.signerlist = signerlist;
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
		setOrder1((String) values[i++]);
		setOdate((Timestamp) values[i++]);
		setEmpno((String) values[i++]);
		setFno((String) values[i++]);
		setDno((String) values[i++]);
		setTamt((Float) values[i++]);
		setStatus((String) values[i++]);
		setAuthority((Integer) values[i++]);
		setSignerno((String) values[i++]);
		setPurpose((String) values[i++]);
		setSignerlist((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		setAodr_id((Integer) values[i++]);
		setOrder1((String) values[i++]);
		setOdate((Timestamp) values[i++]);
		setEmpno((String) values[i++]);
		setFno((String) values[i++]);
		setDno((String) values[i++]);
		setTamt((Float) values[i++]);
		setStatus((String) values[i++]);
		setAuthority((Integer) values[i++]);
		setSignerno((String) values[i++]);
		setPurpose((String) values[i++]);
		setSignerlist((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getOrder1(), getOdate(), getEmpno(), getFno(), getDno(), getTamt(), getStatus(),
				getAuthority(), getSignerno(), getPurpose(), getSignerlist() };
	}

	public Object[] getValuesFull() {
		return new Object[] { getAodr_id(), getOrder1(), getOdate(), getEmpno(), getFno(), getDno(), getTamt(),
				getStatus(), getAuthority(), getSignerno(), getPurpose(), getSignerlist(), getCreated(), getUpdated() };
	}

}
