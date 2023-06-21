package client.controllers;

import client.StartClient;
import client.controllers.util.SavedForEditingStudyGroup;
import client.exeptions.InputException;
import client.global.ResourceBundleSingleton;
import client.global.UserAuth;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import mid.commands.CommandsEnum;
import mid.converters.StudyGroupToTableViewConverter;
import mid.converters.TableViewToStudyGroup;
import mid.data.*;
import mid.fabrics.locale.BundleFabric;
import mid.fabrics.message.MessageFabric;
import mid.fabrics.message.instance.Message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainPageNewController implements Initializable {

    @FXML
    private HBox hBox;

    @FXML
    private Label removeError;

    @FXML
    private Label searchGroup;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button editButton;

    @FXML
    private Button infoButton;

    @FXML
    private TextField inputSearchingGroup;

    @FXML
    private TableView<StudyGroupTableView> studyGroupsTable;

    private ObservableList<StudyGroupTableView> studyGroupTableViews;

    @FXML
    private ChoiceBox<String> languages;

    @FXML
    private Button removeFirstButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button addIfMinButton;

    @FXML
    private Button removeGreaterButton;

    @FXML
    private Button sumOfShouldBeExpelledButton;

    @FXML
    private Button averageOfShouldBeExpelledButton;

    @FXML
    private Button minByNameButton;

    @FXML
    private Label currentUser;

    @FXML
    private Label user;

    @FXML
    private Button visualButton;

    private SortedList<StudyGroupTableView> sortedList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(UserAuth.getUser().getLogin());
        initializeLanguages();
        languages.setOnAction(this::changeLanguage);
        languageValuesInit();
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

        // столбец для вывода id
        TableColumn<StudyGroupTableView, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        studyGroupsTable.getColumns().add(idColumn);

        // столбец для имени владельца
        TableColumn<StudyGroupTableView, String> userColumn = new TableColumn<>("user");
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        studyGroupsTable.getColumns().add(userColumn);


        // столбец для вывода названия группы
        TableColumn<StudyGroupTableView, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        studyGroupsTable.getColumns().add(nameColumn);


        // столбец для вывода информации об админе группы
        TableColumn<StudyGroupTableView, Long> xCoord = new TableColumn<>("x");
        xCoord.setCellValueFactory(new PropertyValueFactory<>("x"));
        studyGroupsTable.getColumns().add(xCoord);

        TableColumn<StudyGroupTableView, Double> yCoord = new TableColumn<>("y");
        yCoord.setCellValueFactory(new PropertyValueFactory<>("y"));
        studyGroupsTable.getColumns().add(yCoord);

        TableColumn<StudyGroupTableView, Integer> studentsCountColumn = new TableColumn<>("studentsCount");
        studentsCountColumn.setCellValueFactory(new PropertyValueFactory<>("studentsCount"));
        studyGroupsTable.getColumns().add(studentsCountColumn);

        TableColumn<StudyGroupTableView, Integer> shouldBeExpelledColumn = new TableColumn<>("shouldBeExpelled");
        shouldBeExpelledColumn.setCellValueFactory(new PropertyValueFactory<>("shouldBeExpelled"));
        studyGroupsTable.getColumns().add(shouldBeExpelledColumn);

        TableColumn<StudyGroupTableView, Integer> transferredStudentsColumn = new TableColumn<>("transferredStudents");
        transferredStudentsColumn.setCellValueFactory(new PropertyValueFactory<>("transferredStudents"));
        studyGroupsTable.getColumns().add(transferredStudentsColumn);

        TableColumn<StudyGroupTableView, Semester> semesterEnumColumn = new TableColumn<>("semesterEnum");
        semesterEnumColumn.setCellValueFactory(new PropertyValueFactory<>("semesterEnum"));
        studyGroupsTable.getColumns().add(semesterEnumColumn);

        TableColumn<StudyGroupTableView, String> nameGroupAdminColumn = new TableColumn<>("nameGroupAdmin");
        nameGroupAdminColumn.setCellValueFactory(new PropertyValueFactory<>("nameGroupAdmin"));
        studyGroupsTable.getColumns().add(nameGroupAdminColumn);

        TableColumn<StudyGroupTableView, Long> heightGroupAdminColumn = new TableColumn<>("heightGroupAdmin");
        heightGroupAdminColumn.setCellValueFactory(new PropertyValueFactory<>("heightGroupAdmin"));
        studyGroupsTable.getColumns().add(heightGroupAdminColumn);

        TableColumn<StudyGroupTableView, String> passportIDGroupAdminColumn = new TableColumn<>("passportIDGroupAdmin");
        passportIDGroupAdminColumn.setCellValueFactory(new PropertyValueFactory<>("passportIDGroupAdmin"));
        studyGroupsTable.getColumns().add(passportIDGroupAdminColumn);

        TableColumn<StudyGroupTableView, Color> eyeColorGroupAdminColumn = new TableColumn<>("eyeColorGroupAdmin");
        eyeColorGroupAdminColumn.setCellValueFactory(new PropertyValueFactory<>("eyeColorGroupAdmin"));
        studyGroupsTable.getColumns().add(eyeColorGroupAdminColumn);

        TableColumn<StudyGroupTableView, Float> xLocationColumn = new TableColumn<>("xLocation");
        xLocationColumn.setCellValueFactory(new PropertyValueFactory<>("xLocation"));
        studyGroupsTable.getColumns().add(xLocationColumn);

        TableColumn<StudyGroupTableView, Float> yLocationColumn = new TableColumn<>("yLocation");
        yLocationColumn.setCellValueFactory(new PropertyValueFactory<>("yLocation"));
        studyGroupsTable.getColumns().add(yLocationColumn);

        TableColumn<StudyGroupTableView, Integer> zLocationColumn = new TableColumn<>("zLocation");
        zLocationColumn.setCellValueFactory(new PropertyValueFactory<>("zLocation"));
        studyGroupsTable.getColumns().add(zLocationColumn);

        TableColumn<StudyGroupTableView, String> nameLocationColumn = new TableColumn<>("nameLocation");
        nameLocationColumn.setCellValueFactory(new PropertyValueFactory<>("nameLocation"));
        studyGroupsTable.getColumns().add(nameLocationColumn);

        inputSearchingGroup.textProperty().addListener((observable, oldValue, newValue) ->
        {
            filteredList.setPredicate(studyGroupSearchModel -> {
                if (newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if (studyGroupSearchModel.getName().toLowerCase().contains(searchKeyword)) {
                    return true;
                }

                if (studyGroupSearchModel.getUsername().toLowerCase().equals(searchKeyword)){
                    return true;
                }
                return false;
            });
        });
        sortedList = new SortedList<>(filteredList);
        try {
            sortedList.comparatorProperty().bind(studyGroupsTable.comparatorProperty());
        } catch (NullPointerException e){
            e.printStackTrace();
        }

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
            // Create new filtered list with current predicate
            FilteredList<StudyGroupTableView> newFilteredList = new FilteredList<>(FXCollections.observableList(studyGroupTableViews), b -> true);
            if (filteredList != null) {
                newFilteredList.setPredicate(filteredList.getPredicate());
            }
            // Replace old filtered list with new filtered list
            filteredList = newFilteredList;
            Platform.runLater(() -> {
                sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(studyGroupsTable.comparatorProperty());
                studyGroupsTable.setItems(sortedList);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void addRow() throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab8/AddingGroupPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        refreshTable();
    }

    @FXML
    private void removeRow() throws IOException, ClassNotFoundException {

        int id = studyGroupsTable.getSelectionModel().getSelectedIndex();
        int id_db = studyGroupsTable.getSelectionModel().getSelectedItem().getId();
        Message response =
                StartClient.
                        sendMessageAndGetResponse(
                                MessageFabric.createMessage(
                                        CommandsEnum.REMOVE_BY_ID,
                                        id_db));
        if (response.getCommand().equals(CommandsEnum.RESPONSE_ERR)){
            Alert alert = new Alert(Alert.AlertType.ERROR, ResourceBundleSingleton.getResourceBundle().getString("removeByIdError"));
            alert.show();
        }

    }

    @FXML
    private void editRow() throws IOException {
        StudyGroupTableView studyGroupTableView = studyGroupsTable.getSelectionModel().getSelectedItem();
        SavedForEditingStudyGroup.setStudyGroupTableView(studyGroupTableView);
        Message request = MessageFabric.createMessage(CommandsEnum.UPDATE, TableViewToStudyGroup.getStudyGroup(studyGroupTableView));
        System.out.println(request.getStudyGroup());
        Message response = null;
        try {
            response = StartClient.sendMessageAndGetResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (response != null && !response.getCommand().equals(CommandsEnum.RESPONSE_ERR)) {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab8/EditingGroupPage.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    ResourceBundleSingleton.getResourceBundle().getString("ownerError"));
            alert.show();
        }
    }

    @FXML
    private void getInfo() throws UnsupportedEncodingException {
        Message request = MessageFabric.createMessage(CommandsEnum.INFO);
        try {
            Message response = StartClient.sendMessageAndGetResponse(request);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    response.getData());
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    new String(ResourceBundleSingleton.getResourceBundle().getString("databaseError").getBytes("ISO-8859-1"), "UTF-8")
            );
            alert.show();
        }
    }

    private void initializeLanguages() {
        languages.getItems().addAll(Languages.getLanguages());
    }

    @FXML
    public void changeLanguage(ActionEvent event) {
        String language = languages.getValue();
        System.out.println(language);
        ResourceBundleSingleton.setResourceBundle(BundleFabric.getLocale("com.example.lab8.locale.locale", language));
        languageValuesInit();
    }

    private void languageValuesInit(){
        ResourceBundle resourceBundle = ResourceBundleSingleton.getResourceBundle();
        addButton.setText(resourceBundle.getString("addButton"));
        addIfMinButton.setText(resourceBundle.getString("addIfMinButton"));
        removeButton.setText(resourceBundle.getString("removeButton"));
        editButton.setText(resourceBundle.getString("editButton"));
        infoButton.setText(resourceBundle.getString("infoButton"));
        removeFirstButton.setText(resourceBundle.getString("removeFirstButton"));
        clearButton.setText(resourceBundle.getString("clearButton"));
        removeGreaterButton.setText(resourceBundle.getString("removeGreaterButton"));
        sumOfShouldBeExpelledButton.setText(resourceBundle.getString("sumOfShouldBeExpelledButton"));
        minByNameButton.setText(resourceBundle.getString("minByNameButton"));
        averageOfShouldBeExpelledButton.setText(resourceBundle.getString("averageOfShouldBeExpelledButton"));
        currentUser.setText(resourceBundle.getString("currentUser"));
    }

    @FXML
    public void removeFirst() {
        Message request = MessageFabric.createMessage(CommandsEnum.REMOVE_FIRST);
        try {
            getDataFromServerByRequest(request);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }

    @FXML
    public void clear() {
        Message request = MessageFabric.createMessage(CommandsEnum.CLEAR);
        try {
            getDataFromServerByRequest(request);
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }

    private void getDataFromServerByRequest(Message request) throws IOException, ClassNotFoundException {
        Message response = StartClient.sendMessageAndGetResponse(request);
        if (response.getCommand().equals(CommandsEnum.RESPONSE_ERR)) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    ResourceBundleSingleton.getResourceBundle().getString(response.getData()));
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    ResourceBundleSingleton.getResourceBundle().getString(response.getData()));
            alert.show();
        }
    }

    @FXML
    private void addIfMin() throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab8/AddingIfMinGroupPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        refreshTable();
    }

    @FXML
    private void removeGreater() throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab8/RemoveGreaterStudyGroupPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        refreshTable();
    }

    @FXML
    private void sumOfShouldBeExpelled() {
        Message request = MessageFabric.createMessage(CommandsEnum.SUM_OF_SHOULD_BE_EXPELLED);
        gettingIntFromServerByRequest(request);
    }

    private void gettingIntFromServerByRequest(Message request) {
        try {
            Message response = StartClient.sendMessageAndGetResponse(request);
            System.out.println(response.getData());

            Alert alert = new Alert(Alert.AlertType.INFORMATION, response.getId().toString());
            alert.show();
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    ResourceBundleSingleton.getResourceBundle().getString("serverUnavailable"));
            alert.show();
        }
    }

    @FXML
    private void averageOfShouldBeExpelled() {
        Message request = MessageFabric.createMessage(CommandsEnum.AVERAGE_OF_SHOULD_BE_EXPELLED);
        gettingIntFromServerByRequest(request);
    }

    @FXML
    private void minByName() {
        Message request = MessageFabric.createMessage(CommandsEnum.MIN_BY_NAME);
        try {
            Message response = StartClient.sendMessageAndGetResponse(request);

            Alert alert;
            if (response.getCommand().equals(CommandsEnum.RESPONSE_ERR)) {
                alert = new Alert(Alert.AlertType.ERROR,
                        ResourceBundleSingleton.getResourceBundle().getString(response.getData()));
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION,
                        response.getStudyGroup().toString());
            }
            alert.show();
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    ResourceBundleSingleton.getResourceBundle().getString("serverUnavailable"));
            alert.show();
        }
    }

    @FXML
    private void getVisualPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab8/MapPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
