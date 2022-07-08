package model.entity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thomas
 */
public enum Gender {
    MALE(1), FEMALE(0), UNKNOWN(-1);
    private final int CODE;
    private static Map<Integer, Gender> genderMap;

    Gender(int code) {
        this.CODE = code;
    }

    public int getCode() {
        return CODE;
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
