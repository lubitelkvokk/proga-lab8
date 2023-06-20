package client.controllers.util;

import mid.data.StudyGroupTableView;

public class SavedForEditingStudyGroup {
    private static StudyGroupTableView studyGroupTableView;

    public static void setStudyGroupTableView(StudyGroupTableView obj){
        studyGroupTableView = obj;
    }

    public static StudyGroupTableView getStudyGroupTableView(){
        return studyGroupTableView;
    }

}
