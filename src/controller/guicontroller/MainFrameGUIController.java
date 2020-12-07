package controller.guicontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import service.DatabaseConnection;

public class MainFrameGUIController extends Node implements Initializable {

    @FXML
    private TabPane root;
    private ArrayList<InsideMainFrameTab> tabs = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void init(Parent root) {
        try {
            this.root = (TabPane)root;
        } catch (Exception e) {
            System.out.println("MainGUIController erorr!!!");
        }

        ObservableList<Tab> allTabs = this.root.getTabs();

        for (int i = 0; i < allTabs.size(); i++) {
            Parent curRoot = ((Parent) allTabs.get(i).getContent());
            tabs.add(new InsideMainFrameTab(curRoot.lookupAll(".toggle-button"), 
                curRoot.lookupAll(".anchor-pane"), curRoot.lookup(".stack-pane"), curRoot.lookupAll(".tool-bar")));
        }

        //tabs.get(1).<Demo>setItemForTable(1, null);
        //PhanThuongCuoiNamGuiController ptcnGuiController = 
            //new PhanThuongCuoiNamGuiController(tabs.get(1).getTable(0), tabs.get(1).getToolBar());

        PhanQuaGuiController phanQuaGuiController =
            new PhanQuaGuiController(tabs.get(3).getTable(0), tabs.get(3).getToolBar()[0]);

        ChauNhoNhanQuaTableController chauNhoNhanQuaTableController =
            new ChauNhoNhanQuaTableController(tabs.get(2).getTable(0), tabs.get(2).getToolBar()[0]);

        DSHocSinhController dsHocSinhController = new DSHocSinhController(tabs.get(1), 0);
        TableThongKeKhuyenHocTheoNha khuyenHocTheoNhaController = new TableThongKeKhuyenHocTheoNha(tabs.get(1), 1);
        TableThongKeKhuyenHocTheoDip tableThongKeKhuyenHocTheoDip = new TableThongKeKhuyenHocTheoDip(tabs.get(1), 2);
        //tabs.get(1).setTable(0, ptcnGuiController.getTable());
        
    }

    public void onClose(){
        DatabaseConnection.closeConnection();
    }

    public void print(){
        /* for(Object obj : InsideMainFrameTab.list1){
            System.out.println(((Demo)obj).getName());
        } */
    }

}
