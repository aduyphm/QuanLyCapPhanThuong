package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ChauNhoNhanQua;
import service.DatabaseConnection;

public class ChauNhoNhanQuaController extends EntityController<ChauNhoNhanQua>{

    private String currentYear;

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet chauNhoList = DatabaseConnection
                .executeQuery("SELECT nhan_khau.ID, nhan_khau.hoTen AS 'Họ tên', nhan_khau.noiThuongTru AS 'Nơi thường trú', (2020 - EXTRACT(YEAR FROM nhan_khau.ngaySinh)) AS 'Tuổi', "
                + "ChongList.hoTen AS 'Bố', VoList.hoTen AS 'Mẹ' FROM nhan_khau INNER JOIN thanh_vien_cua_ho ON thanh_vien_cua_ho.idNhanKhau = nhan_khau.ID "
                + "INNER JOIN (SELECT * FROM thanh_vien_cua_ho INNER JOIN nhan_khau ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau) VoList "
                + "ON VoList.idHoGiaDinh = thanh_vien_cua_ho.idHoGiaDinh AND (VoList.gioiTinh LIKE '%Nữ%' OR VoList.gioiTinh LIKE '%nữ%') "
                + "INNER JOIN (SELECT * FROM thanh_vien_cua_ho INNER JOIN nhan_khau ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau) ChongList "
                + "ON ChongList.idHoGiaDinh = thanh_vien_cua_ho.idHoGiaDinh AND (ChongList.gioiTinh LIKE '%Nam%' OR ChongList.gioiTinh LIKE '%nam%') "
                + "WHERE (2020 - EXTRACT(YEAR FROM nhan_khau.ngaySinh)) < 19 AND ChongList.hoTen != nhan_khau.hoTen AND VoList.hoTen != nhan_khau.hoTen LIMIT " 
                + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));

        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = chauNhoList.getMetaData();
            while (chauNhoList.next()) {
                list.add(new ChauNhoNhanQua(
                    chauNhoList.getInt("ID"), 
                    chauNhoList.getString("Họ tên"), 
                    chauNhoList.getInt("Tuổi"),
                    chauNhoList.getString("Nơi thường trú"), 
                    null,
                    0,
                    chauNhoList.getString("Bố"),
                    chauNhoList.getString("Mẹ")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void addRecord(ChauNhoNhanQua record) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(ChauNhoNhanQua newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(ChauNhoNhanQua oldRecord, ChauNhoNhanQua newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(ChauNhoNhanQua record) {
        // TODO Auto-generated method stub

    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }
    
}
