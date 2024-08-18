package controller;

import db.DataBaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {
    @FXML
    private Label lblAddCustomerMsg;
    @FXML
    private ComboBox<String> cmbTitle;

    @FXML
    private DatePicker dobBirth;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dash_board_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titleList = FXCollections.observableArrayList();

        titleList.add("Mr. ");
        titleList.add("Ms. ");
        cmbTitle.setItems(titleList);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        List<Customer> customerList = DataBaseConnector.getInstance().getConnection();

        String id = txtId.getText();
        String contactNumber = txtNumber.getText();

        boolean idExists = customerList.stream().anyMatch(customer -> customer.getId().equals(id));
        boolean contactNumberExists = customerList.stream().anyMatch(customer -> customer.getNumber().equals(contactNumber));

        if (idExists) {
            lblAddCustomerMsg.setText("Error: Customer ID already exists. Please use a different ID.");
        } else if (contactNumberExists) {
            lblAddCustomerMsg.setText("Error: Contact number already exists. Please use a different number.");
        } else {
            customerList.add(
                    new Customer(
                            txtId.getText(),
                            cmbTitle.getValue(),
                            txtName.getText(),
                            txtAddress.getText(),
                            txtNumber.getText(),
                            dobBirth.getValue()
                    )
            );
            clearText();
            lblAddCustomerMsg.setText("Customer added successfully...");
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearText();

    }
    void clearText(){
        txtId.setText(null);
        cmbTitle.setValue(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtNumber.setText(null);
        dobBirth.setValue(null);
    }


}
