package mid.data;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class StudyGroupTableView {

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой


    @Deprecated
    private Coordinates coordinates; //Поле не может быть null

    private Long x;
    private Double y;
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer studentsCount; //Значение поля должно быть больше 0
    private Integer shouldBeExpelled; //Значение поля должно быть больше 0
    private Integer transferredStudents; //Значение поля должно быть больше 0

    private Semester semesterEnum; //Поле не может быть null

    @Deprecated
    private Person groupAdmin; //Поле не может быть null
    private String nameGroupAdmin; //Поле не может быть null, Строка не может быть пустой
    private Long heightGroupAdmin; //Значение поля должно быть больше 0
    private String passportIDGroupAdmin; //Значение этого поля должно быть уникальным, Длина строки не должна быть больше 21, Длина строки должна быть не меньше 6, Поле не может быть null

    private Color eyeColorGroupAdmin; //Поле не может быть null
    @Deprecated
    private Location location; //Поле не может быть null
    private Float xLocation;
    private Float yLocation; //Поле не может быть null
    private Integer zLocation;
    private String nameLocation; //Длина строки не должна быть больше 797, Поле может быть null

    private User user;

}
