package client.controllers;

import client.StartClient;
import client.global.ResourceBundleSingleton;
import client.global.UserAuth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mid.commands.CommandsEnum;
import mid.data.Languages;
import mid.data.User;
import mid.fabrics.locale.BundleFabric;
import mid.fabrics.message.MessageFabric;
import mid.fabrics.message.instance.Message;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {


    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Label registration;

    @FXML
    private Button submit;

    @FXML
    private Label registerError;

    @FXML
    private ComboBox<String> languages;


    @FXML
    private void submitRegister() throws IOException, ClassNotFoundException {
        User user = new User(login.getText(), password.getText());
        UserAuth.setUser(user);
        Message request = new Message(CommandsEnum.REGISTER_USER);
        Message response = StartClient.sendMessageAndGetResponse(request);
        if (response.getCommand().equals(CommandsEnum.RESPONSE_ERR)) {
            registerError.setText(response.getData());
        } else {
            StartClient.changeScene("/com/example/lab8/MainPage.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languages.getItems().addAll(Languages.getLanguages());
        languages.setOnAction(this::changeLanguage);
    }

    public void changeLanguage(ActionEvent event) {
        String language = languages.getValue();
        ResourceBundleSingleton.setResourceBundle(BundleFabric.getLocale("com.example.lab8.locale.locale", language));
        ResourceBundle resourceBundle = ResourceBundleSingleton.getResourceBundle();
        registration.setText(resourceBundle.getString("registration"));
        login.setText(resourceBundle.getString("login"));
        password.setText(resourceBundle.getString("password"));
    }
}
