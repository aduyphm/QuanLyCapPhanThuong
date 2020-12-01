package entity;

public class ChauNhoNhanQua {
    private int idNhanKhau;
    private String hoTen;
    private int tuoi;
    private String noiThuongTru;
    private String moTaPhanQua;
    private int giaTri;
    private String bo;
    private String me;

    public ChauNhoNhanQua(int idNhanKhau, String hoTen, int tuoi, String noiThuongTru, String moTaPhanQua, int giaTri,
            String bo, String me) {
        this.idNhanKhau = idNhanKhau;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.noiThuongTru = noiThuongTru;
        this.moTaPhanQua = moTaPhanQua;
        this.giaTri = giaTri;
        this.bo = bo;
        this.me = me;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getMoTaPhanQua() {
        return moTaPhanQua;
    }

    public void setMoTaPhanQua(String moTaPhanQua) {
        this.moTaPhanQua = moTaPhanQua;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public String getBo() {
        return bo;
    }

    public void setBo(String bo) {
        this.bo = bo;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getNoiThuongTru() {
        return noiThuongTru;
    }

    public void setNoiThuongTru(String noiThuongTru) {
        this.noiThuongTru = noiThuongTru;
    }

}
