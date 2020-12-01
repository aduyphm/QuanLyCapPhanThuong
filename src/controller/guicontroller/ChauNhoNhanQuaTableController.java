package controller.guicontroller;

import controller.entitycontroller.ChauNhoNhanQuaController;
import entity.ChauNhoNhanQua;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;

public class ChauNhoNhanQuaTableController extends TableController<ChauNhoNhanQua> {

    public ChauNhoNhanQuaTableController(TableView<ChauNhoNhanQua> table, ToolBar toolBar) {
        super(table, toolBar, new ChauNhoNhanQuaController(), 
        new String[]{"hoTen", "tuoi", "bo", "me", "noiThuongTru", "moTaPhanQua", "giaTri"}, 
        new String[]{"Họ tên", "Tuổi", "Bố", "Mẹ", "Địa chỉ", "Phần quà", "Giá trị"},
        40);
    }

    @Override
    public boolean addRecord() {
        return false;
    }

    @Override
    public boolean updateRecord() {
        return false;
    }

    @Override
    public boolean deleteRecord() {
        return false;
    }
    
}
