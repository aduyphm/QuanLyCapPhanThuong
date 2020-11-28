package controller.entitycontroller;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public abstract class EntityController <T> {

    protected ArrayList<T> list = null;
    protected ResultSetMetaData metaData = null;

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ResultSetMetaData metaData) {
        this.metaData = metaData;
    }

    public abstract void getDataFromDatabase(int limit, int page);
    public abstract void addRecord(T record);
    public abstract void updateRecord(int index, T newRecord);
    public abstract void updateRecord(T oldRecord, T newRecord);
    public abstract void deleteRecord(int index);
    public abstract void deleteRecord(T record);

}