package entity;

import java.util.*;

public class PhatQuaDipDacBietRecord {

	private int ID;
	private int idNhanKhau; 
	private Date ngayNhan;
	private String dip;
	private int idPhanQua;
	private int soPhanQua;
	private int giaTri;
	
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
	public Date getNgayNhan() {
		return ngayNhan;
	}
	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}
	public String getDip() {
		return dip;
	}
	public void setDip(String dip) {
		this.dip = dip;
	}
	public int getIdPhanQua() {
		return idPhanQua;
	}
	public void setIdPhanQua(int idPhanQua) {
		this.idPhanQua = idPhanQua;
	}
	public int getSoPhanQua() {
		return soPhanQua;
	}
	public void setSoPhanQua(int soPhanQua) {
		this.soPhanQua = soPhanQua;
	}
	public int getGiaTri() {
		return giaTri;
	}
	public void setGiaTri(int giaTri) {
		this.giaTri = giaTri;
	}
	
	public PhatQuaDipDacBietRecord(int iD, int idNhanKhau, Date ngayNhan, String dip, int idPhanQua, int soPhanQua,
			int giaTri) {
		super();
		ID = iD;
		this.idNhanKhau = idNhanKhau;
		this.ngayNhan = ngayNhan;
		this.dip = dip;
		this.idPhanQua = idPhanQua;
		this.soPhanQua = soPhanQua;
		this.giaTri = giaTri;
	}

}
