package app;

import controller.guicontroller.MainFrameGUIController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
//import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import service.DatabaseConnection;

/* import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date; */

public class QuanLyNhanKhau extends Application {

    // private static Connection myConnection;
    static MainFrameGUIController mainFrameController;
    public static Stage primaryStage = null;
    public static void main(String[] args) {
        /*
         * try { myConnection = DriverManager.getConnection(
         * "jdbc:mysql://localhost:3306/quan_ly_nhan_khau?useUnicode=true&characterEncoding=utf-8",
         * "root", "");
         * 
         * 
         * Statement statement =
         * myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
         * ResultSet.CONCUR_UPDATABLE);
         * 
         * String sql = "SELECT * FROM nhan_khau LIMIT 20";
         * 
         * ResultSet result = statement.executeQuery(sql);
         * 
         * while(result.next()) { if(result.getInt("ID") == 1) {
         * result.updateDate("namSinh", Date.valueOf("2000-1-10")); result.updateRow();
         * } }
         * 
         * result.beforeFirst();
         * 
         * if (!myConnection.isClosed()) myConnection.close();
         * 
         * 
         * } catch (SQLException e) { e.printStackTrace(); }
         */
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //mainFrameController.print();
            
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConnection.initializeConnection("localhost", "3306");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/fxml/MainFrame.fxml").openStream());
        //root.setStyleSheet("/fxml/MainFrameCss.css");
        /* Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds(); */
        QuanLyNhanKhau.primaryStage = primaryStage;
        Scene scene = new Scene(root);

        mainFrameController = (MainFrameGUIController) fxmlLoader.getController();
        mainFrameController.init(root);
        scene.getStylesheets().add("/css/MainFrameCss.css");

        primaryStage.setTitle("Sample application"); 
        primaryStage.setScene(scene);
        primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<Event>(){
            @Override 
            public void handle(Event e) { 
                mainFrameController.onClose();
            } 
        });
        /* primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight()); */
        
        primaryStage.show();

    }
            
}