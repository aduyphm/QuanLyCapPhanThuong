package controller.guicontroller;

import controller.entitycontroller.PhanQuaController;
import entity.PhanQua;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;

public class PhanQuaGuiController extends TableController<PhanQua> {

    public PhanQuaGuiController(TableView<PhanQua> table, ToolBar toolBar) {
        super(table, toolBar, new PhanQuaController(), new String[]{"loaiQua", "moTa", "giaTri"}, 40);
    }

    @Override
    public void addRecord() {
        reloadPage();

    }

    @Override
    public void updateRecord() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRecord() {
        controller.deleteRecord(table.getItems().remove(selectedRow));
    }
    
}
