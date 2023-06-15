package client;

import client.global.UserAuth;
import client.message_processing.getting.MessageGetter;
import client.message_processing.sending.MessageSender;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mid.fabrics.message.instance.Message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

import static client.factory.channel.ChannelFactory.getChannel;


public class StartClient extends Application {


    private static InetSocketAddress address;
    private static DatagramChannel client;

    private static Stage mainStage;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        address = new InetSocketAddress("127.0.0.1", Integer.parseInt(args[0].trim()));
        client = getChannel();
        client.connect(address);
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab8/LoginPage.fxml"));
        stage.setScene(new Scene(root));
        mainStage = stage;
        mainStage.show();
    }


    public static Message sendMessageAndGetResponse(Message message) throws IOException, ClassNotFoundException {
        message.setUser(UserAuth.getUser());
        MessageSender.sendMessage(client, message, address);
        return MessageGetter.getMessage(client);
    }


    public static void changeScene(String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(StartClient.class.getResource(fxmlFile));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
    }

}