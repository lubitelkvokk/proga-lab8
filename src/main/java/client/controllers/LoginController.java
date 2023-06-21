package client.controllers;

import client.StartClient;
import client.global.ResourceBundleSingleton;
import client.global.UserAuth;
import javafx.collections.ObservableList;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label welcome;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Label loginError;

    @FXML
    private ComboBox<String> languages;


    @FXML
    private Button submitLogin;
    @FXML
    private Button register;

    @FXML
    private void submitLogin() throws IOException, ClassNotFoundException {

        Message request = MessageFabric.createMessage(CommandsEnum.LOGIN_USER);
        User user = new User(login.getText(), password.getText());
        UserAuth.setUser(user);
        Message response = StartClient.sendMessageAndGetResponse(request);
        if (response.getCommand().equals(CommandsEnum.RESPONSE_ERR)) {
            loginError.setText(
                    ResourceBundleSingleton.getResourceBundle().getString(response.getData()));
        } else {
            StartClient.changeScene("/com/example/lab8/MainPageNew.fxml");
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        languages.getItems().addAll(Languages.getLanguages());
        languages.setOnAction(this::changeLanguage);

    }


    public void changeLanguage(ActionEvent event){
        String language = languages.getValue();
        System.out.println(language);
        ResourceBundleSingleton.setResourceBundle(BundleFabric.getLocale("com.example.lab8.locale.locale", language));
        ResourceBundle resourceBundle = ResourceBundleSingleton.getResourceBundle();
        welcome.setText(resourceBundle.getString("welcome"));
        login.setText(resourceBundle.getString("login"));
        password.setText(resourceBundle.getString("password"));
        submitLogin.setText(resourceBundle.getString("submitLogin"));
        register.setText(resourceBundle.getString("register"));
    }

    @FXML
    private void submitRegister() throws IOException {
        StartClient.changeScene("/com/example/lab8/RegisterPage.fxml");
    }

}
