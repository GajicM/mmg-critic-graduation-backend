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

}
