package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.HocSinh;
import service.DatabaseConnection;

public class HocSinhController extends EntityController<HocSinh>{

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection
                .executeQuery("Select * from hoc_sinh LIMIT " + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));
        
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new HocSinh(
                    result.getInt("ID"), 
                    result.getString("hoTen"), 
                    result.getString("lop"), 
                    result.getString("truong"), 
                    result.getString("hocLuc"), 
                    result.getString("thanhTichDacBiet"), 
                    result.getString("diaChi"),
                    result.getString("minhChungHocLuc"),
                    result.getString("minhChungThanhTich")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addRecord(HocSinh record) {
        DatabaseConnection.executeUpdate("INSERT INTO hoc_sinh VALUES (null, null, '"
        + record.getHoTen() + "', '"
        + record.getLop() + "', '"
        + record.getTruong() + "', '"
        + record.getHocLuc() + "', '"
        + record.getThanhTichDacBiet() + "', '"
        + record.getDiaChi() + "')"
        );
    }

    @Override
    public void updateRecord(HocSinh newRecord) {
        DatabaseConnection.executeUpdate("UPDATE hoc_sinh SET "
        + "hoTen = '" + newRecord.getHoTen() + "', "
        + "lop = '" + newRecord.getLop() + "', "
        + "truong = '" + newRecord.getTruong() + "', "
        + "hocLuc = '" + newRecord.getHocLuc() + "', "
        + "thanhTichDacBiet = '" + newRecord.getThanhTichDacBiet() + "', "
        + "diaChi = '" + newRecord.getDiaChi() + "', "
        + "minhChungHocLuc = '" + newRecord.getMinhChungHocLuc() + "', "
        + "minhChungThanhTich = '" + newRecord.getMinhChungThanhTich()
        + "' WHERE ID = " + newRecord.getID()
        );
    }

    @Override
    public void updateRecord(HocSinh oldRecord, HocSinh newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(HocSinh record) {
        // TODO Auto-generated method stub

    }
    
}
