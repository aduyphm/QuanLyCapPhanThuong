package controller.guicontroller;

import controller.entitycontroller.PhanThuongCuoiNamController;
import entity.HoGiaDinh;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;

public class PhanThuongCuoiNamGuiController extends TableController<HoGiaDinh>{

    // private TableView<PhanThuongCuoiNamRecord> table;
    public PhanThuongCuoiNamGuiController(TableView<HoGiaDinh> table, ToolBar toolBar){
        super(table, toolBar, new PhanThuongCuoiNamController(), new String[]{"ID", "ngayLap", "diaChi", "idChuHo"}, 40);
        setAddForm("/fxml/FormDemo.fxml");
        setUpdateForm("/fxml/FormDemo.fxml");
    }

    @Override
    public void addRecord() {
        System.out.println("Add record");
        /* for(Node node : addFormRoot.lookupAll("")){
            
        } */
    }

    @Override
    public void updateRecord() {
        System.out.println("Update record");
    }

    @Override
    public void deleteRecord() {
        System.out.println("Delete record");
        //controller.deleteRecord(table.getItems().remove(selectedRow));
    }

}
