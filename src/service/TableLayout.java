package service;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableLayout {

    private static String keyWord[] = {"id", "dia chi" };

    public static void layoutTable(TableView table, ResultSetMetaData metaData) {
        table.getColumns().clear();
        try {
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String label = metaData.getColumnLabel(i + 1);
                TableColumn column = new TableColumn(label);
                column.setCellValueFactory(new PropertyValueFactory<Object, String>(label));

                //column.setPrefWidth(label.length() * 25);
                for (int j = 0; j < keyWord.length; j++) {
                    if(label.toLowerCase().contains(keyWord[j])){
                        column.setMinWidth(label.length() * 18);
                        column.setMaxWidth(label.length() * 18);
                        column.setResizable(false);
                    }
                }
                
                table.getColumns().add(column);
            }    
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
