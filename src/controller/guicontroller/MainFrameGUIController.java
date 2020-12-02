package controller.guicontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import controller.Demo;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import service.DatabaseConnection;

class InsideMainFrameTab {
    // toggleButton[i] ---> pane[i] and a pane[i] has a table view
    private Node[] toggleButtons;
    private Node[] pane;
    private StackPane stackPane;
    private ToolBar toolBar;
    public static ObservableList<Object> list1;

    InsideMainFrameTab(Set<Node> buttons, Set<Node> inPane, Node inStackPane, Node toolBar){
        this.pane = inPane.toArray(new Node[0]);
        this.toggleButtons = buttons.toArray(new Node[0]);
        this.stackPane = (StackPane)inStackPane;
        this.toolBar = (ToolBar)toolBar;

        ((ToggleButton)toggleButtons[0]).setSelected(true);
        ((ToggleButton)toggleButtons[0]).setStyle(
            "-fx-border-color: #a9a9a9;-fx-border-style: solid hidden solid hidden;"
        );
        pane[0].toFront();

        for (int i = 0; i < toggleButtons.length; i++) {
            ToggleButton node = (ToggleButton)toggleButtons[i];
            int index = i;
            node.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override 
                public void handle(MouseEvent e) { 
                    for (Node node2 : toggleButtons) {
                        ((ToggleButton) node2).setSelected(false);
                    }
                    ((ToggleButton) node).setSelected(true);

                    pane[index].toFront();
                } 
            });
        }
    }

    public <T> void setItemForTable(int tableIndex, ObservableList<T> list){
        Node cur = pane[tableIndex];
        @SuppressWarnings("unchecked")
        TableView<T> table =  (TableView<T>)cur.lookup(".table-view");
        
        final ObservableList<TablePosition> selectedCells = table.getSelectionModel().getSelectedCells();

        TableColumn firstNameCol = new TableColumn("First Name");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Demo, String>("name"));
        firstNameCol.setCellFactory(TextFieldTableCell.<Demo>forTableColumn());

        selectedCells.addListener(new ListChangeListener<TablePosition>() {
            @Override
            public void onChanged(Change change) {
                table.edit(-1, firstNameCol);
            }
        });
        
        table.getColumns().add(firstNameCol);
        list1 = FXCollections.observableArrayList(
            (T)(new Demo("HAha")), (T)(new Demo("HAha1")), (T)(new Demo("HAha2"))
        );

        for (int i = 0; i < 50; i++) {
            list1.add((T)(new Demo("HAha" + Integer.toString(i))));
        }

        table.setItems((ObservableList<T>) list1);

    }

    public void setTable(int index, TableView table){
        //TableView table = (TableView)pane[index].lookup(".table-view");
        AnchorPane anchorPane = (AnchorPane)pane[index];
        anchorPane.getChildren().addAll(table);
        table.toFront();
    }

    public TableView getTable(int index){
        return (TableView)pane[index].lookup(".table-view");
    }

    public Node[] getToggleButtons() {
        return this.toggleButtons;
    }

    public void setToggleButtons(Node[] toggleButtons) {
        this.toggleButtons = toggleButtons;
    }

    public Node[] getPane() {
        return this.pane;
    }

    public void setPane(Node[] pane) {
        this.pane = pane;
    }

    public StackPane getStackPane() {
        return this.stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

}

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
                curRoot.lookupAll(".anchor-pane"), curRoot.lookup(".stack-pane"), curRoot.lookup(".tool-bar")));
        }

        //tabs.get(1).<Demo>setItemForTable(1, null);
        //PhanThuongCuoiNamGuiController ptcnGuiController = 
            //new PhanThuongCuoiNamGuiController(tabs.get(1).getTable(0), tabs.get(1).getToolBar());

        PhanQuaGuiController phanQuaGuiController =
            new PhanQuaGuiController(tabs.get(3).getTable(0), tabs.get(3).getToolBar());

        ChauNhoNhanQuaTableController chauNhoNhanQuaTableController =
            new ChauNhoNhanQuaTableController(tabs.get(2).getTable(0), tabs.get(2).getToolBar());

        DSHocSinhController dsHocSinhController = new DSHocSinhController(tabs.get(1).getTable(0), tabs.get(1).getToolBar());
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
