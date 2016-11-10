package tw.youth.project.gift2016.sql.aqty;

public class AQTY {
	// 多廠別進/銷彙總檔
	private Integer _id = 0;

	private Long yymm = 0L; // auto
	// 資料年月
	private String fno = ""; // auto
	// 廠別
	private String fgno = ""; // auto
	// 禮品編號
	private Integer pmqty = 0; // auto
	// 期初數量
	private Integer inqty = 0; // auto
	// 本月進貨
	private Integer udqty = 0; // auto
	// 本月銷貨

	private String[] keys = { "_id", "yymm", "fno", "fgno", "pmqty", "inqty", "udqty" };
	private String[] types = { _id.getClass().getSimpleName(), yymm.getClass().getSimpleName(),
			fno.getClass().getSimpleName(), fgno.getClass().getSimpleName(), pmqty.getClass().getSimpleName(),
			inqty.getClass().getSimpleName(), udqty.getClass().getSimpleName() };
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
	// 以下是儲存的值

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public Long getYymm() {
		return yymm;
	}

	public void setYymm(Long yymm) {
		this.yymm = yymm;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getFgno() {
		return fgno;
	}

	public void setFgno(String fgno) {
		this.fgno = fgno;
	}

	public Integer getPmqty() {
		return pmqty;
	}

	public void setPmqty(Integer pmqty) {
		this.pmqty = pmqty;
	}

	public Integer getInqty() {
		return inqty;
	}

	public void setInqty(Integer inqty) {
		this.inqty = inqty;
	}

	public Integer getUdqty() {
		return udqty;
	}

	public void setUdqty(Integer udqty) {
		this.udqty = udqty;
	}

}
