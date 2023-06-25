package mid.data.processing;

import client.exeptions.InputException;
import client.global.ResourceBundleSingleton;
import mid.data.Color;
import mid.data.StudyGroup;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudyGroupProcessing {

    private ResourceBundle resourceBundle = ResourceBundleSingleton.getResourceBundle();

    public void checkNameStudyGroup(String name) throws InputException {
        if (name == null || name.equals("")) {
            throw new InputException(resourceBundle.getString("nameException"));
        }
    }

    public void checkXStudyGroup(Integer x) throws InputException {
        if (x == null || x < 0 || x > 900) {
            throw new InputException("xStudyGroupException");
        }
    }

    public void checkYCoordinatesStudyGroup(Double y) throws InputException {
        if (y == null || y < 0 || y > 600) {
            throw new InputException("yCoordinatesStudyGroupException");
        }
    }

    public void checkStudentsCountStudyGroup(StudyGroup studyGroup) throws InputException {
        if (studyGroup.getStudentsCount() == null || studyGroup.getStudentsCount() < 0) {
            throw new InputException(resourceBundle.getString("studentsCountException"));
        }
    }

    public void checkTransferredStudentsStudyGroup(StudyGroup studyGroup) throws InputException {
        if (studyGroup.getTransferredStudents() < 0) {
            throw new InputException(resourceBundle.getString("studentsCountException"));
        }
    }

    public void checkShouldBeExpelledStudentsStudyGroup(StudyGroup studyGroup) throws InputException {
        if (studyGroup.getShouldBeExpelled() < 0) {
            throw new InputException(resourceBundle.getString("studentsCountException"));
        }
    }

    public void checkSemesterEnumStudyGroup(StudyGroup studyGroup) throws InputException {
        if (studyGroup.getSemesterEnum() == null) {
            throw new InputException(resourceBundle.getString("nullFieldException"));
        }
    }

    public void checkGroupAdminStudyGroup(StudyGroup studyGroup) throws InputException {
        if (studyGroup.getGroupAdmin() == null) {

        }
    }

    public void nameOfGroupAdmin(String name) throws InputException {
        if (name.equals("")) {
            throw new InputException(resourceBundle.getString("nameException"));
        }
    }

    public void passportIdOfGroupAdmin(String passport) throws InputException {
        if (passport.equals("") || passport.trim().length() < 6 || passport.trim().length() > 21) {
            throw new InputException(resourceBundle.getString("passportIdGroupAdminException"));
        }
    }

    public void eyeColorGroupAdmin(Color color) throws InputException {
        if (color == null) {
            throw new InputException(resourceBundle.getString("nullFieldException"));
        }
    }

    public void heightGroupAdmin(Long height) throws InputException {
        if (height < 0) {
            throw new InputException(resourceBundle.getString("heightGroupAdminException"));
        }
    }

}
