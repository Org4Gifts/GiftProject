package tw.youth.project.gift2016.sql.ainventory;

public class AINVENTORY {
	// 多廠別盤存檔

	private String invo = "";
	// 盤點單號
	private String fno = "";
	// 儲存廠別
	private Integer yymm = 0;
	// 資料年月
	private String fgno = "";
	// 禮品編號
	private Integer ivqty = 0;
	// 盤點數量
	private Integer sqty = 0; // auto
	// 系統數量

	private String[] keys = { "invo", "fno", "yymm", "fgno", "ivqty", "sqty" };
	private String[] types = { invo.getClass().getSimpleName(), fno.getClass().getSimpleName(),
			yymm.getClass().getSimpleName(), fgno.getClass().getSimpleName(), ivqty.getClass().getSimpleName(),
			sqty.getClass().getSimpleName() };
	private String[] uniques = { "" };
	private String primary = "invo";
	
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

	public Integer getYymm() {
		return yymm;
	}

	public void setYymm(Integer yymm) {
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
