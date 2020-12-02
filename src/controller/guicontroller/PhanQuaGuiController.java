package controller.guicontroller;

import controller.entitycontroller.PhanQuaController;
import entity.PhanQua;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class PhanQuaGuiController extends TableController<PhanQua> {

    public PhanQuaGuiController(TableView<PhanQua> table, ToolBar toolBar) {
        //super(table, toolBar, new PhanQuaController(), new String[]{"loaiQua", "moTa", "giaTri"}, 40);
        super(table, toolBar, new PhanQuaController(), 
        new String[]{"loaiQua", "moTa", "giaTri"}, 
        new String[]{"Loại quà", "Mô tả", "Giá trị"},
        40);
        setAddForm("/fxml/Demo.fxml");
        setUpdateForm("/fxml/Demo.fxml");

        toolBar.getItems().get(5).setDisable(true);
        toolBar.getItems().get(5).setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                Parent root = createAForm("/fxml/Demo.fxml");

                getDataForForm(root);

                for(Node node : root.lookupAll(".label")){
                    Label label = (Label)node;
        
                    if(label.getText().equals("Thêm quà")){
                        label.setText("Thông tin chi tiết");
                        label.setPrefWidth(250);
                        label.setLayoutX(210);
                    }
                }

                for (Node node : root.getChildrenUnmodifiable()) {
                    if(node instanceof TextField){
                        ((TextField)node).setEditable(false);
                    }
                    if(node instanceof ComboBox){
                        //((ComboBox<String>)node).setDisable(true);
                        //((ComboBox<String>)node).setStyle("-fx-opacity: 1; -fx-background-color: white;");
                        ((ComboBox<String>)node).setStyle("-fx-font: 18px \"System\";-fx-opacity: 1; -fx-background-color: white; -fx-text-fill: black;");
                        ((ComboBox<String>)node).setDisable(true);
                    }
                }

                showAForm(root);

            }
            
        });
    }

    @Override
    public void firstClickOnTable() {
        toolBar.getItems().get(5).setDisable(false);
    }

    @Override
    public void prepareAddRecord() {
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>)addFormRoot.lookup(".combo-box");
        comboBox.getItems().addAll("Khuyến học", "Dịp đặc biệt");
        comboBox.setStyle("-fx-font: 18px \"System\";");
        
    }
    @Override
    public boolean addRecord() {
        PhanQua phanQua = new PhanQua(0, "", 0, "");

        String loaiQua = null;
        for(Node node : addFormRoot.lookupAll(".combo-box")){
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>)node;
            loaiQua = comboBox.getSelectionModel().getSelectedItem();
        }
        if(loaiQua == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Chưa chọn loại quà !!!");
            alert.showAndWait();
            return false;
        }
        phanQua.setLoaiQua(loaiQua);

        for(Node node : addFormRoot.lookupAll(".text-field")){
            TextField textField = (TextField)node;

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
                if(textField.getText().equals("")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("Mô tả không nên để trống !!!");
                    alert.showAndWait();
                    return false;
                }
                phanQua.setMoTa(textField.getText());
            }
        }

        controller.addRecord(phanQua);
        reloadPage();
        return true;
    }

    @Override
    public void prepareUpdateRecord() {
        getDataForForm(updateFormRoot);

        for(Node node : updateFormRoot.lookupAll(".label")){
            Label label = (Label)node;

            if(label.getText().equals("Thêm quà")){
                label.setText("Chỉnh sửa quà");
                label.setPrefWidth(200);
                label.setLayoutX(220);
            }
        }
    }

    @Override
    public boolean updateRecord() {
        PhanQua phanQua = getSelectedItem();

        String loaiQua = null;
        for(Node node : updateFormRoot.lookupAll(".combo-box")){
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>)node;
            loaiQua = comboBox.getSelectionModel().getSelectedItem();
        }
        if(loaiQua == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setContentText("Chưa chọn loại quà !!!");
            alert.showAndWait();
            return false;
        }
        phanQua.setLoaiQua(loaiQua);

        for(Node node : updateFormRoot.lookupAll(".text-field")){
            TextField textField = (TextField)node;

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
                if(textField.getText().equals("")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error!!!");
                    alert.setContentText("Mô tả không nên để trống !!!");
                    alert.showAndWait();
                    return false;
                }
                phanQua.setMoTa(textField.getText());
            }
        }

        controller.updateRecord(phanQua);
        reloadPage();
        return true;
    }

    @Override
    public boolean deleteRecord() {
        controller.deleteRecord(table.getItems().remove(selectedRow));
        return true;
    }

    private void getDataForForm(Parent root){
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>)root.lookup(".combo-box");
        comboBox.getItems().addAll("Khuyến học", "Dịp đặc biệt");
        comboBox.setStyle("-fx-font: 18px \"System\";");

        PhanQua phanQua = getSelectedItem();
        if(phanQua.getLoaiQua().equals("Khuyến học") || phanQua.getLoaiQua().equals("khuyến học")){
            comboBox.getSelectionModel().select("Khuyến học");
        }else{
            comboBox.getSelectionModel().select("Dịp đặc biệt");
        }

        for(Node node : root.lookupAll(".text-field")){
            TextField textField = (TextField)node;

            if(textField.getPromptText().equals("Giá trị")){
                textField.setText("" + phanQua.getGiaTri());
            }

            if(textField.getPromptText().equals("Mô tả")){
                textField.setText(phanQua.getMoTa());
            }
        }
    }
    
}
