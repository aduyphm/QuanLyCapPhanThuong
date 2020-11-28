package service;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableLayout {

    private static String keyWord[] = {"id", "ngay" };

    public static void layoutTable(TableView table, ResultSetMetaData metaData, List<String> listToDisplay) {
        table.getColumns().clear();
        try {
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            for (int i = 0; i < listToDisplay.size(); i++) {
                String label = listToDisplay.get(i);

                boolean flag = true;
                for (int j = 0; j < metaData.getColumnCount(); j++) {
                    if(metaData.getColumnLabel(j + 1).equals(label)){
                        flag = false;
                        break;
                    }
                }

                if(flag) continue;

                TableColumn column = new TableColumn(label);
                column.setCellValueFactory(new PropertyValueFactory<Object, String>(label));

                //column.setPrefWidth(label.length() * 25);
                for (int j = 0; j < keyWord.length; j++) {
                    if(label.toLowerCase().contains(keyWord[j])){
                        column.setMinWidth(label.length() * 20);
                        column.setMaxWidth(label.length() * 20);
                        column.setResizable(false);
                    }
                }
                
                table.getColumns().add(column);
            }
            /* for (int i = 0; i < metaData.getColumnCount(); i++) {
                String label = metaData.getColumnLabel(i + 1);
                if(listToDisplay != null && !listToDisplay.contains(label)) continue;
                TableColumn column = new TableColumn(label);
                column.setCellValueFactory(new PropertyValueFactory<Object, String>(label));

                //column.setPrefWidth(label.length() * 25);
                for (int j = 0; j < keyWord.length; j++) {
                    if(label.toLowerCase().contains(keyWord[j])){
                        column.setMinWidth(label.length() * 20);
                        column.setMaxWidth(label.length() * 20);
                        column.setResizable(false);
                    }
                }
                
                table.getColumns().add(column);
            }     */
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
