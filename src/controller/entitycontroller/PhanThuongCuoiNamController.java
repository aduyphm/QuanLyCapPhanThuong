package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import entity.HoGiaDinh;
import entity.PhanThuongCuoiNamRecord;

import service.DatabaseConnection;

public class PhanThuongCuoiNamController {
    //private ArrayList<PhanThuongCuoiNamRecord> list = null;

    private ArrayList<HoGiaDinh> list = null;
    private ResultSetMetaData metaData = null;

    public PhanThuongCuoiNamController() {
        getDataFromDatabase(50, 0); //get first page
    }

    public void addRecord(PhanThuongCuoiNamRecord record) {

    }

    public void updateRecord(int index, PhanThuongCuoiNamRecord newRecord) {

    }

    public void deleteRecord(int index) {

    }

    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection
                .executeQuery("Select * from ho_gia_dinh LIMIT " + Integer.toString(limit) + " OFFSET " + Integer.toString(page * 50));
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                /* list.add(new PhanThuongCuoiNamRecord(
                    result.getInt("ID"), 
                    result.getInt("idHocSinh"), 
                    result.getDate("ngay"), 
                    result.getString("namHoc"), 
                    result.getInt("idPhanQua"), 
                    result.getInt("soPhanQua"), 
                    result.getInt("giaTri")
                    )
                ); */
                list.add(new HoGiaDinh(
                    result.getInt("ID"), 
                    result.getString("maHoGiaDinh"), 
                    result.getInt("idChuHo"), 
                    result.getString("maKhuVuc"),
                    result.getString("diaChi"), 
                    Date.valueOf("2000-1-10")
                    //result.getDate("ngayLap")
                    )
                );
            }
            //result.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateToDatabase(){

    }

    public ArrayList<HoGiaDinh> getList() {
        return list;
    }

    public void setList(ArrayList<HoGiaDinh> list) {
        this.list = list;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ResultSetMetaData metaData) {
        this.metaData = metaData;
    }

}
