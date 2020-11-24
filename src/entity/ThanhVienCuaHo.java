package entity;

public class ThanhVienCuaHo {
	
	private int idNhanKhau;
	private int idHoKhau;
	private String quanHeVoiChuHo;
	
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public int getIdHoKhau() {
		return idHoKhau;
	}
	public void setIdHoKhau(int idHoKhau) {
		this.idHoKhau = idHoKhau;
	}
	public String getQuanHeVoiChuHo() {
		return quanHeVoiChuHo;
	}
	public void setQuanHeVoiChuHo(String quanHeVoiChuHo) {
		this.quanHeVoiChuHo = quanHeVoiChuHo;
	}
	
	public ThanhVienCuaHo(int idNhanKhau, int idHoKhau, String quanHeVoiChuHo) {
		super();
		this.idNhanKhau = idNhanKhau;
		this.idHoKhau = idHoKhau;
		this.quanHeVoiChuHo = quanHeVoiChuHo;
	}

}
