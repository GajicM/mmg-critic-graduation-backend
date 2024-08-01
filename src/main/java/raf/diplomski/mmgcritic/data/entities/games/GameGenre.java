package raf.diplomski.mmgcritic.data.entities.games;

import lombok.Getter;

@Getter
public enum GameGenre {
    ACTION_ADVENTURE("Action-Adventure"),
    ACTION("Action"),
    ADVENTURE("Adventure"),
    BOARD_GAME("Board Game"),
    EDUCATION("Education"),
    FIGHTING("Fighting"),
    MMO("MMO"),
    MISC("Misc"),
    MUSIC("Music"),
    PARTY("Party"),
    PLATFORM("Platform"),
    PUZZLE("Puzzle"),
    RACING("Racing"),
    ROLE_PLAYING("Role-Playing"),
    SANDBOX("Sandbox"),
    SHOOTER("Shooter"),
    SIMULATION("Simulation"),
    SPORTS("Sports"),
    STRATEGY("Strategy"),
    VISUAL_NOVEL("Visual Novel");
    private final String value;
    GameGenre(String value){
        this.value=value;
    }

    public static GameGenre fromString(String genre) {
        for (GameGenre movieGenre : GameGenre.values()) {
            if (movieGenre.getValue().equalsIgnoreCase(genre.trim())) {
                return movieGenre;
            }
        }
        throw new IllegalArgumentException("No enum constant " + GameGenre.class.getCanonicalName() + "." + genre);
    }
    }
