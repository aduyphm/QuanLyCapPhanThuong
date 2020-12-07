package controller.guicontroller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Map;

import app.QuanLyNhanKhau;
import controller.entitycontroller.HocSinhController;
import entity.HocSinh;
import entity.PhanQua;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class DSHocSinhController extends TableController<HocSinh> {

    private EventHandler<Event> event = null;
    private int sumValue = 0;
    private int sumGift = 0;

    public DSHocSinhController(InsideMainFrameTab tab, int index) {
        super(tab, index, new HocSinhController(),
                new String[] { "hoTen", "lop", "truong", "diaChi", "hocLuc", "thanhTichDacBiet" },
                new String[] { "Họ tên", "Lớp", "Trường", "Địa chỉ", "Học lực", "Thành tích" }, 40);
        setAddForm("/fxml/Form thêm học sinh.fxml");
        setUpdateForm("/fxml/Form thêm học sinh.fxml");
        toolBar.getItems().get(5).setDisable(true);

        toolBar.getItems().get(5).setOnMouseClicked(s -> {
            Parent detailsForm = createAForm("/fxml/Thông tin chi tiết_HocSinh.fxml");
            getDataForForm(detailsForm);

            for (Node node : detailsForm.lookupAll(".button")) {
                Button button = (Button) node;
                if (button.getText().equals("OK")) {
                    button.setOnMouseClicked(s1 -> {
                        ((Stage) detailsForm.getScene().getWindow()).close();
                        QuanLyNhanKhau.primaryStage.getScene().getRoot().setDisable(false);
                    });
                }
            }

            for (Node node : detailsForm.lookupAll(".text-field")) {
                TextField textField = (TextField) node;
                textField.setEditable(false);
            }

            HocSinh hocSinh = getSelectedItem();

            ImageView imageView = (ImageView) detailsForm.lookup("#minhChungHocLuc");

            // if(!hocSinh.getMinhChungHocLuc().equals(""))
            try {
                imageView.setImage(new Image(hocSinh.getMinhChungHocLuc()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            imageView = (ImageView) detailsForm.lookup("#minhChungThanhTich");

            // if(!hocSinh.getMinhChungThanhTich().equals(""))
            try {
                imageView.setImage(new Image(hocSinh.getMinhChungThanhTich()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            showAForm(detailsForm);
        });

        // toolBar.getItems().get(6).setDisable(true);

        toolBar.getItems().get(6).setOnMouseClicked(s -> {
            Parent giveGiftForm = createAForm("/fxml/Form phát quà cuối năm.fxml");
            giveGiftShowing(giveGiftForm);
            showAForm(giveGiftForm);
        });

    }

    @Override
    public void firstClickOnTable() {
        toolBar.getItems().get(5).setDisable(false);
        // toolBar.getItems().get(6).setDisable(false);
    }

    @Override
    public void prepareAddRecord() {
        prepareForm(addFormRoot);
        for (Node node : addFormRoot.lookupAll(".combo-box")) {
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>) node;
            comboBox.setStyle("-fx-font: 18px \"System\";-fx-opacity: 1; ;");
            if (comboBox.getPromptText().equals("Lớp")) {
                for (int i = 1; i < 13; i++) {
                    comboBox.getItems().add("" + i);
                }
            }
            if (comboBox.getPromptText().equals("Học lực")) {
                comboBox.getItems().addAll("Giỏi", "Tiên tiến", "Trung bình");
            }

        }
    }

    @Override
    public boolean addRecord() {
        HocSinh hocSinh = new HocSinh(0, "", "", "", "", "", "", "", "");

        for (Node node : addFormRoot.lookupAll(".combo-box")) {
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>) node;
            if (comboBox.getPromptText().equals("Lớp")) {
                hocSinh.setLop(comboBox.getSelectionModel().getSelectedItem());
            }
            if (comboBox.getPromptText().equals("Học lực")) {
                hocSinh.setHocLuc(comboBox.getSelectionModel().getSelectedItem());
            }
        }

        for (Node node : addFormRoot.lookupAll(".text-field")) {
            TextField textField = (TextField) node;

            if (textField.getText().isBlank()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Hãy nhập đầy đủ thông tin !!!");
                alert.setContentText("Thông tin chưa đầy đủ !!!");
                alert.showAndWait();
                return false;
            }
            if (textField.getPromptText().equals("Họ tên")) {
                hocSinh.setHoTen(textField.getText());
            }
            if (textField.getPromptText().equals("Địa chỉ")) {
                hocSinh.setDiaChi(textField.getText());
            }

            if (textField.getPromptText().equals("Trường")) {
                hocSinh.setTruong(textField.getText());
            }
            if (textField.getPromptText().equals("Thành tích")) {
                hocSinh.setThanhTichDacBiet(textField.getText());
            }

            if (textField.getPromptText().equals("Minh chứng học lực")) {
                hocSinh.setMinhChungHocLuc(textField.getText());
            }
            if (textField.getPromptText().equals("Minh chứng thành tích")) {
                if (!hocSinh.getThanhTichDacBiet().isBlank())
                    hocSinh.setMinhChungThanhTich("file:///" + textField.getText());
            }

        }

        controller.addRecord(hocSinh);
        reloadPage();
        return true;
    }

    @Override
    public void prepareUpdateRecord() {
        prepareForm(updateFormRoot);
        getDataForForm(updateFormRoot);
        for (Node node : updateFormRoot.lookupAll(".label")) {
            Label label = (Label) node;

            if (label.getText().equals("Thêm học sinh mới")) {
                label.setText("Sửa đổi thông tin học sinh");
                label.setLayoutX(300);
            }

        }

        HocSinh hocSinh = getSelectedItem();
        for (Node node : updateFormRoot.lookupAll(".combo-box")) {
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>) node;
            comboBox.setStyle("-fx-font: 18px \"System\";-fx-opacity: 1; ;");
            if (comboBox.getPromptText().equals("Lớp")) {
                for (int i = 1; i < 13; i++) {
                    comboBox.getItems().add("" + i);
                }
                comboBox.getSelectionModel().select("" + hocSinh.getLop());
            }
            if (comboBox.getPromptText().equals("Học lực")) {
                comboBox.getItems().addAll("Giỏi", "Tiên tiến", "Trung bình");
                comboBox.getSelectionModel().select("" + hocSinh.getHocLuc());
            }

        }

        for (Node node : updateFormRoot.lookupAll(".text-field")) {
            TextField textField = (TextField) node;

            if (textField.getPromptText().equals("Họ tên")) {
                textField.setEditable(false);
            }

            if (textField.getPromptText().equals("Địa chỉ")) {
                textField.setEditable(false);
            }

        }
    }

    @Override
    public boolean updateRecord() {
        HocSinh hocSinh = getSelectedItem();

        for (Node node : updateFormRoot.lookupAll(".combo-box")) {
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>) node;
            if (comboBox.getPromptText().equals("Lớp")) {
                hocSinh.setLop(comboBox.getSelectionModel().getSelectedItem());
            }
            if (comboBox.getPromptText().equals("Học lực")) {
                hocSinh.setHocLuc(comboBox.getSelectionModel().getSelectedItem());
            }
        }

        for (Node node : updateFormRoot.lookupAll(".text-field")) {
            TextField textField = (TextField) node;

            if (!textField.getPromptText().equals("Minh chứng thành tích") && textField.getText().isBlank()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Hãy nhập đầy đủ thông tin !!!");
                alert.setContentText("Thông tin chưa đầy đủ !!!");
                alert.showAndWait();
                return false;
            }
            if (textField.getPromptText().equals("Họ tên")) {
                hocSinh.setHoTen(textField.getText());
            }
            if (textField.getPromptText().equals("Địa chỉ")) {
                hocSinh.setDiaChi(textField.getText());
            }

            if (textField.getPromptText().equals("Trường")) {
                hocSinh.setTruong(textField.getText());
            }
            if (textField.getPromptText().equals("Thành tích")) {
                hocSinh.setThanhTichDacBiet(textField.getText());
            }

            if (textField.getPromptText().equals("Minh chứng học lực")) {
                hocSinh.setMinhChungHocLuc("file:///" + textField.getText());
            }
            if (textField.getPromptText().equals("Minh chứng thành tích")) {
                if (!hocSinh.getThanhTichDacBiet().isBlank())
                    hocSinh.setMinhChungThanhTich("file:///" + textField.getText());
            }

        }

        controller.updateRecord(hocSinh);
        reloadPage();
        return true;
    }

    @Override
    public boolean deleteRecord() {
        // TODO Auto-generated method stub
        return false;
    }

    private void prepareForm(Parent root) {
        for (Node node : root.lookupAll(".button")) {
            Button button = (Button) node;
            if (button.getText().equals("Link1")) {
                button.setOnMouseClicked(s -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src"));
                    fileChooser.getExtensionFilters()
                            .add(new ExtensionFilter("Image File only !!!", "*.png", "*.jpeg", "*.jpg", "*.bmp"));
                    File path = new File("");
                    TextField textField = new TextField();
                    try {
                        path = fileChooser.showOpenDialog((root.getScene().getWindow())).getAbsoluteFile();
                        for (Node node2 : root.lookupAll(".text-field")) {
                            textField = (TextField) node2;
                            if (textField.getPromptText().equals("Minh chứng học lực")) {
                                Path pathAbsolute = Paths.get(path.getAbsolutePath());
                                Path pathBase = Paths.get(System.getProperty("user.dir") + "/src");
                                String pathRelative = pathBase.relativize(pathAbsolute).toString();

                                textField.setText(pathRelative.replace("\\", "/"));
                            }

                        }
                    } catch (Exception e) {
                        if (path != null)
                            textField.setText(path.getAbsolutePath().replace("\\", "/"));
                        return;
                    }
                });

            }
            if (button.getText().equals("Link2")) {
                button.setOnMouseClicked(s -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src"));
                    fileChooser.getExtensionFilters()
                            .add(new ExtensionFilter("Image File only !!!", "*.png", "*.jpeg", "*.jpg", "*.bmp"));
                    File path = new File("");
                    TextField textField = new TextField();
                    try {
                        path = fileChooser.showOpenDialog((root.getScene().getWindow())).getAbsoluteFile();
                        for (Node node2 : root.lookupAll(".text-field")) {
                            textField = (TextField) node2;
                            if (textField.getPromptText().equals("Minh chứng thành tích")) {
                                Path pathAbsolute = Paths.get(path.getAbsolutePath());
                                Path pathBase = Paths.get(System.getProperty("user.dir") + "/src");
                                String pathRelative = pathBase.relativize(pathAbsolute).toString();
                                textField.setText(pathRelative.replace("\\", "/"));
                            }

                        }
                    } catch (Exception e) {
                        if (path != null)
                            textField.setText(path.getAbsolutePath().replace("\\", "/"));
                        return;
                    }
                });
            }
        }
    }

    private void getDataForForm(Parent root) {
        HocSinh hocSinh = getSelectedItem();
        for (Node node : root.lookupAll(".text-field")) {
            TextField textField = (TextField) node;

            if (textField.getPromptText().equals("Họ tên")) {
                textField.setText(hocSinh.getHoTen());
            }

            if (textField.getPromptText().equals("Địa chỉ")) {
                textField.setText(hocSinh.getDiaChi());
            }

            if (textField.getPromptText().equals("Trường")) {
                textField.setText(hocSinh.getTruong());
            }
            if (textField.getPromptText().equals("Thành tích")) {
                textField.setText(hocSinh.getThanhTichDacBiet());
            }

            if (textField.getPromptText().equals("Lớp")) {
                textField.setText(hocSinh.getLop());
            }
            if (textField.getPromptText().equals("Học lực")) {
                textField.setText(hocSinh.getHocLuc());
            }

            if (textField.getPromptText().equals("Minh chứng học lực")) {
                textField.setText(hocSinh.getMinhChungHocLuc().replace("file:///", ""));
            }
            if (textField.getPromptText().equals("Minh chứng thành tích")) {
                textField.setText(hocSinh.getMinhChungThanhTich().replace("file:///", ""));
            }

        }
    }

    @Override
    public void onShowing(int index) {
        Label label = (Label)QuanLyNhanKhau.primaryStage.getScene().lookup("#phanThuongCuoiNamLabel");
        label.setText(null);
        label.setStyle("-fx-background-color: transparent;");
    }

    private void giveGiftShowing(Parent root) {
        ArrayList<PhanQua> phanQuas = ((HocSinhController) controller).getAllGift();
        root.lookup(".date-picker").setStyle("-fx-font: 18px \"System\";");
        for (Node node : root.lookupAll(".combo-box")) {
            @SuppressWarnings("unchecked")
            ComboBox<String> comboBox = (ComboBox<String>) node;
            comboBox.setStyle("-fx-font: 18px \"System\";");
            for (int i = 0; i < phanQuas.size(); i++) {
                comboBox.getItems().add(phanQuas.get(i).getMoTa());
            }
        }

        Integer index[] = new Integer[4];
        for (Node node : root.lookupAll(".button")) {
            Button button = (Button) node;
            if (button.getText().equals("Dự tính")) {
                button.setOnMouseClicked(s -> {
                    Map<String, Integer> map = ((HocSinhController) controller).getAllStudentsPerType();
                    String key[] = { "Giỏi", "Tiên tiến", "Trung bình", "Thành tích đặc biệt" };

                    for (Node node1 : root.lookupAll(".combo-box")) {
                        @SuppressWarnings("unchecked")
                        ComboBox<String> comboBox1 = (ComboBox<String>) node1;
                        if (comboBox1.getSelectionModel().getSelectedIndex() == -1) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setHeaderText("Chưa chọn đủ phần quà");
                            alert.setContentText("Chưa chọn đủ phần quà");
                            ((Stage) root.getScene().getWindow()).setAlwaysOnTop(false);
                            alert.showAndWait();
                            ((Stage) root.getScene().getWindow()).setAlwaysOnTop(true);
                            return;
                        }
                        if (comboBox1.getPromptText().equals("Quà cho học sinh giỏi")) {
                            index[0] = comboBox1.getSelectionModel().getSelectedIndex();
                            index[3] = comboBox1.getSelectionModel().getSelectedIndex();
                        }
                        if (comboBox1.getPromptText().equals("Quà cho học sinh tiên tiến")) {
                            index[1] = comboBox1.getSelectionModel().getSelectedIndex();
                        }
                        if (comboBox1.getPromptText().equals("Quà cho học sinh trung bình")) {
                            index[2] = comboBox1.getSelectionModel().getSelectedIndex();
                        }
                    }

                    sumValue = 0;
                    sumGift = 0;

                    for (int i = 0; i < key.length; i++) {
                        sumValue += map.get(key[i]) * phanQuas.get(index[i]).getGiaTri();
                        sumGift += map.get(key[i]);
                    }

                    for (Node node1 : root.lookupAll(".text-field")) {
                        TextField textField = (TextField) node1;
                        if (textField.getPromptText() == null)
                            continue;
                        if (textField.getPromptText().equals("Tổng tiền phải chi")) {
                            DecimalFormat df = new DecimalFormat();
                            // df.setDecimalFormatSymbols("");
                            DecimalFormatSymbols dcms = new DecimalFormatSymbols();
                            dcms.setGroupingSeparator('.');

                            df.setDecimalFormatSymbols(dcms);
                            df.setGroupingSize(3);
                            // df.setMaximumFractionDigits(2);
                            textField.setText(df.format(sumValue) + " VND");
                        }
                        if (textField.getPromptText().equals("Tổng quà sẽ phát")) {
                            textField.setText(sumGift + " suất");
                        }
                    }
                });
            }

            if (button.getText().equals("Apply")) {

                event = new EventHandler<Event>() {

                    @Override
                    public void handle(Event arg0) {

                    }

                    @Override
                    public boolean equals(Object obj) {
                        return onClickApplyButton(root, phanQuas, index);
                    }

                };

            }

        }

    }

    private boolean onClickApplyButton(Parent root, ArrayList<PhanQua> phanQuas, Integer[] index) {
        int year = 0;
        for (Node node1 : root.lookupAll(".text-field")) {
            TextField textField = (TextField) node1;
            if (textField.getText() == null || textField.getText().isBlank()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Điền đẩy đủ thông tin");
                alert.setContentText("Hãy dự tính trước khi làm nhá!!!");
                ((Stage) root.getScene().getWindow()).setAlwaysOnTop(false);
                alert.showAndWait();
                ((Stage) root.getScene().getWindow()).setAlwaysOnTop(true);
                return false;
            }
            if (textField.getPromptText() != null && textField.getPromptText().equals("Năm học")) {
                try {
                    year = Integer.parseInt(textField.getText());
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Năm học không đúng!!!");
                    alert.setContentText("Năm học sai òi!!!");
                    ((Stage) root.getScene().getWindow()).setAlwaysOnTop(false);
                    alert.showAndWait();
                    ((Stage) root.getScene().getWindow()).setAlwaysOnTop(true);
                    return false;
                }

            }

        }

        DatePicker datePicker = ((DatePicker) root.lookup(".date-picker"));

        try {
            ((HocSinhController) controller).giveGift(Date.valueOf(datePicker.getValue()), year, phanQuas, index, sumGift, sumValue);
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Trùng ngày phát với các đợt trước!!!");
            alert.setContentText("Đợt đó phát zòi!!!");
            ((Stage) root.getScene().getWindow()).setAlwaysOnTop(false);
            alert.showAndWait();
            ((Stage) root.getScene().getWindow()).setAlwaysOnTop(true);
            return true;
        }
        return true;
    }

    @Override
    public boolean actionOnForm() {
        return event.equals(null);
    }

}
