package entity;

import java.util.*;

public class DanhSachPhatQuaCuoiNam {

	private int ID;
	private int idHocSinh;
	private Date ngayNhan;
	private String namHoc;
	private int idPhanQua;
	private int soPhanQua;
	private int giaTri;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getIdHocSinh() {
		return idHocSinh;
	}
	public void setIdHocSinh(int idHocSinh) {
		this.idHocSinh = idHocSinh;
	}
	public Date getNgayNhan() {
		return ngayNhan;
	}
	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}
	public String getNamHoc() {
		return namHoc;
	}
	public void setNamHoc(String namHoc) {
		this.namHoc = namHoc;
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
	
	public DanhSachPhatQuaCuoiNam(int iD, int idHocSinh, Date ngayNhan, String namHoc, int idPhanQua, int soPhanQua,
			int giaTri) {
		super();
		ID = iD;
		this.idHocSinh = idHocSinh;
		this.ngayNhan = ngayNhan;
		this.namHoc = namHoc;
		this.idPhanQua = idPhanQua;
		this.soPhanQua = soPhanQua;
		this.giaTri = giaTri;
	}

}
