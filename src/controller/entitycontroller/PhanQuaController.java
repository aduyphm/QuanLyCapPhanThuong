package controller.entitycontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.PhanQua;
import service.DatabaseConnection;

public class PhanQuaController extends EntityController<PhanQua> {

    @Override
    public void getDataFromDatabase(int limit, int page) {
        ResultSet result = DatabaseConnection
                .executeQuery("Select * from phan_qua LIMIT " + Integer.toString(limit) + " OFFSET " + Integer.toString(page * limit));
        if (list != null) {
            list.clear();
        } else {
            list = new ArrayList<>();
        }

        try {
            metaData = result.getMetaData();
            while (result.next()) {
                list.add(new PhanQua(
                    result.getInt("ID"), 
                    result.getString("maPhanQua"), 
                    result.getString("loaiQua"),
                    result.getInt("giaTri"), 
                    result.getString("moTa")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addRecord(PhanQua record) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(int index, PhanQua newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRecord(PhanQua oldRecord, PhanQua newRecord) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord(PhanQua record) {
        DatabaseConnection.executeUpdate("Delete from phan_qua where phan_qua.ID = " + record.getID() + ";");
    }
    
}
