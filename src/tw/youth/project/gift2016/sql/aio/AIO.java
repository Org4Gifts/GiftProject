package tw.youth.project.gift2016.sql.aio;

public class AIO {
	// 多廠別進/銷主檔
	private Integer _id = 0;

	private String vhno = "";
	// 單據編號
	private String fno = "";
	// 廠別
	private Integer vhdt = 0;
	// 單據日期
	private String ano = "";
	// 單位編號
	private Character dc = 'D'; // ?
	// 進/銷
	private Float tamt = 0.0f;
	// 進銷金額
	private String memo = "";
	// 調撥理由

	private String[] keys = { "_id", "vhno", "fno", "vhdt", "ano", "dc", "tamt", "memo" };
	private String[] types = { _id.getClass().getSimpleName(), vhno.getClass().getSimpleName(),
			fno.getClass().getSimpleName(), vhdt.getClass().getSimpleName(), ano.getClass().getSimpleName(),
			dc.getClass().getSimpleName(), tamt.getClass().getSimpleName(), memo.getClass().getSimpleName() };
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

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public Integer getVhdt() {
		return vhdt;
	}

	public void setVhdt(Integer vhdt) {
		this.vhdt = vhdt;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Character getDc() {
		return dc;
	}

	public void setDc(Character dc) {
		this.dc = dc;
	}

	public Float getTamt() {
		return tamt;
	}

	public void setTamt(Float tamt) {
		this.tamt = tamt;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
