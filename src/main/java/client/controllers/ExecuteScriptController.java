package client.controllers;

import client.StartClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mid.commands.CommandsEnum;
import mid.fabrics.message.MessageFabric;
import mid.fabrics.message.instance.Message;

import java.net.URL;
import java.util.ResourceBundle;

public class ExecuteScriptController implements Initializable {

    @FXML
    private Label scriptName;

    @FXML
    private TextField scriptNameTextField;

    @FXML
    private void submit(){
        Message request = MessageFabric.createMessage(CommandsEnum.EXECUTE_SCRIPT, scriptNameTextField.getText());
        try{
            Message response = StartClient.sendMessageAndGetResponse(request);
            Alert alert = new Alert(Alert.AlertType.ERROR, response.getData());
            alert.show();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
