package tw.youth.project.gift2016.sql.aio;

import java.sql.Timestamp;

public class AIO {
	// 多廠別進/銷主檔
	private Integer _id = 0;

	private String vhno = "";
	// 單據編號
	private String empno = "";
	// 員工編號
	private String dno = "";
	// 部門編號 4碼
	private String fno = "";
	// 轉出廠別 2碼
	private Timestamp vhdt = new Timestamp(100000L);
	// 單據日期
	private Timestamp vrdt = new Timestamp(100000L);
	// 需求日期
	private String ano = "";
	// 轉入廠別 2碼
	private String dc = "D"; // ?
	// 進/銷 不使用
	private Float tamt = 0.0f;
	// 進銷金額
	private String status = "";
	// 調撥單狀態
	// Status0 : Rejected (單據被退件)
	// Status1 : Preparing (單據準備中，尚未送出)
	// STatus2 : Processing (單據被送出，待簽核中)
	// Status8 : Approved (簽核流程結束)
	// Status9 : Completed (行政部門結案，禮品已轉出，系統自動扣除 【及時庫存量】)
	// Status10 : Obsoleted (行政部門作廢，系統自動加回 【及時庫存量】)
	private Short authority = 0;
	// 調撥單最高簽核層級
	private String signerno = "";
	// 調撥單目前簽核人員
	private String memo = "";
	// 調撥理由
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "_id", "vhno", "empno", "dno", "fno", "vhdt", "vrdt", "ano", "dc", "tamt", "status",
			"authority", "signerno", "memo" };
	private String[] types = { _id.getClass().getSimpleName(), vhno.getClass().getSimpleName(),
			empno.getClass().getSimpleName(), dno.getClass().getSimpleName(), fno.getClass().getSimpleName(),
			vhdt.getClass().getSimpleName(), vrdt.getClass().getSimpleName(), ano.getClass().getSimpleName(),
			dc.getClass().getSimpleName(), tamt.getClass().getSimpleName(), status.getClass().getSimpleName(),
			authority.getClass().getSimpleName(), signerno.getClass().getSimpleName(),
			memo.getClass().getSimpleName() };
	private String[] uniques = { "vhno" };

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

	public String getVhno() {
		return vhno;
	}

	public void setVhno(String vhno) {
		this.vhno = vhno;
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

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public Timestamp getVhdt() {
		return vhdt;
	}

	public void setVhdt(Timestamp vhdt) {
		this.vhdt = vhdt;
	}

	public Timestamp getVrdt() {
		return vrdt;
	}

	public void setVrdt(Timestamp vrdt) {
		this.vrdt = vrdt;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
		setVhno((String) values[i++]);
		setEmpno((String) values[i++]);
		setDno((String) values[i++]);
		setFno((String) values[i++]);
		setVhdt((Timestamp) values[i++]);
		setVrdt((Timestamp) values[i++]);
		setAno((String) values[i++]);
		setDc((String) values[i++]);
		setTamt((Float) values[i++]);
		setStatus((String) values[i++]);
		setAuthority((Integer) values[i++]);
		setSignerno((String) values[i++]);
		setMemo((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setVhno((String) values[i++]);
		setEmpno((String) values[i++]);
		setDno((String) values[i++]);
		setFno((String) values[i++]);
		setVhdt((Timestamp) values[i++]);
		setVrdt((Timestamp) values[i++]);
		setAno((String) values[i++]);
		setDc((String) values[i++]);
		setTamt((Float) values[i++]);
		setStatus((String) values[i++]);
		setAuthority((Integer) values[i++]);
		setSignerno((String) values[i++]);
		setMemo((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getVhno(), getEmpno(), getDno(), getFno(), getVhdt(), getVrdt(), getAno(), getDc(),
				getTamt(), getStatus(), getAuthority(), getSignerno(), getMemo() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getEmpno(), getDno(), getVhno(), getFno(), getVhdt(), getVrdt(), getAno(), getDc(), getTamt(),
				getMemo(), getStatus(), getAuthority(), getSignerno(), getCreated(), getUpdated() };
	}

}
