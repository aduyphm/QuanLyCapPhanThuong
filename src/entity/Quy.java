package entity;

import java.util.*;

public class Quy {

	private int ID; 
	private Date ngay;
	private int soTienThemVao;
	private int soTienLayRa;
	private int soDuConLai;
	private int idNguoiThucHien;
	private String moTa;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Date getNgay() {
		return ngay;
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	public int getSoTienThemVao() {
		return soTienThemVao;
	}
	public void setSoTienThemVao(int soTienThemVao) {
		this.soTienThemVao = soTienThemVao;
	}
	public int getSoTienLayRa() {
		return soTienLayRa;
	}
	public void setSoTienLayRa(int soTienLayRa) {
		this.soTienLayRa = soTienLayRa;
	}
	public int getSoDuConLai() {
		return soDuConLai;
	}
	public void setSoDuConLai(int soDuConLai) {
		this.soDuConLai = soDuConLai;
	}
	public int getIdNguoiThucHien() {
		return idNguoiThucHien;
	}
	public void setIdNguoiThucHien(int idNguoiThucHien) {
		this.idNguoiThucHien = idNguoiThucHien;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	public Quy(int iD, Date ngay, int soTienThemVao, int soTienLayRa, int soDuConLai, int idNguoiThucHien,
			String moTa) {
		super();
		ID = iD;
		this.ngay = ngay;
		this.soTienThemVao = soTienThemVao;
		this.soTienLayRa = soTienLayRa;
		this.soDuConLai = soDuConLai;
		this.idNguoiThucHien = idNguoiThucHien;
		this.moTa = moTa;
	}

}
