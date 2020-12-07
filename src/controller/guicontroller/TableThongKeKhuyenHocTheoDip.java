package controller.guicontroller;

import app.QuanLyNhanKhau;
import controller.entitycontroller.KhuyenHocTheoDipController;
import entity.KhuyenHocTheoDip;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class TableThongKeKhuyenHocTheoDip extends TableController<KhuyenHocTheoDip> {

    public TableThongKeKhuyenHocTheoDip(InsideMainFrameTab tab, int index) {
        super(tab, index, new KhuyenHocTheoDipController(), 
        new String[]{"date", "tongQua", "tongTien"}, 
        new String[]{"Ngày phát", "Tổng quà đã phát", "Tổng tiền"}, 
        50);
        disable(2, toolBar.getItems().size());
    }

    @Override
    public void onShowing(int index) {
        Node node = QuanLyNhanKhau.primaryStage.getScene().lookup("#phanThuongCuoiNamLabel");
        Label label = (Label)node;

        label.setText(null);
        label.setStyle("-fx-background-color: transparent;");
    }

    @Override
    public void firstClickOnTable() {
        disable(2, toolBar.getItems().size());
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
