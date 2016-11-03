package tw.youth.project.gift2016.sql.aodr;

public class AODR {
	// 訂單主檔

	private String order1 = "";
	// 訂單編號
	private Integer odate = 0;
	// 訂定日期
	private String empno = "";
	// 員工編號
	private Long tamt = 0L;
	// 訂單金額
	private String purpose = "";
	// 需求目的

	private String[] keys = { "ORDER1", "ODATE", "EMPNO", "TAMT", "PURPOSE" };
	private String[] types = { order1.getClass().getSimpleName(), odate.getClass().getSimpleName(),
			empno.getClass().getSimpleName(), tamt.getClass().getSimpleName(), purpose.getClass().getSimpleName() };
	private String[] uniques = { "" };
	private String primary = "ORDER1";
	
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
	//以下是儲存的值

	public String getOrder1() {
		return order1;
	}

	public void setOrder1(String order1) {
		this.order1 = order1;
	}

	public Integer getOdate() {
		return odate;
	}

	public void setOdate(Integer odate) {
		this.odate = odate;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public Long getTamt() {
		return tamt;
	}

	public void setTamt(Long tamt) {
		this.tamt = tamt;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}	

}
