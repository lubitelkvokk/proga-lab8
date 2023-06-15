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
import mid.data.Languages;
import mid.fabrics.locale.BundleFabric;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {


    @FXML
    private Label user;


    @FXML
    private Button signOut;


    @FXML
    private void signOut() throws IOException {
        StartClient.changeScene("/com/example/lab8/LoginPage.fxml");
    }

    @FXML
    private ComboBox<String> languages;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(UserAuth.getUser().getLogin());
        languages.getItems().addAll(Languages.getLanguages());
        languages.setOnAction(this::changeLanguage);
    }

    public void changeLanguage(ActionEvent event){
        String language = languages.getValue();
        ResourceBundleSingleton.setResourceBundle(BundleFabric.getLocale("com.example.lab8.locale.locale", language));
        ResourceBundle resourceBundle = ResourceBundleSingleton.getResourceBundle();
        signOut.setText(resourceBundle.getString("sign_out"));
    }
}
