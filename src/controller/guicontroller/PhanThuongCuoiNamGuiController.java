package controller.guicontroller;

import java.sql.SQLException;

import controller.entitycontroller.PhanThuongCuoiNamController;
//import entity.HoGiaDinh;
//import entity.HoGiaDinh;
//import entity.PhanThuongCuoiNamRecord;
import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;

public class PhanThuongCuoiNamGuiController {

    // private TableView<PhanThuongCuoiNamRecord> table;
    private TableView table;
    private PhanThuongCuoiNamController controller;

    public PhanThuongCuoiNamGuiController() {
        table = new TableView<>();

        //table.setStyle(arg0);

        controller = new PhanThuongCuoiNamController();

        try {
            for (int i = 0; i < controller.getMetaData().getColumnCount(); i++) {
                table.getColumns().add(new TableColumn(controller.getMetaData().getColumnLabel(i + 1)));
            }    
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(FXCollections.observableArrayList(controller.getList()));
    }

    public PhanThuongCuoiNamGuiController(TableView table){

        this.table = table;

        controller = new PhanThuongCuoiNamController();

        service.TableLayout.layoutTable(table, controller.getMetaData());

        table.setItems(FXCollections.observableArrayList(controller.getList()));
    }

    public TableView getTable() {
        return table;
    }

    public void setTable(TableView table) {
        this.table = table;
    }

    public PhanThuongCuoiNamController getController() {
        return controller;
    }

    public void setController(PhanThuongCuoiNamController controller) {
        this.controller = controller;
    }

}
