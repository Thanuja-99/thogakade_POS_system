package db;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DataBaseConnector {
    private static DataBaseConnector instance;
    private List<Customer>connection;

    private DataBaseConnector(){
        connection=new ArrayList<>();
    }
    public List<Customer> getConnection(){
        return connection;
    }

    public static DataBaseConnector getInstance(){
        return null == instance ? instance = new DataBaseConnector() : instance;
    }


}
