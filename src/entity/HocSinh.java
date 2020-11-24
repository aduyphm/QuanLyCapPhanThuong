package entity;

public class HocSinh {

	private int ID;
	private int idNhanKhau;
	private String lop;
	private String truong;
	private String hocLuc;
	private String thanhTichDacBiet;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getTruong() {
		return truong;
	}
	public void setTruong(String truong) {
		this.truong = truong;
	}
	public String getHocLuc() {
		return hocLuc;
	}
	public void setHocLuc(String hocLuc) {
		this.hocLuc = hocLuc;
	}
	public String getThanhTichDacBiet() {
		return thanhTichDacBiet;
	}
	public void setThanhTichDacBiet(String thanhTichDacBiet) {
		this.thanhTichDacBiet = thanhTichDacBiet;
	}
	
	public HocSinh(int iD, int idNhanKhau, String lop, String truong, String hocLuc, String thanhTichDacBiet) {
		super();
		ID = iD;
		this.idNhanKhau = idNhanKhau;
		this.lop = lop;
		this.truong = truong;
		this.hocLuc = hocLuc;
		this.thanhTichDacBiet = thanhTichDacBiet;
	}
	
}
