package controller;

import com.jfoenix.controls.JFXTextField;
import db.DataBaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewCustomerFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBirth;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TextField txtAddressS;

    @FXML
    private TextField txtBirthS;

    @FXML
    private TextField txtContactS;

    @FXML
    private TextField txtIdS;

    @FXML
    private TextField txtNameS;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Customer selectedCustomer=tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer !=null){
            DataBaseConnector.getInstance().getConnection().remove(selectedCustomer);
            loadTable();
           clearSearchBoxes();
           txtSearch.setText(null);


        }

    }
    private void clearSearchBoxes() {
        txtIdS.clear();
        txtNameS.clear();
        txtAddressS.clear();
        txtContactS.clear();
        txtBirthS.clear();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colBirth.setCellValueFactory(new PropertyValueFactory<>("birth"));

        List<Customer> customerList = DataBaseConnector.getInstance().getConnection();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        customerList.forEach(obj->{
            customerObservableList.add(obj);
        });
        tblCustomer.setItems(customerObservableList);
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchText = txtSearch.getText().trim().toLowerCase();

        if (!searchText.isEmpty()) {
            List<Customer> customerList = DataBaseConnector.getInstance().getConnection();


            for (Customer customer : customerList) {
                if (customer.getId().toLowerCase().contains(searchText) ||
                        customer.getName().toLowerCase().contains(searchText) ||
                        customer.getAddress().toLowerCase().contains(searchText) ||
                        customer.getNumber().toLowerCase().contains(searchText) ||
                        customer.getBirth().toString().toLowerCase().contains(searchText)) {


                    setTextToValues(customer);
                    break;
                }
            }
        }
    }

    private void setTextToValues(Customer customer) {
        if (customer != null) {
            txtIdS.setText(customer.getId());
            txtNameS.setText(customer.getName());
            txtAddressS.setText(customer.getAddress());
            txtContactS.setText(customer.getNumber());
            txtBirthS.setText(customer.getBirth().toString());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        Customer selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {

            selectedCustomer.setId(txtIdS.getText());
            selectedCustomer.setName(txtNameS.getText());
            selectedCustomer.setAddress(txtAddressS.getText());
            selectedCustomer.setNumber(txtContactS.getText());
            selectedCustomer.setBirth(LocalDate.parse(txtBirthS.getText()));


            List<Customer> customerList = DataBaseConnector.getInstance().getConnection();


            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getId().equals(selectedCustomer.getId())) {
                    customerList.set(i, selectedCustomer);
                    break;
                }
            }


            loadTable();
        }
    }
    private void loadTable(){
        List<Customer> customerList = DataBaseConnector.getInstance().getConnection();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(customerList);
        tblCustomer.getItems().clear();
        tblCustomer.setItems(customerObservableList);
        tblCustomer.refresh();
    }



}
