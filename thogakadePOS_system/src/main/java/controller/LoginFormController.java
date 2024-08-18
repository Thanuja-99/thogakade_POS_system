package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private Hyperlink hyprFogotPassword;

    @FXML
    private Label lblMsg;

    @FXML
    private AnchorPane loginForm;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

        private String name;
        private  String password;
    @FXML
    void loginOnAction(ActionEvent event) {
        Stage stage = new Stage();
        String name = txtUserName.getText();
        String password = txtPassword.getText();

        if(name.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1234")) {
            lblMsg.setText("Login Successfuly....");
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dash_board_form.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();
        }else {
            lblMsg.setText("Login Fail Try Again....");
        }
    }

}
