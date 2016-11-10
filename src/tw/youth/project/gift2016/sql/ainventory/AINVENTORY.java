package tw.youth.project.gift2016.sql.ainventory;

public class AINVENTORY {
	// 多廠別盤存檔
	private Integer _id = 0;

	private String invo = "";
	// 盤點單號
	private String fno = "";
	// 儲存廠別
	private Long yymm = 0L;
	// 資料年月
	private String fgno = "";
	// 禮品編號
	private Integer ivqty = 0;
	// 盤點數量
	private Integer sqty = 0; // auto
	// 系統數量

	private String[] keys = { "_id", "invo", "fno", "yymm", "fgno", "ivqty", "sqty" };
	private String[] types = { _id.getClass().getSimpleName(), invo.getClass().getSimpleName(),
			fno.getClass().getSimpleName(), yymm.getClass().getSimpleName(), fgno.getClass().getSimpleName(),
			ivqty.getClass().getSimpleName(), sqty.getClass().getSimpleName() };
	private String[] uniques = { "invo" };

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

	public String getInvo() {
		return invo;
	}

	public void setInvo(String invo) {
		this.invo = invo;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public Long getYymm() {
		return yymm;
	}

	public void setYymm(Long yymm) {
		this.yymm = yymm;
	}

	public String getFgno() {
		return fgno;
	}

	public void setFgno(String fgno) {
		this.fgno = fgno;
	}

	public Integer getIvqty() {
		return ivqty;
	}

	public void setIvqty(Integer ivqty) {
		this.ivqty = ivqty;
	}

	public Integer getSqty() {
		return sqty;
	}

	public void setSqty(Integer sqty) {
		this.sqty = sqty;
	}

}
