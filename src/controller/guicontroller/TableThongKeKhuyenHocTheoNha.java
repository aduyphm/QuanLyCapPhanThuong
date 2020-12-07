package controller.guicontroller;

import java.sql.Date;
import java.util.ArrayList;

import app.QuanLyNhanKhau;
import controller.entitycontroller.KhuyenHocTheoNhaController;
import entity.KhuyenHocTheoNha;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class TableThongKeKhuyenHocTheoNha extends TableController<KhuyenHocTheoNha> {

    public TableThongKeKhuyenHocTheoNha(InsideMainFrameTab tab, int index) {
        super(tab, index, new KhuyenHocTheoNhaController(), 
        new String[]{"hocSinhList", "diaChi", "moTaSuatQuaDuocNhan", "moTaPhanQuaDuocNhan", "sumValue"}, 
        new String[]{"Học sinh", "Địa chỉ", "Các loại quà", "Chi tiết", "Tổng giá trị"}, 
        50);
        //toolBar.getItems().get(2).setDisable(true);
        setAddForm("/fxml/ThongKeTheoNhaDialog.fxml");
    }

    @Override
    public void firstClickOnTable() {
        if(toolBar.getItems().size() > 3) toolBar.getItems().get(3).setDisable(true);
        if(toolBar.getItems().size() > 4) toolBar.getItems().get(4).setDisable(true);
    }

    @Override
    public void prepareAddRecord() {
        ComboBox<String> comboBox = (ComboBox<String>)addFormRoot.lookup(".combo-box");
        ArrayList<String> ngayPhatQua = ((KhuyenHocTheoNhaController)controller).getNgayPhatQua();

        comboBox.getItems().addAll(ngayPhatQua);
        if(((KhuyenHocTheoNhaController)controller).getDate() != null){
            comboBox.getSelectionModel().select(((KhuyenHocTheoNhaController)controller).getDate().toString());
        }
        comboBox.setStyle("-fx-font: 18px \"System\";");
    }

    @Override
    public boolean addRecord() {
        ComboBox<String> comboBox = (ComboBox<String>)addFormRoot.lookup(".combo-box");
        String date = comboBox.getSelectionModel().getSelectedItem();
        if(date == null || date.isBlank()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Chưa chọn ngày !!!");
            alert.showAndWait();
            return false;
        }

        ((KhuyenHocTheoNhaController)controller).setDate(Date.valueOf(date));
        reloadPage();

        Node node = QuanLyNhanKhau.primaryStage.getScene().lookup("#phanThuongCuoiNamLabel");
        Label label = (Label)node;

        label.setText(" Ngày phát: " + date);
        label.setStyle("-fx-background-color: #ccebfa;");
        
        return true;
    }

    @Override
    public void onShowing(int index) {
        Node node = QuanLyNhanKhau.primaryStage.getScene().lookup("#phanThuongCuoiNamLabel");
        Label label = (Label)node;
        if(((KhuyenHocTheoNhaController)controller).getDate() != null){
            label.setText(" Ngày phát: " + ((KhuyenHocTheoNhaController)controller).getDate());
            label.setStyle("-fx-background-color: #ccebfa;");
        }
    }

    @Override
    public boolean updateRecord() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteRecord() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
