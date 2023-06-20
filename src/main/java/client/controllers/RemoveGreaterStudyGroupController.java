package client.controllers;

import client.StartClient;
import client.exeptions.InputException;
import client.global.ResourceBundleSingleton;
import client.global.UserAuth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mid.commands.CommandsEnum;
import mid.data.*;
import mid.data.processing.StudyGroupProcessing;
import mid.fabrics.message.MessageFabric;
import mid.fabrics.message.instance.Message;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RemoveGreaterStudyGroupController implements Initializable {


    @FXML
    private Button addStudyGroupButton;

    @FXML
    private TextField creationDateStudyGroup;

    @FXML
    private Label creationDateStudyGroupException;

    @FXML
    private ChoiceBox<Color> eyeColorGroupAdmin;

    @FXML
    private Label eyeColorGroupAdminException;

    @FXML
    private TextField nameGroupAdmin;

    @FXML
    private Label nameGroupAdminException;

    @FXML
    private TextField nameLocation;

    @FXML
    private Label nameLocationException;

    @FXML
    private TextField nameStudyGroup;

    @FXML
    private Label nameStudyGroupException;

    @FXML
    private TextField passportIdGroupAdmin;

    @FXML
    private Label passportIdGroupAdminException;

    @FXML
    private ChoiceBox<Semester> semesterStudyGroup;

    @FXML
    private Label semesterStudyGroupException;

    @FXML
    private TextField shouldBeExpelledStudyGroup;

    @FXML
    private Label shouldBeExpelledStudyGroupException;

    @FXML
    private TextField studentsCountStudyGroup;

    @FXML
    private Label studentsCountStudyGroupException;

    @FXML
    private TextField transferredStudentsStudyGroup;

    @FXML
    private Label transferredStudentsStudyGroupException;

    @FXML
    private TextField xLocation;

    @FXML
    private TextField xStudyGroup;

    @FXML
    private TextField yLocation;

    @FXML
    private Label yLocationException;

    @FXML
    private TextField yStudyGroup;

    @FXML
    private Label yStudyGroupException;

    @FXML
    private TextField zLocation;

    @FXML
    private TextField heightGroupAdmin;

    @FXML
    private Label heightGroupAdminException;

    @FXML
    private Label response;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSemesterStudyGroup();
        setEyeColorGroupAdmin();
    }

    public void setSemesterStudyGroup() {
        ObservableList<Semester> semesters = FXCollections.observableList(Arrays.stream(Semester.values()).toList());
        semesterStudyGroup.setItems(semesters);
    }

    public void setEyeColorGroupAdmin() {
        ObservableList<Color> colors = FXCollections.observableList(Arrays.stream(Color.values()).toList());
        eyeColorGroupAdmin.setItems(colors);
    }

    @FXML
    public void removeGreater() {
        response.setText("");
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setCreationDate(ZonedDateTime.now());
        boolean indicator = false;
        StudyGroupProcessing studyGroupProcessing = new StudyGroupProcessing();
        // Вводим название группы
        try {
            studyGroup.setName(nameStudyGroup.getText());
            studyGroupProcessing.checkNameStudyGroup(studyGroup.getName());
            nameStudyGroupException.setText("");
        } catch (InputException e){
            indicator = true;
            nameStudyGroupException.setText(e.getMessage());
        }
        // Вводим количество студентов в группе
        try {
            studyGroup.setStudentsCount(Integer.parseInt(studentsCountStudyGroup.getText()));
            studyGroupProcessing.checkStudentsCountStudyGroup(studyGroup);
            studentsCountStudyGroupException.setText("");
        } catch (InputException e) {
            indicator = true;
            studentsCountStudyGroupException.setText(e.getMessage());
        } catch (Exception e){
            indicator = true;
            studentsCountStudyGroupException.setText(ResourceBundleSingleton.getResourceBundle().getString("nullFieldException"));
        }
        // enter count of transferred students
        try {
            studyGroup.setTransferredStudents(Integer.parseInt(transferredStudentsStudyGroup.getText()));
            studyGroupProcessing.checkTransferredStudentsStudyGroup(studyGroup);
            transferredStudentsStudyGroupException.setText("");
        } catch (InputException e) {
            indicator = true;
            transferredStudentsStudyGroupException.setText(e.getMessage());
        }  catch (Exception e){
            indicator = true;
            transferredStudentsStudyGroupException.setText(ResourceBundleSingleton.getResourceBundle().getString("nullFieldException"));
        }
        // Enter count of should be expelled
        try {
            studyGroup.setShouldBeExpelled(Integer.parseInt(shouldBeExpelledStudyGroup.getText()));
            studyGroupProcessing.checkShouldBeExpelledStudentsStudyGroup(studyGroup);
            shouldBeExpelledStudyGroupException.setText("");
        } catch (InputException e) {
            indicator = true;
            shouldBeExpelledStudyGroupException.setText(e.getMessage());
        }  catch (Exception e){
            indicator = true;
            shouldBeExpelledStudyGroupException.setText(ResourceBundleSingleton.getResourceBundle().getString("nullFieldException"));
        }
        // Enter in which semester the study group is studying
        try {
            studyGroup.setSemesterEnum(semesterStudyGroup.getValue());
            studyGroupProcessing.checkSemesterEnumStudyGroup(studyGroup);
            semesterStudyGroupException.setText("");
        } catch (Exception e){
            indicator = true;
            semesterStudyGroupException.setText(e.getMessage());
        }
        // Enter a coordinates
        Coordinates coordinates = new Coordinates();
        try {
            coordinates.setX(Long.parseLong(xStudyGroup.getText()));
        } catch (Exception e) {
            coordinates.setX(0L);
        }
        try {
            coordinates.setY(Double.parseDouble(yStudyGroup.getText()));
            studyGroupProcessing.checkYCoordinatesStudyGroup(coordinates.getY());
            yStudyGroupException.setText("");
        } catch (InputException e) {
            indicator = true;
            yStudyGroupException.setText(e.getMessage());
        } catch (Exception e){
            indicator = true;
            yStudyGroupException.setText(ResourceBundleSingleton.getResourceBundle().getString("nullFieldException"));
        }
        studyGroup.setCoordinates(coordinates);

        Person person = new Person();

        // Enter the name of the study group admin
        try{
            String name = nameGroupAdmin.getText();
            studyGroupProcessing.nameOfGroupAdmin(name);
            person.setName(name);
            nameGroupAdminException.setText("");
        } catch (InputException e) {
            indicator = true;
            nameGroupAdminException.setText(e.getMessage());
        }

        try{
            long height = Long.parseLong(heightGroupAdmin.getText());
            studyGroupProcessing.heightGroupAdmin(height);
            person.setHeight(height);
            heightGroupAdminException.setText("");
        } catch (InputException e){
            heightGroupAdminException.setText(e.getMessage());
        } catch (Exception e){
            indicator = true;
            heightGroupAdminException.setText(ResourceBundleSingleton.getResourceBundle().getString("numericTypeException"));
        }

        try{
            String passport = passportIdGroupAdmin.getText();
            studyGroupProcessing.passportIdOfGroupAdmin(passport);
            person.setPassportID(passport);
            passportIdGroupAdminException.setText("");
        } catch (InputException e) {
            indicator = true;
            passportIdGroupAdminException.setText(e.getMessage());
        }
        try{
            Color color = eyeColorGroupAdmin.getValue();
            studyGroupProcessing.eyeColorGroupAdmin(color);
            person.setEyeColor(color);
            eyeColorGroupAdminException.setText("");
        } catch (InputException e) {
            indicator = true;
            eyeColorGroupAdminException.setText(e.getMessage());
        }

        Location location = new Location();
        try{
            location.setX(Float.parseFloat(xLocation.getText()));
        } catch (Exception e){}
        try{
            Float value =  Float.parseFloat(yLocation.getText());
            location.setY(value);
            yLocationException.setText("");
        } catch (Exception e){
            indicator = true;
            yLocationException.setText(ResourceBundleSingleton.getResourceBundle().getString("numericTypeException"));
        }
        try{
            location.setZ(Integer.parseInt(zLocation.getText()));
        } catch (Exception e){}
        try{
            String name = nameLocation.getText();
            studyGroupProcessing.nameOfGroupAdmin(name);
            location.setName(name);
            nameLocationException.setText("");
        } catch (InputException e) {
            indicator = true;
            nameLocationException.setText(e.getMessage());
        }
        person.setLocation(location);
        studyGroup.setGroupAdmin(person);
        if (!indicator) {
//            UserAuth.setUser(new User("VASYAAAAA", "bibaboba"));
            Message request = MessageFabric.createMessage(CommandsEnum.REMOVE_GREATER, studyGroup);
            try {
                Message serverResponse = StartClient.sendMessageAndGetResponse(request);
                response.setText(serverResponse.getData());
                if (!serverResponse.getCommand().equals(CommandsEnum.RESPONSE_ERR)){
                    clearTextFields();
                }
            } catch (IOException | ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }


    private void clearTextFields(){
        nameStudyGroup.setText("");
        studentsCountStudyGroup.setText("");
        shouldBeExpelledStudyGroup.setText("");
        transferredStudentsStudyGroup.setText("");
        semesterStudyGroup.setValue(null);
        xStudyGroup.setText("");
        yStudyGroup.setText("");
        nameGroupAdmin.setText("");
        heightGroupAdmin.setText("");
        passportIdGroupAdmin.setText("");
        eyeColorGroupAdmin.setValue(null);
        xLocation.setText("");
        yLocation.setText("");
        zLocation.setText("");
        nameLocation.setText("");
    }
}
