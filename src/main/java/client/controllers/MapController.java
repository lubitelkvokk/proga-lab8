package client.controllers;

import client.StartClient;
import client.global.UserAuth;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import mid.commands.CommandsEnum;
import mid.converters.StudyGroupToTableViewConverter;
import mid.converters.TableViewToStudyGroup;
import mid.data.*;
import mid.fabrics.message.instance.Message;
import netscape.javascript.JSObject;
import javafx.concurrent.Worker.State;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private AnchorPane anchorPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        UserAuth.setUser(new User("VASYAAAAA", "bibaboba"));

        try {
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {

            new Thread(() -> {
                try {
                    refreshTable();
                } catch (Exception e) {
                }
            }).start();

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


//        inputSearchingGroup.textProperty().addListener((observable, oldValue, newValue) ->
//        {
//            filteredList.setPredicate(studyGroupSearchModel -> {
//                if (newValue.isEmpty() || newValue.isBlank()) {
//                    return true;
//                }
//                String searchKeyword = newValue.toLowerCase();
//
//                if (studyGroupSearchModel.getName().toLowerCase().contains(searchKeyword)) {
//                    return true;
//                }
//                return false;
//            });
//        });
//        sortedList = new SortedList<>(filteredList);
//        try {
//            sortedList.comparatorProperty().bind(studyGroupsTable.comparatorProperty());
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }

    }

    private FilteredList<StudyGroupTableView> filteredList = null;

    private void refreshTable() throws IOException, ClassNotFoundException {
        Message message = new Message(CommandsEnum.SHOW);
        Message response = null;
        try {
            response = StartClient.sendMessageAndGetResponse(message);
            LinkedList<StudyGroupTableView> studyGroupTableViews = new LinkedList<>();
            for (StudyGroup x : response.getStudyGroups()) {
                studyGroupTableViews.add(StudyGroupToTableViewConverter.getTableView(x));
            }
            // очистка контейнера меток
            Platform.runLater(() -> markersGroup.getChildren().clear());
            // добавление новых меток на карту
            for (StudyGroupTableView studyGroupTableView : studyGroupTableViews) {

                Platform.runLater(() -> writeObjectOnMap(studyGroupTableView));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createMark(Integer x, Integer y, Integer radius, String color) {
        Label label = new Label();
        Circle circle = new Circle(radius);
        circle.setStroke(Paint.valueOf("blue"));
        label.setGraphic(circle);
        label.setLayoutX(100);
        label.setLayoutY(200);
        anchorPane.getChildren().add(label);
    }

    private Group markersGroup = new Group(); // создание контейнера для меток

    private void writeObjectOnMap(StudyGroupTableView studyGroupTableView) {

        String str = studyGroupTableView.getUsername(); // ваша строка
        int hashCode = str.hashCode(); // получаем хеш-код строки

// создаем цвет на основе хеш-кода
        java.awt.Color awtColor = new java.awt.Color(hashCode);
// преобразуем цвет в JavaFX
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());

// создаем объект Circle с цветом


        Label label = new Label();
        Circle circle = new Circle(studyGroupTableView.getStudentsCount(), fxColor);
        circle.setStroke(Paint.valueOf("black"));
        label.setGraphic(circle);
        label.setLayoutX(studyGroupTableView.getX());
        label.setLayoutY(studyGroupTableView.getY());
        if (!markersGroup.getChildren().contains(label)) {
            label.setOnMouseClicked((event)->{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        TableViewToStudyGroup.getStudyGroup(studyGroupTableView).toString());
                alert.show();
            });
            markersGroup.getChildren().add(label); // добавление метки в контейнер

        }
        if (!anchorPane.getChildren().contains(markersGroup)) {
            anchorPane.getChildren().add(markersGroup); // добавление контейнера на AnchorPane
        }
    }

}