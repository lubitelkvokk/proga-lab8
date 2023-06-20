package mid.converters;

import mid.data.StudyGroup;
import mid.data.StudyGroupTableView;

public class StudyGroupToTableViewConverter {

    public static StudyGroupTableView getTableView(StudyGroup studyGroup){
        StudyGroupTableView studyGroupTableView = new StudyGroupTableView();
        studyGroupTableView.setId(studyGroup.getId());
        studyGroupTableView.setCreationDate(studyGroup.getCreationDate());
        studyGroupTableView.setName(studyGroup.getName());
        studyGroupTableView.setStudentsCount(studyGroup.getStudentsCount());
        studyGroupTableView.setShouldBeExpelled(studyGroup.getShouldBeExpelled());
        studyGroupTableView.setTransferredStudents(studyGroup.getTransferredStudents());
        studyGroupTableView.setSemesterEnum(studyGroup.getSemesterEnum());
        studyGroupTableView.setX(studyGroup.getCoordinates().getX());
        studyGroupTableView.setY(studyGroup.getCoordinates().getY());
        studyGroupTableView.setNameGroupAdmin(studyGroup.getGroupAdmin().getName());
        studyGroupTableView.setHeightGroupAdmin(studyGroup.getGroupAdmin().getHeight());
        studyGroupTableView.setPassportIDGroupAdmin(studyGroup.getGroupAdmin().getPassportID());
        studyGroupTableView.setEyeColorGroupAdmin(studyGroup.getGroupAdmin().getEyeColor());
        studyGroupTableView.setXLocation(studyGroup.getGroupAdmin().getLocation().getX());
        studyGroupTableView.setYLocation(studyGroup.getGroupAdmin().getLocation().getY());
        studyGroupTableView.setZLocation(studyGroup.getGroupAdmin().getLocation().getZ());
        studyGroupTableView.setNameLocation(studyGroup.getGroupAdmin().getLocation().getName());
        studyGroupTableView.setId(studyGroup.getId());
        studyGroupTableView.setUser(studyGroup.getUser());
        return studyGroupTableView;
    }
}
