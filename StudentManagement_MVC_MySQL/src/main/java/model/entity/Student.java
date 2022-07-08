package model.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Student {

    private Clazz classroom;
    private String id;
    private String fullName;
    private Date birthday;
    private Gender gender;

}
