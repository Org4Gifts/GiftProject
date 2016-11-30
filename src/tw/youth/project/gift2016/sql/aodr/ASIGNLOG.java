package tw.youth.project.gift2016.sql.aodr;

import java.sql.Timestamp;

public class ASIGNLOG {
	// 訂單主檔
	private Integer _id = 0;

	private String order1 = "";
	// 訂單編號
	private String empno = "";
	// 簽核人員工號
	private String signature = "";
	// 簽核人員名稱
	private Float spent = 0.0f;
	// 簽核花費時間 (上一個簽核人員的時間為主)
	private String status = "";
	// 訂單簽核狀態  Send 發送請求者 ,None 尚未簽核 ,Approve 同意 ,Reject 拒絕 ,Complete 完成
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "_id", "order1", "empno", "signature", "spent", "status" };
	private String[] types = { _id.getClass().getSimpleName(), order1.getClass().getSimpleName(),
			empno.getClass().getSimpleName(), signature.getClass().getSimpleName(), spent.getClass().getSimpleName(),
			status.getClass().getSimpleName() };
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
	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
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
		setSpent((Float) values[i++]);
		setStatus((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setOrder1((String) values[i++]);
		setEmpno((String) values[i++]);
		setSignature((String) values[i++]);
		setSpent((Float) values[i++]);
		setStatus((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);

	}

	public Object[] getValues() {
		return new Object[] { getOrder1(), getEmpno(), getSignature(), getSpent(), getStatus() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getOrder1(), getEmpno(), getSignature(), getSpent(), getStatus(), getCreated(),
				getUpdated() };
	}
}
