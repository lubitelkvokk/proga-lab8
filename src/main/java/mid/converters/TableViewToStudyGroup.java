package mid.converters;

import mid.data.*;

public class TableViewToStudyGroup {

    public static StudyGroup getStudyGroup(StudyGroupTableView studyGroupTableView){
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setId(studyGroupTableView.getId());
        studyGroup.setCreationDate(studyGroupTableView.getZonedDateTime());
        studyGroup.setName(studyGroupTableView.getName());
        studyGroup.setStudentsCount(studyGroupTableView.getStudentsCount());
        studyGroup.setTransferredStudents(studyGroupTableView.getTransferredStudents());
        studyGroup.setShouldBeExpelled(studyGroupTableView.getShouldBeExpelled());
        studyGroup.setSemesterEnum(studyGroupTableView.getSemesterEnum());
        Coordinates coordinates = new Coordinates(
                studyGroupTableView.getX(),
                studyGroupTableView.getY()
        );
        studyGroup.setCoordinates(coordinates);
        Person groupAdmin = new Person();
        groupAdmin.setName(studyGroupTableView.getNameGroupAdmin());
        groupAdmin.setPassportID(studyGroupTableView.getPassportIDGroupAdmin());
        groupAdmin.setHeight(studyGroupTableView.getHeightGroupAdmin());
        groupAdmin.setEyeColor(studyGroupTableView.getEyeColorGroupAdmin());
        Location location = new Location();
        location.setX(studyGroupTableView.getXLocation());
        location.setY(studyGroupTableView.getYLocation());
        location.setZ(studyGroupTableView.getZLocation());
        location.setName(studyGroupTableView.getNameLocation());
        groupAdmin.setLocation(location);
        studyGroup.setGroupAdmin(groupAdmin);
        User user = new User();
        user.setLogin(studyGroupTableView.getUsername());
        user.setId(studyGroupTableView.getUser_id());
        user.setPassword(studyGroupTableView.getPassword());
        studyGroup.setUser(user);
        studyGroup.setId(studyGroupTableView.getId());
        return studyGroup;
    }
}
