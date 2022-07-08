package model.entity;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    MALE(1), FEMALE(0), UNKNOWN(-1);
    private int code;
    private static Map<Integer, Gender> genderMap;

    Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Gender getGender(int code) {
        if (genderMap == null) {
            genderMap = new HashMap<>();
            genderMap.put(1, Gender.MALE);
            genderMap.put(0, Gender.FEMALE);
            genderMap.put(-1, Gender.UNKNOWN);
        }
        return genderMap.get(code);
    }
}
