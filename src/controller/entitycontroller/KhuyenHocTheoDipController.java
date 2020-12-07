package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.KhuyenHocTheoDip;
import service.DatabaseConnection;

public class KhuyenHocTheoDipController extends EntityController<KhuyenHocTheoDip> {

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection
                .executeQuery("Select * from phan_qua_khuyen_hoc LIMIT " + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new KhuyenHocTheoDip(
                    result.getDate("date"), 
                    result.getInt("idQuaGioi"), 
                    result.getInt("idQuaTienTien"), 
                    result.getInt("idQuaTrungBinh"), 
                    result.getInt("tongQua"), 
                    result.getInt("tongTien")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void addRecord(KhuyenHocTheoDip record) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(KhuyenHocTheoDip newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(KhuyenHocTheoDip oldRecord, KhuyenHocTheoDip newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(KhuyenHocTheoDip record) {
        // TODO Auto-generated method stub

    }
    
}
