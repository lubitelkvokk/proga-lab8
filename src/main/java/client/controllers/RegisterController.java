package client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController {


    @FXML
    private TextField registerLogin;

    @FXML
    private TextField registerPassword;

    @FXML
    private void submitRegister(){
        System.out.println(registerLogin.getText());
        System.out.println(registerPassword.getText());
    }
}
