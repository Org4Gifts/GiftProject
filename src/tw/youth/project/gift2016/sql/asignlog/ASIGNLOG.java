package tw.youth.project.gift2016.sql.asignlog;

import java.sql.Timestamp;

public class ASIGNLOG {
	// 訂單主檔
	private Integer asignlog_id = 0;

	private String order1 = "";
	// 訂單編號
	private String empno = "";
	// 簽核人員工號
	private String signature = "";
	// 簽核人員名稱
	private String dno = "";
	// 簽核人員部門代碼 4碼
	private Float spent = 0.0f;
	// 簽核花費時間 (上一個簽核人員的時間為主)
	private String status = "";
	// 訂單簽核狀態 Send 發送請求者 ,None 尚未簽核 ,Approve 同意 ,Reject 拒絕 ,Complete 完成
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "asignlog_id", "order1", "empno", "signature", "dno", "spent", "status" };
	private String[] types = { asignlog_id.getClass().getSimpleName(), order1.getClass().getSimpleName(),
			empno.getClass().getSimpleName(), signature.getClass().getSimpleName(), dno.getClass().getSimpleName(),
			spent.getClass().getSimpleName(), status.getClass().getSimpleName() };
	private String[] uniques = { "" };

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
	public Integer getAsignlog_id() {
		return asignlog_id;
	}

	public void setAsignlog_id(Integer asignlog_id) {
		this.asignlog_id = asignlog_id;
	}

	public String getOrder1() {
		return order1;
	}

	public void setOrder1(String order1) {
		this.order1 = order1;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public Float getSpent() {
		return spent;
	}

	public void setSpent(Float spent) {
		this.spent = spent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		setEmpno((String) values[i++]);
		setSignature((String) values[i++]);
		setDno((String) values[i++]);
		setSpent((Float) values[i++]);
		setStatus((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		setAsignlog_id((Integer) values[i++]);
		setOrder1((String) values[i++]);
		setEmpno((String) values[i++]);
		setSignature((String) values[i++]);
		setDno((String) values[i++]);
		setSpent((Float) values[i++]);
		setStatus((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);

	}

	public Object[] getValues() {
		return new Object[] { getOrder1(), getEmpno(), getSignature(), getDno(), getSpent(), getStatus() };
	}

	public Object[] getValuesFull() {
		return new Object[] { getAsignlog_id(), getOrder1(), getEmpno(), getSignature(), getDno(), getSpent(), getStatus(),
				getCreated(), getUpdated() };
	}
}
