package raf.diplomski.mmgcritic.data.entities;

import lombok.Getter;

@Getter
public enum ReviewType {
    GAME("GAME"),
    MUSIC("MUSIC"),
    MOVIE("MOVIE"),
    ;
    private final String value;
     ReviewType(String value){
        this.value=value;
    }
    public static ReviewType fromString(String type) {
        for (ReviewType type1 : ReviewType.values()) {
            if (type1.getValue().equalsIgnoreCase(type.trim())) {
                return type1;
            }
        }
        throw new IllegalArgumentException("No enum constant " + ReviewType.class.getCanonicalName() + "." + type);
    }

}
