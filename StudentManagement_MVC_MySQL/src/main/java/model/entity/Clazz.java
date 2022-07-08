package model.entity;

import java.util.Map;
import lombok.Data;

@Data
public class Clazz {

    private String id;
    private String name;
    private Map<String, Student> lsStudent;
}
