package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

class InsideMainFrameTab {
    // toggleButton[i] ---> pane[i]
    private Node[] toggleButtons;
    private Node[] pane;
    private StackPane stackPane;

    InsideMainFrameTab(Set<Node> buttons, Set<Node> inPane, Node inStackPane){
        this.pane = inPane.toArray(new Node[0]);
        this.toggleButtons = buttons.toArray(new Node[0]);
        this.stackPane = (StackPane)inStackPane;

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

}

public class MainFrameGUIController extends Node implements Initializable {
    /* @FXML
    private ToggleButton trangChuTongQuan;
    @FXML
    private ToggleButton trangChuLich; */

    /* @FXML
    private ToggleButton dsHsPTKhuyenHoc;
    @FXML
    private ToggleButton dsPTKHTheoNha;
    @FXML
    private ToggleButton dsPTKHTheoDip;
    @FXML
    private ToggleButton dsChildQua;
    @FXML
    private ToggleButton dsQuaTheoNha;
    @FXML
    private ToggleButton dsQuaTheoDip;

    ArrayList<ToggleButton> toggleButtons = new ArrayList<>(); */

    @FXML
    private TabPane root;
    @FXML
    private Tab trangChu;
    @FXML
    private Tab dsKhuyenHoc;
    @FXML
    private Tab dsQua;

    private ArrayList<InsideMainFrameTab> tabs = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /* toggleButtons.add(dsHsPTKhuyenHoc);
        toggleButtons.add(dsPTKHTheoNha);
        toggleButtons.add(dsPTKHTheoDip);
        toggleButtons.add(dsChildQua);
        toggleButtons.add(dsQuaTheoNha);
        toggleButtons.add(dsQuaTheoDip);
        setActionForAllToggleButton(); */
    }

    public void init(Parent root) {
        try {
            this.root = (TabPane)root;
        } catch (Exception e) {
            System.out.println("MainGUIController erorr!!!");
        }

        ObservableList<Tab> allTabs = this.root.getTabs();

        for (int i = 0; i < 3; i++) {
            Parent curRoot = ((Parent) allTabs.get(i).getContent());
            tabs.add(new InsideMainFrameTab(curRoot.lookupAll(".toggle-button"), 
                curRoot.lookupAll(".anchor-pane"), curRoot.lookup(".stack-pane")));
        }

    }

}
