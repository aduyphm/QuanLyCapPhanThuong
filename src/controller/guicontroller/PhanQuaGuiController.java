package controller.guicontroller;

import controller.entitycontroller.PhanQuaController;
import entity.PhanQua;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;

public class PhanQuaGuiController extends TableController<PhanQua> {

    public PhanQuaGuiController(TableView<PhanQua> table, ToolBar toolBar) {
        super(table, toolBar, new PhanQuaController(), new String[]{"loaiQua", "moTa", "giaTri"}, 40);
    }

    @Override
    public boolean addRecord() {

        PhanQua phanQua = new PhanQua(0, "", "", 0, "");
        for(Node node : addFormRoot.lookupAll(".text-field")){
            //System.out.println("ID");
            //if(node instanceof TextField){
            TextField textField = (TextField)node;
            if(textField.getPromptText().equals("ID here")){
                try {
                    
                    phanQua.setID(Integer.parseInt(textField.getText()));
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("ID must be an integer");
                    alert.showAndWait();
                    return false;
                }
            }

            if(textField.getPromptText().equals("Mã phần quà")){
                phanQua.setMaPhanQua(textField.getText());
            }

            if(textField.getPromptText().equals("Loại quà")){
                phanQua.setLoaiQua(textField.getText());
            }

            if(textField.getPromptText().equals("Giá trị")){
                try {
                    phanQua.setGiaTri(Integer.parseInt(textField.getText()));
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("Giá trị must be an integer");
                    alert.showAndWait();
                    return false;
                }
            }

            if(textField.getPromptText().equals("Mô tả")){
                phanQua.setMoTa(textField.getText());
            }

            //}
        }

        controller.addRecord(phanQua);
        reloadPage();
        return true;
    }

    @Override
    public boolean updateRecord() {
        return true;

    }

    @Override
    public boolean deleteRecord() {
        controller.deleteRecord(table.getItems().remove(selectedRow));
        return true;
    }
    
}
