package entity;

public class PhanQua {

	private int ID;
	private String maPhanQua;
	private String loaiQua;
	private int giaTri;
	private String moTa;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getMaPhanQua() {
		return maPhanQua;
	}
	public void setMaPhanQua(String maPhanQua) {
		this.maPhanQua = maPhanQua;
	}
	public String getLoaiQua() {
		return loaiQua;
	}
	public void setLoaiQua(String loaiQua) {
		this.loaiQua = loaiQua;
	}
	public int getGiaTri() {
		return giaTri;
	}
	public void setGiaTri(int giaTri) {
		this.giaTri = giaTri;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	public PhanQua(int iD, String maPhanQua, String loaiQua, int giaTri, String moTa) {
		super();
		ID = iD;
		this.maPhanQua = maPhanQua;
		this.loaiQua = loaiQua;
		this.giaTri = giaTri;
		this.moTa = moTa;
	}

}
